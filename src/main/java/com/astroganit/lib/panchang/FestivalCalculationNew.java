package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.astroganit.lib.panchang.model.FestivalDetail;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;
import com.astroganit.lib.panchang.util.EnumContainer;
import com.astroganit.lib.panchang.util.PanUtil;

public class FestivalCalculationNew extends PanchangBase {
	ArrayList<ArrayList<FestivalDetail>> festList;
	ArrayList<FestivalDetail> festivals;
	HashMap<String, FestivalDetail> hashMap;
	PanUtil panUtil = new PanUtil();
	Calendar calendar = (Calendar) Calendar.getInstance().clone();
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
	double[] sunSetND15;
	double[] sunSetNDND15;
	double[] sunSetNDPD15;

	public FestivalCalculationNew(int languageCode) {
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
		rashi = constants.getRashiList();
		ekadashiS = constants.getEkadashiS();
		ekadashiK = constants.getEkadashiK();
		hindiMonth = constants.getAdhikMasas();
	}

	public ArrayList<ArrayList<FestivalDetail>> getFestivalList(int currentYear, int languageCode) {
		System.out.println(currentYear);
		calendar.set(Calendar.YEAR, currentYear);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		festivals = new ArrayList<>();
		festList = new ArrayList<>();
		hashMap = new HashMap<String, FestivalDetail>();
		for (int month = 0; month < 12; month++) {
			getMonthlyFestivals(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		festivals = new ArrayList<>(hashMap.values());
		festList.add(festivals);
		return festList;
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
			sunSetND15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15);
			sunSetNDPD15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd - 1.0, sunSetPreDay, 15);
			sunSetNDND15 = baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 15);

			int[] x = baseCalculationNew.panchangCalculation.masa(jd);
			int[] x1 = baseCalculationNew.panchangCalculation.masa(jd - 1.0);
			if (x[1] != 1) {
				moonMonth = x[0];
			} else {
				moonMonth = 13;
			}
			if (x1[1] != 1) {
				moonMonthPrevDay = x1[0];
			} else {
				moonMonthPrevDay = 13;
			}

			FestivalDetail festivalDetail = calculateRakshaBandhan();

			if (festivalDetail != null) {
				hashMap.put("onam", festivalDetail);
			}

