package com.astroganit.lib.panchang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.astroganit.lib.panchang.model.PanchakTimeBean;
import com.astroganit.lib.panchang.util.PanUtil;

public class PanchakCalculation extends PanchangBase {
	PanUtil panUtil = new PanUtil();

	private ArrayList<int[]> getPanchakDateArr(Calendar cal, int month) {
		ArrayList<int[]> arrayList = new ArrayList<int[]>();
		Calendar calendar = (Calendar) cal.clone();
		calendar.set(Calendar.DATE, 1);
		//calendar.set(Calendar.MONTH, month);
		int selectedMonth = calendar.get(Calendar.MONTH);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		while (calendar.get(Calendar.DATE) != days + 1 && calendar.get(Calendar.MONTH) == selectedMonth) {

			if (isDhanisthaNakshtraNew(calendar)) {
				int[] intArray = { calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.YEAR) };
				arrayList.add(intArray);
				calendar.add(Calendar.DATE, 10);
				continue;
			}
			calendar.add(Calendar.DATE, 1);
		}
		return arrayList;
	}

	public ArrayList<PanchakTimeBean> getPanchakData(Calendar cal, int month) {

		ArrayList<PanchakTimeBean> arrayList = new ArrayList<PanchakTimeBean>();
		ArrayList<int[]> panchakDateArr = getPanchakDateArr(cal, month);
		for (int i = 0; i < panchakDateArr.size(); i++) {

			int arr[] = panchakDateArr.get(i);
			Calendar calendar = (Calendar) cal.clone();
			calendar.set(Calendar.DATE, arr[0]);
			calendar.set(Calendar.MONTH, arr[1]);
			calendar.set(Calendar.YEAR, arr[2]);
			double[] dhanisthaData = getNaksData(calendar, 23);
			double[] shravanData = getNaksData(calendar, 22);
			String panchakStartTime = getPanchakStartDate(shravanData[1], dhanisthaData[1]);
			if (panchakStartTime.contains("+")) {
				panchakStartTime = panchakStartTime.replace("+", "");
				calendar.add(Calendar.DATE, 1);
			}
			int panchakStartDate = calendar.get(Calendar.DATE);
			int panchakStartMonth = calendar.get(Calendar.MONTH);
			int panchakStartYear = calendar.get(Calendar.YEAR);
			int panchakStartVar = calendar.get(Calendar.DAY_OF_WEEK);

			double[] revatiData = getNaksData(calendar, 27);
			String panchakEndTime = panUtil.convertTimeToAmPm(panchangUtil.dms(revatiData[1]));
			if (panchakEndTime.contains("+")) {
				panchakEndTime = panchakEndTime.replace("+", "");
				calendar.add(Calendar.DATE, 1);
			}
			int panchakEndDate = calendar.get(Calendar.DATE);
			int panchakEndMonth = calendar.get(Calendar.MONTH);
			int panchakEndYear = calendar.get(Calendar.YEAR);
			int panchakEndVar = calendar.get(Calendar.DAY_OF_WEEK);
			arrayList.add(new PanchakTimeBean(String.valueOf(panchakStartDate), String.valueOf(panchakEndDate),
					String.valueOf(panchakStartVar), String.valueOf(panchakEndVar), panchakStartTime, panchakEndTime,
					String.valueOf(panchakStartMonth), String.valueOf(panchakEndMonth),
					String.valueOf(panchakStartYear), String.valueOf(panchakEndYear)));
		}

		return arrayList;
	}

	private double[] getNaksData(Calendar cal, int naksh) {
		double[] arr = { 0.0, 0.0 };
		double[] nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(cal));
		if (nakshatraData.length == 4 && (int) nakshatraData[0] == naksh) {
			arr[0] = nakshatraData[0];
			arr[1] = nakshatraData[1];
		} else if (nakshatraData.length == 4 && (int) nakshatraData[2] == naksh) {
			arr[0] = nakshatraData[2];
			arr[1] = nakshatraData[3];
		} else if (nakshatraData.length == 4 && (int) nakshatraData[0] > naksh) {
			while (((int) nakshatraData[0] != naksh)
					|| (nakshatraData.length == 4 && (int) nakshatraData[2] != naksh)) {
				nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(cal));
				if (nakshatraData.length == 4 && (int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
				} else if (nakshatraData.length == 4 && (int) nakshatraData[2] == naksh) {
					arr[0] = nakshatraData[2];
					arr[1] = nakshatraData[3];
				} else if ((int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
				} else {
					cal.add(Calendar.DATE, -1);
				}
			}
		} else if (nakshatraData.length == 4 && (int) nakshatraData[2] < naksh) {
			while (((int) nakshatraData[0] != naksh)
					|| (nakshatraData.length == 4 && (int) nakshatraData[2] != naksh)) {
				nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(cal));
				if (nakshatraData.length == 4 && (int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
				} else if (nakshatraData.length == 4 && (int) nakshatraData[2] == naksh) {
					arr[0] = nakshatraData[2];
					arr[1] = nakshatraData[3];
				} else if ((int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
				} else {
					cal.add(Calendar.DATE, 1);
				}
			}
		} else if ((int) nakshatraData[0] == naksh) {
			arr[0] = nakshatraData[0];
			arr[1] = nakshatraData[1];
		} else if ((int) nakshatraData[0] > naksh) {
			while (true) {
				nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(cal));
				if (nakshatraData.length == 4 && (int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
					break;
				} else if (nakshatraData.length == 4 && (int) nakshatraData[2] == naksh) {
					arr[0] = nakshatraData[2];
					arr[1] = nakshatraData[3];
					break;
				} else if ((int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
					break;
				} else {
					cal.add(Calendar.DATE, -1);
				}
			}
		} else if ((int) nakshatraData[0] < naksh) {
			while (true) {
				nakshatraData = baseCalculationNew.panchangCalculation.nakshatra(getJulianDate(cal));
				if (nakshatraData.length == 4 && (int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
					break;
				} else if (nakshatraData.length == 4 && (int) nakshatraData[2] == naksh) {
					arr[0] = nakshatraData[2];
					arr[1] = nakshatraData[3];
					break;
				} else if ((int) nakshatraData[0] == naksh) {
					arr[0] = nakshatraData[0];
					arr[1] = nakshatraData[1];
					break;
				} else {
					cal.add(Calendar.DATE, 1);
				}
			}
		}
		return arr;
	}

	private String getPanchakStartDate(double date1, double date2) {
		var prefix = "";
		var dateStr1 = panUtil.convertTimeToAmPm(panchangUtil.dms(date1));
		if (dateStr1.contains("+")) {
			prefix = "+";
		}
		var dateStr2 = panUtil.convertTimeToAmPm(panchangUtil.dms(date2));
		ArrayList<String> dateArr1;
		ArrayList<String> dateArr2;
		if (dateStr1.contains("AM")) {
			dateStr1 = dateStr1.replace("AM", "").replace("+", "").trim();
			dateArr1 = new ArrayList<>(Arrays.asList(dateStr1.split(":")));
		} else {
			dateStr1 = dateStr1.replace("PM", "").replace("+", "").trim();
			dateArr1 = new ArrayList<>(Arrays.asList(dateStr1.split(":")));
			int updatedHour = 12 + Integer.parseInt(dateArr1.get(0));
			dateArr1.set(0, String.valueOf(updatedHour));
		}

		if (dateStr2.contains("AM")) {
			if (dateStr2.contains("+")) {
				dateStr2 = dateStr2.replace("AM", "").replace("+", "").trim();
				dateArr2 = new ArrayList<>(Arrays.asList(dateStr2.split(":")));
				// dateArr2[0] = (24 + dateArr2[0].toInt()).toString()
			} else {
				dateStr2 = dateStr2.replace("AM", "").replace("+", "").trim();
				dateArr2 = new ArrayList<>(Arrays.asList(dateStr2.split(":")));
			}

		} else {
			dateStr2 = dateStr2.replace("PM", "").replace("+", "").trim();
			dateArr2 = new ArrayList<>(Arrays.asList(dateStr2.split(":")));
			int updatedHour = 12 + Integer.parseInt(dateArr2.get(0));
			dateArr1.set(0, String.valueOf(updatedHour));
		}
		int hours = Integer.parseInt(dateArr1.get(0));
		int minutes = Integer.parseInt(dateArr1.get(1));
		int minDiff = (24 * 60) - (hours * 60) - minutes;

		int hourPart = Integer.parseInt(dateArr2.get(0));
		int minutePart = Integer.parseInt(dateArr2.get(1));
		int minute = minDiff + (hourPart * 60) + minutePart;

		minute /= 2;
		minute += Integer.parseInt(dateArr1.get(0)) * 60 + Integer.parseInt(dateArr1.get(1));

		int hrs = minute / 60;
		int min = minute % 60;

		String timeStr;
		if (hrs > 24) {
			hrs -= 24;
			timeStr = String.valueOf(hrs) + ":" + min + "AM";
			prefix = "+";
			if (min < 10) {
				timeStr = String.valueOf(hrs) + ":0" + min + "AM";
			}
		} else if (hrs > 12) {
			hrs -= 12;
			timeStr = String.valueOf(hrs) + ":" + min + "PM";
			if (min < 10) {
				timeStr = String.valueOf(hrs) + ":0" + min + "PM";
			}
		} else {
			timeStr = String.valueOf(hrs) + ":" + min + "AM";
			if (min < 10) {
				timeStr = String.valueOf(hrs) + ":0" + min + "AM";
			}
		}
		return prefix + timeStr;
	}

}