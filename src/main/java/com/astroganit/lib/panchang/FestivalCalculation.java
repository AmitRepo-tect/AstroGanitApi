package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.astroganit.lib.panchang.model.FestivalDetail;
import com.astroganit.lib.panchang.util.ConstantsEn;
import com.astroganit.lib.panchang.util.ConstantsHi;
import com.astroganit.lib.panchang.util.CustomCamparator;
import com.astroganit.lib.panchang.util.EnumContainer;
import com.astroganit.lib.panchang.util.PanUtil;

public class FestivalCalculation extends PanchangBase {
	PanUtil panUtil = new PanUtil();
	protected int arraySize = 0;
	protected int year = 0;

	// protected var place: Place? = null
	double jd = 0.0;
	int[] sun_month;
	int[] moon_month;
	int[] tithi_sunrise;
	int[] nakshatram_sunrise;

	// var festivalDayMapJD: HashMap<String, Double> = HashMap()
	HashMap<String, FestivalDetail> festivalDetailMap = new HashMap();
	String[] rashi ;
	String[] ekadashiK ;
	String[] ekadashiS ;
	String[] hindiMonth ;
	ArrayList<FestivalDetail> festDetails = new ArrayList<FestivalDetail>();

	public FestivalCalculation(int languageCode) {
		if (languageCode == 1) {
			constants = new ConstantsEn();
		} else {
			constants = new ConstantsHi();
		}
		rashi = constants.getRashiList();
		ekadashiK = constants.getEkadashiK();
		ekadashiS = constants.getEkadashiS();
		hindiMonth = constants.getAdhikMasas();
	}

	ArrayList<FestivalDetail> festivalsInYear(int yearOfPanchang) {
		year = yearOfPanchang;
		arraySize = 368;
		sun_month = new int[arraySize];
		moon_month = new int[arraySize];
		tithi_sunrise = new int[arraySize];
		nakshatram_sunrise = new int[arraySize];
		jd = baseCalculationNew.panchangCalculation.toJulian(year - 1, 12, 31);
		this.assignArrays(jd);
		return this.festivalsFindMap();
	}

	void assignArrays(double jdP) {
		for (int i = 0; i < arraySize; i++) {
			double sunRiseJd = baseCalculationNew.panchangCalculation.sunriseJd(jdP + (double) i);
			sun_month[i] = baseCalculationNew.panchangCalculation.solarMonth(sunRiseJd);
			tithi_sunrise[i] = baseCalculationNew.panchangCalculation.getTithiD(sunRiseJd);
			nakshatram_sunrise[i] = (int) baseCalculationNew.panchangCalculation.nakshatra(jdP + (double) i)[0];
			int[] x = baseCalculationNew.panchangCalculation.masa(jdP + (double) i);
			if (x[1] != 1) {
				moon_month[i] = x[0];
			} else {
				moon_month[i] = 13;
			}
		}
	}

