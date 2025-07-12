package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;

import com.astroganit.lib.panchang.model.DoGhatiBean;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;
import com.astroganit.lib.panchang.util.PanUtil;

public class DoGhatiCalculation extends PanchangBase {
	PanUtil panUtil = new PanUtil();

	public DoGhatiCalculation(int languageCode) {
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
	}

	DoGhatiBean getCurrentDoGhati(Calendar calendar) {
		ArrayList<DoGhatiBean> horaList = getDoGhatiData(calendar);
		DoGhatiBean doGhatiBean = null;
		for (int i = 0; i < horaList.size(); i++) {
			doGhatiBean = horaList.get(i);
			if (panUtil.isTimeBetweenTwoTime(doGhatiBean.getEnterTime(), doGhatiBean.getExitTime())) {
				/*
				 * val duration =
				 * com.bhakti_sangrahalay.util.Utility.convertTimeToAmPm(doGhatiBean.enterTime).
				 * replace("+", "Tomorrow\n") + " - " +
				 * com.bhakti_sangrahalay.util.Utility.convertTimeToAmPm(doGhatiBean.exitTime).
				 * replace("+", "Tomorrow\n") panchangBean =
				 * PanchangBean(doGhatiBean.planetName, duration,
				 * doGhatiBean.planetCurrentHoraMeaning)
				 */

				break;
			}
		}
		return doGhatiBean;
	}

