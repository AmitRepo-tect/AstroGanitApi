package com.astroganit.lib.panchang;

import com.astroganit.lib.panchang.util.Time;

public class SunTimeCalculation {
	private static double sinDeg(double var0) {
		return Math.sin(var0 * 2.0D * 3.141592653589793D / 360.0D);
	}

	private static double acosDeg(double var0) {
		return Math.acos(var0) * 360.0D / 6.283185307179586D;
	}

	private static double asinDeg(double var0) {
		return Math.asin(var0) * 360.0D / 6.283185307179586D;
	}

	private static double tanDeg(double var0) {
		return Math.tan(var0 * 2.0D * 3.141592653589793D / 360.0D);
	}

	private static double cosDeg(double var0) {
		return Math.cos(var0 * 2.0D * 3.141592653589793D / 360.0D);
	}

	private static int getDayOfYear(int var0, int var1, int var2) {
		int var3 = 275 * var1 / 9;
		int var4 = (var1 + 9) / 12;
		int var5 = 1 + (var0 - 4 * (var0 / 4) + 2) / 3;
		int var6 = var3 - var4 * var5 + var2 - 30;
		return var6;
	}

	private static double getHoursFromMeridian(double var0) {
		return var0 / 15.0D;
	}

	private static double getApproxTimeDays(int var0, double var1, int var3) {
		return var3 == 0 ? (double) var0 + (6.0D - var1) / 24.0D : (double) var0 + (18.0D - var1) / 24.0D;
	}

	private static double getMeanAnomaly(int var0, double var1, int var3) {
		return 0.9856D * getApproxTimeDays(var0, getHoursFromMeridian(var1), var3) - 3.289D;
	}

	private static double getSunTrueLongitude(double var0) {
		double var2 = var0 + 1.916D * sinDeg(var0) + 0.02D * sinDeg(2.0D * var0) + 282.634D;
		if (var2 >= 360.0D) {
			var2 -= 360.0D;
		}

		if (var2 < 0.0D) {
			var2 += 360.0D;
		}

		return var2;
	}

	private static double getSunRightAscensionHours(double var0) {
		double var2 = 0.91764D * tanDeg(var0);
		double var4 = 57.29577951308232D * Math.atan(var2);
		double var6 = Math.floor(var0 / 90.0D) * 90.0D;
		double var8 = Math.floor(var4 / 90.0D) * 90.0D;
		var4 += var6 - var8;
		return var4 / 15.0D;
	}

	private static double getCosLocalHourAngle(double var0, double var2, double var4) {
		double var6 = 0.39782D * sinDeg(var0);
		double var8 = cosDeg(asinDeg(var6));
		double var10 = (cosDeg(var4) - var6 * sinDeg(var2)) / (var8 * cosDeg(var2));
		return var10;
	}

	private static double getCosLocalHourAngle(double var0, double var2) {
		return getCosLocalHourAngle(var0, var2, 90.83333333333333D);
	}

	private static double getLocalMeanTime(double var0, double var2, double var4) {
		return var0 + var2 - 0.06571D * var4 - 6.622D;
	}

	private static Time getTimeUTC(int var0, int var1, int var2, double var3, double var5, double var7, int var9)
			throws Exception {
		int var10 = getDayOfYear(var0, var1, var2);
		double var11 = getMeanAnomaly(var10, var3, var9);
		double var13 = getSunTrueLongitude(var11);
		double var15 = getSunRightAscensionHours(var13);
		double var17 = getCosLocalHourAngle(var13, var5, var7);
		double var19;
		if (var9 == 0) {
			if (var17 > 1.0D) {
				throw new Exception("Sun never rises on the specified date at the specified location");
			}

			var19 = 360.0D - acosDeg(var17);
		} else {
			if (var9 != 1) {
				throw new Exception("Internal error");
			}

			if (var17 < -1.0D) {
				throw new Exception("Sun never sets on the specified date at the specified location");
			}

			var19 = acosDeg(var17);
		}

		double var21 = var19 / 15.0D;
		double var23 = getLocalMeanTime(var21, var15, getApproxTimeDays(var10, getHoursFromMeridian(var3), var9));
		return new Time(var23 - getHoursFromMeridian(var3));
	}

	public static Time getSunriseTimeUTC(int var0, int var1, int var2, double var3, double var5, double var7)
			throws Exception {
		return getTimeUTC(var0, var1, var2, var3, var5, var7, 0);
	}

	public static Time getSunsetTimeUTC(int var0, int var1, int var2, double var3, double var5, double var7)
			throws Exception {
		return getTimeUTC(var0, var1, var2, var3, var5, var7, 1);
	}
}
