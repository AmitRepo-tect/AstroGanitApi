package com.astroganit.api.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class StatusImgUtil {

	public List<String> getSpecialDaysList(long time) {
		Calendar cal = (Calendar) Calendar.getInstance().clone();
		cal.setTimeInMillis(time);
		cal.add(Calendar.HOUR, 5);
		cal.add(Calendar.MINUTE, 30);
		TimeZone tz = TimeZone.getTimeZone("GMT");
		// cal.setTimeZone(tz);
		ArrayList<String> list = new ArrayList<String>();
		list.add(getDayGreeting(cal));
		list.add(getVar(cal));

		if (isRakshaBandhan(cal)) {
			list.add("raksha_bandhan");
		}
		if (isSavanMonth(cal)) {
			list.add("savan");
		}
		if (isJanmastami(cal)) {
			list.add("Janmastami");
		}
		return list;
	}

	String getDayGreeting(Calendar calendar) {
		String greeting;
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 5 && calendar.get(Calendar.HOUR_OF_DAY) < 12) {
			greeting = "Good Morning";
		} else if (calendar.get(Calendar.HOUR_OF_DAY) >= 12 && calendar.get(Calendar.HOUR_OF_DAY) < 16) {
			greeting = "Good Afternoon";
		} else if (calendar.get(Calendar.HOUR_OF_DAY) >= 16 && calendar.get(Calendar.HOUR_OF_DAY) < 20) {
			greeting = "Good Evening";
		} else {
			greeting = "good_night";
		}
		System.out.println("getDayGreeting--" + greeting + "--" + calendar.get(Calendar.HOUR_OF_DAY));
		return greeting;
	}

	String getVar(Calendar calendar) {
		String day;
		if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
			day = "shivji";
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
			day = "tuesday";
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
			day = "wednesday";
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
			day = "thrusday";
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
			day = "friday";
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
			day = "saturday";
		} else {
			day = "sunday";
		}
		System.out.println("Size--" + day);
		return day;
	}

	boolean isSavanMonth(Calendar calendar) {
		Calendar minCal = (Calendar) calendar.clone();
		minCal.set(Calendar.DATE, 22);
		minCal.set(Calendar.MONTH, 6);
		Calendar maxCal = (Calendar) calendar.clone();
		maxCal.set(Calendar.DATE, 20);
		maxCal.set(Calendar.MONTH, 7);
		if (calendar.after(minCal) && calendar.before(maxCal)) {
			return true;
		}
		return false;

	}

	boolean isRakshaBandhan(Calendar calendar) {
		if ((calendar.get(Calendar.DATE) == 18 || calendar.get(Calendar.DATE) == 19)
				&& calendar.get(Calendar.MONTH) == 7 && calendar.get(Calendar.YEAR) == 2024) {
			return true;
		}
		return false;
	}

	boolean isJanmastami(Calendar calendar) {
		if (calendar.get(Calendar.DATE) == 26 && calendar.get(Calendar.MONTH) == 7
				&& calendar.get(Calendar.YEAR) == 2024) {
			return true;
		}
		return false;
	}
}
