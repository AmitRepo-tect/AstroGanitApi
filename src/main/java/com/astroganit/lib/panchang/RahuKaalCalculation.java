package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;

import com.astroganit.lib.panchang.model.RahuKaalBean;
import com.astroganit.lib.panchang.util.PanUtil;

public class RahuKaalCalculation extends PanchangBase {
	PanUtil panUtil = new PanUtil();

	public ArrayList<RahuKaalBean> getRahuKaalData(Calendar calendar) {
		ArrayList<RahuKaalBean> arrayList = new ArrayList<RahuKaalBean>();
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(calendar.getTime());
			for (int i = 0; i <= 7; i++) {
				String date = panUtil.getDateShowRahuKaal(cal.getTime());
				ArrayList<String> rahuKaalTime = getRahuKaalVela(cal);
				arrayList.add(new RahuKaalBean(date, rahuKaalTime.get(0), rahuKaalTime.get(1)));
				cal.add(Calendar.DATE, 1);
			}

		} catch (Exception ex) {

		}
		return arrayList;
	}

	RahuKaalBean getCurrentRahuKaal() {
		Calendar calendar = Calendar.getInstance();
		ArrayList<String> duration = getRahuKaalVela(calendar);
		return new RahuKaalBean(panUtil.getDateShowRahuKaal(calendar.getTime()), duration.get(0), duration.get(1));

	}

}
