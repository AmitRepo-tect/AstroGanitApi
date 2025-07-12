package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;

import com.astroganit.lib.panchang.model.HoraBean;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;
import com.astroganit.lib.panchang.util.PanUtil;


public class HoraCalculation extends PanchangBase {
	PanUtil panUtil = new PanUtil();

	public HoraCalculation(int languageCode) {
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
	}

	public HoraBean getCurrentHora(Calendar calendar) {
		ArrayList<HoraBean> horaList = getHoradata(calendar.get(Calendar.DAY_OF_WEEK) - 1, calendar);
		HoraBean horaBean = null;
		for (int i = 0; i < horaList.size(); i++) {
			horaBean = horaList.get(i);
			if (panUtil.isTimeBetweenTwoTime(horaBean.getEnterTime(), horaBean.getExitTime())) {
				break;
			}
		}
		return horaBean;
	}

	public ArrayList<HoraBean> getHoradata(int dayOfMonth, Calendar calendar) {
		double jd = baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
		double sunRise = getSunRiseTimeInDouble(calendar);
		double sunSet = getSunSetTimeInDouble(calendar);
		int[] dayLordForDayHora = new int[24];
		ArrayList<HoraBean> horaBeanArrayList = new ArrayList<HoraBean>();
		try {
			double entryTime;
			double exitTime;
			String[] planetName = constants.getHoraPlanetName();
			String[] planetNameMeaning = constants.getHoraPlanetMeaning();
			String[] planetNameMeaningForCurrentHora = constants.getCurrentHoraPlanetMeaning();
			for (int i = 0; i <= 23; i++) {
				dayLordForDayHora[i] = (dayOfMonth + i * 5) % 7;

				if (i >= 12) {
					entryTime = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 12)[i - 12];
					exitTime = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 12)[i - 12 + 1];
				} else {
					entryTime = baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 12)[i];
					exitTime = baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 12)[i + 1];
				}
				HoraBean hora = new HoraBean(planetName[dayLordForDayHora[i]], planetNameMeaning[dayLordForDayHora[i]],
						planetNameMeaningForCurrentHora[dayLordForDayHora[i]],
						panUtil.FormatDMSIn2DigitStringWithSignForhora(entryTime),
						panUtil.FormatDMSIn2DigitStringWithSignForhora(exitTime),
						getTimeString(
								panUtil.convertTimeToAmPm(panUtil.FormatDMSIn2DigitStringWithSignForhora(entryTime)),
								panUtil.convertTimeToAmPm(panUtil.FormatDMSIn2DigitStringWithSignForhora(exitTime)))

				);

				horaBeanArrayList.add(hora);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horaBeanArrayList;
	}

	public ArrayList<HoraBean> getDayHoraData(int dayOfMonth, Calendar calendar) {
		double jd = baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
		double sunRise = getSunRiseTimeInDouble(calendar);
		int[] dayLordForDayHora = new int[12];
		ArrayList<HoraBean> horaBeanArrayList = new ArrayList<HoraBean>();
		try {
			double entryTime;
			double exitTime;
			String[] planetName = constants.getHoraPlanetName();
			String[] planetNameMeaning = constants.getHoraPlanetMeaning();
			String[] planetNameMeaningForCurrentHora = constants.getCurrentHoraPlanetMeaning();
			for (int i = 0; i <= 11; i++) {
				dayLordForDayHora[i] = (dayOfMonth + i * 5) % 7;
				entryTime = baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 12)[i];
				exitTime = baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 12)[i + 1];

				HoraBean hora = new HoraBean(planetName[dayLordForDayHora[i]], planetNameMeaning[dayLordForDayHora[i]],
						planetNameMeaningForCurrentHora[dayLordForDayHora[i]],
						panUtil.FormatDMSIn2DigitStringWithSignForhora(entryTime),
						panUtil.FormatDMSIn2DigitStringWithSignForhora(exitTime),
						getTimeString(
								panUtil.convertTimeToAmPm(panUtil.FormatDMSIn2DigitStringWithSignForhora(entryTime)),
								panUtil.convertTimeToAmPm(panUtil.FormatDMSIn2DigitStringWithSignForhora(exitTime)))

				);

				horaBeanArrayList.add(hora);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horaBeanArrayList;
	}

	public ArrayList<HoraBean> getNightHoraData(int dayOfMonth, Calendar calendar) {
		double jd = baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
		double sunSet = getSunSetTimeInDouble(calendar);
		int[] dayLordForDayHora = new int[12];
		ArrayList<HoraBean> horaBeanArrayList = new ArrayList<HoraBean>();
		try {
			double entryTime;
			double exitTime;
			String[] planetName = constants.getHoraPlanetName();
			String[] planetNameMeaning = constants.getHoraPlanetMeaning();
			String[] planetNameMeaningForCurrentHora = constants.getCurrentHoraPlanetMeaning();
			for (int i = 0; i <= 11; i++) {
				dayLordForDayHora[i] = (dayOfMonth + (i + 12) * 5) % 7;
				entryTime = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 12)[i];
				exitTime = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 12)[i + 1];

				HoraBean hora = new HoraBean(planetName[dayLordForDayHora[i]],
						panUtil.FormatDMSIn2DigitStringWithSignForhora(entryTime),
						panUtil.FormatDMSIn2DigitStringWithSignForhora(exitTime),
						planetNameMeaning[dayLordForDayHora[i]], planetNameMeaningForCurrentHora[dayLordForDayHora[i]],
						getTimeString(
								panUtil.convertTimeToAmPm(panUtil.FormatDMSIn2DigitStringWithSignForhora(entryTime)),
								panUtil.convertTimeToAmPm(panUtil.FormatDMSIn2DigitStringWithSignForhora(exitTime))));

				horaBeanArrayList.add(hora);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horaBeanArrayList;

	}

	String getTimeString(String startTime, String endTime) {
		if (languageCode == 1) {
			return "From " + startTime + " to " + endTime;
		} else {
			return startTime + " से " + endTime;
		}

	}
}
