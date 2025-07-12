package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.astroganit.lib.panchang.util.IConstants;
import com.astroganit.lib.panchang.util.PanUtil;

public class PanchangBase {
	int fetchCompleteData = 1;
	int fetchOnlyValue = 2;
	int fetchOnlyTime = 3;
	int languageCode = 0;
	PanUtil panchangUtil = new PanUtil();
	IConstants constants;
	Date date;
	int[] panchakNakshtra = { 23, 24, 25, 26, 27 };
	BaseCalculationNew baseCalculationNew = new BaseCalculationNew();

	void initDate(Date date) {
		this.date = date;
	}

	boolean isVishtiKaran(Calendar calendar) {
		var boolVal = false;
		double[] karan = baseCalculationNew.panchangCalculation.karana(getJulianDate(calendar));
		String karanName = getString(karan, "Karan", getNextDaySunRise(calendar), fetchOnlyValue);
		if (karanName.contains("विष्टि") || karanName.contains("Vishti")) {
			boolVal = true;
		}
		;
		return boolVal;
	}

	ArrayList<String> getNakshtra(Calendar calendar) {
		ArrayList<String> nakshtraArr = new ArrayList<String>();
		double[] nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(calendar));
		// panchangModel.nakshatra = getString(nakshatraData, "Naksh",
		// getNextDaySunRise(), fetchCompleteData)
		nakshtraArr.add(getString(nakshatraData, "Naksh", getNextDaySunRise(calendar), fetchOnlyValue));
		nakshtraArr.add(getString(nakshatraData, "Naksh", getNextDaySunRise(calendar), fetchOnlyTime));
		return nakshtraArr;
	}

	boolean isDhanisthaNakshtra(Calendar calendar) {
		boolean boolVal = false;
		ArrayList<String> nakshtraArr = new ArrayList<String>();
		double[] nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(calendar));
		// panchangModel.nakshatra = getString(nakshatraData, "Naksh",
		// getNextDaySunRise(), fetchCompleteData)
		String nakshName = getString(nakshatraData, "Naksh", getNextDaySunRise(calendar), fetchOnlyValue);

		if (nakshName.contains("धनिष्ठा") || nakshName.contains("Dhanishta")) {
			boolVal = true;
		}
		return boolVal;
	}

	@SuppressWarnings("unlikely-arg-type")
	boolean isDhanisthaNakshtraNew(Calendar calendar) {
		var boolVal = false;
		double[] nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(calendar));
		List<Integer> panchakList = Arrays.stream(panchakNakshtra)
                .boxed()
                .collect(Collectors.toList());
		if (nakshatraData.length == 4) {
			if (panchakList.contains((int) nakshatraData[0])
					|| panchakList.contains((int) nakshatraData[2])) {
				boolVal = true;
			}
		} else {
			if (panchakList.contains((int) nakshatraData[0])) {
				boolVal = true;
			}
		}

		return boolVal;
	}

	double getJulianDate(Calendar calendar) {
		return baseCalculationNew.panchangCalculation.toJulian(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
	}

	public String getString(double[] dataArray, String stringTitle, double nextDaySunRise, int id) {
		StringBuilder result = new StringBuilder(); // Using StringBuilder for efficient string concatenation
		for (int i = 0; i < dataArray.length; i += 2) {
			switch (languageCode) {
			case 0: { // Assuming 0 represents the `Language.hi` case
				switch (id) {
				case 1:
					if (i != 0) {
						result.append(",\n");
					}
					result.append(getDay(dataArray[i], stringTitle)).append(" upto ")
							.append(getHoverStringFullNight(dataArray[i + 1], nextDaySunRise));
					break;

				case 2:
					if (i != 0) {
						result.append(",\n");
					}
					result.append(getDay(dataArray[i], stringTitle));
					break;

				default:
					if (i != 0) {
						result.append(",\n");
					}
					result.append(getHoverStringFullNight(dataArray[i + 1], nextDaySunRise));
					break;
				}
				break;
			}

			default: {
				switch (id) {
				case 1:
					if (i != 0) {
						result.append(",\n");
					}
					result.append(getDay(dataArray[i], stringTitle)).append(" upto ")
							.append(getHoverStringFullNight(dataArray[i + 1], nextDaySunRise));
					break;

				case 2:
					if (i != 0) {
						result.append(",\n");
					}
					result.append(getDay(dataArray[i], stringTitle));
					break;

				default:
					if (i != 0) {
						result.append(",\n");
					}
					result.append(getHoverStringFullNight(dataArray[i + 1], nextDaySunRise));
					break;
				}
				break;
			}
			}
		}
		return result.toString(); // Convert StringBuilder to String and return
	}

	double getNextDaySunRise(Calendar calendar) {
		return baseCalculationNew.panchangCalculation.getSunRise((int) getJulianDate(calendar) + 1);
	}

	String getDay(double dayValue, String title) {
		int day = (int) dayValue;
		String result = day + "";
		switch (title) {
		case "MoonSign":
			result = constants.getMoonSign(day);
			break;
		case "Tith":
			result = constants.getTithi(day);
			break;
		case "Naksh":
			result = constants.getNakshatra(day);
			break;
		case "Yog":
			result = constants.getYoga(day);
			break;
		case "Karan":
			result = constants.getKaranas(day);
			break;
		}
		return result;
	}

	private String getHoverStringFullNight(double actualTime, double nextDaySunRise) {
		String result;

		if (actualTime < 24.0) {
			result = panchangUtil.dms(actualTime);
		}

		else if (actualTime > nextDaySunRise + 24.00) {
			switch (languageCode) {
			case 0: {
				result = "पूर्ण रात्रि";
				break;
			}
			default: {
				result = "Full Night";
				break;
			}
			}
		}

		else {
			Date nextdate = panchangUtil.getAddDays(date, 1);
			Calendar calnPan = Calendar.getInstance();
			calnPan.setTime(nextdate);
			switch (languageCode) {
			case 0: {
				result = panchangUtil.dms(actualTime);
				;
				break;
			}
			default: {
				result = panchangUtil.dms(actualTime);
				;
				break;
			}
			}
		}

		return result;
	}

	ArrayList<String> getRahuKaalVela(Calendar calendar) {
		ArrayList<String> rahuKaalVelaArr = new ArrayList<String>();
		// panchangModel.rahuKaalVela = GetFromToString(fromRahuKaalVela,
		// toRahuKaalVela)
		rahuKaalVelaArr.add(panchangUtil.convertTimeToAmPm(
				panchangUtil.dms(getEightDivisions(calendar)[getRahuYamaGulikaKaal(calendar)[0] - 1])));
		rahuKaalVelaArr.add(panchangUtil
				.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar)[getRahuYamaGulikaKaal(calendar)[0]])));
		return rahuKaalVelaArr;
	}

	double[] getEightDivisions(Calendar calendar) {
		return baseCalculationNew.muhuratCalculation.getDayDivisons(getJulianDate(calendar),
				baseCalculationNew.muhuratCalculation.getSunRise(getJulianDate(calendar)), 8);
	}

	int[] getRahuYamaGulikaKaal(Calendar calendar) {

		return baseCalculationNew.muhuratCalculation.getRahuYamaGulikaKaal(getJulianDate(calendar));
	}

	ArrayList<String> getBhadraDataList(Calendar calendar) {
		ArrayList<String> list = new ArrayList<String>();
		double[] bhadraTime = baseCalculationNew.panchangCalculation.bhadraStartEndTime(getJulianDate(calendar));
		list.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(bhadraTime[0])));
		list.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(bhadraTime[1])));
		list.add(String.valueOf(bhadraTime[2]));
		return list;
	}

	ArrayList<String> getPanchakDataList(Calendar calendar) {
		ArrayList<String> list = new ArrayList<String>();
		double[] panchakTime = baseCalculationNew.panchangCalculation.panchakStartEndTime(getJulianDate(calendar));
		list.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(panchakTime[0])));
		list.add(panchangUtil.convertTimeToAmPm(panchangUtil.dms(panchakTime[1])));
		list.add(String.valueOf(panchakTime[2]));
		list.add(String.valueOf(panchakTime[3]));
		return list;
	}

	double getSunRiseTimeInDouble(Calendar calendar) {
		return baseCalculationNew.panchangCalculation.getSunRise(getJulianDate(calendar));
	}

	double getSunSetTimeInDouble(Calendar calendar) {
		return baseCalculationNew.panchangCalculation.getSunSet(getJulianDate(calendar));
	}
}
