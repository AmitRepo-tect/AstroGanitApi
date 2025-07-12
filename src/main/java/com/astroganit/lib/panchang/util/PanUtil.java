package com.astroganit.lib.panchang.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PanUtil {
	public static double fract(double x) {
		long i;
		double y;
		i = (long) x;
		y = x - (double) i;
		return y;
	}

	public static String makelength(String x, int y) {
		int diff = y - x.length();
		for (int i = 0; i < diff; i++)
			x = "0" + x;
		return x;
	}

	public String dms(double x) {
		String parts[] = new String[3];
		double temp;
		String sdms;
		int deg = (int) x;
		parts[0] = makelength(String.valueOf(deg), 2);
		temp = (x - (double) ((int) x));
		int min = (int) (temp * 60);
		parts[1] = makelength(String.valueOf(min), 2);
		temp = temp * 60;
		temp = (temp - (double) ((int) temp));
		int sec = (int) (temp * 60);
		parts[2] = makelength(String.valueOf(sec), 2);
		sdms = parts[0] + getDashString(1) + parts[1] + getDashString(1) + parts[2];
		return sdms;
	}

	public String getDashString(int noOfDash) {
		String DASH = "";
		if (getLanguageCode().equalsIgnoreCase("0")) {
			DASH = ":";
		} else if (getLanguageCode().equalsIgnoreCase("1")) {
			DASH = "&";
		}
		String dash = "";
		for (int i = 0; i < noOfDash; i++) {
			dash = dash + DASH;
		}
		return dash;
	}

	public String getLanguageCode() {
		return "0";
	}

	// *********** Increment in Date Days ***************
	public Date getAddDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	// ***************** fetch DST or not from Time_Zone_String ***************
	public boolean isDst(String timezoneString, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		/*
		 * cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 0);
		 * cal.set(Calendar.SECOND, 0); cal.set(Calendar.MILLISECOND, 0);
		 */
		Date dateTime = cal.getTime();
		boolean inDs;
		try {
			TimeZone tz = TimeZone.getTimeZone(timezoneString);
			inDs = tz.inDaylightTime(dateTime);
		} catch (Exception ex) {
			inDs = false;
		}
		return inDs;
	}

	// ********* Get Constant Class Object **********
	public IConstants getIConstantsObj(String language) {
		/*
		 * if (language.equals("en")) { return (new ConstantEn()); }
		 */
		return (new ConstantsHi());
	}

	public String convertTimeToAmPm(String time) {
		int hr;
		int min;
		String timeWithMeradian;
		String[] splitTime = time.split(":");
		try {
			hr = Integer.parseInt(splitTime[0]);
			min = Integer.parseInt(splitTime[1]);

			// am
			if (hr < 12) {
				timeWithMeradian = appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " AM";
			} else if (hr < 24) { // pm
				if (hr == 12) {
					hr = 12;
				} else {
					hr = hr - 12;
				}
				;
				timeWithMeradian = appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " PM";
			} else { // more than 24
				hr -= 24;
				timeWithMeradian = "+" + appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " AM";
			}
		} catch (Exception ex) {
			timeWithMeradian = time;
		}
		return timeWithMeradian;
	}

	private String appendZeroOnSingleDigit(int time) {
		String rTime = Integer.toString(time);
		if (time < 10) {
			rTime = "0" + time;
		}
		return rTime;
	}

	public boolean isTimeBetweenTwoTime(String initialTime, String finalTime) {
		String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
		boolean valid = false;

		try {
			// Validate time format
			if (initialTime.matches(reg) && finalTime.matches(reg)) {

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

				// Get current time
				Date currentTime = Calendar.getInstance().getTime();
				Date inTime = sdf.parse(initialTime);
				Date finTime = sdf.parse(finalTime);

				// Adjust if final time is earlier than initial time (spanning midnight)
				if (finTime.before(inTime)) {
					finTime = new Date(finTime.getTime() + 24 * 60 * 60 * 1000); // add 1 day (24 hours)
				}

				// Compare current time to initial and final time
				if (!currentTime.before(inTime) && currentTime.before(finTime)) {
					valid = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception for better debugging
		}

		return valid;
	}

	public String FormatDMSIn2DigitStringWithSignForhora(double _fDeg) {
		String strFormattedDeg = null;
		String sDeg = null;
		String sMin = null;
		String sSec = null;
		int _min = 0, _sec = 0;
		double temp = 0;

		double fDeg = _fDeg;
		sDeg = String.valueOf((int) (fDeg));
		switch (sDeg.trim().length()) {
		case 0:
			strFormattedDeg = "00" + sDeg;
			break;
		case 1:
			strFormattedDeg = "0" + sDeg;
			break;
		default:
			strFormattedDeg = sDeg;
			break;
		}

		strFormattedDeg = strFormattedDeg + ":";
		temp = (_fDeg - (double) ((int) _fDeg));
		_min = (int) (temp * 60);
		sMin = String.valueOf(_min);

		if (sMin.trim().length() < 2) {
			strFormattedDeg = strFormattedDeg + "0" + sMin;
		} else {
			strFormattedDeg = strFormattedDeg + sMin;
		}

		strFormattedDeg = strFormattedDeg + ":";

		temp = temp * 60;
		temp = (temp - (double) ((int) temp));
		_sec = (int) (temp * 60);
		sSec = String.valueOf(_sec);

		if (sSec.trim().length() < 2)
			strFormattedDeg = strFormattedDeg + "0" + sSec;
		else
			strFormattedDeg = strFormattedDeg + sSec;

		strFormattedDeg = strFormattedDeg;

		return strFormattedDeg;
	}

	public String getDateShowRahuKaal(Date date) {
		String inputPattern = "EEE MMM dd HH:mm:ss zzzz yyyy";
		String outputPattern = "dd MMM, yyyy";
		SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
		SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

		Date dateNew = null;
		String str = null;

		try {
			// dateNew = inputFormat.parse(date);
			str = outputFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public String[] getMonthList(int languageCode) {

		if (languageCode != 1) {
			String[] monthList = { "जनवरी", "फरवरी", "मार्च", "अप्रैल", "मई", "जून", "जुलाई", "अगस्त", "सितम्बर",
					"अक्टूबर", "नवम्बर", "दिसम्बर" };
			return monthList;
		} else {
			String[] monthList = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
					"October", "November", "December" };
			return monthList;
		}

	}

	public String[] getWeekList(int languageCode) {
		if (languageCode != 1) {
			String[] days = { "रविवार", "सोमवार", "मंगलवार", "बुधवार", "गुरुवार", "शुक्रवार", "शनिवार" };
			return days;
		} else {
			String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
			return days;
		}
	}

	public int[] fromJulian(Double injulian) {
		int JGREG = 588829;
		double HALFSECOND = 0.5;
		double julian = injulian + HALFSECOND / 86400.0;
		var ja = (int) julian;
		if (ja >= JGREG) {
			int jalpha = (int) (((double) (ja - 1867216) - 0.25) / 36524.25);
			ja = ja + 1 + jalpha - jalpha / 4;
		}
		int jb = ja + 1524;
		int jc = (int) (6680.0 + ((double) (jb - 2439870) - 122.1) / 365.25);
		int jd = 365 * jc + jc / 4;
		int je = (int) ((double) (jb - jd) / 30.6001);
		int day = jb - jd - (int) (30.6001 * (double) je);
		var month = je - 1;
		if (month > 12) {
			month -= 12;
		}
		var year = jc - 4715;
		if (month > 2) {
			--year;
		}
		if (year <= 0) {
			--year;
		}
		int[] arr = { year, month, day };
		return arr;
	}

}