			calendar.add(Calendar.DATE, 1);
		}
		return festivals;
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
			System.out.println("JD-" + convertJDtoDate(jd) + " t10-" + t10 + " t11-" + t11 + " t12-" + t12 + " t13-"
					+ t13 + " sunRiseDDPD5[2]-" + sunRiseDDPD5[2] + " sr-" + sunRisePreDay);

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
			System.out.println("JD-" + convertJDtoDate(jd) + " t10-" + t10 + " t11-" + t11 + " t12-" + t12 + " t13-"
					+ t13 + " sunRiseDDPD5[2]-" + sunRiseDDPD5[2] + " sr-" + sunRisePreDay);

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

	FestivalDetail calculateMahaShivRashri() {
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
			System.out.println(" jd-" + convertJDtoDate(jd) + " tithiSunrise-" + tithiSunrise + " tithiSunriseNextDay-"
					+ tithiSunriseNextDay);

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
			System.out.println("Jd-" + convertJDtoDate(jd) + " tithiSunrise-" + tithiSunrise + " tithiSunriseNextDay-"
					+ tithiSunriseNextDay);
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
			System.out.println("jd-" + convertJDtoDate(jd) + " t10-" + t10 + " t12-" + t12 + " t21-" + t21);
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

	FestivalDetail calculateChaitraNavratriGhatasthapana(int moonMonth, int moonMonthPrevDay, int tithiSunrise) {
		if (moonMonth == 1 && moonMonthPrevDay != 1) {
			if (tithiSunrise == 2) {
				return new FestivalDetail(constants.chaitraNavratriGhatasthapana, jd - 1.0);
			}
			return new FestivalDetail(constants.chaitraNavratriGhatasthapana, jd);
		}
		return null;
	}

	FestivalDetail calculateKajariTeej() {
		if (moonMonth == 5) {
			if (tithiSunrise == 17 && tithiSunriseNextDay == 19) {
				return new FestivalDetail(constants.kajariTeej, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_kajri_teej.png");
			} else if (tithiSunrise == 18) {
				if (tithiSunriseNextDay == 18) {
					return new FestivalDetail(constants.kajariTeej, jd + 1.0, EnumContainer.FestType.FESTIVALS,
							"holidays/img_kajri_teej.png");
				} else {
					return new FestivalDetail(constants.kajariTeej, jd, EnumContainer.FestType.FESTIVALS,
							"holidays/img_kajri_teej.png");
				}
			}
		}

		return null;
	}

	FestivalDetail calculateJanmashtami() {
		int t10, t12, t21, t22;
		if (moonMonth == 5 && (tithiSunrise == 22 || tithiSunrise == 23)) {
			t10 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[7] / 24.0);
			t12 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[8] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD((jd + 1.0)
					+ baseCalculationNew.muhuratCalculation.getNightDivisons((jd + 1.0), sunSetNextDay, 15)[7] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD((jd + 1.0)
					+ baseCalculationNew.muhuratCalculation.getNightDivisons((jd + 1.0), sunSetNextDay, 15)[8] / 24.0);
			if ((t10 == 23 || t12 == 23) && (t21 == 23 || t22 == 23)) {
				return new FestivalDetail(constants.janmashtami, jd + 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_janmastmi.png");
			} else if ((t10 == 23 || t12 == 23) && t21 != 23) {
				return new FestivalDetail(constants.janmashtami, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_janmastmi.png");
			}
		}
		return null;
	}

	FestivalDetail calculateAnantChaturdashi() {
		int t10, t12;
		if (moonMonth == 6) {
			if (tithiSunrise == 13 || tithiSunrise == 14) {
				t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD15[2] / 24.0);
				t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND15[2] / 24.0);
				if (t10 == 14) {
					return new FestivalDetail(constants.anantChaturdashi, jd, EnumContainer.FestType.FESTIVALS,
							"holidays/img_anant_chaturdashi.png");
				} else if (tithiSunrise == 13 && t12 != 14) {
					return new FestivalDetail(constants.anantChaturdashi, jd, EnumContainer.FestType.FESTIVALS,
							"holidays/img_anant_chaturdashi.png");
				} else if (tithiSunrise == 13 && tithiSunriseNextDay == 15) {
					return new FestivalDetail(constants.anantChaturdashi, jd, EnumContainer.FestType.FESTIVALS,
							"holidays/img_anant_chaturdashi.png");
				}
			}
		}
		return null;

	}

	FestivalDetail calculateHartalikaTeej() {
		if (moonMonth == 6) {
			if (tithiSunrise == 2) {
				if (tithiSunriseNextDay == 4) {
					return new FestivalDetail(constants.hartalikaTeej, jd, EnumContainer.FestType.FESTIVALS,
							"holidays/img_hartalika_teej.png");
				}
			} else if (tithiSunrise == 3) {
				if (tithiSunriseNextDay == 3) {
					return new FestivalDetail(constants.hartalikaTeej, jd + 1.0, EnumContainer.FestType.FESTIVALS,
							"holidays/img_hartalika_teej.png");
				} else {
					return new FestivalDetail(constants.hartalikaTeej, jd, EnumContainer.FestType.FESTIVALS,
							"holidays/img_hartalika_teej.png");
				}
			}

		}
		return null;
	}

	FestivalDetail calculateGaneshChaturthi() {
		int t10, t12, t21, t22;
		if (moonMonth == 6) {
			if (tithiSunrise == 3 || tithiSunrise == 4) {
				t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[2] / 24.0);
				t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[3] / 24.0);
				t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[2] / 24.0);
				t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[3] / 24.0);
				double festDate = -1;
				if ((t10 == 4 && t12 == 4) || (t12 == 4 && t21 != 4)) {
					festDate = jd; // Festival today
				} else if ((t21 == 4 && t12 != 4) || (t10 != 4 && t12 == 4 && t21 == 4 && t22 == 4)) {
					festDate = jd + 1.0; // Festival next day
				} else if (t10 == 4 && t12 == 4 && t21 == 4 && t22 == 4) {
					festDate = jd; // Could also be jd+1, depends on your rule
				}
				if (festDate != -1) {
					return new FestivalDetail(constants.ganeshChaturthi, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_ganesh_chaturthi.png");
				}
			}
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
		/*
		 * if (moonMonth == 7) { if (tithiSunrise == 9 || tithiSunrise == 10) {
		 * 
		 * t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunRiseDD5[3] /
		 * 24.0); t12 = baseCalculationNew.panchangCalculation.getTithiD(jd +
		 * sunRiseDD5[4] / 24.0); t21 =
		 * baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunRiseDDND5[1] /
		 * 24.0); t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 +
		 * sunRiseDDND5[3] / 24.0); boolean isSravanNak = false; if
		 * (nakshatraSunRiseNextDay == 22 && (nakshatraSunRiseNextDayET >
		 * sunRiseDDND5[3])) { isSravanNak = true; } System.out.println(jd + ":" +
		 * convertJDtoDate(jd) + " t10:" + t10 + " t12:" + t12 + " t21:" + t21 + " t22:"
		 * + t22 + " isSravanNak:" + isSravanNak + " tithiSunrise:" + tithiSunrise +
		 * " tithiSunriseNextDay:" + tithiSunriseNextDay); double festDate = -1; if
		 * (tithiSunrise == 9 && tithiSunriseNextDay == 11) { festDate = jd; } else if
		 * (t10 == 10 && t21 != 10) { festDate = jd; } else if (t10 == 10 && t21 == 10
		 * && isSravanNak) { festDate = jd + 1.0; } else if (t10 == 10 && t21 == 10 &&
		 * !isSravanNak) { festDate = jd; } else if (t12 == 10 && t22 != 10) { festDate
		 * = jd; } else if (t12 == 10 && t22 == 10 && isSravanNak) { festDate = jd +
		 * 1.0; } if (festDate != -1) { return new
		 * FestivalDetail(constants.vijayaDashami, festDate,
		 * EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"); } } }
		 */

		if (moonMonth == 7 && (tithiSunrise == 9 || tithiSunrise == 10)) {
			System.out.println(jd + ":" + convertJDtoDate(jd));
			t10 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 5)[3] / 24.0);
			t12 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getDayDivisons(jd, sunRise, 5)[4] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0
					+ baseCalculationNew.muhuratCalculation.getDayDivisons(jd + 1.0, sunRiseNextDay, 5)[3] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0
					+ baseCalculationNew.muhuratCalculation.getDayDivisons(jd + 1.0, sunRiseNextDay, 5)[1] / 24.0);
			boolean isSravanNak = false;
			if (tithiSunriseNextDay == 10 && nakshatraSunRiseNextDay == 22) {
				double nakET = baseCalculationNew.panchangCalculation.nakshatra(jd + 1.0)[1];
				double aprhanT = baseCalculationNew.muhuratCalculation.getDayDivisons(jd + 1.0, sunRiseNextDay, 5)[3];
				if (nakET > aprhanT) {
					isSravanNak = true;
				}
			}

			if (t10 != 10 && t21 != 10 && isSravanNak) {
				return new FestivalDetail(constants.vijayaDashami, jd + 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t10 == 10 && t21 != 10 && isSravanNak) {
				return new FestivalDetail(constants.vijayaDashami, jd + 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t10 == 10 && t21 != 10 && !isSravanNak) {
				return new FestivalDetail(constants.vijayaDashami, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t12 != 10 && t21 == 10 && isSravanNak) {
				return new FestivalDetail(constants.vijayaDashami, jd + 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t12 == 10 && t22 == 10 && nakshatraSunRiseNextDay == 22) {
				return new FestivalDetail(constants.vijayaDashami, jd + 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t10 == 10 && t21 == 10) {
				return new FestivalDetail(constants.vijayaDashami, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t10 != 10 && t21 == 10) {
				return new FestivalDetail(constants.vijayaDashami, jd + 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t10 == 10 && t21 != 10) {
				return new FestivalDetail(constants.vijayaDashami, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			} else if (t10 != 10 && t21 != 10) {
				return new FestivalDetail(constants.vijayaDashami, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_dushera.png");
			}
		}

		return null;
	}

	FestivalDetail calculateKarvChauth() {
		int t10;
		if (moonMonth == 7) {
			if (tithiSunrise == 18 || tithiSunrise == 19) {
				MoonCalculation objCMoon = new MoonCalculation();
				double[] moonRiseTime = objCMoon.getMoonRiseSetTime(jd);
				double[] moonRiseTimeNextDay = objCMoon.getMoonRiseSetTime(jd + 1.0);
				t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + moonRiseTime[0] / 24.0);
				// t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 +
				// moonRiseTimeNextDay[0] / 24.0);
				double festDate = (t10 == 19) ? jd : jd + 1.0;
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
			if (t10 == 28 || t12 == 28) {
				{
					double festDate = (t21 != 28 && t22 != 28) ? jd : jd + 1.0;
					return new FestivalDetail(constants.dhanteras, festDate, EnumContainer.FestType.FESTIVALS,
							"holidays/img_dhan_teras.png");
				}
			}

		}
		return null;
	}

	/*
	 * FestivalDetail calculateDiwaliPoojaCalendar() { int t10, t12, t21, t22;
	 * 
	 * if (moonMonth == 7 && (tithiSunrise == 27 || tithiSunrise == 28)) { t10 =
	 * baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[0] / 24.0);
	 * t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[1] /
	 * 24.0); t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 +
	 * sinSetNDND5[0] / 24.0); t22 =
	 * baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sinSetNDND5[1] /
	 * 24.0);
	 * 
	 * if (t10 == 28 || t12 == 28) { { double festDate = (t21 != 28 && t22 != 28) ?
	 * jd : jd + 1.0; return new FestivalDetail(constants.diwaliPujaCalendar,
	 * festDate); } } } return null; }
	 */

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
		int t10, t12, t21, t22;
		if (moonMonth == 7 && (tithiSunrise == 29 || tithiSunrise == 30)) {
			System.out.println(convertJDtoDate(jd));
			t10 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[0] / 24.0);
			t12 = baseCalculationNew.panchangCalculation.getTithiD(jd + sunSetND5[1] / 24.0);
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[0] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0 + sunSetNDND5[1] / 24.0);
			boolean noAmavasyaTomorrow = (t21 != 30 && t22 != 30);
			boolean noAmavasyaToday = (t10 != 30 && t12 != 30);

			double festDate;

			if (noAmavasyaTomorrow) {
				if (noAmavasyaToday) {
					if (tithiSunrise == 30 || (tithiSunrise == 29 && tithiSunriseNextDay == 1)) {
						festDate = jd;
					} else {
						festDate = jd;
					}
				} else {
					festDate = jd;
				}
			} else {
				festDate = jd + 1.0;
			}

			return new FestivalDetail(constants.diwali, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_diwali.png");
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
			System.out.println(convertJDtoDate(jd));
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

	FestivalDetail calculateOnam() {
		if ((sunMonth == 5 && datSunMonth == 5) && (nakshatraSunRise == 21 || nakshatraSunRise == 22)) {
			System.out.println("jd-" + convertJDtoDate(jd) + " sunMonth-" + sunMonth + " datSunMonth-" + datSunMonth
					+ "nakshatraSunRise-" + nakshatraSunRise + " nakshatraSunRiseNextDay-" + nakshatraSunRiseNextDay);

			if (nakshatraSunRise == 21 && nakshatraSunRiseNextDay == 23) {
				return new FestivalDetail(constants.onam, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_onam.png");
			} else if (nakshatraSunRise == 22 && nakshatraSunRiseNextDay == 22) {
				return new FestivalDetail(constants.onam, jd + 1.0, EnumContainer.FestType.FESTIVALS,
						"holidays/img_onam.png");
			} else if (nakshatraSunRise == 22) {
				return new FestivalDetail(constants.onam, jd, EnumContainer.FestType.FESTIVALS,
						"holidays/img_onam.png");
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

	FestivalDetail calculatePongal(int sunMonth, int preSunMonth) {
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
			double festDate = (baseCalculationNew.panchangCalculation.solarMonth(sunriseJD - 0.67) == 10) ? jd - 1.0
					: jd;
			return new FestivalDetail(constants.lohri, festDate, EnumContainer.FestType.FESTIVALS,
					"holidays/img_lohri.png");
		}
		return null;
	}

	FestivalDetail calculateEkadashi() {
		if (tithiSunrise == 10 || tithiSunrise == 11) {
			if (tithiSunrise == 10 && tithiSunriseNextDay == 12) {
				return new FestivalDetail(ekadashiS[moonMonth - 1 % 12], jd, EnumContainer.FestType.EKADASHI_VRAT,
						null);
			} else if (tithiSunrise == 11) {
				return new FestivalDetail(ekadashiS[moonMonth - 1 % 12], jd, EnumContainer.FestType.EKADASHI_VRAT,
						null);
			}
		}

		/*
		 * if (tithiSunrise == 11) { return new FestivalDetail(ekadashiS[moonMonth - 1 %
		 * 12], jd, EnumContainer.FestType.EKADASHI_VRAT, null); } if (tithiSunrise ==
		 * 26) { return new FestivalDetail(ekadashiK[moonMonth - 1 % 12], jd,
		 * EnumContainer.FestType.EKADASHI_VRAT, null); }
		 */
		return null;
	}

	FestivalDetail calculatePurnima() {
		if (tithiSunrise == 14 || tithiSunrise == 15) {

			String festName = hindiMonth[moonMonth - 1];
			if (tithiSunrise == 14 && tithiSunrise == 1) {
				return new FestivalDetail(festName + " " + constants.purnimaVrat, jd,
						EnumContainer.FestType.PURNIMA_VRAT, null);
			} else if (tithiSunrise == 15) {
				return new FestivalDetail(festName + " " + constants.purnimaVrat, jd,
						EnumContainer.FestType.PURNIMA_VRAT, null);
			}
			/*
			 * if (tithiSunrise == 15 || tithiSunriseNextDay == 16) { return new
			 * FestivalDetail(festName + " " + constants.purnimaVrat, jd,
			 * EnumContainer.FestType.PURNIMA_VRAT, null);
			 * 
			 * }
			 */
		}
		return null;
	}

	FestivalDetail calculateAmavsya(int tithiSunrise, int tithiSunriseNextDay, int moonMonth) {
		if (tithiSunrise == 29 || tithiSunrise == 30) {
			String festName = hindiMonth[moonMonth % 12];
			if (tithiSunrise == 29 && tithiSunrise == 1) {
				return new FestivalDetail(festName + " " + constants.amavasya, jd, EnumContainer.FestType.AMAVSYA,
						null);
			} else if (tithiSunrise == 30) {
				return new FestivalDetail(festName + " " + constants.amavasya, jd, EnumContainer.FestType.AMAVSYA,
						null);
			}
			/*
			 * if (tithiSunrise == 30 || tithiSunriseNextDay == 1) { return new
			 * FestivalDetail(festName + " " + constants.amavasya, jd,
			 * EnumContainer.FestType.AMAVSYA, null); }
			 */
		}
		return null;
	}

	FestivalDetail calculateSankashtiChaturthi(int tithiSunrise, int tithiSunriseNextDay, int moonMonth) {
		int t21;
		int t22;
		if (tithiSunrise == 18 || tithiSunrise == 19) {
			MoonCalculation objCMoon = new MoonCalculation();
			t21 = baseCalculationNew.panchangCalculation.getTithiD(jd + objCMoon.getMoonRiseSetTime(jd)[0] / 24.0);
			t22 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + 1.0 + objCMoon.getMoonRiseSetTime(jd + 1.0)[0] / 24.0);
			if (t21 == 19 && t22 == 19) {
				return new FestivalDetail(constants.sankashtiChaturthi, jd, EnumContainer.FestType.SANKSHTI_CHATURTHI,
						null);
			} else if (t21 == 19 && t22 != 19) {
				return new FestivalDetail(constants.sankashtiChaturthi, jd, EnumContainer.FestType.SANKSHTI_CHATURTHI,
						null);
			} else if (t21 != 19 && t22 == 19) {
				return new FestivalDetail(constants.sankashtiChaturthi, jd + 1,
						EnumContainer.FestType.SANKSHTI_CHATURTHI, null);
			} else {
				return new FestivalDetail(constants.sankashtiChaturthi, jd + 1,
						EnumContainer.FestType.SANKSHTI_CHATURTHI, null);
			}
		}
		return null;
	}

	FestivalDetail calculateMashikShivRatri(int tithiSunrise, int tithiSunriseNextDay, double sunSet,
			double sunSetNextDay, int moonMonth) {
		int t12, t21, t22;
		if (tithiSunrise == 28 || tithiSunrise == 29) {
			t12 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[7] / 24.0);
			t21 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 15)[8] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0
					+ baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 15)[8] / 24.0);
			if (t12 != 29 && t21 != 29) {
				return new FestivalDetail(constants.masikShivratri, jd + 1.0, EnumContainer.FestType.MASIK_SHIVRATRI,
						null);

			} else if (t22 == 29) {
				return new FestivalDetail(constants.masikShivratri, jd + 1.0, EnumContainer.FestType.MASIK_SHIVRATRI,
						null);
			} else {
				return new FestivalDetail(constants.masikShivratri, jd, EnumContainer.FestType.MASIK_SHIVRATRI, null);
			}
		}
		return null;
	}

	FestivalDetail calculatePradoshVratS(int tithiSunrise, int tithiSunriseNextDay, double sunSet, double sunSetNextDay,
			int moonMonth) {
		int t23, t12, t21, t22;
		if (tithiSunrise == 12 || tithiSunrise == 13) {
			t12 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 5)[0] / 24.0);
			t21 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 5)[1] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0
					+ baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 5)[0] / 24.0);
			t23 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0
					+ baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 5)[1] / 24.0);
			if (t12 == 13 || t21 == 13) {
				if (t22 == 13 && t23 == 13) {
					return new FestivalDetail(constants.pradoshS, jd + 1.0, EnumContainer.FestType.PRADOSH_VRAT, null);
				} else {
					return new FestivalDetail(constants.pradoshS, jd, EnumContainer.FestType.PRADOSH_VRAT, null);
				}
			}
		}
		return null;
	}

	FestivalDetail calculatePradoshVratK(int tithiSunrise, int tithiSunriseNextDay, double sunSet, double sunSetNextDay,
			int moonMonth) {
		int t12, t21, t22, t23;
		if (tithiSunrise == 27 || tithiSunrise == 28) {
			t12 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 5)[0] / 24.0);
			t21 = baseCalculationNew.panchangCalculation
					.getTithiD(jd + baseCalculationNew.muhuratCalculation.getNightDivisons(jd, sunSet, 5)[1] / 24.0);
			t22 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0
					+ baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 5)[0] / 24.0);
			t23 = baseCalculationNew.panchangCalculation.getTithiD(jd + 1.0
					+ baseCalculationNew.muhuratCalculation.getNightDivisons(jd + 1.0, sunSetNextDay, 5)[1] / 24.0);
			if (t12 == 28 || t21 == 28) {
				if (t22 == 28 && t23 == 28) {
					return new FestivalDetail(constants.pradoshK, jd + 1.0, EnumContainer.FestType.PRADOSH_VRAT, null);
				} else {
					return new FestivalDetail(constants.pradoshK, jd, EnumContainer.FestType.PRADOSH_VRAT, null);
				}
			}
		}
		return null;
	}

	FestivalDetail calculateNewYear() {
		if (calendar.get(Calendar.MONTH) == 0 && calendar.get(Calendar.DATE) == 1) {
			return new FestivalDetail(constants.englishNewYear,
					baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR), 1, 1),
					EnumContainer.FestType.FESTIVALS, "holidays/img_new_year.png");
		}
		return null;

	}

	FestivalDetail calculateBoseJayanti() {
		if (calendar.get(Calendar.MONTH) == 0 && calendar.get(Calendar.DATE) == 23) {
			return new FestivalDetail(constants.subhasChandraBoseJayanti,
					baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR), 1, 23),
					EnumContainer.FestType.FESTIVALS, "holidays/img_subhash_chandra_bose.png");
		}
		return null;

	}

	FestivalDetail calculateRepublicDay() {
		if (calendar.get(Calendar.MONTH) == 0 && calendar.get(Calendar.DATE) == 26) {
			return new FestivalDetail(constants.republicDay,
					baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR), 1, 26),
					EnumContainer.FestType.FESTIVALS, "holidays/img_republic_day.png");
		}
		return null;

	}

}
