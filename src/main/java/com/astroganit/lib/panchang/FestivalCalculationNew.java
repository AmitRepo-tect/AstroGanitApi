package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.astroganit.lib.panchang.model.FestivalDetail;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;
import com.astroganit.lib.panchang.util.EnumContainer;
import com.astroganit.lib.panchang.util.IConstants;
import com.astroganit.lib.panchang.util.PanUtil;

public class FestivalCalculationNew extends PanchangBase {

	ArrayList<FestivalDetail> festivals;
	HashMap<String, FestivalDetail> hashMap;
	PanUtil panUtil = new PanUtil();
	Calendar calendar = (Calendar) Calendar.getInstance().clone();
	int selectedYear;
	double jd;
	int sankrantiMonth = 10;
	String[] rashi;
	String[] ekadashiS;
	String[] ekadashiK;
	String[] hindiMonth;

	double sunriseJD = 0.0;
	double preSunriseJD = 0.0;
	double nextSunriseJD = 0.0;
	double datSunriseJD = 0.0;
	int sunMonth;
	int preSunMonth;
	int nextSunMonth;
	int datSunMonth;
	int nakshatraSunRise;
	int nakshatraSunRiseNextDay;
	double nakshatraSunRiseET;
	double nakshatraSunRiseNextDayET;
	int moonMonth;
	int moonMonthPrevDay;
	int dhikMonth;
	int tithiSunrise;
	int tithiSunriseNextDay;
	int tithiSunrisePrevDay;

	double sunRise;
	double sunRiseNextDay;
	double sunRisePreDay;
	double sunSet;
	double sunSetNextDay;
	double sunSetPreDay;
	double[] sunRiseDD5;
	double[] sunRiseDDND5;
	double[] sunRiseDDPD5;
	double[] sunRiseDD15;
	double[] sunRiseDDND15;
	double[] sunRiseDDPD15;

	double[] sunSetND5;
	double[] sunSetNDND5;
	double[] sunSetNDPD5;
	double[] sunSetND15;
	double[] sunSetNDND15;
	double[] sunSetNDPD15;

	public FestivalCalculationNew(int languageCode) {
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
		constants = new ConstantsHi();
		rashi = constants.getRashiList();
		ekadashiS = constants.getEkadashiS();
		ekadashiK = constants.getEkadashiK();
		hindiMonth = constants.getAdhikMasas();
	}

	public ArrayList<ArrayList<FestivalDetail>> getFestivalList(int currentYear, int languageCode) {
		selectedYear = currentYear;
		calendar.set(Calendar.YEAR, currentYear);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		festivals = new ArrayList<>();
		// festList = new ArrayList<>();
		hashMap = new HashMap<String, FestivalDetail>();
		for (int month = 0; month < 12; month++) {
			getMonthlyFestivals(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}

		festivals = new ArrayList<>(hashMap.values());

		festivals.add(calculateNewYear());
		festivals.add(calculateBoseJayanti());
		festivals.add(calculateRepublicDay());
		festivals.add(calculateBankHoliday());
		festivals.add(calculateAmbedkarJayanti());
		festivals.add(calculateIndependenceDay());
		festivals.add(calculateGandhiJayanti());
		festivals.add(calculateChildrenDay());
		festivals.add(calculateMerryChristmas());

		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));

		return groupByMonth();

