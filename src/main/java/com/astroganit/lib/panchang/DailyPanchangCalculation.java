package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.astroganit.lib.panchang.model.PanchangModel;
import com.astroganit.lib.panchang.model.PlaceInfo;
import com.astroganit.lib.panchang.model.SampurnPanchangModel;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;




public class DailyPanchangCalculation extends PanchangBase {
	private PanchangModel panchangModel;
	private int datePanJd = 0;
	//BaseCalculationNew baseCalculationNew = new BaseCalculationNew();

	public DailyPanchangCalculation(Calendar calendar,PlaceInfo placeInfo, int language) {
		// Calendar calendar = Calendar.getInstance();
		//date = calendar.getTime();
		initDate(calendar.getTime());
		baseCalculationNew.setPlace(placeInfo);
		datePanJd = (int) baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		panchangModel = new PanchangModel();
		languageCode = language;
		// setLanguage();
		// val panchangPlaceModel = Utility.getPlaceForPanchang();
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
		initPanchangCalculation(calendar);
	}

	private void initPanchangCalculation(Calendar calendar) {
		getSunRiseTime(calendar);
		getSunSetTime(calendar);
		getMoonRiseSetTime(calendar);
	}

	public SampurnPanchangModel getSampurnPanchangModel(Calendar calendar) {
		return new SampurnPanchangModel(getSunRiseTime(calendar), getSunSetTime(calendar), getMoonRiseSetTime(calendar),
				getTithi(calendar), getTithiInt(calendar), getNakshtra(calendar), getYog(calendar), getKaran(calendar),
				getPaksh(calendar), getVar(calendar), getRitu(calendar), getMoonSign(calendar),
				getAmantaMonth(calendar), getPurnimantMonth(calendar), getShakaSamvatYear(calendar),
				getKaliSamvatYear(calendar), getVikramSamvatYear(calendar), getDayDuration(calendar),
				getAbhijitMuhurat(calendar), getAshubMuhuratList(calendar), getDishShool(calendar),
				getTaraBal(calendar), getChandraBal(calendar));
	}

	String getSunRiseTime(Calendar calendar) {
		return panchangUtil.convertTimeToAmPm(
				panchangUtil.dms(baseCalculationNew.panchangCalculation.getSunRise(getJulianDate(calendar))));
	}

	String getSunSetTime(Calendar calendar) {
		return panchangUtil.convertTimeToAmPm(
				panchangUtil.dms(baseCalculationNew.panchangCalculation.getSunSet(getJulianDate(calendar))));
	}

