package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;

import com.astroganit.lib.panchang.model.BhadraBean;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;
import com.astroganit.lib.panchang.util.PanUtil;

public class BhadraCalculation extends PanchangBase {
	PanUtil panUtil = new PanUtil();
	public BhadraCalculation(int languageCode) {
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
	}
	public ArrayList<BhadraBean> getBhadraData(Calendar calendar, int langCode) {
		ArrayList<BhadraBean> bhadraTimelist = new ArrayList<BhadraBean>();
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= days; i++) {
			if (isVishtiKaran(calendar)) {
				ArrayList<String> bhadraList = getBhadraDataList(calendar);
				double karanStartDate = Double.parseDouble(bhadraList.get(2));
				if (karanStartDate == -1.0) {
					calendar.add(Calendar.DATE, -1);
				}
				String startDate = "" + calendar.get(Calendar.DATE);
				String startMonth = panUtil.getMonthList(langCode)[calendar.get(Calendar.MONTH)];
				String startDay = panUtil.getWeekList(langCode)[calendar.get(Calendar.DAY_OF_WEEK) - 1];
				String startTime = bhadraList.get(0);
				if (karanStartDate == -1.0) {
					calendar.add(Calendar.DATE, 1);
				}
				String endDate;
				String endMonth;
				String endDay;
				String endTime;

				if (bhadraList.get(1).contains("+")) {
					calendar.add(Calendar.DATE, 1);
					endDate = ("" + calendar.get(Calendar.DATE));
					endMonth = panUtil.getMonthList(langCode)[calendar.get(Calendar.MONTH)];
					endDay = panUtil.getWeekList(langCode)[calendar.get(Calendar.DAY_OF_WEEK) - 1];
					endTime = bhadraList.get(1).replace("+", "");
					calendar.add(Calendar.DATE, -1);
				} else {
					endDate = "" + calendar.get(Calendar.DATE);
					endMonth = panUtil.getMonthList(langCode)[calendar.get(Calendar.MONTH)];
					endDay = panUtil.getWeekList(langCode)[calendar.get(Calendar.DAY_OF_WEEK) - 1];
					endTime = bhadraList.get(1);
				}

				bhadraTimelist.add(
						new BhadraBean(startDay, startMonth, startDate, startTime, endDay, endMonth, endDate, endTime));
			}
			calendar.add(Calendar.DATE, 1);
		}
		return bhadraTimelist;
	}

}
