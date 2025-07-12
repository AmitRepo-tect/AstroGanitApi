package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.astroganit.lib.panchang.model.ChogdiyaBean;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;
import com.astroganit.lib.panchang.util.PanUtil;

public class ChogdiyaCalculation extends PanchangBase {
	PanUtil panUtil = new PanUtil();

	public ChogdiyaCalculation(int languageCode) {
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
	}

	public ChogdiyaBean getCurrentChogdia(Calendar calendar) {
		ArrayList<ChogdiyaBean> horaList = getChogdiaData(calendar);
		ChogdiyaBean chogdiyaBean = null;
		for (int i = 0; i < horaList.size(); i++) {
			chogdiyaBean = horaList.get(i);
			if (panUtil.isTimeBetweenTwoTime(chogdiyaBean.getEnterTime(), chogdiyaBean.getExitTime())) {
				/*
				 * val timeDuration =
				 * Utility.convertTimeToAmPm(chogdiyaBean.enterTime).replace("+", "Tomorrow\n")
				 * + " - " + Utility.convertTimeToAmPm(chogdiyaBean.exitTime).replace("+",
				 * "Tomorrow\n") panchangBean = PanchangBean(chogdiyaBean.planetName,
				 * timeDuration, chogdiyaBean.planetMeaning)
				 */
				break;
			}
		}
		return chogdiyaBean;
	}

