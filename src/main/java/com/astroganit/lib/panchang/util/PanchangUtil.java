package com.astroganit.lib.panchang.util;

public class PanchangUtil {
	public static double interpolate(double f0, double f1, double f2, double p) {
		double a = f1 - f0;
		double b = f2 - f1 - a;
		double f = f0 + p * (2.0D * a + b * (2.0D * p - 1.0D));
		return f;
	}
}
