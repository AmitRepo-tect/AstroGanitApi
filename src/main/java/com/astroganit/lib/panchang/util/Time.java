package com.astroganit.lib.panchang.util;

public class Time {
	public int hour = 0;
	public int minute = 0;
	public int second = 0;

	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public Time(double timeDouble) {
		double var1 = timeDouble;
		while (var1 < 0.0) {
			var1 += 24.0;
		}
		while (var1 >= 24.0) {
			var1 -= 24.0;
		}
		hour = (int) var1;
		minute = (int) ((var1 - (double) hour) * 60.0);
		second = (int) (((var1 - (double) hour) * 60.0 - (double) minute) * 60.0);
	}

	double getFractionalHours() {
		return (double) hour + (double) minute / 60.0 + (double) second / 3600.0;
	}
}