		/*
		 * festList.add(festivals); return festList;
		 */
	}

	public ArrayList<ArrayList<FestivalDetail>> getVratList(int currentYear, int languageCode) {
		ArrayList<ArrayList<FestivalDetail>> festList = new ArrayList<>();
		selectedYear = currentYear;
		calendar.set(Calendar.YEAR, currentYear);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		festivals = new ArrayList<>();
		int totalDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

		HashMap<String, FestivalDetail> puranimaMap = new HashMap<String, FestivalDetail>();
		HashMap<String, FestivalDetail> amavsyaMap = new HashMap<String, FestivalDetail>();
		HashMap<String, FestivalDetail> ekadashiMap = new HashMap<String, FestivalDetail>();
		HashMap<String, FestivalDetail> pradoshMap = new HashMap<String, FestivalDetail>();
		HashMap<String, FestivalDetail> masikShivratriMap = new HashMap<String, FestivalDetail>();
		HashMap<String, FestivalDetail> sankastiChaturthiMap = new HashMap<String, FestivalDetail>();
		HashMap<String, FestivalDetail> sankrantiMap = new HashMap<String, FestivalDetail>();
		FestivalDetail festivalDetail;
		for (int day = 0; day < totalDays; day++) {
			jd = baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
			sunriseJD = baseCalculationNew.panchangCalculation.sunriseJd(jd);
			nextSunriseJD = baseCalculationNew.panchangCalculation.sunriseJd(jd + 1.0);
			preSunriseJD = baseCalculationNew.panchangCalculation.sunriseJd(jd - 1.0);
			sunMonth = baseCalculationNew.panchangCalculation.solarMonth(sunriseJD);
			preSunMonth = baseCalculationNew.panchangCalculation.solarMonth(preSunriseJD);
			nextSunMonth = baseCalculationNew.panchangCalculation.solarMonth(nextSunriseJD);
			tithiSunrise = baseCalculationNew.panchangCalculation.getTithiD(sunriseJD);
			tithiSunriseNextDay = baseCalculationNew.panchangCalculation.getTithiD(nextSunriseJD);
			tithiSunrisePrevDay = baseCalculationNew.panchangCalculation.getTithiD(preSunriseJD);
			sunSet = baseCalculationNew.panchangCalculation.getSunSet(jd);
			sunSetNextDay = baseCalculationNew.panchangCalculation.getSunSet(jd + 1.0);
			sunSetPreDay = baseCalculationNew.panchangCalculation.getSunSet(jd - 1.0);

			sunSetND5 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 5);
			sunSetNDND5 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 5);
			sunSetNDPD5 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd - 1.0, sunSetPreDay, 5);
			sunSetND15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15);
			sunSetNDPD15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd - 1.0, sunSetPreDay, 15);
			sunSetNDND15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 15);
			int[] x = baseCalculationNew.panchangCalculation.masa(jd);
			moonMonth = x[0];
			dhikMonth = x[1];
			festivalDetail = calculatePurnima();
			if (festivalDetail != null) {
				puranimaMap.put(hindiMonth[moonMonth - 1] + dhikMonth, festivalDetail);
			}
			festivalDetail = calculateAmavsya();
			if (festivalDetail != null) {
				amavsyaMap.put(hindiMonth[moonMonth - 1] + dhikMonth, festivalDetail);
			}
			festivalDetail = calculateEkadashi();
			if (festivalDetail != null) {
				ekadashiMap.put(festivalDetail.getFestName() + festivalDetail.getFestDate(), festivalDetail);
			}
			festivalDetail = calculatePradoshVrat();
			if (festivalDetail != null && !pradoshMap
					.containsKey(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth)) {
				pradoshMap.put(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth, festivalDetail);
			}
			festivalDetail = calculateMashikShivRatri();
			if (festivalDetail != null && !masikShivratriMap
					.containsKey(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth)) {
				masikShivratriMap.put(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth,
						festivalDetail);
			}
			festivalDetail = calculateSankashtiChaturthi();
			if (festivalDetail != null && !sankastiChaturthiMap
					.containsKey(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth)) {
				sankastiChaturthiMap.put(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth,
						festivalDetail);
			}
			festivalDetail = calculateSankrantiMonth();
			if (festivalDetail != null && !sankrantiMap
					.containsKey(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth)) {
				sankrantiMap.put(festivalDetail.getFestName() + calendar.get(Calendar.MONTH) + moonMonth,
						festivalDetail);
			}

			calendar.add(Calendar.DATE, 1);
		}
		festivals = new ArrayList<>(puranimaMap.values());
		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));
		festList.add(festivals);
		festivals = new ArrayList<>(amavsyaMap.values());
		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));
		festList.add(festivals);
		festivals = new ArrayList<>(ekadashiMap.values());
		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));
		festList.add(festivals);
		festivals = new ArrayList<>(pradoshMap.values());
		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));
		festList.add(festivals);
		festivals = new ArrayList<>(masikShivratriMap.values());
		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));
		festList.add(festivals);
		festivals = new ArrayList<>(sankastiChaturthiMap.values());
		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));
		festList.add(festivals);
		festivals = new ArrayList<>(sankrantiMap.values());
		festivals.sort(Comparator.comparingDouble(FestivalDetail::getFestDate));
		festList.add(festivals);
		return festList;

	}

	ArrayList<ArrayList<FestivalDetail>> groupByMonth() {
		Map<Integer, ArrayList<FestivalDetail>> groupedByMonth = new HashMap<>();

		for (int m = 1; m <= 12; m++) {
			groupedByMonth.put(m, new ArrayList<>());
		}

		for (FestivalDetail f : festivals) {
			double festDate = f.getFestDate();
			Calendar cal = fromJulian(festDate);
			int month = cal.get(Calendar.MONTH) + 1;
			groupedByMonth.get(month).add(f);
		}

		ArrayList<ArrayList<FestivalDetail>> result = new ArrayList<>();
		for (int m = 1; m <= 12; m++) {
			result.add(groupedByMonth.get(m));
		}

		return result;
	}

	ArrayList<FestivalDetail> getMonthlyFestivals(int days) {

		for (int day = 0; day < days; day++) {
			jd = baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
			sunriseJD = baseCalculationNew.panchangCalculation.sunriseJd(jd);
			preSunriseJD = baseCalculationNew.panchangCalculation.sunriseJd(jd - 1.0);
			nextSunriseJD = baseCalculationNew.panchangCalculation.sunriseJd(jd + 1.0);
			datSunriseJD = baseCalculationNew.panchangCalculation.sunriseJd(jd + 2.0);
			sunMonth = baseCalculationNew.panchangCalculation.solarMonth(sunriseJD);
			preSunMonth = baseCalculationNew.panchangCalculation.solarMonth(preSunriseJD);
			nextSunMonth = baseCalculationNew.panchangCalculation.solarMonth(nextSunriseJD);
			datSunMonth = baseCalculationNew.panchangCalculation.solarMonth(datSunriseJD);
			nakshatraSunRise = (int) baseCalculationNew.panchangCalculation.nakshatra(jd)[0];
			nakshatraSunRiseNextDay = (int) baseCalculationNew.panchangCalculation.nakshatra(jd + 1.0)[0];
			nakshatraSunRiseET = baseCalculationNew.panchangCalculation.nakshatra(jd)[1];
			nakshatraSunRiseNextDayET = baseCalculationNew.panchangCalculation.nakshatra(jd + 1.0)[1];
			sunRise = baseCalculationNew.panchangCalculation.getSunRise(jd);
			sunRiseNextDay = baseCalculationNew.panchangCalculation.getSunRise(jd + 1.0);
			sunRisePreDay = baseCalculationNew.panchangCalculation.getSunRise(jd - 1.0);

			sunSet = baseCalculationNew.panchangCalculation.getSunSet(jd);
			sunSetNextDay = baseCalculationNew.panchangCalculation.getSunSet(jd + 1.0);
			sunSetPreDay = baseCalculationNew.panchangCalculation.getSunSet(jd - 1.0);

			tithiSunrise = baseCalculationNew.panchangCalculation.getTithiD(sunriseJD);
			tithiSunrisePrevDay = baseCalculationNew.panchangCalculation.getTithiD(preSunriseJD);
			tithiSunriseNextDay = baseCalculationNew.panchangCalculation.getTithiD(nextSunriseJD);
			sunRiseDD5 = baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 5);
			sunRiseDDND5 = baseCalculationNew.muhuratCalculation.getDayDivisons(jd + 1.0, sunRiseNextDay, 5);
			sunRiseDDPD5 = baseCalculationNew.muhuratCalculation.getDayDivisons(jd - 1.0, sunRisePreDay, 5);
			sunRiseDD15 = baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 15);
			sunRiseDDND15 = baseCalculationNew.muhuratCalculation.getDayDivisons(jd + 1.0, sunRiseNextDay, 15);
			sunRiseDDPD15 = baseCalculationNew.muhuratCalculation.getDayDivisons(jd - 1.0, sunRisePreDay, 15);

			sunSetND5 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 5);
			sunSetNDND5 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 5);
			sunSetNDPD5 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd - 1.0, sunSetPreDay, 5);
			sunSetND15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15);
			sunSetNDPD15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd - 1.0, sunSetPreDay, 15);
			sunSetNDND15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 15);

			int[] x = baseCalculationNew.panchangCalculation.masa(jd);
			int[] x1 = baseCalculationNew.panchangCalculation.masa(jd - 1.0);
			moonMonth = x[0];
			dhikMonth = x[1];
			moonMonthPrevDay = x1[0];

			/*
			 * if (x[1] != 1) { moonMonth = x[0]; } else { moonMonth = 13; } if (x1[1] != 1)
			 * { moonMonthPrevDay = x1[0]; } else { moonMonthPrevDay = 13; }
			 */

			FestivalDetail festivalDetail = calculateLohri();
			if (festivalDetail != null) {
				hashMap.put(constants.lohri, festivalDetail);
			}
			festivalDetail = calculatePongal();
			if (festivalDetail != null) {
				hashMap.put(constants.pongal, festivalDetail);
			}
			festivalDetail = calculateUttarayan();
			if (festivalDetail != null) {
				hashMap.put(constants.uttarayanam, festivalDetail);
			}
			festivalDetail = calculateMakarSankranti();
			if (festivalDetail != null) {
				hashMap.put(constants.makarSankranti, festivalDetail);
			}
			festivalDetail = calculateVasantPanchmi();
			if (festivalDetail != null) {
				hashMap.put(constants.vasantPanchami, festivalDetail);
			}
			festivalDetail = calculateSarsvatiPooja();
			if (festivalDetail != null) {
				hashMap.put(constants.saraswatiPuja, festivalDetail);
			}
			festivalDetail = calculateMahaShivRatri();
			if (festivalDetail != null) {
				hashMap.put(constants.mahaShivratri, festivalDetail);
			}
			festivalDetail = calculateHolikaDahan();
			if (festivalDetail != null) {
				hashMap.put(constants.holikaDahan, festivalDetail);
			}
			festivalDetail = calculateHoli();
			if (festivalDetail != null) {
				hashMap.put(constants.holi, festivalDetail);
			}
			festivalDetail = calculateChaitraNavratri();
			if (festivalDetail != null) {
				hashMap.put(IConstants.chaitraNavratri, festivalDetail);
			}
			festivalDetail = calculateChaitraNavratriParana();
			if (festivalDetail != null) {
				hashMap.put(IConstants.chaitraNavratri, festivalDetail);
			}
			festivalDetail = calculateUgadi();
			if (festivalDetail != null) {
				hashMap.put(constants.ugadi, festivalDetail);
			}
			festivalDetail = calculateGudiPadwa();
			if (festivalDetail != null) {
				hashMap.put(constants.gudiPadwa, festivalDetail);
			}
			festivalDetail = calculateChetiChand();
			if (festivalDetail != null) {
				hashMap.put(constants.chetiChand, festivalDetail);
			}
			festivalDetail = calculateRamNavami();
			if (festivalDetail != null) {
				hashMap.put(constants.ramNavami, festivalDetail);
			}
			festivalDetail = calculateChaitraHanumanJayanti();
			if (festivalDetail != null) {
				hashMap.put(constants.chaitraHanumanJayanti, festivalDetail);
			}
			festivalDetail = calculateBaishakhi();
			if (festivalDetail != null) {
				hashMap.put(constants.baisakhi, festivalDetail);
			}
			festivalDetail = calculateAkshayaTritiya();
			if (festivalDetail != null) {
				hashMap.put(constants.akshayaTritiya, festivalDetail);
			}
			festivalDetail = calculateJagannathRathYatra();
			if (festivalDetail != null) {
				hashMap.put(constants.jagannathRathYatra, festivalDetail);
			}
			festivalDetail = calculateAashadiEkadashi();
			if (festivalDetail != null) {
				hashMap.put(constants.ashadhiEkadashi, festivalDetail);
			}
			festivalDetail = calculateGuruPurnima();
			if (festivalDetail != null) {
				hashMap.put(constants.guruPurnima, festivalDetail);
			}
			festivalDetail = calculateHariyaliTeej();
			if (festivalDetail != null) {
				hashMap.put(constants.hariyaliTeej, festivalDetail);
			}
			festivalDetail = calculateNagPanchami();
			if (festivalDetail != null) {
				hashMap.put(constants.nagPanchami, festivalDetail);
			}
			festivalDetail = calculateRakshaBandhan();
			if (festivalDetail != null) {
				hashMap.put(constants.rakshaBandhan, festivalDetail);
			}
			festivalDetail = calculateKajariTeej();
			if (festivalDetail != null) {
				hashMap.put(constants.kajariTeej, festivalDetail);
			}
			festivalDetail = calculateJanmashtami();
			if (festivalDetail != null) {
				hashMap.put(constants.janmashtami, festivalDetail);
			}
			festivalDetail = calculateHartalikaTeej();
			if (festivalDetail != null) {
				hashMap.put(constants.hartalikaTeej, festivalDetail);
			}
			festivalDetail = calculateGaneshChaturthi();
			if (festivalDetail != null) {
				hashMap.put(constants.ganeshChaturthi, festivalDetail);
			}
			festivalDetail = calculateOnam();
			if (festivalDetail != null) {
				hashMap.put(constants.onam, festivalDetail);
			}
			festivalDetail = calculateAnantChaturdashi();
			if (festivalDetail != null) {
				hashMap.put(constants.anantChaturdashi, festivalDetail);
			}
			festivalDetail = calculateAshwinNavratri();
			if (festivalDetail != null) {
				hashMap.put(constants.ashwinNavratri, festivalDetail);
			}
			festivalDetail = calculateDurgaPujaAstami();
			if (festivalDetail != null) {
				hashMap.put(constants.durgaPujaAstmi, festivalDetail);
			}
			festivalDetail = calculateDurgaPujaNavami();
			if (festivalDetail != null) {
				hashMap.put(constants.durgaPujaNavami, festivalDetail);
			}
			festivalDetail = calculateVijyaDashmi();
			if (festivalDetail != null) {
				hashMap.put(constants.vijayaDashami, festivalDetail);
			}
			festivalDetail = calculateAshwinNavratriParana();
			if (festivalDetail != null) {
				hashMap.put(constants.ashwinNavratriParana, festivalDetail);
			}
			festivalDetail = calculateKarvChauth();
			if (festivalDetail != null) {
				hashMap.put(constants.karvaChauth, festivalDetail);
			}
			festivalDetail = calculateDhanTeras();
			if (festivalDetail != null) {
				hashMap.put(constants.dhanteras, festivalDetail);
			}
			festivalDetail = calculateNarakChaturdshi();
			if (festivalDetail != null) {
				hashMap.put(constants.narakChaturdashi, festivalDetail);
			}
			festivalDetail = calculateDiwali();
			if (festivalDetail != null) {
				hashMap.put(constants.diwali, festivalDetail);
			}
			festivalDetail = calculateGoverdhan();
			if (festivalDetail != null) {
				hashMap.put(constants.govardhan, festivalDetail);
			}
			festivalDetail = calculateBhaiDooj();
			if (festivalDetail != null) {
				hashMap.put(constants.bhaiDooj, festivalDetail);
			}
			festivalDetail = calculateChathPooja();
			if (festivalDetail != null) {
				hashMap.put(constants.chathPooja, festivalDetail);
			}

			calendar.add(Calendar.DATE, 1);
		}
		return festivals;
	}

	public static Calendar fromJulian(double jd) {
		int JGREG = 15 + 31 * (10 + 12 * 1582);
		double HALFSECOND = 0.5;
		int jalpha, ja, jb, jc, jdInt, je;

		jd += HALFSECOND / 86400.0;
		int jdi = (int) jd;
		if (jdi >= JGREG) {
			jalpha = (int) (((jdi - 1867216) - 0.25) / 36524.25);
			ja = jdi + 1 + jalpha - jalpha / 4;
		} else {
			ja = jdi;
		}
		jb = ja + 1524;
		jc = (int) (6680.0 + ((jb - 2439870) - 122.1) / 365.25);
		jdInt = (int) (365 * jc + jc / 4);
		je = (int) ((jb - jdInt) / 30.6001);
		int day = jb - jdInt - (int) (30.6001 * je);
		int month = je - 1;
		if (month > 12)
			month -= 12;
		int year = jc - 4715;
		if (month > 2)
			year--;
		if (year <= 0)
			year--;

		Calendar cal = new GregorianCalendar(year, month - 1, day);
		return cal;
	}

	String convertJDtoDate(double jd) {
		int[] arr = baseCalculationNew.panchangCalculation.fromJulian(jd);
		return arr[0] + "--" + arr[1] + "--" + arr[2];
	}

	FestivalDetail calculateHinduNewYear() {
		if (moonMonth == 1 && moonMonthPrevDay != 1) {
			double festDate = (tithiSunrise == 2 && tithiSunrisePrevDay != 1) ? jd - 1.0 : jd;
			return new FestivalDetail(constants.hinduNewYear, festDate);
		}
		return null;

	}

	FestivalDetail calculateVasantPanchmi() {
		int t10, t11, t12, t13;
		if (moonMonth == 11 && (tithiSunrise == 4 || tithiSunrise == 5)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + sunRiseDDPD5[2] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[2] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[5] / 24.0);
			t13 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[5] / 24.0);

			double festDate = -1;
			if (t10 == 4 && (t11 == 6 || t12 == 6)) {
				festDate = jd;
			} else if (t11 == 5 || t12 == 5) {
				festDate = (t13 == 5) ? jd + 1.0 : (t10 != 5 ? jd : -1);
			}

			return (festDate != -1)
					? new FestivalDetail(constants.vasantPanchami, festDate, EnumContainer.FestType.FESTIVALS, null)
					: null;

		}
		return null;

	}

	FestivalDetail calculateSarsvatiPooja() {
		int t10, t11, t12, t13;
		if (moonMonth == 11 && (tithiSunrise == 4 || tithiSunrise == 5)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + sunRiseDDPD5[2] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[2] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[5] / 24.0);
			t13 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[5] / 24.0);

			double festDate = -1;
			if (t10 == 4 && (t11 == 6 || t12 == 6)) {
				festDate = jd;
			} else if (t11 == 5 || t12 == 5) {
				festDate = (t13 == 5) ? jd + 1.0 : (t10 != 5 ? jd : -1);
			}

			return (festDate != -1)
					? new FestivalDetail(constants.saraswatiPuja, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_sarswati_mata.png")
					: null;

		}

		return null;

	}

	FestivalDetail calculateMahaShivRatri() {
		int t10, t11, t12;
		if (moonMonth == 11 && (tithiSunrise == 28 || tithiSunrise == 29)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[7] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[8] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND15[8] / 24.0);

			double festDate = -1;
			if ((t11 == 28) && t12 == 30) {
				festDate = jd + 1.0;
			} else if (t10 == 29 || t11 == 29) {
				festDate = jd;
			}
			return (festDate != -1)
					? new FestivalDetail(constants.mahaShivratri, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_shivji.png")
					: null;

		}

		return null;
	}

	FestivalDetail calculateHolikaDahan() {
		int t10, t11, t12, t13;

		if (moonMonth == 12 && (tithiSunrise == 14 || tithiSunrise == 15)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[0] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[1] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[0] / 24.0);
			t13 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[4] / 24.0);
			if (t10 == 15 || t11 == 15) {
				double festivalDate = (t12 != 15 && t13 != 15) ? jd : jd + 1.0;
				return new FestivalDetail(constants.holikaDahan, festivalDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_holika_dahan.png");
			}

		}

		return null;

	}

	FestivalDetail calculateHoli() {
		int t10, t11, t12, t13;
		if (moonMonth == 12 && (tithiSunrise == 14 || tithiSunrise == 15)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[0] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[1] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[0] / 24.0);
			t13 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[4] / 24.0);
			if (t10 == 15 || t11 == 15) {
				double festDate = (t12 != 15 && t13 != 15) ? jd + 1.0 : jd + 2.0;
				return new FestivalDetail(constants.holi, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_holi.png");
			}
		}
		return null;

	}

	FestivalDetail calculateChaitraNavratri() {
		if (moonMonth == 1 && moonMonthPrevDay != 1) {
			double festDate = (tithiSunrise == 2 && tithiSunrisePrevDay != 1) ? jd - 1.0 : jd;
			return new FestivalDetail(constants.chaitraNavratri, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_navratri.png");
		}
		return null;
	}

	FestivalDetail calculateChaitraNavratriParana() {
		if (moonMonth == 1) {

			double festDate = -1;
			if (tithiSunrise == 9 && tithiSunrisePrevDay == 7) {
				if (paranaVart(jd, tithiSunrise)) {
					festDate = jd;
				} else {
					festDate = jd + 1.0;
				}
			} else if (tithiSunrise == 10 && tithiSunrisePrevDay == 8) {
				festDate = jd;

			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 10) {
				if (paranaVart(jd, tithiSunrise)) {
					festDate = jd;
				} else {
					festDate = jd + 1.0;
				}
			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 11) {
				festDate = jd;

			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 9) {
				festDate = jd + 1.0;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.chaitraNavratriParana, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_navratri.png");
			}

		}
		return null;
	}

	FestivalDetail calculateUgadi() {
		if (moonMonth == 1 && moonMonthPrevDay != 1) {
			double festDate = (tithiSunrise == 2 && tithiSunrisePrevDay != 1) ? jd - 1.0 : jd;
			return new FestivalDetail(constants.ugadi, festDate, EnumContainer.FestType.FESTIVALS, null);
		}
		return null;
	}

	FestivalDetail calculateGudiPadwa() {
		if (moonMonth == 1 && moonMonthPrevDay != 1) {
			double festDate = (tithiSunrise == 2 && tithiSunrisePrevDay != 1) ? jd - 1.0 : jd;
			return new FestivalDetail(constants.gudiPadwa, festDate, EnumContainer.FestType.FESTIVALS, null);
		}

		return null;
	}

	FestivalDetail calculateChetiChand() {
		if (moonMonth == 1 && (tithiSunrise == 1 || tithiSunrise == 2)) {
			double festDate = -1;

			if (tithiSunrise == 1 && tithiSunriseNextDay == 3) {
				festDate = jd;
			} else if (tithiSunrise == 2 && tithiSunrisePrevDay != 2) {
				festDate = jd;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.chetiChand, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_chetichand.png");
			}
		}
		return null;

	}

	FestivalDetail calculateRamNavami() {
		int t10, t11, t12, t13;
		if (moonMonth == 1) {
			if (tithiSunrise == 8 || tithiSunrise == 9) {
				t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[2] / 24.0);
				t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[3] / 24.0);
				t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[2] / 24.0);
				t13 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[3] / 24.0);
				double festDate = -1;
				if (t10 == 9 || t11 == 9) {
					if (t12 == 9 || t13 == 9) {
						festDate = jd + 1.0;
					} else if (t10 == 8 && t12 == 10) {
						festDate = jd;
					} else {
						festDate = jd;
					}
				}
				if (festDate != -1) {
					return new FestivalDetail(constants.ramNavami, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_ram_navmi.png");
				}
			}
		}

		return null;
	}

	FestivalDetail calculateChaitraHanumanJayanti() {
		if (moonMonth == 1 && (tithiSunrise == 14 || tithiSunrise == 15)) {

			if (tithiSunrise == 14 && tithiSunriseNextDay == 16) {
				return new FestivalDetail(constants.chaitraHanumanJayanti, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_hanumaan_jayanti.png");
			} else if (tithiSunrise == 15) {
				return new FestivalDetail(constants.chaitraHanumanJayanti, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_hanumaan_jayanti.png");
			}
		}
		return null;
	}

	FestivalDetail calculateBaishakhi() {
		if (sunMonth == 1 && preSunMonth == 12) {
			double festDate = (baseCalculationNew.panchangCalculation.solarMonth(jd - 0.0104) == 1) ? jd - 1.0 : jd;
			return new FestivalDetail(constants.baisakhi, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_baishakhi.png");
		}
		return null;
	}

	FestivalDetail calculateAkshayaTritiya() {
		int t10, t11;
		if (moonMonth == 2 && (tithiSunrise == 2 || tithiSunrise == 3)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[3] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND15[3] / 24.0);

			double festDate = -1;
			if (t10 == 2 && t11 == 4) {
				festDate = jd;
			} else if (t10 == 3) {
				festDate = jd;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.akshayaTritiya, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_akshay_tritya.png");
			}
		}

		return null;
	}

	FestivalDetail calculateJagannathRathYatra() {
		if (moonMonth == 4) {
			double festDate = -1;
			if (tithiSunrise == 1 && tithiSunriseNextDay == 3) {
				festDate = jd;
			} else if (tithiSunrise == 2 && tithiSunriseNextDay == 2) {
				festDate = jd + 1.0;
			} else if (tithiSunrise == 2) {
				festDate = jd;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.jagannathRathYatra, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_jagannath_yatra.png");
			}
		}

		return null;
	}

	FestivalDetail calculateAashadiEkadashi() {

		if (moonMonth == 4 && (tithiSunrise == 10 || tithiSunrise == 11)) {
			double festDate = ((tithiSunrise == 10 && tithiSunriseNextDay == 12) || tithiSunrise == 11) ? jd : -1;

			return (festDate != -1)
					? new FestivalDetail(constants.ashadhiEkadashi, festDate, EnumContainer.FestType.FESTIVALS, null)
					: null;
		}

		return null;
	}

	FestivalDetail calculateGuruPurnima() {
		int t10, t12;
		if (moonMonth == 4 && (tithiSunrise == 14 || tithiSunrise == 15)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[2] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND15[2] / 24.0);
			double festDate = (t10 == 14 && t12 == 16) || t10 == 15 ? jd : -1;

			return (festDate != -1)
					? new FestivalDetail(constants.guruPurnima, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_guru_purnima.png")
					: null;
		}

		return null;
	}

	FestivalDetail calculateHariyaliTeej() {
		if (moonMonth == 5 && (tithiSunrise == 2 || tithiSunrise == 3)) {
			double festDate = ((tithiSunrise == 2 && tithiSunriseNextDay == 4) || tithiSunrise == 3) ? jd : -1;
			return festDate != -1
					? new FestivalDetail(constants.hariyaliTeej, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_haryali_teej.png")
					: null;
		}

		return null;
	}

	FestivalDetail calculateNagPanchami() {
		int t10, t12, t21, t22;
		if (moonMonth == 5 && (tithiSunrise == 4 || tithiSunrise == 5)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[2] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[3] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND15[3] / 24.0);

			double festDate = ((t12 == 4 && t21 == 6) || (t10 == 5 && t12 == 5)) ? jd : -1;
			return festDate != -1
					? new FestivalDetail(constants.nagPanchami, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_naag_panchami.png")
					: null;
		}

		return null;
	}

	FestivalDetail calculateRakshaBandhan() {
		int t10, t11;
		if (moonMonth == 5 && (tithiSunrise == 14 || tithiSunrise == 15)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[3] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND15[3] / 24.0);
			double festDate = ((t10 == 14 && t11 == 16) || t10 == 15) ? jd : -1;

			return festDate != -1
					? new FestivalDetail(constants.rakshaBandhan, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_raksha_bandhan.png")
					: null;

		}

		return null;
	}

	FestivalDetail calculateKajariTeej() {
		if (moonMonth == 5 && (tithiSunrise == 17 || tithiSunrise == 18)) {
			double festDate = (tithiSunrise == 17 && tithiSunriseNextDay == 19) ? jd
					: (tithiSunrise == 18 && tithiSunriseNextDay == 18) ? jd + 1.0 : (tithiSunrise == 18) ? jd : -1;

			return (festDate != -1)
					? new FestivalDetail(constants.kajariTeej, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_kajri_teej.png")
					: null;
		}

		return null;
	}

	FestivalDetail calculateJanmashtami() {
		int t10, t12, t21, t22;
		if (moonMonth == 5 && (tithiSunrise == 22 || tithiSunrise == 23)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[7] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[8] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD((jd + 1.0) + sunSetNDND15[7] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD((jd + 1.0) + sunSetNDND15[8] / 24.0);
			boolean dayHas23 = (tithiSunrise == 23) || (t10 == 23) || (t12 == 23);
			boolean dayHas24 = (tithiSunrise == 24) || (t10 == 24) || (t12 == 24);
			boolean nextHas24 = (tithiSunriseNextDay == 24) || (t21 == 24) || (t22 == 24);
			boolean nextHas25 = (tithiSunriseNextDay == 25) || (t21 == 25) || (t22 == 25);
			double festDate = ((dayHas23 && nextHas24) || (dayHas24 && nextHas25)) ? jd : -1;

			return (festDate != -1)
					? new FestivalDetail(constants.janmashtami, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img.png")
					: null;

		}
		return null;
	}

	FestivalDetail calculateHartalikaTeej() {
		if (moonMonth == 6 && (tithiSunrise == 2 || tithiSunrise == 3)) {
			double festDate = (tithiSunrise == 2 && tithiSunriseNextDay == 4) ? jd
					: (tithiSunrise == 3 && tithiSunriseNextDay == 3) ? jd + 1.0 : (tithiSunrise == 3) ? jd : -1;

			return (festDate != -1)
					? new FestivalDetail(constants.hartalikaTeej, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_hartalika_teej.png")
					: null;
		}
		return null;
	}

	FestivalDetail calculateGaneshChaturthi() {
		int t10, t11, t12, t13;

		if (moonMonth == 6 && (tithiSunrise == 3 || tithiSunrise == 4)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[2] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[3] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[2] / 24.0);
			t13 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[3] / 24.0);
			double festDate = -1;

			if (t10 == 4 && t11 == 4 && t12 == 4 && t13 == 4) {
				festDate = jd;

			} else if (t10 == 4 && t11 == 4) {
				festDate = jd;

			} else if (t11 != 4 && t12 == 4) {
				festDate = jd + 1.0;

			} else if (t11 == 4 && t12 != 4) {
				festDate = jd;

			} else if (t10 != 4 && t11 == 4 && t12 == 4 && t13 == 4) {
				festDate = jd + 1.0;

			}
			if (festDate != -1) {
				return new FestivalDetail(constants.ganeshChaturthi, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_ganesh_chaturthi.png");
			}
		}

		return null;
	}

	FestivalDetail calculateOnam() {
		if ((sunMonth == 5 && datSunMonth == 5) && ((nakshatraSunRise == 21 || nakshatraSunRise == 22))) {

			double festDate = -1;
			if (nakshatraSunRise == 21 && nakshatraSunRiseNextDay == 23) {
				festDate = jd;
			} else if (nakshatraSunRise == 21 && nakshatraSunRiseNextDay == 22) {
				festDate = jd + 1.0;
			} else if (nakshatraSunRise == 22 && nakshatraSunRiseNextDay == 22) {
				festDate = jd + 1.0;
			} else if (nakshatraSunRise == 22) {
				festDate = jd;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.onam, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_onam.png");
			}
		}
		return null;
	}

	FestivalDetail calculateAnantChaturdashi() {
		int t10, t12;
		if (moonMonth == 6 && (tithiSunrise == 13 || tithiSunrise == 14)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[2] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND15[2] / 24.0);
			double festDate = (tithiSunrise == 13 && t12 == 15) || (t10 == 14) ? jd : -1;
			return (festDate != -1)
					? new FestivalDetail(constants.anantChaturdashi, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_anant_chaturdashi.png")
					: null;
		}
		return null;

	}

	FestivalDetail calculateAshwinNavratri() {
		if (moonMonth == 7 && moonMonthPrevDay != 7) {
			if (tithiSunrise == 2) {
				return new FestivalDetail(constants.ashwinNavratri, jd - 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_navratri.png");
			}
			return new FestivalDetail(constants.ashwinNavratri, jd, EnumContainer.FestType.FESTIVALS,
					"holidays/img_navratri.png");
		}
		return null;
	}

	FestivalDetail calculateAshwinNavratriParana() {

		if (moonMonth == 7) {
			double festDate = -1;
			if (tithiSunrise == 9 && tithiSunrisePrevDay == 7) {
				if (paranaVart(jd, tithiSunrise)) {
					festDate = jd;
				} else {
					festDate = jd + 1.0;
				}
			} else if (tithiSunrise == 10 && tithiSunrisePrevDay == 8) {
				festDate = jd;

			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 10) {
				if (paranaVart(jd, tithiSunrise)) {
					festDate = jd;
				} else {
					festDate = jd + 1.0;
				}
			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 11) {
				festDate = jd;

			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 9) {
				festDate = jd + 1.0;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.ashwinNavratriParana, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_navratri.png");
			}

		}
		return null;
	}

	FestivalDetail calculateAshwinNavratriDurgaVisarjan() {

		if (moonMonth == 7) {
			double festDate = -1;

			if (tithiSunrise == 9 && tithiSunrisePrevDay == 7) {
				if (paranaVart(jd, tithiSunrise)) {
					return new FestivalDetail(constants.ashwinNavratriDurgaVisarjan, jd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png");
				} else {
					return new FestivalDetail(constants.ashwinNavratriDurgaVisarjan, jd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png");
				}
			} else if (tithiSunrise == 10 && tithiSunrisePrevDay == 8) {

				return new FestivalDetail(constants.ashwinNavratriDurgaVisarjan, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_navratri.png");

			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 10) {
				if (paranaVart(jd, tithiSunrise)) {
					return new FestivalDetail(constants.ashwinNavratriDurgaVisarjan, jd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png");
				} else {
					return new FestivalDetail(constants.ashwinNavratriDurgaVisarjan, jd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png");
				}
			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 11) {
				return new FestivalDetail(constants.ashwinNavratriDurgaVisarjan, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_navratri.png");
			} else if (tithiSunrise == 9 && tithiSunriseNextDay == 9) {
				return new FestivalDetail(constants.ashwinNavratriDurgaVisarjan, jd + 1.0,
						EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png");
			}

		}
		return null;
	}

	FestivalDetail calculateChaitraNavratriGhatasthapana(int moonMonth, int moonMonthPrevDay, int tithiSunrise) {
		if (moonMonth == 1 && moonMonthPrevDay != 1) {
			if (tithiSunrise == 2) {
				return new FestivalDetail(constants.chaitraNavratriGhatasthapana, jd - 1.0);
			}
			return new FestivalDetail(constants.chaitraNavratriGhatasthapana, jd);
		}
		return null;
	}

	FestivalDetail calculateDurgaPujaNavami() {
		int t10, t12;
		double festDate = -1;
		if (moonMonth == 7) {
			if (tithiSunrise == 8 || tithiSunrise == 9) {
				t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[12] / 24.0);
				t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[13] / 24.0);
				if (tithiSunrise == 8) {
					if (t10 == 9 || t12 == 9) {
						festDate = jd;
					}
				} else if (tithiSunrise == 9) {
					festDate = jd;
				}
				if (festDate != -1) {
					return new FestivalDetail(constants.durgaPujaNavami, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_navratri.png");
				}
			}
		}

		return null;
	}

	FestivalDetail calculateDurgaPujaAstami() {

		if (moonMonth == 7) {
			double festDate = -1;
			if ((tithiSunrise == 7 && tithiSunriseNextDay == 9) || tithiSunrise == 8) {
				festDate = jd;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.durgaPujaAstmi, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_navratri.png");
			}
		}

		return null;
	}

	FestivalDetail calculateVijyaDashmi() {
		int t10, t12, t21, t22;

		if (moonMonth == 7) {
			if (tithiSunrise == 9 || tithiSunrise == 10) {

				t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[3] / 24.0);
				t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[4] / 24.0);
				t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[1] / 24.0);
				t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[3] / 24.0);
				boolean isSravanNak = false;
				if (nakshatraSunRise == 22 && (nakshatraSunRiseET > sunRiseDD5[3])) {
					isSravanNak = true;
				}

				double festDate = -1;
				if (isSravanNak && ((t10 == 10 || t12 == 10) || (t10 == 11 || t12 == 11))) {
					festDate = jd;
				} else if (!isSravanNak && tithiSunrise == 10 && t12 == 10) {
					festDate = jd;
				} else if (!isSravanNak && t10 == 10 && t12 == 10) {
					festDate = jd;
				}

				if (festDate != -1) {
					return new FestivalDetail(constants.vijayaDashami, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_dushera.png");
				}
			}
		}

		return null;
	}

	FestivalDetail calculateKarvChauth() {
		int t10, t11, t12;
		if (moonMonth == 7 && (tithiSunrise == 18 || tithiSunrise == 19)) {
			MoonCalculation objCMoon = new MoonCalculation();

			double[] moonRiseTime = objCMoon.getMoonRiseSetTime(jd);
			double[] moonRiseTimePreDay = objCMoon.getMoonRiseSetTime(jd - 1.0);
			double[] moonRiseTimeNextDay = objCMoon.getMoonRiseSetTime(jd + 1.0);
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + moonRiseTimePreDay[0] / 24.0);
			t11 = baseCalculationNew.panchangCalculation.getTithiD(jd + moonRiseTime[0] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + moonRiseTimeNextDay[0] / 24.0);
			double festDate = (t11 == 18 && t12 == 20) || (t10 == 18 && t11 == 20) || (t11 == 19) ? jd : -1;
			if (festDate != -1) {
				return new FestivalDetail(constants.karvaChauth, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_karva_chauth.png");
			}

		}

		return null;
	}

	FestivalDetail calculateDhanTeras() {
		int t10, t12, t21, t22;

		if (moonMonth == 7 && (tithiSunrise == 27 || tithiSunrise == 28)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[0] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[1] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[0] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[1] / 24.0);

			double festDate = (t12 == 27 && t21 == 29) ? jd
					: (t10 == 28 || t12 == 28) ? ((t21 != 28 && t22 != 28) ? jd : jd + 1.0) : -1;
			if (festDate != -1) {
				return new FestivalDetail(constants.dhanteras, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dhan_teras.png");
			}
		}
		return null;
	}

	FestivalDetail calculateNarakChaturdshi() {
		int t10, t12, t21, t22;
		if (moonMonth == 7 && (tithiSunrise == 28 || tithiSunrise == 29)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + sunSetNDPD15[13] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + sunSetNDPD15[14] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[13] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[14] / 24.0);
			boolean noAmavasyaToday = (t10 != 29 && t12 != 29 && tithiSunrise != 29);
			boolean noAmavasyaTomorrow = (t21 != 29 && t22 != 29 && tithiSunriseNextDay != 29);
			double festDate;
			if (noAmavasyaToday && noAmavasyaTomorrow) {
				festDate = (tithiSunrise == 28 && tithiSunriseNextDay == 28) ? jd + 2.0 : jd;
			} else if (noAmavasyaToday) {
				festDate = jd + 1.0;
			} else {
				festDate = jd;
			}

			return new FestivalDetail(constants.narakChaturdashi, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_narak_chaturdashi.png");
		}

		return null;
	}

	FestivalDetail calculateDiwali() {
		int t8, t9, t10, t12, t21, t22;
		if (moonMonth == 7 && (tithiSunrise == 29 || tithiSunrise == 30)) {
			t8 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + sunSetNDPD5[0] / 24.0);
			t9 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + sunSetNDPD5[1] / 24.0);
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[0] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[1] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[0] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[1] / 24.0);

			double festDate = -1;
			if (t12 == 29 && t21 == 1) {
				festDate = jd;
			} else if (t10 == 29 && t12 == 30) {
				festDate = jd;
			} else if (t10 == 30 && t12 == 30 && t21 == 1) {
				festDate = jd;
			} else if (t10 == 30 && t12 == 1) {
				festDate = jd;
			} else if (t10 == 1 && tithiSunrise == 30 && t9 == 29) {
				festDate = jd;
			}

			if (festDate != -1) {
				return new FestivalDetail(constants.diwali, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_diwali.png");
			}

		}

		return null;
	}

	FestivalDetail calculateGoverdhan() {
		int t10, t12;
		if (moonMonth == 8 && moonMonthPrevDay != 8 && (tithiSunrise == 1 || tithiSunrise == 2)) {
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd - 1.0 + sunRiseDDPD15[14] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[9] / 24.0);

			double festDate = (t12 == 1) ? jd : (t10 == 1) ? jd - 1.0 : jd;

			return new FestivalDetail(constants.govardhan, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_goverdhan.png");
		}

		return null;
	}

	FestivalDetail calculateBhaiDooj() {
		int t10, t12, t21, t22;
		if (moonMonth == 8 && (tithiSunrise == 1 || tithiSunrise == 2)) {

			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[3] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[4] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[3] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[4] / 24.0);

			if (t10 == 2 || t12 == 2) {
				double festDate = (t21 != 2 && t22 != 2) ? jd : jd + 1.0;
				return new FestivalDetail(constants.bhaiDooj, festDate, EnumContainer.FestType.FESTIVALS,
						"holidays/img_bhaidooj.png");
			}

		}
		return null;
	}

	FestivalDetail calculateChathPooja() {
		int t10, t12, t21, t22;
		if (moonMonth == 8 && (tithiSunrise == 5 || tithiSunrise == 6)) {

			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[3] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[4] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[3] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[4] / 24.0);

			if (tithiSunrise == 5 || tithiSunriseNextDay == 7) {

				return new FestivalDetail(constants.chathPooja, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_bhaidooj.png");
			} else if (tithiSunrise == 6) {
				return new FestivalDetail(constants.chathPooja, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_bhaidooj.png");
			}

		}
		return null;
	}

	FestivalDetail calculateSankranti(int sunMonth, int sunMonthPrevDay) {
		int t10;
		int sMonth = (sankrantiMonth != 1) ? sankrantiMonth - 1 : 12;

		if (sunMonth == sankrantiMonth && sunMonthPrevDay == sMonth) {
			String rashiName = rashi[sankrantiMonth];
			++sankrantiMonth;
			if (sankrantiMonth == 13) {
				sankrantiMonth = 1;
			}
			if (sankrantiMonth - 1 != 4 && sankrantiMonth - 1 != 10) {
				t10 = baseCalculationNew.panchangCalculation.solarMonth(jd - 0.0104);
				if (t10 == sankrantiMonth - 1) {
					return new FestivalDetail(rashiName + " " + constants.sankranti, jd - 1.0,
							EnumContainer.FestType.SANKRANTI, null);
				} else {
					return new FestivalDetail(rashiName + " " + constants.sankranti, jd,
							EnumContainer.FestType.SANKRANTI, null);
				}

			}

		}

		if (sunMonth == 4 && sunMonthPrevDay == 3) {

			return new FestivalDetail(rashi[4] + " " + constants.sankranti, jd - 1.0, EnumContainer.FestType.SANKRANTI,
					null);
		}

		return null;
	}

	FestivalDetail calculateMakarSankranti() {

		if (sunMonth == 10 && preSunMonth == 9) {
			double festDate = (baseCalculationNew.panchangCalculation.solarMonth(sunriseJD - 0.67) == 10) ? jd - 1.0
					: jd;
			return new FestivalDetail(rashi[10] + " " + constants.sankranti, festDate,
					EnumContainer.FestType.FEST_SANKRANTI, "holidays/img_makar_sankranti.png");
		}
		return null;
	}

	FestivalDetail calculateUttarayan() {
		int t10;
		if (sunMonth == 10 && preSunMonth == 9) {
			double festDate = (baseCalculationNew.panchangCalculation.solarMonth(sunriseJD - 0.67) == 10) ? jd - 1.0
					: jd;
			return new FestivalDetail(constants.uttarayanam, festDate, EnumContainer.FestType.FESTIVALS, null);

		}
		return null;
	}

	FestivalDetail calculatePongal() {
		if (sunMonth == 10 && preSunMonth == 9) {
			double festDate = (baseCalculationNew.panchangCalculation.solarMonth(sunriseJD - 0.67) == 10) ? jd - 1.0
					: jd;
			return new FestivalDetail(constants.pongal, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_pongal.png");
		}
		return null;
	}

	FestivalDetail calculateLohri() {
		int t10;
		if (sunMonth == 10 && preSunMonth == 9) {

			double festDate = (baseCalculationNew.panchangCalculation.solarMonth(sunriseJD - 0.67) == 10) ? jd - 2.0
					: jd - 1.0;
			return new FestivalDetail(constants.lohri, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_lohri.png");
		}
		return null;
	}

	FestivalDetail calculateEkadashi() {

		if (tithiSunrise == 10 || tithiSunrise == 11) {

			String festName = ekadashiS[moonMonth - 1];
			if (dhikMonth == 1) {
				festName = festName + "(Adik)";
			}
			if (tithiSunrise == 10 && tithiSunriseNextDay == 12) {
				return new FestivalDetail(festName, jd, EnumContainer.FestType.EKADASHI_VRAT, null);
			} else if (tithiSunrise == 11) {
				return new FestivalDetail(festName, jd, EnumContainer.FestType.EKADASHI_VRAT, null);
			}
		} else if (tithiSunrise == 25 || tithiSunrise == 26) {

			String festName = ekadashiK[moonMonth];
			if (dhikMonth == 1) {
				festName = festName + "(Adik)";
			}
			if (tithiSunrise == 25 && tithiSunriseNextDay == 27) {
				return new FestivalDetail(festName, jd, EnumContainer.FestType.EKADASHI_VRAT, null);
			} else if (tithiSunrise == 26) {
				return new FestivalDetail(festName, jd, EnumContainer.FestType.EKADASHI_VRAT, null);
			}
		}

		return null;
	}

	FestivalDetail calculatePurnima() {
		if (tithiSunrise == 14 || tithiSunrise == 15) {

			String festName = hindiMonth[moonMonth - 1];
			if (dhikMonth == 1) {
				festName = festName + "( Adik)";
			}
			double festDate = -1;
			if (tithiSunrise == 14 && tithiSunriseNextDay == 16) {
				festDate = jd;
			} else if (tithiSunrise == 15) {
				festDate = jd;
			}

			if (festDate != -1) {
				return new FestivalDetail(festName + " " + constants.purnimaVrat, jd,
						EnumContainer.FestType.PURNIMA_VRAT, null);
			}

		}
		return null;
	}

	FestivalDetail calculateAmavsya() {
		if (tithiSunrise == 29 || tithiSunrise == 30) {

			String festName = hindiMonth[moonMonth - 1];
			if (dhikMonth == 1) {
				festName = festName + "( Adik)";
			}
			if (tithiSunrise == 29 && tithiSunriseNextDay == 1) {
				return new FestivalDetail(festName + " " + constants.amavasya, jd, EnumContainer.FestType.AMAVSYA,
						null);
			} else if (tithiSunrise == 30) {
				return new FestivalDetail(festName + " " + constants.amavasya, jd, EnumContainer.FestType.AMAVSYA,
						null);
			}

		}
		return null;
	}

	FestivalDetail calculateSankashtiChaturthi() {
		int t21, t22;
		if (tithiSunrise == 18 || tithiSunrise == 19) {
			MoonCalculation objCMoon = new MoonCalculation();
			double moonRise = objCMoon.getMoonRiseSetTime(jd)[0];
			double moonRiseND = objCMoon.getMoonRiseSetTime(jd + 1.0)[0];
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + moonRise / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + moonRiseND / 24.0);
			double festDate = -1;
			festDate = (t21 == 19) ? jd : jd + 1;
			System.out.println("festDate--" + festDate);
			if (festDate != -1) {
				return new FestivalDetail(constants.sankashtiChaturthi, festDate,
						EnumContainer.FestType.SANKSHTI_CHATURTHI, null);
			}
		}
		return null;
	}

	FestivalDetail calculateMashikShivRatri() {
		int t12, t21, t22;
		if (tithiSunrise == 28 || tithiSunrise == 29) {
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[7] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND15[8] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND15[8] / 24.0);
			double festDate = -1;
			if (t12 != 29 && t21 != 29) {
				festDate = jd + 1.0;
			} else if (t22 == 29) {
				festDate = jd + 1.0;
			} else {
				festDate = jd;
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.masikShivratri, festDate, EnumContainer.FestType.MASIK_SHIVRATRI,
						null);
			}
		}
		return null;
	}

	FestivalDetail calculatePradoshVrat() {
		int t23, t12, t21, t22;
		if (tithiSunrise == 12 || tithiSunrise == 13 || tithiSunrise == 27 || tithiSunrise == 28) {
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[0] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[1] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[0] / 24.0);
			t23 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[1] / 24.0);
			double festDate = -1;
			if (t12 == 13 || t21 == 13) {
				if (t22 == 13 && t23 == 13) {
					festDate = jd + 1.0;
				} else {
					festDate = jd;
				}
			} else if (t12 == 28 || t21 == 28) {
				if (t22 == 28 && t23 == 28) {
					festDate = jd + 1.0;

				} else {
					festDate = jd;
				}
			}
			if (festDate != -1) {
				return new FestivalDetail(constants.pradoshK, festDate, EnumContainer.FestType.PRADOSH_VRAT, null);
			}
		}
		return null;
	}

	FestivalDetail calculateSankrantiMonth() {
		if (sunMonth != preSunMonth) {
			int t10 = baseCalculationNew.panchangCalculation.solarMonth(jd - 0.0104);
			double festDate = (sunMonth == t10) ? jd - 1.0 : jd;

			return new FestivalDetail(rashi[sunMonth] + " " + constants.sankranti, festDate,
					EnumContainer.FestType.SANKRANTI, null);
		}
		return null;
	}

	FestivalDetail calculateNewYear() {
		return new FestivalDetail(constants.englishNewYear,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 1, 1), EnumContainer.FestType.FESTIVALS,
				"holidays/img_new_year.png");
	}

	FestivalDetail calculateBoseJayanti() {
		return new FestivalDetail(constants.subhasChandraBoseJayanti,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 1, 23), EnumContainer.FestType.FESTIVALS,
				"holidays/img_subhash_chandra_bose.png");
	}

	FestivalDetail calculateRepublicDay() {
		return new FestivalDetail(constants.republicDay,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 1, 26), EnumContainer.FestType.FESTIVALS,
				"holidays/img_republic_day.png");
	}

	FestivalDetail calculateBankHoliday() {
		return new FestivalDetail(constants.banksHoliday,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 4, 1), EnumContainer.FestType.FESTIVALS,
				"holidays/img_bank_holiday.png");
	}

	FestivalDetail calculateAmbedkarJayanti() {

		return new FestivalDetail(constants.ambedkarJayanti,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 4, 14), EnumContainer.FestType.FESTIVALS,
				"holidays/img_ambedkar.png");

	}

	FestivalDetail calculateIndependenceDay() {
		return new FestivalDetail(constants.independenceDay,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 8, 15), EnumContainer.FestType.FESTIVALS,
				"holidays/img_independence_day.png");
	}

	FestivalDetail calculateGandhiJayanti() {
		return new FestivalDetail(constants.gandhiJayanti,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 10, 2), EnumContainer.FestType.FESTIVALS,
				"holidays/img_ghandi.png");
	}

	FestivalDetail calculateChildrenDay() {
		return new FestivalDetail(constants.childrenDay,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 11, 14), EnumContainer.FestType.FESTIVALS,
				"holidays/img_baal_divas.png");
	}

	FestivalDetail calculateMerryChristmas() {
		return new FestivalDetail(constants.merryChristmas,
				baseCalculationNew.panchangCalculation.toJulian(selectedYear, 12, 25), EnumContainer.FestType.FESTIVALS,
				"holidays/img_christmas.png");
	}

	boolean paranaVart(double jd, double tithi) {
		double[] tithiParana = baseCalculationNew.panchangCalculation.tithi(jd);
		double sunSet = baseCalculationNew.panchangCalculation.getSunSet(jd);
		boolean boolVal;
		if ((int) tithi == (int) tithiParana[0]) {
			boolVal = (tithiParana[1] <= sunSet);
		} else if (tithiParana.length > 2 && (int) tithi == (int) tithiParana[2]) {
			boolVal = (tithiParana[3] <= sunSet);
		} else {
			boolVal = true;
		}
		return boolVal;
	}
}