	ArrayList<FestivalDetail> festivalsFindMap() {
		LinkedHashMap<String, int[]> festival_day_list = new LinkedHashMap<String, int[]>();
		int sankrantiMonth = 10;
		int countSawanPVrat = 0;
		int countSawanAVrat = 0;

		for (int i = 1; i < arraySize - 1; i++) {
			int intTithiSunrise = tithi_sunrise[i];
			int intTithiSunriseNextDay = tithi_sunrise[i + 1];
			int intTithiSunrisePrevDay = tithi_sunrise[i - 1];
			int intSunMonth = sun_month[i];
			int intSunMonthPrevDay = sun_month[i - 1];
			int intNakshatraSunRise = nakshatram_sunrise[i];
			int intNakshatraSunRiseNextDay = nakshatram_sunrise[i + 1];
			int intMoonMonth = moon_month[i];
			int intMoonMonthPrevDay = moon_month[i - 1];
			double doubleDateJd = jd + (double) i;
			double doubleSunRise = baseCalculationNew.panchangCalculation.getSunRise(doubleDateJd);
			double doubleSunRiseNextDay = baseCalculationNew.panchangCalculation.getSunRise(doubleDateJd + 1.0);
			double doubleSunSet = baseCalculationNew.panchangCalculation.getSunSet(doubleDateJd);
			double doubleSunSetNextDay = baseCalculationNew.panchangCalculation.getSunSet(doubleDateJd + 1.0);
			int intWeekDay = baseCalculationNew.panchangCalculation.vaara(doubleDateJd);
			int t_10;
			if (intSunMonth == 1 && intSunMonthPrevDay == 12) {
				t_10 = baseCalculationNew.panchangCalculation.solarMonth(doubleDateJd - 0.0104);
				if (t_10 == 1) {
					addFestivalToList("baisakhi", new FestivalDetail(constants.baisakhi, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_baishakhi.png"));
				} else {
					addFestivalToList("baisakhi", new FestivalDetail(constants.baisakhi, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_baishakhi.png"));
				}
			}
			if ((intSunMonth == 5) && (sun_month[i + 2] == 5)
					&& (intNakshatraSunRise == 21 || intNakshatraSunRise == 22)) {
				if (intNakshatraSunRise == 21 && intNakshatraSunRiseNextDay == 22) {
					addFestivalToList("onam", new FestivalDetail(constants.onam, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_onam.png"));
				} else if (intNakshatraSunRise == 21 && intNakshatraSunRiseNextDay == 23) {
					addFestivalToList("onam", new FestivalDetail(constants.onam, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_onam.png"));
				} else if (intNakshatraSunRise == 22) {
					addFestivalToList("onam", new FestivalDetail(constants.onam, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_onam.png"));
				}
			}
			int sMonth;
			if (sankrantiMonth != 1)
				sMonth = sankrantiMonth - 1;
			else
				sMonth = 12;
			if (intSunMonth == sankrantiMonth && intSunMonthPrevDay == sMonth) {
				if (sankrantiMonth != 4 && sankrantiMonth != 10) {
					t_10 = baseCalculationNew.panchangCalculation.solarMonth(doubleDateJd - 0.0104);
					if (t_10 == sankrantiMonth) {
						addFestivalToList("sankranti_$sankrantiMonth",
								new FestivalDetail(rashi[sankrantiMonth] + " " + constants.sankranti,
										doubleDateJd - 1.0, EnumContainer.FestType.SANKRANTI, null));
					} else {
						addFestivalToList("sankranti_$sankrantiMonth",
								new FestivalDetail(rashi[sankrantiMonth] + " " + constants.sankranti, doubleDateJd,
										EnumContainer.FestType.SANKRANTI, null));
					}
				}
				++sankrantiMonth;
				if (sankrantiMonth == 13) {
					sankrantiMonth = 1;
				}
			}
			if (intSunMonth == 10 && intSunMonthPrevDay == 9) {
				t_10 = baseCalculationNew.panchangCalculation
						.solarMonth(baseCalculationNew.panchangCalculation.sunriseJd(doubleDateJd) - 0.67);
				if (t_10 == 10) {
					addFestivalToList("uttarayanam", new FestivalDetail(constants.uttarayanam, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, null));
					/*
					 * addFestivalToList( "makar_sankranti", FestivalDetail(
					 * context.resources.getString(R.string.makar_sankranti), doubleDateJd - 1.0, 1
					 * ) )
					 */
					addFestivalToList("pongal", new FestivalDetail(constants.pongal, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_pongal.png"));
					addFestivalToList("sankranti_10",
							new FestivalDetail(rashi[10] + " " + constants.sankranti, doubleDateJd - 1.0,
									EnumContainer.FestType.FEST_SANKRANTI, "holidays/img_makar_sankranti.png"));
					addFestivalToList("lohri", new FestivalDetail(constants.lohri, doubleDateJd - 2.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_lohri.png"));

				} else {
					addFestivalToList("uttarayanam", new FestivalDetail(constants.uttarayanam, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, null));
					/*
					 * addFestivalToList( "makar_sankranti", FestivalDetail(
					 * context.resources.getString(R.string.makar_sankranti), doubleDateJd, 1 ) )
					 */
					addFestivalToList("pongal", new FestivalDetail(constants.pongal, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_pongal.png"));
					addFestivalToList("sankranti_10", new FestivalDetail(rashi[10] + " " + constants.sankranti,
							doubleDateJd, EnumContainer.FestType.FEST_SANKRANTI, "holidays/img_makar_sankranti.png"));
					addFestivalToList("lohri", new FestivalDetail(constants.lohri, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_lohri.png"));

				}
			}
			if (intSunMonth == 4 && intSunMonthPrevDay == 3) {
				addFestivalToList("sankranti_4", new FestivalDetail(rashi[4] + " " + constants.sankranti,
						doubleDateJd - 1.0, EnumContainer.FestType.SANKRANTI, null));
			}
			String key;
			if (intTithiSunrise == 11 || intTithiSunrise == 12) {
				key = "ekadashi_1_$intMoonMonth";
				if (intTithiSunriseNextDay == 11) {
					putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth - 1, ekadashiS,
							EnumContainer.FestType.EKADASHI_VRAT);
				} else if (intTithiSunrise == 11) {
					putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth - 1, ekadashiS,
							EnumContainer.FestType.EKADASHI_VRAT);
				} else if (intTithiSunrise == 12) {
					putKeyInMapCheckContainNew(key, doubleDateJd - 1.0, intMoonMonth - 1, ekadashiS,
							EnumContainer.FestType.EKADASHI_VRAT);
				}
				if (key == "ekadashi_1_4" && festivalDetailMap.get(key) != null) {
					putKeyInMapCheckContainNew("ashadhi_ekadashi", doubleDateJd, intMoonMonth,
							constants.ashadhiEkadashi, EnumContainer.FestType.OTHER);
				}
			}
			if (intTithiSunrise == 26 || intTithiSunrise == 27) {
				key = "ekadashi_2_$intMoonMonth";
				if (intTithiSunriseNextDay == 26) {
					putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth, ekadashiK,
							EnumContainer.FestType.EKADASHI_VRAT);
				} else if (intTithiSunrise == 26) {
					putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth, ekadashiK,
							EnumContainer.FestType.EKADASHI_VRAT);
				} else if (intTithiSunrise == 27) {
					putKeyInMapCheckContainNew(key, doubleDateJd - 1.0, intMoonMonth, ekadashiK,
							EnumContainer.FestType.EKADASHI_VRAT);
				}
			}
			if (intTithiSunrise == 14 || intTithiSunrise == 15) {
				key = "purnima_$intMoonMonth";
				String festName = hindiMonth[intMoonMonth - 1];

				if (intTithiSunrise == 15) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth,
							festName + " " + constants.purnimaVrat, EnumContainer.FestType.PURNIMA_VRAT);
				} else if (intTithiSunriseNextDay == 15) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth,
							festName + " " + constants.purnimaVrat, EnumContainer.FestType.PURNIMA_VRAT);
				} else if (intTithiSunriseNextDay == 16) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth,
							festName + " " + constants.purnimaVrat, EnumContainer.FestType.PURNIMA_VRAT);
				}
			}
			if (intTithiSunrise == 29 || intTithiSunrise == 30) {
				key = "amavasya_$intMoonMonth";
				String festName = hindiMonth[intMoonMonth % 12];

				if (intTithiSunrise == 30) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth,
							festName + " " + constants.amavasya, EnumContainer.FestType.AMAVSYA);
				} else if (intTithiSunriseNextDay == 30) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth,
							festName + " " + constants.amavasya, EnumContainer.FestType.AMAVSYA);
				} else if (intTithiSunriseNextDay == 1) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth,
							festName + " " + constants.amavasya, EnumContainer.FestType.AMAVSYA);
				}
			}
			int t_21;
			int t_22;
			if (intTithiSunrise == 18 || intTithiSunrise == 19) {
				key = "sankashti_chaturthi_$intMoonMonth";
				MoonCalculation objCMoon = new MoonCalculation();
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + objCMoon.getMoonRiseSetTime(doubleDateJd)[0] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + objCMoon.getMoonRiseSetTime(doubleDateJd + 1.0)[0] / 24.0);
				if (t_21 == 19 && t_22 == 19) {
					putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth, constants.sankashtiChaturthi,
							EnumContainer.FestType.SANKSHTI_CHATURTHI);
				} else if (t_21 == 19 && t_22 != 19) {
					putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth, constants.sankashtiChaturthi,
							EnumContainer.FestType.SANKSHTI_CHATURTHI);
				} else if (t_21 != 19 && t_22 == 19) {
					putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth, constants.sankashtiChaturthi,
							EnumContainer.FestType.SANKSHTI_CHATURTHI);
				} else {
					putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth, constants.sankashtiChaturthi,
							EnumContainer.FestType.SANKSHTI_CHATURTHI);
				}
			}
			int t_12;
			if (intTithiSunrise == 28 || intTithiSunrise == 29) {
				key = "masik_shivratri_$intMoonMonth";
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[7]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[8]
								/ 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 15)[8] / 24.0);
				if (t_12 != 29 && t_21 != 29) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth, constants.masikShivratri,
							EnumContainer.FestType.MASIK_SHIVRATRI);
				} else if (t_22 == 29) {
					this.putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth, constants.masikShivratri,
							EnumContainer.FestType.MASIK_SHIVRATRI);
				} else {
					this.putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth, constants.masikShivratri,
							EnumContainer.FestType.MASIK_SHIVRATRI);
				}
			}
			int t_23;
			if (intTithiSunrise == 12 || intTithiSunrise == 13) {
				key = "pradosh_1_$intMoonMonth";
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[0]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[1]
								/ 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[0] / 24.0);
				t_23 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[1] / 24.0);
				if (t_12 == 13 || t_21 == 13) {
					if (t_22 == 13 && t_23 == 13) {
						this.putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth, constants.pradoshS,
								EnumContainer.FestType.PRADOSH_VRAT);
					} else {
						this.putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth, constants.pradoshS,
								EnumContainer.FestType.PRADOSH_VRAT);
					}
				}
			}
			if (intTithiSunrise == 27 || intTithiSunrise == 28) {
				key = "pradosh_2_$intMoonMonth";
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[0]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[1]
								/ 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[0] / 24.0);
				t_23 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[1] / 24.0);
				if (t_12 == 28 || t_21 == 28) {
					if (t_22 == 28 && t_23 == 28) {
						this.putKeyInMapCheckContainNew(key, doubleDateJd + 1.0, intMoonMonth, constants.pradoshK,
								EnumContainer.FestType.PRADOSH_VRAT);
					} else {
						this.putKeyInMapCheckContainNew(key, doubleDateJd, intMoonMonth, constants.pradoshK,
								EnumContainer.FestType.PRADOSH_VRAT);
					}
				}
			}
			if (intMoonMonth == 1 && intMoonMonthPrevDay != 1) {
				addFestivalToList("hindu_new_year", new FestivalDetail(constants.hinduNewYear, doubleDateJd));
				addFestivalToList("ugadi",
						new FestivalDetail(constants.ugadi, doubleDateJd, EnumContainer.FestType.FESTIVALS, null));
				addFestivalToList("gudi_padwa",
						new FestivalDetail(constants.gudiPadwa, doubleDateJd, EnumContainer.FestType.FESTIVALS, null));
				addFestivalToList("chaitra_navratri_ghatasthapana",
						new FestivalDetail(constants.chaitraNavratriGhatasthapana, doubleDateJd));
				addFestivalToList("chaitra_navratri", new FestivalDetail(constants.chaitraNavratri, doubleDateJd,
						EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
				if (intTithiSunriseNextDay == 3) {
					addFestivalToList("cheti_chand", new FestivalDetail(constants.chetiChand, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_chetichand.png"));
				} else if (intTithiSunriseNextDay == 1 && tithi_sunrise[i + 2] == 2) {
					addFestivalToList("cheti_chand", new FestivalDetail(constants.chetiChand, doubleDateJd + 2.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_chetichand.png"));
				} else {
					addFestivalToList("cheti_chand", new FestivalDetail(constants.chetiChand, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_chetichand.png"));
				}
				if (intTithiSunrise == 2) {
					addFestivalToList("hindu_new_year", new FestivalDetail(constants.hinduNewYear, doubleDateJd - 1.0));
					addFestivalToList("ugadi", new FestivalDetail(constants.ugadi, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, null));
					addFestivalToList("gudi_padwa", new FestivalDetail(constants.gudiPadwa, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, null));
					addFestivalToList("chaitra_navratri_ghatasthapana",
							new FestivalDetail(constants.chaitraNavratriGhatasthapana, doubleDateJd - 1.0));
					addFestivalToList("chaitra_navratri", new FestivalDetail(constants.chaitraNavratri,
							doubleDateJd - 1.0, EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
					addFestivalToList("cheti_chand", new FestivalDetail(constants.chetiChand, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_chetichand.png"));
				}
			}
			int tithiSunrise;
			int ni;
			String key1;
			String key2;
			if (intMoonMonth == 1 && intMoonMonthPrevDay != 1) {
				key = "";
				key1 = "";
				key2 = "";
				t_22 = 0;
				t_23 = 1;
				ni = 0;
				while (ni < 12) {
					key = "chaitra_navratri_" + (ni + 1);
					key1 = "chaitra_navratri_" + (ni + 2);
					key2 = "chaitra_navratri_" + (ni + 3);
					tithiSunrise = tithi_sunrise[i + t_22];
					if (ni == 0 && tithiSunrise == 2) {
						/*
						 * addFestivalToList( "chaitra_navratri_1", FestivalDetail(
						 * context.resources.getString(R.string.chaitra_navratri), doubleDateJd - 1.0, 1
						 * ) ) addFestivalToList( "chaitra_navratri_2", FestivalDetail(
						 * context.resources.getString(R.string.chaitra_navratri), doubleDateJd - 1.0, 1
						 * ) )
						 */
						++t_23;
						ni = 1;
					} else if (tithiSunrise >= 9) {
						if (tithiSunrise == 9 && tithi_sunrise[i + t_22 - 1] == 7) {
							if (this.paranaVart(doubleDateJd + (double) t_22, 9.0)) {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
								 * 1.0, 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
								 * t_22.toDouble(), 1) )
								 */
								addFestivalToList("chaitra_navratri_parana",
										new FestivalDetail(constants.chaitraNavratriParana,
												doubleDateJd + (double) t_22, EnumContainer.FestType.FESTIVALS,
												"holidays/img_ram_navmi.png"));
							} else {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
								 * 1.0, 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
								 * t_22.toDouble(), 1) ) addFestivalToList( key2, FestivalDetail(key2,
								 * doubleDateJd + t_22.toDouble() + 1.0, 1) )
								 */
								addFestivalToList("chaitra_navratri_parana",
										new FestivalDetail(constants.chaitraNavratriParana,
												doubleDateJd + (double) t_22 + 1.0, EnumContainer.FestType.FESTIVALS,
												"holidays/img_ram_navmi.png"));
							}
							++t_22;
						} else if (tithiSunrise == 10 && tithi_sunrise[i + t_22 - 1] == 8) {
							/*
							 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
							 * 1.0, 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
							 * t_22.toDouble(), 1) )
							 */
							addFestivalToList("chaitra_navratri_parana",
									new FestivalDetail(constants.chaitraNavratriParana, doubleDateJd + (double) t_22,
											EnumContainer.FestType.FESTIVALS, "holidays/img_ram_navmi.png"));
							++t_22;
						} else if (tithiSunrise == 9 && tithi_sunrise[i + t_22 + 1] == 10) {
							if (this.paranaVart(doubleDateJd + (double) t_22, 9.0)) {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
								 * 1) )
								 */
								addFestivalToList("chaitra_navratri_parana",
										new FestivalDetail(constants.chaitraNavratriParana,
												doubleDateJd + (double) t_22, EnumContainer.FestType.FESTIVALS,
												"holidays/img_ram_navmi.png"));
							} else {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
								 * 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
								 * t_22.toDouble() + 1.0, 1) )
								 */
								addFestivalToList("chaitra_navratri_parana",
										new FestivalDetail(constants.chaitraNavratriParana,
												doubleDateJd + (double) t_22 + 1.0, EnumContainer.FestType.FESTIVALS,
												"holidays/img_ram_navmi.png"));
							}
							++t_22;
						} else if (tithiSunrise == 9 && tithi_sunrise[i + t_22 + 1] == 11) {
							/*
							 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
							 * 1) )
							 */
							addFestivalToList("chaitra_navratri_parana",
									new FestivalDetail(constants.chaitraNavratriParana, doubleDateJd + (double) t_22,
											EnumContainer.FestType.FESTIVALS, "holidays/img_ram_navmi.png"));
							++t_22;
						} else if (tithiSunrise == 9 && tithi_sunrise[i + t_22 + 1] == 9) {
							/*
							 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
							 * 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
							 * t_22.toDouble() + 1.0, 1) )
							 */
							addFestivalToList("chaitra_navratri_parana",
									new FestivalDetail(constants.chaitraNavratriParana,
											doubleDateJd + (double) t_22 + 1.0, EnumContainer.FestType.FESTIVALS,
											"holidays/img_ram_navmi.png"));
							++t_22;
						}
					} else if (tithiSunrise == t_23) {
						/*
						 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
						 * 1) )
						 */
						++t_23;
						++t_22;
					} else if (tithiSunrise == t_23 - 1) {
						/*
						 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
						 * 1) )
						 */
						++t_23;
						++t_22;
					} else if (tithiSunrise == t_23 + 1) {
						/*
						 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
						 * 1.0, 1) )
						 */
						++t_23;
					}
					++ni;
				}
			}
			if (intMoonMonth == 1 && (intTithiSunrise == 8 || intTithiSunrise == 9)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[2]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[3]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[2] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[3] / 24.0);
				if (t_10 == 9 || t_12 == 9) {
					if (t_21 != 9 && t_22 != 9) {
						if (t_10 == 8) {
							if (t_21 == 10) {
								addFestivalToList("ram_navami", new FestivalDetail(constants.ramNavami, doubleDateJd,
										EnumContainer.FestType.FESTIVALS, "holidays/img_ram_navmi.png"));
							}
						} else {
							addFestivalToList("ram_navami", new FestivalDetail(constants.ramNavami, doubleDateJd,
									EnumContainer.FestType.FESTIVALS, "holidays/img_ram_navmi.png"));
						}
					} else {
						addFestivalToList("ram_navami", new FestivalDetail(constants.ramNavami, doubleDateJd + 1.0,
								EnumContainer.FestType.FESTIVALS, "holidays/img_ram_navmi.png"));
					}
				}
			}
			if (intMoonMonth == 1 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
				if (intTithiSunrise == 15 && (intTithiSunrisePrevDay == 14 || intTithiSunrisePrevDay == 13)) {
					addFestivalToList("chaitra_hanuman_jayanti", new FestivalDetail(constants.chaitraHanumanJayanti,
							doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_hanumaan_jayanti.png"));
				} else if (intTithiSunrise == 14 && intTithiSunriseNextDay == 16) {
					addFestivalToList("chaitra_hanuman_jayanti", new FestivalDetail(constants.chaitraHanumanJayanti,
							doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_hanumaan_jayanti.png"));
				}
			}
			if (intMoonMonth == 2 && (intTithiSunrise == 2 || intTithiSunrise == 3)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[3]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[3] / 24.0);
				if (intTithiSunrise == 3 && intTithiSunrisePrevDay != 3 && t_10 == 3) {
					addFestivalToList("akshaya_tritiya", new FestivalDetail(constants.akshayaTritiya, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_akshay_tritya.png"));
				} else if (intTithiSunrise == 2 && t_12 == 3) {
					addFestivalToList("akshaya_tritiya", new FestivalDetail(constants.akshayaTritiya,
							doubleDateJd + 1.0, EnumContainer.FestType.FESTIVALS, "holidays/img_akshay_tritya.png"));
				} else if (intTithiSunrise == 2 && t_12 != 3) {
					addFestivalToList("akshaya_tritiya", new FestivalDetail(constants.akshayaTritiya, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_akshay_tritya.png"));
				} else if (intTithiSunrise == 2 && intTithiSunriseNextDay == 4) {
					addFestivalToList("akshaya_tritiya", new FestivalDetail(constants.akshayaTritiya, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_akshay_tritya.png"));
				}
			}
			if (intMoonMonth == 4 && (intTithiSunrise == 2 || intTithiSunrise == 3)) {
				if (intTithiSunrise == 2) {
					if (intTithiSunriseNextDay == 2) {
						addFestivalToList("jagannath_rath_yatra",
								new FestivalDetail(constants.jagannathRathYatra, doubleDateJd + 1.0,
										EnumContainer.FestType.FESTIVALS, "holidays/img_jagannath_yatra.png"));
					} else {
						addFestivalToList("jagannath_rath_yatra", new FestivalDetail(constants.jagannathRathYatra,
								doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_jagannath_yatra.png"));
					}
				} else if (intTithiSunrise == 3 && intTithiSunrisePrevDay == 1) {
					addFestivalToList("jagannath_rath_yatra", new FestivalDetail(constants.jagannathRathYatra,
							doubleDateJd - 1.0, EnumContainer.FestType.FESTIVALS, "holidays/img_jagannath_yatra.png"));
				}
			}
			if (intMoonMonth == 4 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[2]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[2] / 24.0);
				if (intTithiSunrise == 15 && intTithiSunrisePrevDay != 15 && t_10 == 15) {
					addFestivalToList("guru_purnima", new FestivalDetail(constants.guruPurnima, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_guru_purnima.png"));
				} else if (intTithiSunrise == 14 && t_12 == 15) {
					addFestivalToList("guru_purnima", new FestivalDetail(constants.guruPurnima, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_guru_purnima.png"));
				} else if (intTithiSunrise == 14 && t_12 != 15) {
					addFestivalToList("guru_purnima", new FestivalDetail(constants.guruPurnima, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_guru_purnima.png"));

				} else if (intTithiSunrise == 14 && intTithiSunriseNextDay == 16) {
					addFestivalToList("guru_purnima", new FestivalDetail(constants.guruPurnima, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_guru_purnima.png"));

				}
			}
			if (intMoonMonth == 4 && intTithiSunrise > 15) {
				if (intTithiSunrise == 16) {
					/*
					 * addFestivalToList( "sawan_purnimanta_start",
					 * FestivalDetail("Sawan Purnimanta Start", (doubleDateJd), 1) );
					 */
				} else if (intTithiSunrise == 17 && intTithiSunrisePrevDay == 15) {
					/*
					 * addFestivalToList( "sawan_purnimanta_start",
					 * FestivalDetail("Sawan Purnimanta Start", (doubleDateJd), 1) );
					 */
				}
				if (intWeekDay == 1) {
					++countSawanPVrat;
					/*
					 * addFestivalToList( "sawansomwarvrat_purnimanta_$countSawanPVrat",
					 * FestivalDetail("Sawan Purnimanta Start", (doubleDateJd), 1) );
					 */
				}
			}
			if (intMoonMonth == 5 && intTithiSunrise < 16) {
				if (intTithiSunrise == 15) {
					/*
					 * addFestivalToList( "sawan_purnimanta_end",
					 * FestivalDetail("Sawan Purnimanta End", (doubleDateJd), 1) );
					 */
				} else if (intTithiSunrise == 14 && intTithiSunriseNextDay == 16) {
					/*
					 * addFestivalToList( "sawan_purnimanta_end",
					 * FestivalDetail("Sawan Purnimanta End", (doubleDateJd), 1) );
					 */
				}
				if (intWeekDay == 1) {
					++countSawanPVrat;
					/*
					 * addFestivalToList( "sawansomwarvrat_purnimanta_$countSawanPVrat",
					 * FestivalDetail("Sawan Purnimanta End", (doubleDateJd), 1) );
					 */
				}
			}
			if (intMoonMonth == 5 && intMoonMonthPrevDay != 5) {
				/*
				 * addFestivalToList( "sawan_amanta_start", FestivalDetail("Sawan Amanta Start",
				 * (doubleDateJd), 1) );
				 */
			}
			if (intMoonMonth == 6 && intMoonMonthPrevDay != 6) {
				/*
				 * addFestivalToList( "sawan_amanta_end", FestivalDetail("Sawan Amanta End",
				 * (doubleDateJd - 1.0), 1) );
				 */
			}
			if (intMoonMonth == 5 && intWeekDay == 1) {
				++countSawanAVrat;
				/*
				 * addFestivalToList( "sawan_somwar_vrat_amanta" + countSawanAVrat,
				 * FestivalDetail("Sawan Somwar Vrat Amanta$countSawanAVrat", (doubleDateJd), 1)
				 * );
				 */
			}
			if (intMoonMonth == 5) {
				if (intTithiSunrise == 3 || intTithiSunrise == 4) {
					if (intTithiSunrise == 3) {
						if (intTithiSunriseNextDay == 3) {
							addFestivalToList("hariyali_teej",
									new FestivalDetail(constants.hariyaliTeej, doubleDateJd + 1.0,
											EnumContainer.FestType.FESTIVALS, "holidays/img_haryali_teej.png"));
						} else {
							addFestivalToList("hariyali_teej", new FestivalDetail(constants.hariyaliTeej, doubleDateJd,
									EnumContainer.FestType.FESTIVALS, "holidays/img_haryali_teej.png"));
						}
					} else if (intTithiSunrise == 4 && intTithiSunrisePrevDay == 2) {
						addFestivalToList("hariyali_teej", new FestivalDetail(constants.hariyaliTeej,
								doubleDateJd - 1.0, EnumContainer.FestType.FESTIVALS, "holidays/img_haryali_teej.png"));
					}
				}
				if (intTithiSunrise == 18 || intTithiSunrise == 19) {
					if (intTithiSunrise == 18) {
						if (intTithiSunriseNextDay == 18) {
							addFestivalToList("kajari_teej",
									new FestivalDetail(constants.kajariTeej, doubleDateJd + 1.0,
											EnumContainer.FestType.FESTIVALS, "holidays/img_kajri_teej.png"));
						} else {
							addFestivalToList("kajari_teej", new FestivalDetail(constants.kajariTeej, doubleDateJd,
									EnumContainer.FestType.FESTIVALS, "holidays/img_kajri_teej.png"));
						}
					} else if (intTithiSunrise == 19 && intTithiSunrisePrevDay == 17) {
						addFestivalToList("kajari_teej", new FestivalDetail(constants.kajariTeej, doubleDateJd - 1.0,
								EnumContainer.FestType.FESTIVALS, "holidays/img_kajri_teej.png"));
					}
				}
			}
			if (intMoonMonth == 5 && (intTithiSunrise == 4 || intTithiSunrise == 5)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[2]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[3]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[3] / 24.0);
				if (intTithiSunrise == 4 && t_10 == 5 && t_21 != 5) {
					addFestivalToList("nag_panchami", new FestivalDetail(constants.nagPanchami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_naag_panchami.png"));
				} else if (intTithiSunrise == 5 && intTithiSunrisePrevDay != 5 && t_12 == 5) {
					addFestivalToList("nag_panchami", new FestivalDetail(constants.nagPanchami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_naag_panchami.png"));
				} else if (intTithiSunrise == 4 && t_21 == 5) {
					addFestivalToList("nag_panchami", new FestivalDetail(constants.nagPanchami, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_naag_panchami.png"));
				} else if (intTithiSunrise == 4 && t_21 != 5) {
					addFestivalToList("nag_panchami", new FestivalDetail(constants.nagPanchami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_naag_panchami.png"));
				} else if (intTithiSunrise == 4 && intTithiSunriseNextDay == 6) {
					addFestivalToList("nag_panchami", new FestivalDetail(constants.nagPanchami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_naag_panchami.png"));
				}
			}
			if (intMoonMonth == 5 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[3]
								/ 24.0);
				if (intTithiSunrise == 15 && t_10 == 15) {
					addFestivalToList("raksha_bandhan", new FestivalDetail(constants.rakshaBandhan, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_raksha_bandhan.png"));
				} else if (intTithiSunrise == 14 && t_10 == 15 && intTithiSunriseNextDay == 16) {
					addFestivalToList("raksha_bandhan", new FestivalDetail(constants.rakshaBandhan, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_raksha_bandhan.png"));
				} else {
					addFestivalToList("raksha_bandhan", new FestivalDetail(constants.rakshaBandhan, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_raksha_bandhan.png"));
				}
			}

			if (intMoonMonth == 5 && (intTithiSunrise == 22 || intTithiSunrise == 23)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[7]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[8]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD((doubleDateJd + 1.0) + baseCalculationNew.muhuratCalculation
								.getNightDivisons((doubleDateJd + 1.0), doubleSunSetNextDay, 15)[7] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD((doubleDateJd + 1.0) + baseCalculationNew.muhuratCalculation
								.getNightDivisons((doubleDateJd + 1.0), doubleSunSetNextDay, 15)[8] / 24.0);
				if ((t_10 == 23 || t_12 == 23) && (t_21 == 23 || t_22 == 23)) {
					addFestivalToList("janmashtami", new FestivalDetail(constants.janmashtami, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_janmastmi.png"));
				} else if ((t_10 == 23 || t_12 == 23) && t_21 != 23) {
					addFestivalToList("janmashtami", new FestivalDetail(constants.janmashtami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_janmastmi.png"));
				}
			}
			if (intMoonMonth == 6) {
				if (intTithiSunrise == 3 || intTithiSunrise == 4) {
					t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
							+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[2]
									/ 24.0);
					t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
							+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[3]
									/ 24.0);
					t_21 = baseCalculationNew.panchangCalculation
							.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
									.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[2] / 24.0);
					t_22 = baseCalculationNew.panchangCalculation
							.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
									.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[3] / 24.0);
					if (t_10 == 4 && t_12 == 4 && t_21 == 4 && t_22 == 4) {
						addFestivalToList("ganesh_chaturthi", new FestivalDetail(constants.ganeshChaturthi,
								doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_ganesh_chaturthi.png"));
					} else if (t_10 == 4 && t_12 == 4) {
						addFestivalToList("ganesh_chaturthi", new FestivalDetail(constants.ganeshChaturthi,
								doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_ganesh_chaturthi.png"));
					} else if (t_12 != 4 && t_21 == 4) {
						addFestivalToList("ganesh_chaturthi",
								new FestivalDetail(constants.ganeshChaturthi, doubleDateJd + 1.0,
										EnumContainer.FestType.FESTIVALS, "holidays/img_ganesh_chaturthi.png"));
					} else if (t_12 == 4 && t_21 != 4) {
						addFestivalToList("ganesh_chaturthi", new FestivalDetail(constants.ganeshChaturthi,
								doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_ganesh_chaturthi.png"));
					} else if (t_10 != 4 && t_12 == 4 && t_21 == 4 && t_22 == 4) {
						addFestivalToList("ganesh_chaturthi",
								new FestivalDetail(constants.ganeshChaturthi, doubleDateJd + 1.0,
										EnumContainer.FestType.FESTIVALS, "holidays/img_ganesh_chaturthi.png"));
					}
				}
				if (intTithiSunrise == 3 || intTithiSunrise == 4) {
					if (intTithiSunrise == 3) {
						if (intTithiSunriseNextDay == 3) {
							addFestivalToList("hartalika_teej",
									new FestivalDetail(constants.hartalikaTeej, doubleDateJd + 1.0,
											EnumContainer.FestType.FESTIVALS, "holidays/img_hartalika_teej.png"));
						} else {
							addFestivalToList("hartalika_teej", new FestivalDetail(constants.hartalikaTeej,
									doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_hartalika_teej.png"));
						}
					} else if (intTithiSunrise == 4 && intTithiSunrisePrevDay == 2) {
						addFestivalToList("hartalika_teej",
								new FestivalDetail(constants.hartalikaTeej, doubleDateJd - 1.0,
										EnumContainer.FestType.FESTIVALS, "holidays/img_hartalika_teej.png"));
					}
				}
				if (intTithiSunrise == 13 || intTithiSunrise == 14) {
					t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
							+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[2]
									/ 24.0);
					t_12 = baseCalculationNew.panchangCalculation
							.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
									.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[2] / 24.0);
					if (t_10 == 14) {
						addFestivalToList("anant_chaturdashi", new FestivalDetail(constants.anantChaturdashi,
								doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_anant_chaturdashi.png"));
					} else if (intTithiSunrise == 13 && t_12 != 14) {
						addFestivalToList("anant_chaturdashi", new FestivalDetail(constants.anantChaturdashi,
								doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_anant_chaturdashi.png"));
					} else if (intTithiSunrise == 13 && intTithiSunriseNextDay == 15) {
						addFestivalToList("anant_chaturdashi", new FestivalDetail(constants.anantChaturdashi,
								doubleDateJd, EnumContainer.FestType.FESTIVALS, "holidays/img_anant_chaturdashi.png"));
					}
				}
			}
			if (intMoonMonth == 7 && intMoonMonthPrevDay != 7) {
				addFestivalToList("ashwin_navratri_ghatasthapana",
						new FestivalDetail(constants.ashwinNavratriGhataSthapana, doubleDateJd));
				addFestivalToList("ashwin_navratri", new FestivalDetail(constants.ashwinNavratri, doubleDateJd,
						EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
				key = "";
				key1 = "";
				key2 = "";
				t_22 = 0;
				t_23 = 1;
				ni = 0;
				while (ni < 12) {
					key = "ashwin_navratri_" + (ni + 1);
					key1 = "ashwin_navratri_" + (ni + 2);
					key2 = "ashwin_navratri_" + (ni + 3);
					tithiSunrise = tithi_sunrise[i + t_22];
					if (ni == 0 && tithiSunrise == 2) {
						addFestivalToList("ashwin_navratri_ghatasthapana",
								new FestivalDetail(constants.ashwinNavratriGhataSthapana, doubleDateJd - 1.0));
						addFestivalToList("ashwin_navratri", new FestivalDetail(constants.ashwinNavratri,
								doubleDateJd - 1.0, EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
						/*
						 * addFestivalToList( "ashwin_navratri_1", FestivalDetail(
						 * context.resources.getString(R.string.ashwin_navratri), doubleDateJd - 1.0, 1
						 * ) ) addFestivalToList( "ashwin_navratri_2", FestivalDetail(
						 * context.resources.getString(R.string.ashwin_navratri), doubleDateJd - 1.0, 1
						 * ) )
						 */
						++t_23;
						ni = 1;
					} else if (tithiSunrise >= 9) {
						if (tithiSunrise == 9 && tithi_sunrise[i + t_22 - 1] == 7) {
							if (paranaVart(doubleDateJd + (double) t_22, 9.0)) {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
								 * 1.0, 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
								 * t_22.toDouble(), 1) )
								 */
								addFestivalToList("ashwin_navratri_parana",
										new FestivalDetail(constants.ashwinNavratriParana, doubleDateJd + (double) t_22,
												EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
								addFestivalToList("ashwin_navratri_durgavisarjan", new FestivalDetail(
										constants.ashwinNavratriDurgaVisarjan, doubleDateJd + (double) t_22 + 1.0));
							} else {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
								 * 1.0, 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
								 * t_22.toDouble(), 1) ) addFestivalToList( key2, FestivalDetail(key2,
								 * doubleDateJd + t_22.toDouble() + 1.0, 1) )
								 */
								addFestivalToList("ashwin_navratri_parana",
										new FestivalDetail(constants.ashwinNavratriParana,
												doubleDateJd + (double) t_22 + 1.0, EnumContainer.FestType.FESTIVALS,
												"holidays/img_navratri.png"));
								addFestivalToList("ashwin_navratri_durgavisarjan", new FestivalDetail(
										constants.ashwinNavratriDurgaVisarjan, doubleDateJd + (double) t_22 + 1.0));
							}
							++t_22;
						} else if (tithiSunrise == 10 && tithi_sunrise[i + t_22 - 1] == 8) {
							/*
							 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
							 * 1.0, 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
							 * t_22.toDouble(), 1) )
							 */
							addFestivalToList("ashwin_navratri_parana",
									new FestivalDetail(constants.ashwinNavratriParana, doubleDateJd + (double) t_22,
											EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
							addFestivalToList("ashwin_navratri_durgavisarjan", new FestivalDetail(
									constants.ashwinNavratriDurgaVisarjan, doubleDateJd + (double) t_22));
							++t_22;
						} else if (tithiSunrise == 9 && tithi_sunrise[i + t_22 + 1] == 10) {
							if (paranaVart(doubleDateJd + (double) t_22, 9.0)) {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
								 * 1) )
								 */
								addFestivalToList("ashwin_navratri_parana",
										new FestivalDetail(constants.ashwinNavratriParana, doubleDateJd + (double) t_22,
												EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
								addFestivalToList("ashwin_navratri_durgavisarjan", new FestivalDetail(
										constants.ashwinNavratriDurgaVisarjan, doubleDateJd + (double) t_22 + 1.0));
							} else {
								/*
								 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
								 * 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
								 * t_22.toDouble() + 1.0, 1) )
								 */
								addFestivalToList("ashwin_navratri_parana",
										new FestivalDetail(constants.ashwinNavratriParana,
												doubleDateJd + (double) t_22 + 1.0, EnumContainer.FestType.FESTIVALS,
												"holidays/img_navratri.png"));
								addFestivalToList("ashwin_navratri_durgavisarjan", new FestivalDetail(
										constants.ashwinNavratriDurgaVisarjan, doubleDateJd + (double) t_22 + 1.0));
							}
							++t_22;
						} else if (tithiSunrise == 9 && tithi_sunrise[i + t_22 + 1] == 11) {
							/*
							 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
							 * 1) )
							 */
							addFestivalToList("ashwin_navratri_parana",
									new FestivalDetail(constants.ashwinNavratriParana, doubleDateJd + (double) t_22,
											EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
							addFestivalToList("ashwin_navratri_durgavisarjan", new FestivalDetail(
									constants.ashwinNavratriDurgaVisarjan, doubleDateJd + (double) t_22));
							++t_22;
						} else if (tithiSunrise == 9 && tithi_sunrise[i + t_22 + 1] == 9) {
							/*
							 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
							 * 1) ) addFestivalToList( key1, FestivalDetail(key1, doubleDateJd +
							 * t_22.toDouble() + 1.0, 1) )
							 */
							addFestivalToList("ashwin_navratri_parana",
									new FestivalDetail(constants.ashwinNavratriParana,
											doubleDateJd + (double) t_22 + 1.0, EnumContainer.FestType.FESTIVALS,
											"holidays/img_navratri.png"));
							addFestivalToList("ashwin_navratri_durgavisarjan", new FestivalDetail(
									constants.ashwinNavratriDurgaVisarjan, doubleDateJd + (double) t_22 + 1.0

							));
							++t_22;
						}
					} else if (tithiSunrise == t_23) {
						/*
						 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
						 * 1) )
						 */
						++t_23;
						++t_22;
					} else if (tithiSunrise == t_23 - 1) {
						/*
						 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble(),
						 * 1) )
						 */
						++t_23;
						++t_22;
					} else if (tithiSunrise == t_23 + 1) {
						/*
						 * addFestivalToList( key, FestivalDetail(key, doubleDateJd + t_22.toDouble() -
						 * 1.0, 1) )
						 */
						++t_23;
					}
					++ni;
				}
			}
			if (intMoonMonth == 7) {
				if ((intTithiSunrise == 6 || intTithiSunrise == 7) && !festivalDetailMap.containsKey("navpatrika")) {
					t_10 = baseCalculationNew.panchangCalculation
							.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
									.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[2] / 24.0);
					t_12 = baseCalculationNew.panchangCalculation
							.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
									.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[3] / 24.0);
					if (intTithiSunriseNextDay == 7 && (t_10 == 7 || t_12 == 7)) {
						addFestivalToList("nav_patrika", new FestivalDetail(constants.navPatrika, doubleDateJd + 1.0));
					} else if (intTithiSunriseNextDay != 6) {
						addFestivalToList("nav_patrika", new FestivalDetail(constants.navPatrika, doubleDateJd));
					}
				}
				if ((intTithiSunrise == 5 || intTithiSunrise == 6) && !festivalDetailMap.containsKey("kalparambha")) {
					t_10 = baseCalculationNew.panchangCalculation
							.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
									.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[2] / 24.0);
					t_12 = baseCalculationNew.panchangCalculation
							.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
									.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 15)[3] / 24.0);
					if (intTithiSunriseNextDay == 6 && (t_10 == 6 || t_12 == 6)) {
						addFestivalToList("kalparambha", new FestivalDetail(constants.kalparambha, doubleDateJd + 1.0));
						addFestivalToList("durga_puja_calendar",
								new FestivalDetail(constants.durgaPujaCalendar, doubleDateJd + 1.0));
					} else if (intTithiSunriseNextDay != 5) {
						addFestivalToList("kalparambha", new FestivalDetail(constants.kalparambha, doubleDateJd));
						addFestivalToList("durga_puja_calendar",
								new FestivalDetail(constants.durgaPujaCalendar, doubleDateJd));
					}
				}
			}
			if (intMoonMonth == 7 && (intTithiSunrise == 8 || intTithiSunrise == 9)
					&& !festivalDetailMap.containsKey("durga_puja_navami")) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[12]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[13]
								/ 24.0);
				if (t_10 != 9 && t_12 != 9) {
					addFestivalToList("durga_puja_navami", new FestivalDetail(constants.durgaPujaNavami,
							doubleDateJd + 1.0, EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
				} else {
					addFestivalToList("durga_puja_navami", new FestivalDetail(constants.durgaPujaNavami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
				}
				if (intTithiSunrise == 8) {
					addFestivalToList("durga_puja_ashtami", new FestivalDetail(constants.durgaPujaAstmi, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
				} else {
					addFestivalToList("durga_puja_ashtami", new FestivalDetail(constants.durgaPujaAstmi,
							doubleDateJd - 1.0, EnumContainer.FestType.FESTIVALS, "holidays/img_navratri.png"));
				}
			}

			if (intMoonMonth == 7 && (intTithiSunrise == 9 || intTithiSunrise == 10)
					&& !this.festivalDetailMap.containsKey("vijaya_dashami")) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[3]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[4]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[3] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[1] / 24.0);
				boolean isSravanNak = false;
				if (intTithiSunriseNextDay == 10 && intNakshatraSunRiseNextDay == 22) {
					double nakET = baseCalculationNew.panchangCalculation.nakshatra(doubleDateJd + 1.0)[1];
					double aprhanT = baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd + 1.0,
							doubleSunRiseNextDay, 5)[3];
					if (nakET > aprhanT) {
						isSravanNak = true;
					}
				}
				if (t_10 != 10 && t_21 != 10 && isSravanNak) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_10 == 10 && t_21 != 10 && isSravanNak) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_10 == 10 && t_21 != 10 && !isSravanNak) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_12 != 10 && t_21 == 10 && isSravanNak) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_12 == 10 && t_22 == 10 && intNakshatraSunRiseNextDay == 22) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_10 == 10 && t_21 == 10) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_10 != 10 && t_21 == 10) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_10 == 10 && t_21 != 10) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				} else if (t_10 != 10 && t_21 != 10) {
					addFestivalToList("vijaya_dashami", new FestivalDetail(constants.vijayaDashami, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_dushera.png"));
				}
			}

			if (intMoonMonth == 7 && (intTithiSunrise == 18 || intTithiSunrise == 19)
					&& !festivalDetailMap.containsKey("karva_chauth")) {
				MoonCalculation objCMoon = new MoonCalculation();
				t_12 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + objCMoon.getMoonRiseSetTime(doubleDateJd)[0] / 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + objCMoon.getMoonRiseSetTime(doubleDateJd + 1.0)[0] / 24.0);
				if (t_12 == 19 && t_21 == 19) {
					addFestivalToList("karva_chauth", new FestivalDetail(constants.karvaChauth, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_karva_chauth.png"));
				} else if (t_12 == 19 && t_21 != 19) {
					addFestivalToList("karva_chauth", new FestivalDetail(constants.karvaChauth, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_karva_chauth.png"));
				} else if (t_12 != 19 && t_21 == 19) {
					addFestivalToList("karva_chauth", new FestivalDetail(constants.karvaChauth, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_karva_chauth.png"));
				} else {
					addFestivalToList("karva_chauth", new FestivalDetail(constants.karvaChauth, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_karva_chauth.png"));
				}
			}

			if (intMoonMonth == 7 && (intTithiSunrise == 27 || intTithiSunrise == 28)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[0]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[1]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[0] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[1] / 24.0);
				if (t_10 == 28 || t_12 == 28) {
					if (t_21 != 28 && t_22 != 28) {
						addFestivalToList("dhanteras", new FestivalDetail(constants.dhanteras, doubleDateJd,
								EnumContainer.FestType.FESTIVALS, "holidays/img_dhan_teras.png"));
						addFestivalToList("diwali_puja_calendar",
								new FestivalDetail(constants.diwaliPujaCalendar, doubleDateJd));
					} else {
						addFestivalToList("dhanteras", new FestivalDetail(constants.dhanteras, doubleDateJd + 1.0,
								EnumContainer.FestType.FESTIVALS, "holidays/img_dhan_teras.png"));
						addFestivalToList("diwali_puja_calendar",
								new FestivalDetail(constants.diwaliPujaCalendar, doubleDateJd + 1.0));
					}
				}
			}
			if (intMoonMonth == 7 && (intTithiSunrise == 28 || intTithiSunrise == 29)
					&& !festivalDetailMap.containsKey("narak_chaturdashi")) {
				t_10 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd - 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd - 1.0, doubleSunSet, 15)[13] / 24.0);
				t_12 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd - 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd - 1.0, doubleSunSet, 15)[14] / 24.0);
				t_21 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[13]
								/ 24.0);
				t_22 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[13]
								/ 24.0);
				if (t_10 != 29 && t_12 != 29 && intTithiSunrise != 29) {
					if (t_21 != 29 && t_22 != 29 && intTithiSunriseNextDay != 29) {
						if (intTithiSunrise == 28 && intTithiSunriseNextDay == 28) {
							addFestivalToList("narak_chaturdashi",
									new FestivalDetail(constants.narakChaturdashi, doubleDateJd + 2.0,
											EnumContainer.FestType.FESTIVALS, "holidays/img_narak_chaturdashi.png"));
						} else {
							addFestivalToList("narak_chaturdashi",
									new FestivalDetail(constants.narakChaturdashi, doubleDateJd,
											EnumContainer.FestType.FESTIVALS, "holidays/img_narak_chaturdashi.png"));
						}
					} else {
						addFestivalToList("narak_chaturdashi",
								new FestivalDetail(constants.narakChaturdashi, doubleDateJd + 1.0,
										EnumContainer.FestType.FESTIVALS, "holidays/img_narak_chaturdashi.png"));
					}
				} else {
					addFestivalToList("narak_chaturdashi", new FestivalDetail(constants.narakChaturdashi, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_narak_chaturdashi.png"));
				}
			}

			if (intMoonMonth == 7 && (intTithiSunrise == 29 || intTithiSunrise == 30)
					&& !festivalDetailMap.containsKey("diwali")) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[0]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[1]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[0] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[1] / 24.0);
				if (t_21 != 30 && t_22 != 30) {
					if (t_10 != 30 && t_12 != 30) {
						if (intTithiSunrise == 30) {
							addFestivalToList("diwali", new FestivalDetail(constants.diwali, doubleDateJd,
									EnumContainer.FestType.FESTIVALS, "holidays/img_diwali.png"));
						} else if (intTithiSunrise == 29 && intTithiSunriseNextDay == 1) {
							addFestivalToList("diwali", new FestivalDetail(constants.diwali, doubleDateJd,
									EnumContainer.FestType.FESTIVALS, "holidays/img_diwali.png"));
						}
					} else {
						addFestivalToList("diwali", new FestivalDetail(constants.diwali, doubleDateJd,
								EnumContainer.FestType.FESTIVALS, "holidays/img_diwali.png"

						));
					}
				} else {
					addFestivalToList("diwali", new FestivalDetail(constants.diwali, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_diwali.png"));
				}
			}

			if (intMoonMonth == 8 && intMoonMonthPrevDay != 8 && (intTithiSunrise == 1 || intTithiSunrise == 2)
					&& !festivalDetailMap.containsKey("govardhan")) {
				t_10 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd - 1.0
								+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd - 1.0,
										baseCalculationNew.panchangCalculation.getSunRise(doubleDateJd - 1.0), 15)[14]
										/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 15)[9]
								/ 24.0);
				if (t_12 == 1) {
					addFestivalToList("govardhan", new FestivalDetail(constants.govardhan, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_goverdhan.png"));
				} else if (t_10 == 1) {
					addFestivalToList("govardhan", new FestivalDetail(constants.govardhan, doubleDateJd - 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_goverdhan.png"));
				}
			}

			if (intMoonMonth == 8 && (intTithiSunrise == 1 || intTithiSunrise == 2)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[3]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[4]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[3] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[4] / 24.0);
				if (t_10 == 2 || t_12 == 2) {
					if (t_21 != 2 && t_22 != 2) {
						addFestivalToList("bhai_dooj", new FestivalDetail(constants.bhaiDooj, doubleDateJd,
								EnumContainer.FestType.FESTIVALS, "holidays/img_bhaidooj.png"));
					} else {
						addFestivalToList("bhai_dooj", new FestivalDetail(constants.bhaiDooj, doubleDateJd + 1.0,
								EnumContainer.FestType.FESTIVALS, "holidays/img_bhaidooj.png"));
					}
				}
			}

			if (intMoonMonth == 11 && (intTithiSunrise == 28 || intTithiSunrise == 29)
					&& !festivalDetailMap.containsKey("maha_shivratri")) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[7]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 15)[8]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 15)[8] / 24.0);
				if (t_10 != 29 && t_12 != 29) {
					addFestivalToList("maha_shivratri", new FestivalDetail(constants.mahaShivratri, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_shivji.png"));
				} else if (t_21 == 29) {
					addFestivalToList("maha_shivratri", new FestivalDetail(constants.mahaShivratri, doubleDateJd + 1.0,
							EnumContainer.FestType.FESTIVALS, "holidays/img_shivji.png"));
				} else {
					addFestivalToList("maha_shivratri", new FestivalDetail(constants.mahaShivratri, doubleDateJd,
							EnumContainer.FestType.FESTIVALS, "holidays/img_shivji.png"));

				}
			}

			if (intMoonMonth == 11 && (intTithiSunrise == 4 || intTithiSunrise == 5)) {
				t_10 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd - 1.0
								+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd - 1.0,
										baseCalculationNew.panchangCalculation.getSunRise(doubleDateJd - 1.0), 5)[2]
										/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[2]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getDayDivisons(doubleDateJd, doubleSunRise, 5)[5]
								/ 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[5] / 24.0);
				if (t_12 == 5 || t_21 == 5) {
					if (t_22 == 5) {
						addFestivalToList("vasant_panchami", new FestivalDetail(constants.vasantPanchami,
								doubleDateJd + 1.0, EnumContainer.FestType.FESTIVALS, null));
						addFestivalToList("saraswati_puja",
								new FestivalDetail(constants.saraswatiPuja, doubleDateJd + 1.0,
										EnumContainer.FestType.FESTIVALS, "holidays/img_sarswati_mata.png"));
					} else if (t_10 != 5) {
						addFestivalToList("vasant_panchami", new FestivalDetail(constants.vasantPanchami, doubleDateJd,
								EnumContainer.FestType.FESTIVALS, null));
						addFestivalToList("saraswati_puja", new FestivalDetail(constants.saraswatiPuja, doubleDateJd,
								EnumContainer.FestType.FESTIVALS, "holidays/img_sarswati_mata.png"));
					}
				}
			}

			if (intMoonMonth == 12 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
				t_10 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[0]
								/ 24.0);
				t_12 = baseCalculationNew.panchangCalculation.getTithiD(doubleDateJd
						+ baseCalculationNew.muhuratCalculation.getNightDivisons(doubleDateJd, doubleSunSet, 5)[1]
								/ 24.0);
				t_21 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[0] / 24.0);
				t_22 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getNightDivisons(doubleDateJd + 1.0, doubleSunSetNextDay, 5)[1] / 24.0);
				t_23 = baseCalculationNew.panchangCalculation
						.getTithiD(doubleDateJd + 1.0 + baseCalculationNew.muhuratCalculation
								.getDayDivisons(doubleDateJd + 1.0, doubleSunRiseNextDay, 5)[4] / 24.0);
				if (t_10 == 15 || t_12 == 15) {
					if (t_21 != 15 && t_22 != 15 && t_23 != 15) {
						addFestivalToList("holika_dahan", new FestivalDetail(constants.holikaDahan, doubleDateJd,
								EnumContainer.FestType.FESTIVALS, "holidays/img_holika_dahan.png"));
						;
						addFestivalToList("holi", new FestivalDetail(constants.holi, doubleDateJd + 1.0,
								EnumContainer.FestType.FESTIVALS, "holidays/img_holi.png"));
					} else {
						addFestivalToList("holika_dahan", new FestivalDetail(constants.holikaDahan, doubleDateJd + 1.0,
								EnumContainer.FestType.FESTIVALS, "holidays/img_holika_dahan.png"));
						addFestivalToList("holi", new FestivalDetail(constants.holi, doubleDateJd + 2.0,
								EnumContainer.FestType.FESTIVALS, "holidays/img_holi.png"));
					}
				}
			}
		}

		addFestivalToList("subhas_chandra_bose_jayanti",
				new FestivalDetail(constants.subhasChandraBoseJayanti,
						baseCalculationNew.panchangCalculation.toJulian(year, 1, 23), EnumContainer.FestType.FESTIVALS,
						"holidays/img_subhash_chandra_bose.png"));
		addFestivalToList("republic_day",
				new FestivalDetail(constants.republicDay, baseCalculationNew.panchangCalculation.toJulian(year, 1, 26),
						EnumContainer.FestType.FESTIVALS, "holidays/img_republic_day.png"));
		addFestivalToList("banks_holiday",
				new FestivalDetail(constants.banksHoliday, baseCalculationNew.panchangCalculation.toJulian(year, 4, 1),
						EnumContainer.FestType.FESTIVALS, "holidays/img_bank_holiday.png"));
		addFestivalToList("ambedkar_jayanti",
				new FestivalDetail(constants.ambedkarJayanti,
						baseCalculationNew.panchangCalculation.toJulian(year, 4, 14), EnumContainer.FestType.FESTIVALS,
						"holidays/img_ambedkar.png"));
		addFestivalToList("independence_day",
				new FestivalDetail(constants.independenceDay,
						baseCalculationNew.panchangCalculation.toJulian(year, 8, 15), EnumContainer.FestType.FESTIVALS,
						"holidays/img_independence_day.png"));
		addFestivalToList("gandhi_jayanti",
				new FestivalDetail(constants.gandhiJayanti,
						baseCalculationNew.panchangCalculation.toJulian(year, 10, 2), EnumContainer.FestType.FESTIVALS,
						"holidays/img_ghandi.png"));
		addFestivalToList("children_day",
				new FestivalDetail(constants.childrenDay, baseCalculationNew.panchangCalculation.toJulian(year, 11, 14),
						EnumContainer.FestType.FESTIVALS, "holidays/img_baal_divas.png"));
		addFestivalToList("english_new_year",
				new FestivalDetail(constants.englishNewYear,
						baseCalculationNew.panchangCalculation.toJulian(year, 1, 1), EnumContainer.FestType.FESTIVALS,
						"holidays/img_new_year.png"));
		addFestivalToList("merry_christmas",
				new FestivalDetail(constants.merryChristmas,
						baseCalculationNew.panchangCalculation.toJulian(year, 12, 25), EnumContainer.FestType.FESTIVALS,
						"holidays/img_christmas.png"));
		/*
		 * val fvc = ValueComparator(festivalDayMapJD) val sorted_map: TreeMap<String,
		 * Double> = TreeMap(fvc) sorted_map.putAll(festivalDayMapJD) val var38:
		 * Iterator<*> = sorted_map.entries.iterator()
		 * 
		 * while (var38.hasNext()) { val (key, value) = var38.next() as Map.Entry<*, *>
		 * val dateArray = fromJulian((value as Double)!!) if (dateArray[0] == year) {
		 * festival_day_list[key as String] = dateArray } }
		 */

		int count = 0;
		Iterator<Map.Entry<String, FestivalDetail>> var7 = festivalDetailMap.entrySet().iterator();

		while (var7.hasNext()) {
			Map.Entry<?, ?> entry = var7.next();
			Object key = entry.getKey();

			FestivalDetail detail = (FestivalDetail) festivalDetailMap.get(key);
			int[] dt = panUtil.fromJulian(detail.getFestDate()); // assuming fromJulian returns int[]

			if (key.toString().startsWith("vijaya_dashami")) {
				count++;

			}
		}

		// Convert Map values to ArrayList
		ArrayList<FestivalDetail> festDetails = new ArrayList<>(festivalDetailMap.values());

		// Sort the list using custom comparator
		Collections.sort(festDetails, new CustomCamparator());

		return festDetails;
	}

	/*
	 * private fun putKeyInMapCheckContain(key: String, jd: Double, moonMonth: Int)
	 * { if (!festivalDayMapJD.containsKey(key)) { festivalDayMapJD[key] = jd } else
	 * if ((moonMonth == 9 || moonMonth == 10) && (festivalDayMapJD[key] != jd) &&
	 * festivalDayMapJD[key] != jd - 1.0 && festivalDayMapJD[key] != jd - 2.0) { if
	 * (!festivalDayMapJD.containsKey(key + "_2")) { festivalDayMapJD[key + "_2"] =
	 * jd } else if (festivalDayMapJD[key + "_2"] != jd && festivalDayMapJD[key +
	 * "_2"] != jd - 1.0 && festivalDayMapJD[key + "_2"] != jd - 2.0) {
	 * festivalDayMapJD[key + "_2"] = jd } } }
	 */

	private void putKeyInMapCheckContainNew(String key, double jd, int moonMonth, String festName,
			EnumContainer.FestType festType) {// : = EnumContainer.FestType.OTHER
		if (!festivalDetailMap.containsKey(key)) {
			addFestivalToList(key, new FestivalDetail(festName, jd, festType, null));
		} else if ((moonMonth == 9 || moonMonth == 10) && (festivalDetailMap.get(key).getFestDate() != jd)
				&& festivalDetailMap.get(key).getFestDate() != jd - 1.0
				&& festivalDetailMap.get(key).getFestDate() != jd - 2.0) {
			if (!festivalDetailMap.containsKey(key + "_2")) {
				addFestivalToList(key + "_2", new FestivalDetail(festName, jd, festType, null));
			} else if (festivalDetailMap.get(key + "_2").getFestDate() != jd
					&& festivalDetailMap.get(key + "_2").getFestDate() != jd - 1.0
					&& festivalDetailMap.get(key + "_2").getFestDate() != jd - 2.0) {
				addFestivalToList(key + "_2", new FestivalDetail(festName, jd, festType, null));
			}
		}
	}

	private void putKeyInMapCheckContainNew(String key, double jd, int moonMonth, String[] arr,
			EnumContainer.FestType festType) {// = EnumContainer.FestType.OTHER
		if (!festivalDetailMap.containsKey(key)) {
			addFestivalToList(key, new FestivalDetail(arr[moonMonth % 12], jd, festType, null));
		} else if ((moonMonth == 9 || moonMonth == 10) && (festivalDetailMap.get(key).getFestDate() != jd)
				&& festivalDetailMap.get(key).getFestDate() != jd - 1.0
				&& festivalDetailMap.get(key).getFestDate() != jd - 2.0) {
			if (!festivalDetailMap.containsKey(key + "_2")) {
				addFestivalToList(key + "_2", new FestivalDetail(arr[moonMonth % 12], jd, festType, null));
			} else if (festivalDetailMap.get(key + "_2").getFestDate() != jd
					&& festivalDetailMap.get(key + "_2").getFestDate() != jd - 1.0
					&& festivalDetailMap.get(key + "_2").getFestDate() != jd - 2.0) {
				addFestivalToList(key + "_2", new FestivalDetail(arr[moonMonth % 12], jd, festType, null));
			}
		}
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

	private void addFestivalToList(String key, FestivalDetail festivalDetail) {
		festivalDetailMap.put(key, festivalDetail);
	}

	public HashMap<Integer, ArrayList<ArrayList<FestivalDetail>>> getFestivalList(int currentYear, int languageCode) {
		FestivalCalculation festivalCalculation = new FestivalCalculation(languageCode);
		List<FestivalDetail> festivalList = festivalCalculation.festivalsInYear(currentYear);

		Map<Double, ArrayList<FestivalDetail>> hm = new HashMap<>();

		for (int i = 0; i < festivalList.size(); i++) {
			FestivalDetail fest = festivalList.get(i);
			if (fest.getFestType() == EnumContainer.FestType.FESTIVALS
					|| fest.getFestType() == EnumContainer.FestType.FEST_SANKRANTI) {

				ArrayList<FestivalDetail> arrList = hm.get(fest.getFestDate());
				if (arrList == null) {
					arrList = new ArrayList<>();
				}

				arrList.add(fest);
				hm.put(fest.getFestDate(), arrList);
			}
		}

		Set<Double> keys = hm.keySet();
		HashMap<Integer, ArrayList<ArrayList<FestivalDetail>>> hashMap = new HashMap<>();

		for (int i = 0; i <= 11; i++) {
			hashMap.put(i, new ArrayList<>());
		}

		int index = 0;
		for (Double key : keys) {
			int[] dateArr = panUtil.fromJulian(key);

			if (dateArr[0] == currentYear) {

				int monthIndex = dateArr[1] - 1; // Because Jan = 1, ArrayList index = 0
				if (monthIndex >= 0 && monthIndex < 12) {
					ArrayList<ArrayList<FestivalDetail>> arrayList = hashMap.get(monthIndex);
					if (arrayList != null) {
						ArrayList<FestivalDetail> details = hm.get(key);
						if (details != null) {
							arrayList.add(details);
						}
					}
				}
			}
			index++;
		}

		return hashMap;
	}

}
