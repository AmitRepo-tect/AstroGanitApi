package com.astroganit.lib.horo.util;

public class Util {
	double[] y1 = { 7.0, 20.0, 6.0, 10.0, 7.0, 18.0, 16.0, 19.0, 17.0 };

	public static double fract(double x) {
		long var2 = (long) x;
		return x - (double) var2;
	}

	int getSub(double d) {
		double dval = d;
		int var4 = 0;
		int var2 = (int) (dval / 120.0);
		dval -= (double) ((int) (var2 * 120.0));
		var2 = (int) (dval * 3.0 / 40.0);
		dval = (dval - (double) var2 * 40.0 / 3.0) * 9.0;
		for (int var3 = 0; var3 <= 8; var3++) {
			var4 = var3;
			if (var2 + var3 >= 9) {
				var4 -= 9;
			}
			if (y1[var4] > d) {
				break;
			}
			dval -= y1[var4];
		}
		return var4;
	}

	public static String dms(double x, String lang) {
		String[] var3 = new String[3];
		int var4 = (int) x;
		var3[0] = makelength(String.valueOf(var4), 3);
		double var7 = x - (double) var4;
		var x1 = (int) (var7 * 60.0);
		var3[1] = makelength(String.valueOf(x1), 2);
		var7 *= 60.0;
		x1 = (int) ((var7 - (double) x1) * 60.0 + 0.5);
		var3[2] = makelength(String.valueOf(x1), 2);
		return var3[0] + getDashString(1, lang) + var3[1] + getDashString(1, lang) + var3[2];
	}

	public static String makelength(String x, int y) {
		// Subtract the length of x from y
		y -= x.length();

		// Use a loop to prepend '0' to x
		for (int var2 = 0; var2 < y; var2++) {
			x = "0" + x; // Prepend "0" to the string x
		}

		return x;
	}

	private static String getDashString(int noOfDash, String lang) {
		String var2 = "";

		if (lang.equalsIgnoreCase("0")) {
			var2 = "-";
		} else if (lang.equalsIgnoreCase("1")) {
			var2 = "&";
		}

		StringBuilder var3 = new StringBuilder(); // Use StringBuilder for efficient string concatenation

		for (int var4 = 0; var4 < noOfDash; var4++) {
			var3.append(var2); // Append the string
		}

		return var3.toString(); // Convert StringBuilder back to String
	}

	public static String hms(double x) {
		String[] var3 = new String[3];
		int var4 = (int) x;
		var3[0] = makelength(String.valueOf(var4), 2);
		var var7 = x - (double) var4;
		var x1 = (int) (var7 * 60.0);
		var3[1] = makelength(String.valueOf(x1), 2);
		var7 *= 60.0;
		x1 = (int) ((var7 - (double) x1) * 60.0);
		var3[2] = makelength(String.valueOf(x1), 2);
		return var3[0] + ":" + var3[1] + ":" + var3[2];
	}

	public static String getSlashString(String lang) {
		String var1 = "";

		if (lang.equalsIgnoreCase("0")) {
			var1 = "/";
		} else if (lang.equalsIgnoreCase("1")) {
			var1 = "@";
		}

		return var1;
	}

	int[] getDHMS(double x) {
		int[] var2 = new int[4];
		int var3 = (int) x;
		var2[0] = var3;
		if (var2[0] > 6) {
			var2[0] %= 7;
		}
		double var6 = x - (double) var3;
		int x1 = (int) (var6 * 24.0);
		var2[1] = x1;
		var6 = var6 * 24.0 - (double) x1;
		x1 = (int) (var6 * 60.0);
		var2[2] = x1;
		var6 = var6 * 60.0 - (double) x1;
		x1 = (int) (var6 * 60.0);
		var2[3] = x1;
		return var2;
	}

	double makeDHMStoDouble(int day, int hour, int min, int sec) {
		return (double) day + (double) hour / 24.0 + (double) min / 1440.0 + (double) sec / 86400.0;
	}

	public static double kpayan(double dd, double mm, double yy) {
		return (yy + (mm * 30.0 + dd) / 365.0 - 297.3204723) * 50.2388475 / 3600.0;
	}

	public static double kpayannew(double dd, double mm, double yy) {
		double var8 = 22.0 + (1335.0 + (yy - 1900.0) * 50.2388475) / 3600.0
				+ (yy - 1900.0) * (yy - 1900.0) * 1.11E-4 / 3600.0;
		double var12 = ((mm - 1.0) * 30.0 + (dd - 1.0)) / 3600.0 / 365.0 * 50.2410675;
		return var8 + var12;
	}

	public static double kpayankhu(double dd, double mm, double yy) {
		double var8 = (yy - 291.0) * 365.25 + (mm * 30.0 + dd - 114.0);
		return var8 * 0.1375464681724846 / 3600.0;
	}

	public static double atand(double A) {
		return Math.atan(A) * 57.29577951308232;
	}

	public static double asind(double B) {
		return Math.asin(B) * 57.29577951308232;
	}

	public static double cosd(double d) {
		return Math.cos(d * 0.017453292519943295);
	}

	public static double tand(double z) {
		return Math.tan(z * 0.017453292519943295);
	}

	public static double sind(double x) {
		return Math.sin(x * 0.017453292519943295);
	}
}