	public ArrayList<ChogdiyaBean> getChogdiaData(Calendar cal) {

		double jd = baseCalculationNew.panchangCalculation.toJulian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DATE));
		double sunRise = getSunRiseTimeInDouble(cal);
		double sunSet = getSunSetTimeInDouble(cal);
		ArrayList<ChogdiyaBean> arrayList = new ArrayList<ChogdiyaBean>();
		Calendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));
		int reslut = calendar.get(Calendar.DAY_OF_WEEK);
		int valueforday = getStartChoghadiaForDay(reslut);
		int valuefornight = getStartChoghadiaForNight(reslut);
		ArrayList<String> chogadiyanameforday = getChogadiaDayName(valueforday);
		ArrayList<String> chogadiyanamefornight = getChogadiaNightName(valuefornight);
		ArrayList<String> chogadiyanamefordaymeaning = getChogadiaDayNameMeaning(valueforday);
		ArrayList<String> chogadiyanamefornightmeaning = getChogadiaNightNameMeaning(valuefornight);
		String chogdiaName;
		String chogdiaMeaning;
		String entryTime;
		String exitTime;
		ChogdiyaBean chogdiyaBean;
		for (int i = 0; i <= 15; i++) {

			if (i < 8) {
				chogdiaName = chogadiyanameforday.get(i);
				chogdiaMeaning = chogadiyanamefordaymeaning.get(i);
				entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 8)[i]);
				exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 8)[i + 1]);
			} else {
				chogdiaName = chogadiyanamefornight.get(i - 8);
				chogdiaMeaning = chogadiyanamefornightmeaning.get(i - 8);
				entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 8)[i - 8]);
				exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 8)[i - 8 + 1]);
			}
			chogdiyaBean = new ChogdiyaBean(chogdiaName, chogdiaMeaning, entryTime, exitTime,
					getTimeString(panUtil.convertTimeToAmPm(entryTime), panUtil.convertTimeToAmPm(exitTime)));

			arrayList.add(chogdiyaBean);
		}
		return arrayList;
	}

	public ArrayList<ChogdiyaBean> getDayChogdiaData(Calendar cal) {

		double jd = baseCalculationNew.panchangCalculation.toJulian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DATE));
		double sunRise = getSunRiseTimeInDouble(cal);
		ArrayList<ChogdiyaBean> arrayList = new ArrayList<ChogdiyaBean>();
		Calendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));
		int reslut = calendar.get(Calendar.DAY_OF_WEEK);
		int valueforday = getStartChoghadiaForDay(reslut);
		ArrayList<String> chogadiyanameforday = getChogadiaDayName(valueforday);
		ArrayList<String> chogadiyanamefordaymeaning = getChogadiaDayNameMeaning(valueforday);
		String chogdiaName;
		String chogdiaMeaning;
		String entryTime;
		String exitTime;
		ChogdiyaBean chogdiyaBean;
		for (int i = 0; i <= 7; i++) {
			chogdiaName = chogadiyanameforday.get(i);
			chogdiaMeaning = chogadiyanamefordaymeaning.get(i);
			entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 8)[i]);
			exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 8)[i + 1]);
			chogdiyaBean = new ChogdiyaBean(chogdiaName, chogdiaMeaning, entryTime, exitTime,
					getTimeString(panUtil.convertTimeToAmPm(entryTime), panUtil.convertTimeToAmPm(exitTime)));

			arrayList.add(chogdiyaBean);
		}
		return arrayList;
	}

	public ArrayList<ChogdiyaBean> getNightChogdiaData(Calendar cal) {
		double jd = baseCalculationNew.panchangCalculation.toJulian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DATE));
		double sunSet = getSunSetTimeInDouble(cal);
		ArrayList<ChogdiyaBean> arrayList = new ArrayList<ChogdiyaBean>();
		Calendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));
		int reslut = calendar.get(Calendar.DAY_OF_WEEK);
		int valuefornight = getStartChoghadiaForNight(reslut);
		ArrayList<String> chogadiyanamefornight = getChogadiaNightName(valuefornight);
		ArrayList<String> chogadiyanamefornightmeaning = getChogadiaNightNameMeaning(valuefornight);
		String chogdiaName;
		String chogdiaMeaning;
		String entryTime;
		String exitTime;
		ChogdiyaBean chogdiyaBean;
		for (int i = 0; i <= 7; i++) {

			chogdiaName = chogadiyanamefornight.get(i);
			chogdiaMeaning = chogadiyanamefornightmeaning.get(i);
			entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 8)[i]);
			exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 8)[i + 1]);

			chogdiyaBean = new ChogdiyaBean(chogdiaName, chogdiaMeaning, entryTime, exitTime,
					getTimeString(panUtil.convertTimeToAmPm(entryTime), panUtil.convertTimeToAmPm(exitTime)));

			arrayList.add(chogdiyaBean);
		}
		return arrayList;
	}

	private int getStartChoghadiaForDay(int day) {
		int startDay = 0;
		switch (day) {
		case Calendar.SUNDAY:
			startDay = 0;
			break;
		case Calendar.MONDAY:
			startDay = 3;
			break;
		case Calendar.TUESDAY:
			startDay = 6;
			break;
		case Calendar.WEDNESDAY:
			startDay = 2;
			break;
		case Calendar.THURSDAY:
			startDay = 5;
			break;
		case Calendar.FRIDAY:
			startDay = 1;
			break;
		case Calendar.SATURDAY:
			startDay = 4;
			break;
		}
		return startDay;
	}

	private int getStartChoghadiaForNight(int day) {
		int startNight = 0;
		switch (day) {
		case Calendar.SUNDAY:
			startNight = 0;
			break;
		case Calendar.MONDAY:
			startNight = 2;
			break;
		case Calendar.TUESDAY:
			startNight = 4;
			break;
		case Calendar.WEDNESDAY:
			startNight = 6;
			break;
		case Calendar.THURSDAY:
			startNight = 1;
			break;
		case Calendar.FRIDAY:
			startNight = 3;
			break;
		case Calendar.SATURDAY:
			startNight = 5;
			break;
		}
		return startNight;
	}

	private ArrayList<String> getChogadiaDayName(int startDay) {
		ArrayList<String> dayChoghadiaName = new ArrayList<String>();
		String[] chogadiyadayName = constants.getDayChogdia();
		var j = 0;
		var i = startDay;
		while (i < 7) {
			dayChoghadiaName.add(chogadiyadayName[i]);
			i++;
			j++;
		}
		for (int l = 0; l <= startDay; l++) {
			dayChoghadiaName.add(chogadiyadayName[l]);
			j++;
		}
		return dayChoghadiaName;
	}

	private ArrayList<String> getChogadiaDayNameMeaning(int startDay) {
		ArrayList<String> dayChoghadiaNameMeaning = new ArrayList<String>();
		String[] chogadiyadayName = constants.getDayChogdia();
		int j = 0;
		int i = startDay;
		while (i < 7) {
			dayChoghadiaNameMeaning.add(chogadiyadayName[i]);
			i++;
			j++;
		}
		for (int l = 0; l <= startDay; l++) {
			dayChoghadiaNameMeaning.add(chogadiyadayName[l]);
			j++;
		}
		return dayChoghadiaNameMeaning;
	}

	private ArrayList<String> getChogadiaNightName(int startDay) {
		ArrayList<String> nightChoghadiaName = new ArrayList<String>();
		String[] chogadiyaNightName = constants.getNightChogdia();
		int j = 0;
		int i = startDay;
		while (i < 7) {
			nightChoghadiaName.add(chogadiyaNightName[i]);
			i++;
			j++;
		}
		for (int l = 0; l <= startDay; l++) {
			nightChoghadiaName.add(chogadiyaNightName[l]);
			j++;
		}
		return nightChoghadiaName;
	}

	private ArrayList<String> getChogadiaNightNameMeaning(int startDay) {
		ArrayList<String> nightChoghadiaNameMeaning = new ArrayList<String>();
		String[] chogadiyaNightNameMeaning = constants.getNightChogdiaMeaning();
		int j = 0;
		int i = startDay;
		while (i < 7) {
			if (chogadiyaNightNameMeaning.length > i) {
				nightChoghadiaNameMeaning.add(chogadiyaNightNameMeaning[i]);
			}
			i++;
			j++;
		}
		for (int l = 0; l <= startDay; l++) {
			if (chogadiyaNightNameMeaning.length > l) {
				nightChoghadiaNameMeaning.add(chogadiyaNightNameMeaning[l]);
			}
			j++;
		}
		return nightChoghadiaNameMeaning;
	}

	String getTimeString(String startTime, String endTime) {
		if (languageCode == 1) {
			return "From " + startTime + " to " + endTime;
		} else {
			return startTime + " से " + endTime;
		}

	}

}