	public ArrayList<String> getMoonRiseSetTime(Calendar calendar) {
		ArrayList<String> moonRiseSetArr = new ArrayList<>();
		try {
			// Assuming BaseCalculationNew.moonCalculation.getMoonRiseSetTime returns a
			// double array
			double[] moonRiseSet = baseCalculationNew.moonCalculation.getMoonRiseSetTime(getJulianDate(calendar));

			// Add moonrise time or exception string
			moonRiseSetArr.add(panchangUtil.convertTimeToAmPm(
					moonRiseSet[0] != 0.0 ? getHoverString(moonRiseSet[0]) : constants.getExString(1)));

			// Add moonset time or exception string
			moonRiseSetArr.add(panchangUtil.convertTimeToAmPm(
					moonRiseSet[2] != 0.0 ? getHoverString(moonRiseSet[2]) : constants.getExString(1)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return moonRiseSetArr;
	}

	ArrayList<String> getTithi(Calendar calendar) {
		ArrayList<String> tithiArr = new ArrayList<String>();
		double[] tithiData = baseCalculationNew.panchangCalculation.tithi(getJulianDate(calendar));
		tithiArr.add(getString(tithiData, "Tith", getNextDaySunRise(calendar), fetchOnlyValue));
		tithiArr.add(getString(tithiData, "Tith", getNextDaySunRise(calendar), fetchOnlyTime));
		return tithiArr;
	}

	int getTithiInt(Calendar calendar) {
		double[] tithiData = baseCalculationNew.panchangCalculation.tithi(getJulianDate(calendar));
		return (int) tithiData[0];
	}

	double[] getTithiDouble(Calendar calendar) {
		double[] tithiData = baseCalculationNew.panchangCalculation.tithi(getJulianDate(calendar));
		return tithiData;
	}

	ArrayList<String> getYog(Calendar calendar) {
		ArrayList<String> yogArr = new ArrayList<String>();
		double[] yogaData = baseCalculationNew.panchangCalculation.yoga(getJulianDate(calendar));
		yogArr.add(getString(yogaData, "Yog", getNextDaySunRise(calendar), fetchOnlyValue));
		yogArr.add(getString(yogaData, "Yog", getNextDaySunRise(calendar), fetchOnlyTime));
		return yogArr;
	}

	ArrayList<String> getKaran(Calendar calendar) {
		ArrayList<String> karanArr = new ArrayList<String>();
		double[] karanaData = baseCalculationNew.panchangCalculation.karana(getJulianDate(calendar));
		karanArr.add(getString(karanaData, "Karan", getNextDaySunRise(calendar), fetchOnlyValue));
		karanArr.add(getString(karanaData, "Karan", getNextDaySunRise(calendar), fetchOnlyTime));
		return karanArr;
	}

	String getPaksh(Calendar calendar) {
		return constants.getPakshas(baseCalculationNew.panchangCalculation.getPaksha(getJulianDate(calendar)));
	}

	String getVar(Calendar calendar) {
		return constants.getVaras(baseCalculationNew.panchangCalculation.vaara(getJulianDate(calendar)));
	}

	String getRitu(Calendar calendar) {
		return constants.getRitus(baseCalculationNew.panchangCalculation.rituDrik(getJulianDate(calendar)));
	}

	ArrayList<String> getMoonSign(Calendar calendar) {
		ArrayList<String> moonSignArr = new ArrayList<String>();
		double[] moonSign = baseCalculationNew.panchangCalculation.moonsign(getJulianDate(calendar));
		double sunRiseVal = baseCalculationNew.panchangCalculation.getSunRise(getJulianDate(calendar));
		if (moonSign[1] > sunRiseVal + 24.00000) {
			String nameMoonSign = getDay(moonSign[0], "MoonSign");
			moonSignArr.add(nameMoonSign);
		} else {
			// panchangModel.moonSign = getString(MoonSign, "MoonSign", nextdaysunrise,
			// fetchCompleteData)
			moonSignArr.add(getString(moonSign, "MoonSign", getNextDaySunRise(calendar), fetchOnlyValue));
			// moonSignArr.add(getString(moonSign, "MoonSign", getNextDaySunRise(),
			// fetchOnlyTime))
		}
		return moonSignArr;
	}

	private int[] getMasaPurnimant(Calendar calendar) {
		return baseCalculationNew.panchangCalculation.masaPurnimanta(getJulianDate(calendar));
	}

	private int[] getKaliShakaVikramSamvat(Calendar calendar) {
		int[] masaMoon = getMasaPurnimant(calendar);
		return baseCalculationNew.panchangCalculation.elapsedYear(getJulianDate(calendar), masaMoon[0]);
	}

	String getAmantaMonth(Calendar calendar) {
		int[] masaMoon = getMasaPurnimant(calendar);
		if (masaMoon[1] == 1) {
			return constants.getMasas(masaMoon[0] - 1).toString() + " " + constants.getExString(3);
		} else {
			return constants.getMasas(masaMoon[0] - 1);
		}
	}

	String getPurnimantMonth(Calendar calendar) {
		int[] masaMoon = getMasaPurnimant(calendar);
		if (masaMoon[1] == 1) {
			return constants.getMasas(masaMoon[2] - 1).toString() + " " + constants.getExString(3);
		} else {
			return constants.getMasas(masaMoon[2] - 1);
		}
	}

	String getShakaSamvatYear(Calendar calendar) {
		return String.valueOf(getKaliShakaVikramSamvat(calendar)[1]);
	}

	String getVikramSamvatYear(Calendar calendar) {
		return String.valueOf(getKaliShakaVikramSamvat(calendar)[2]);
	}

	String getKaliSamvatYear(Calendar calendar) {
		return String.valueOf(getKaliShakaVikramSamvat(calendar)[3]);
	}

	String getDayDuration(Calendar calendar) {
		return panchangUtil.dms(baseCalculationNew.muhuratCalculation.dayDuration(getJulianDate(calendar)));
	}

	ArrayList<ArrayList<String>> getAshubMuhuratList(Calendar calendar) {
		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		arrayList.add(getDushtaMuhurtas(calendar));
		arrayList.add(getKantakaMrityu(calendar));
		arrayList.add(getYamaGhanta(calendar));
		arrayList.add(getRahuKaalVela(calendar));
		arrayList.add(getKulika(calendar));
		arrayList.add(getKalavelaArdhayaam(calendar));
		arrayList.add(getYamagandaVela(calendar));
		arrayList.add(getGulikaKaalVela(calendar));
		return arrayList;
	}

	private double[] getFifteenMuhurat(Calendar calendar) {
		return baseCalculationNew.muhuratCalculation.getFifteenMuhurtaForDay(getJulianDate(calendar));
	}

	private int[] getKulikaKantakaKalavelaYama(Calendar calendar) {
		return baseCalculationNew.muhuratCalculation.getKulikaKantakaKalavelaYama(getJulianDate(calendar));
	}

	private int[] getDushtaMuhurta(Calendar calendar) {
		return baseCalculationNew.muhuratCalculation.getDushtaMuhurta(getJulianDate(calendar));
	}

	ArrayList<String> getAbhijitMuhurat(Calendar calendar) {
		ArrayList<String> abhijitMuhuratArr = new ArrayList<String>();
		double[] fifteenMuhurtas = getFifteenMuhurat(calendar);
		if (baseCalculationNew.muhuratCalculation.vaara(getJulianDate(calendar)) != 3) // For Wednesday
		{
			abhijitMuhuratArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[8 - 1])));
			abhijitMuhuratArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[8])));
		} else {
			abhijitMuhuratArr.add(constants.getExString(0));
			abhijitMuhuratArr.add("");
		}
		return abhijitMuhuratArr;
	}

	private ArrayList<String> getKulika(Calendar calendar) {
		ArrayList<String> kulikaArr = new ArrayList<String>();
		double[] fifteenMuhurtas = getFifteenMuhurat(calendar);
		int[] kulikaEtc = getKulikaKantakaKalavelaYama(calendar);
		kulikaArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[0] - 1])));
		kulikaArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[0]])));
		return kulikaArr;
	}

	private ArrayList<String> getKantakaMrityu(Calendar calendar) {
		ArrayList<String> kantakaMrityuArr = new ArrayList<String>();
		double[] fifteenMuhurtas = getFifteenMuhurat(calendar);
		int[] kulikaEtc = getKulikaKantakaKalavelaYama(calendar);
		// panchangModel.kantaka_Mrityu = GetFromToString(fromKantaka_Mrityu,
		// toKantaka_Mrityu)
		kantakaMrityuArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[1] - 1])));
		kantakaMrityuArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[1]])));
		return kantakaMrityuArr;
	}

	private ArrayList<String> getKalavelaArdhayaam(Calendar calendar) {
		ArrayList<String> kalavelaArdhayaamArr = new ArrayList<String>();
		double[] fifteenMuhurtas = getFifteenMuhurat(calendar);
		int[] kulikaEtc = getKulikaKantakaKalavelaYama(calendar);
		// panchangModel.kalavela_Ardhayaam = GetFromToString(fromKalavela_Ardhayaam,
		// toKalavela_Ardhayaam)
		kalavelaArdhayaamArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[2] - 1])));
		kalavelaArdhayaamArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[2]])));
		return kalavelaArdhayaamArr;
	}

	private ArrayList<String> getYamaGhanta(Calendar calendar) {
		ArrayList<String> yamaGhantaArr = new ArrayList<String>();
		double[] fifteenMuhurtas = getFifteenMuhurat(calendar);
		int[] kulikaEtc = getKulikaKantakaKalavelaYama(calendar);
		yamaGhantaArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[3] - 1])));
		yamaGhantaArr.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[3]])));
		return yamaGhantaArr;
	}

	private ArrayList<String> getDushtaMuhurtas(Calendar calendar) {
		ArrayList<String> dushtaMuhurtasArr = new ArrayList<String>();
		int[] dushtaMuhurtas = getDushtaMuhurta(calendar);
		double[] fifteenMuhurtas = getFifteenMuhurat(calendar);
		if (dushtaMuhurtas.length > 1) {
			String from1 = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0] - 1]);
			String to1 = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0]]);
			String from2 = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[1] - 1]);
			String to2 = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[1]]);
			// panchangModel.dushtaMuhurtas = GetFromToString(from1, to1) + ", " +
			// GetFromToString(from2, to2)
			dushtaMuhurtasArr
					.add(panchangUtil.convertTimeToAmPm(from1) + ",\n" + panchangUtil.convertTimeToAmPm(from2));

			dushtaMuhurtasArr.add(panchangUtil.convertTimeToAmPm(to1) + ",\n" + panchangUtil.convertTimeToAmPm(to2));
		} else {
			String from1 = panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0] - 1]));
			String to1 = panchangUtil.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0]]));
			panchangModel.setDushtaMuhurtas(getFromToString(from1, to1));
			dushtaMuhurtasArr.add(from1);
			dushtaMuhurtasArr.add(to1);
		}
		return dushtaMuhurtasArr;
	}

	private ArrayList<String> getGulikaKaalVela(Calendar calendar) {
		ArrayList<String> gulikaKaalVelaArr = new ArrayList<String>();
		// panchangModel.gulikaKaalVela = GetFromToString(fromGulikaKaalVela,
		// toGulikaKaalVela)
		gulikaKaalVelaArr.add(panchangUtil.convertTimeToAmPm(
				panchangUtil.dms(getEightDivisions(calendar)[getRahuYamaGulikaKaal(calendar)[1] - 1])));
		gulikaKaalVelaArr.add(panchangUtil
				.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar)[getRahuYamaGulikaKaal(calendar)[1]])));
		return gulikaKaalVelaArr;
	}

	private ArrayList<String> getYamagandaVela(Calendar calendar) {
		ArrayList<String> yamagandaVelaArr = new ArrayList<String>();
		// panchangModel.yamagandaVela = GetFromToString(fromYamagandaVela,
		// toYamagandaVela)
		yamagandaVelaArr.add(panchangUtil.convertTimeToAmPm(
				panchangUtil.dms(getEightDivisions(calendar)[getRahuYamaGulikaKaal(calendar)[2] - 1])));
		yamagandaVelaArr.add(panchangUtil
				.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar)[getRahuYamaGulikaKaal(calendar)[2]])));
		return yamagandaVelaArr;
	}

	private String getHoverString(double actualTime) {
		String result;
		if (actualTime < 24.0) {
			result = panchangUtil.dms(actualTime);
		} else {
			Date nextDate = panchangUtil.getAddDays(date, 1);
			Calendar calnPan = Calendar.getInstance();
			calnPan.setTime(nextDate);

			switch (languageCode) {
			case 0: {
				result = panchangUtil.dms(actualTime);
				break;
			}
			default: {
				result = panchangUtil.dms(actualTime);
				break;
			}
			}
		}
		return result;
	}

	private String getFromToString(String from, String to) {
		switch (languageCode) {
		case 0: // Hindi language code
			return from + " से " + to + " तक";
		default:
			return "From " + from + " To " + to;
		}
	}

	// Function for merging elements from a list based on indices in an array
	private String getMergeString(int[] data, String[] list) {
		StringBuilder str = new StringBuilder();
		for (int item : data) {
			if (str.length() != 0) {
				str.append(", ");
			}
			str.append(list[item]);
		}
		return str.toString();
	}

	String getDishShool(Calendar calendar) {
		return constants.getDishas(baseCalculationNew.muhuratCalculation.getDishaShoola(getJulianDate(calendar)) - 1);
	}

	String getTaraBal(Calendar calendar) {
		return getMergeString(
				baseCalculationNew.muhuratCalculation.getTaraBaliNakshatra(
						(int) baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(calendar))[0]),
				constants.getTaraBala());
	}

	private String getChandraBal(Calendar calendar) {
		return getMergeString(
				baseCalculationNew.muhuratCalculation.getChandraBaliRasi(
						(int) baseCalculationNew.panchangCalculation.moonsign(getJulianDate(calendar))[0]),
				constants.getChandraBala());
	}
}
