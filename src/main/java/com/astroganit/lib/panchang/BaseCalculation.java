package com.astroganit.lib.panchang;

import com.astroganit.lib.panchang.util.Place;
import com.astroganit.lib.panchang.util.Time;
public class BaseCalculation {
	Place place;
	private SunTimeCalculation sunTimeCalculation;

	BaseCalculation() {
		place = new Place(28.644800, 77.216721, 5.5);
		sunTimeCalculation = new SunTimeCalculation();
	}

	public void initPlace(Place place) {
		this.place = place;
	}

	public  double toJulian(int year, int month, int day) {
		int JGREG = 588829;
		double HALFSECOND = 0.5D;
		int julianYear = year;
		if (year < 0) {
			julianYear = year + 1;
		}

		int julianMonth;
		if (month > 2) {
			julianMonth = month + 1;
		} else {
			--julianYear;
			julianMonth = month + 13;
		}

		double julian = Math.floor(365.25D * (double) julianYear) + Math.floor(30.6001D * (double) julianMonth)
				+ (double) day + 1720995.0D;
		if (day + 31 * (month + 12 * year) >= JGREG) {
			int ja = (int) (0.01D * (double) julianYear);
			julian += (double) (2 - ja) + 0.25D * (double) ja;
		}

		return Math.floor(julian);
	}

	public static int[] fromJulian(double injulian) {
		int JGREG = 588829;
		double HALFSECOND = 0.5D;
		double julian = injulian + HALFSECOND / 86400.0D;
		int ja = (int) julian;
		if (ja >= JGREG) {
			int jalpha = (int) (((double) (ja - 1867216) - 0.25D) / 36524.25D);
			ja = ja + 1 + jalpha - jalpha / 4;
		}

		int jb = ja + 1524;
		int jc = (int) (6680.0D + ((double) (jb - 2439870) - 122.1D) / 365.25D);
		int jd = 365 * jc + jc / 4;
		int je = (int) ((double) (jb - jd) / 30.6001D);
		int day = jb - jd - (int) (30.6001D * (double) je);
		int month = je - 1;
		if (month > 12) {
			month -= 12;
		}

		int year = jc - 4715;
		if (month > 2) {
			--year;
		}

		if (year <= 0) {
			--year;
		}

		return new int[] { year, month, day };
	}

	private double getSunRise(int yr, int mon, int date, double lg, int lt, int tz) {
		double sunrise = 0.0D;
		try {
			Time t1 = sunTimeCalculation.getSunriseTimeUTC(yr, mon, date, lg, lt, 90.83333333333333);
			sunrise = decimal(t1.hour, t1.minute, t1.second) + tz;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return correct(sunrise);
	}

	public double getSunRise(double jd) {
		int[] fromJul = fromJulian(jd);
		int yr = fromJul[0];
		int mon = fromJul[1];
		int date = fromJul[2];
		double lg = place.longitude;
		double lt = place.latitude;
		double tz = place.timezone;
		return getSunRise(yr, mon, date, lg, lt, tz);
	}

	private double getSunRise(int yr, int mon, int date, double lg, double lt, double tz) {
		double sunrise = 0.0;
		try {
			Time t1 = sunTimeCalculation.getSunriseTimeUTC(yr, mon, date, lg, lt, 90.83333333333333);
			sunrise = decimal(t1.hour, t1.minute, t1.second) + tz;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return correct(sunrise);
	}

	public static double decimal(int deg, int min, int sec) {
		double temp = (double) ((deg * 60 + min) * 60 + sec);
		double res = temp / 3600.0D;
		return res;
	}

	public double getSunSet(double jd) {
		int[] fromJul = fromJulian(jd);
		int yr = fromJul[0];
		int mon = fromJul[1];
		int date = fromJul[2];
		double lg = place.longitude;
		double lt = place.latitude;
		double tz = place.timezone;
		return getSunSet(yr, mon, date, lg, lt, tz);
	}

	private double getSunSet(int yr, int mon, int date, double lg, double lt, double tz) {
		double sunset = 0.0D;
		try {
			Time t2 = sunTimeCalculation.getSunsetTimeUTC(yr, mon, date, lg, lt, 90.83333333333333);
			sunset = decimal(t2.hour, t2.minute, t2.second) + tz;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return correct(sunset);
	}

	public double inverseLagrange(double[] x, double[] y, double ya) {
		double total = 0.0D;

		for (int i = 0; i < x.length; ++i) {
			double numer = 1.0D;
			double denom = 1.0D;

			for (int j = 0; j < x.length; ++j) {
				if (j != i) {
					numer *= ya - y[j];
					denom *= y[i] - y[j];
				}
			}

			total += numer * x[i] / denom;
		}

		return total;
	}

	private double correct(double n) {
		double val;
		if (n < 0.0) {
			val = n % 24.0 + 24.0;
		} else {
			val = n % 24.0;
		}
		return val;
	}

	double degreeDifference(double degreeFrom, double degree) {
		double difference = degreeFrom - degree;
		if (difference < 0.0D) {
			difference += 360.0D;
		}

		return difference;
	}

	double mod(double value, double base) {
		double modulus = value % base;
		return (int) value == (int) base ? base : modulus;
	}
}