	public ArrayList<DoGhatiBean> getDoGhatiData(Calendar cal) {
		// val place = com.bhakti_sangrahalay.util.Utility.getPlaceForPanchang()
		double jd = baseCalculationNew.panchangCalculation.toJulian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DATE));
		double sunRise = getSunRiseTimeInDouble(cal);
		double sunSet = getSunSetTimeInDouble(cal);
		ArrayList<DoGhatiBean> arrayList = new ArrayList<DoGhatiBean>();
		String[] planetName = constants.getPaharNameList();
		String[] planetNameMeaning = constants.getDoGhatiPauranikMuhuratList();
		String[] planetNameMeaningForCurrentHora = constants.getDoGhatiNakshtra();
		String[] doGhatiSecondMeaning = constants.getDoGhatiNameListMeaningSecond();
		String[] doGhatiSecondMeaningWikipedia = constants.getDoGhatiNameListMeaning();
		String[] doGhatiMuhurat = constants.getDoGhatiMuhurat();
		String entryTime;
		String exitTime;
		for (int i = 0; i <= 29; i++) {
			String plaName = "";
			if (i <= 2) {
				plaName = planetName[0];
			} else if (i <= 5) {
				plaName = planetName[1];
			} else if (i <= 8) {
				plaName = planetName[2];
			} else if (i <= 11) {
				plaName = planetName[3];
			} else if (i <= 14) {
				plaName = planetName[4];
			} else if (i <= 17) {
				plaName = planetName[5];
			} else if (i <= 21) {
				plaName = planetName[6];
			} else if (i <= 22) {
				plaName = planetName[7];
			} else if (i <= 27) {
				plaName = planetName[8];
			} else if (i <= 29) {
				plaName = planetName[9];
			}
			if (i < 15) {
				entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 15)[i]);
				exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 15)[i + 1]);
			} else {
				entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[i - 15]);
				exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
						baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[i - 15 + 1]);
			}
			DoGhatiBean doGhatiBean = new DoGhatiBean(plaName, planetNameMeaning[i], doGhatiSecondMeaning[i],
					doGhatiSecondMeaningWikipedia[i], planetNameMeaningForCurrentHora[i], doGhatiMuhurat[i], entryTime,
					exitTime, panUtil.convertTimeToAmPm(entryTime) + " from " + panUtil.convertTimeToAmPm(exitTime));

			arrayList.add(doGhatiBean);
		}
		return arrayList;
	}

	public ArrayList<DoGhatiBean> getDayDoGhatiData(Calendar cal) {
		// val place = com.bhakti_sangrahalay.util.Utility.getPlaceForPanchang()
		double jd = baseCalculationNew.panchangCalculation.toJulian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DATE));
		double sunRise = getSunRiseTimeInDouble(cal);
		ArrayList<DoGhatiBean> arrayList = new ArrayList<DoGhatiBean>();
		String[] pahars = constants.getPaharNameList();
		String[] pauranikMuhurats = constants.getDoGhatiPauranikMuhuratList();
		String[] planetNameMeaningForCurrentHora = constants.getDoGhatiPauranikMuhuratList();
		String[] doGhatiSecondMeaning = constants.getDoGhatiNameListMeaningSecond();
		String[] doGhatiSecondMeaningWikipedia = constants.getDoGhatiNameListMeaning();
		String[] doGhatiMuhurat = constants.getDoGhatiMuhurat();
		String entryTime;
		String exitTime;
		for (int i = 0; i <= 14; i++) {
			String pahar = "";
			if (i <= 2) {
				pahar = pahars[0];
			} else if (i <= 5) {
				pahar = pahars[1];
			} else if (i <= 8) {
				pahar = pahars[2];
			} else if (i <= 11) {
				pahar = pahars[3];
			} else if (i <= 14) {
				pahar = pahars[4];
			}

			entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 15)[i]);
			exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 15)[i + 1]);
			String duration;
			if (languageCode == 1) {
				duration = panUtil.convertTimeToAmPm(entryTime) + " to " + panUtil.convertTimeToAmPm(exitTime);
			} else {
				duration = panUtil.convertTimeToAmPm(entryTime) + " से " + panUtil.convertTimeToAmPm(exitTime) + " तक";
			}
			DoGhatiBean doGhatiBean = new DoGhatiBean(pahar, entryTime, exitTime, pauranikMuhurats[i],
					doGhatiSecondMeaning[i], doGhatiSecondMeaningWikipedia[i], planetNameMeaningForCurrentHora[i],
					doGhatiMuhurat[i], duration);

			arrayList.add(doGhatiBean);
		}
		return arrayList;
	}

	public ArrayList<DoGhatiBean> getNightDoGhatiData(Calendar cal) {
		// val place = com.bhakti_sangrahalay.util.Utility.getPlaceForPanchang()
		double jd = baseCalculationNew.panchangCalculation.toJulian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DATE));
		double sunSet = getSunSetTimeInDouble(cal);
		ArrayList<DoGhatiBean> arrayList = new ArrayList<DoGhatiBean>();

		String[] pahars = constants.getPaharNameList();
		String[] pauranikMuhurats = constants.getDoGhatiPauranikMuhuratList();
		String[] planetNameMeaningForCurrentHora = constants.getDoGhatiNakshtra();
		String[] doGhatiSecondMeaning = constants.getDoGhatiNameListMeaningSecond();
		String[] doGhatiSecondMeaningWikipedia = constants.getDoGhatiNameListMeaning();
		String[] doGhatiMuhurat = constants.getDoGhatiMuhurat();
		String entryTime;
		String exitTime;
		for (int i = 0; i <= 14; i++) {
			var pahar = "";
			if (i <= 2) {
				pahar = pahars[5];
			} else if (i <= 6) {
				pahar = pahars[6];
			} else if (i <= 7) {
				pahar = pahars[7];
			} else if (i <= 12) {
				pahar = pahars[8];
			} else if (i <= 14) {
				pahar = pahars[9];
			}

			entryTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[i]);
			exitTime = panUtil.FormatDMSIn2DigitStringWithSignForhora(
					baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[i + 1]);
			String duration;
			if (languageCode == 1) {
				duration = panUtil.convertTimeToAmPm(entryTime) + " to " + panUtil.convertTimeToAmPm(exitTime);
			} else {
				duration = panUtil.convertTimeToAmPm(entryTime) + " से " + panUtil.convertTimeToAmPm(exitTime) + " तक";
			}
			DoGhatiBean doGhatiBean = new DoGhatiBean(pahar, entryTime, exitTime, pauranikMuhurats[i + 15],
					doGhatiSecondMeaning[i], doGhatiSecondMeaningWikipedia[i], planetNameMeaningForCurrentHora[i],
					doGhatiMuhurat[i], duration);

			arrayList.add(doGhatiBean);
		}
		return arrayList;
	}
}
