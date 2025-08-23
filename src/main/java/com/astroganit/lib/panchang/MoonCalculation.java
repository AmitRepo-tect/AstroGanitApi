package com.astroganit.lib.panchang;

import com.astroganit.lib.panchang.util.PanchangUtil;

public class MoonCalculation extends BaseCalculation {

	double PI = 3.141592653589793D;
	double DR = 0.0;
	double K1 = 0.0;
	boolean moonRise = false;
	boolean moonSet = false;
	int[] riseTime = new int[2];
	int[] setTime = new int[2];
	double riseAZ = 0.0;
	double setAZ = 0.0;
	double[] sky = { 0.0, 0.0, 0.0 };
	double[] ran = { 0.0, 0.0, 0.0 };
	double[] dec = { 0.0, 0.0, 0.0 };
	double[] vhz = { 0.0, 0.0, 0.0 };

	public MoonCalculation() {
		DR = PI / 180.0;
		K1 = 15.0 * DR * 1.0027379;
	}

	private void calcMoon(double jd) {
		double h = 0.606434D + 0.03660110129D * jd;
		double m = 0.374897D + 0.03629164709D * jd;
		double f = 0.259091D + 0.0367481952D * jd;
		double d = 0.827362D + 0.03386319198D * jd;
		double n = 0.347343D - 1.4709391E-4D * jd;
		double g = 0.993126D + 0.0027377785D * jd;
		h -= Math.floor(h);
		m -= Math.floor(m);
		f -= Math.floor(f);
		d -= Math.floor(d);
		n -= Math.floor(n);
		g -= Math.floor(g);
		h = h * 2.0D * this.PI;
		m = m * 2.0D * this.PI;
		f = f * 2.0D * this.PI;
		d = d * 2.0D * this.PI;
		n = n * 2.0D * this.PI;
		g = g * 2.0D * this.PI;
		double v = 0.39558D * Math.sin(f + n);
		v += 0.082D * Math.sin(f);
		v += 0.03257D * Math.sin(m - f - n);
		v += 0.01092D * Math.sin(m + f + n);
		v += 0.00666D * Math.sin(m - f);
		v -= 0.00644D * Math.sin(m + f - 2.0D * d + n);
		v -= 0.00331D * Math.sin(f - 2.0D * d + n);
		v -= 0.00304D * Math.sin(f - 2.0D * d);
		v -= 0.0024D * Math.sin(m - f - 2.0D * d - n);
		v += 0.00226D * Math.sin(m + f);
		v -= 0.00108D * Math.sin(m + f - 2.0D * d);
		v -= 7.9E-4D * Math.sin(f - n);
		v += 7.8E-4D * Math.sin(f + 2.0D * d + n);
		double u = 1.0D - 0.10828D * Math.cos(m);
		u -= 0.0188D * Math.cos(m - 2.0D * d);
		u -= 0.01479D * Math.cos(2.0D * d);
		u += 0.00181D * Math.cos(2.0D * m - 2.0D * d);
		u -= 0.00147D * Math.cos(2.0D * m);
		u -= 0.00105D * Math.cos(2.0D * d - g);
		u -= 7.5E-4D * Math.cos(m - 2.0D * d + g);
		double w = 0.10478D * Math.sin(m);
		w -= 0.04105D * Math.sin(2.0D * f + 2.0D * n);
		w -= 0.0213D * Math.sin(m - 2.0D * d);
		w -= 0.01779D * Math.sin(2.0D * f + n);
		w += 0.01774D * Math.sin(n);
		w += 0.00987D * Math.sin(2.0D * d);
		w -= 0.00338D * Math.sin(m - 2.0D * f - 2.0D * n);
		w -= 0.00309D * Math.sin(g);
		w -= 0.0019D * Math.sin(2.0D * f);
		w -= 0.00144D * Math.sin(m + n);
		w -= 0.00144D * Math.sin(m - 2.0D * f - n);
		w -= 0.00113D * Math.sin(m + 2.0D * f + 2.0D * n);
		w -= 9.4E-4D * Math.sin(m - 2.0D * d + g);
		w -= 9.2E-4D * Math.sin(2.0D * m - 2.0D * d);
		double s = w / Math.sqrt(u - v * v);
		this.sky[0] = h + Math.atan(s / Math.sqrt(1.0D - s * s));
		s = v / Math.sqrt(u);
		this.sky[1] = Math.atan(s / Math.sqrt(1.0D - s * s));
		this.sky[2] = 60.40974D * Math.sqrt(u);
	}

	public double[] getMoonRiseSetTime(double julDate) {
		double jd = julDate;
		double rise = getSunRise(jd);
		jd -= 2451545.5;
		double[] returnValues = new double[4];

		double lat = place.latitude;
		double lon = place.longitude;
		double timeZone = place.timezone;
		double[][] mp = new double[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				mp[i][j] = 0.0D;
			}
		}
		lon /= 360.0D;
		double tz = timeZone / 24.0D * -1.0D;
		double t0 = getLst(lon, jd, tz);
		jd += tz;
		for (int k = 0; k < 3; ++k) {
			calcMoon(jd);
			mp[k][0] = this.sky[0];
			mp[k][1] = this.sky[1];
			mp[k][2] = this.sky[2];
			jd += 0.5D;
		}
		if (mp[1][0] <= mp[0][0]) {
			mp[1][0] += 2.0D * this.PI;
		}

		if (mp[2][0] <= mp[1][0]) {
			mp[2][0] += 2.0D * this.PI;
		}
		this.ran[0] = mp[0][0];
		this.dec[0] = mp[0][1];
		this.moonRise = false;
		this.moonSet = false;
		double ph = 0.0D;

		for (double l = rise - 1.0D; l < 24.0D + rise; ++l) {
			ph = (l + 1.0D) / 24.0D;
			this.ran[2] = PanchangUtil.interpolate(mp[0][0], mp[1][0], mp[2][0], ph);
			this.dec[2] = PanchangUtil.interpolate(mp[0][1], mp[1][1], mp[2][1], ph);
			this.vhz[2] = setRiseSetMoon(l, t0, lat, mp[1][2]);
			this.ran[0] = this.ran[2];
			this.dec[0] = this.dec[2];
			this.vhz[0] = this.vhz[2];
		}
		returnValues[0] = (double) riseTime[0] + (double) riseTime[1] / 60.0;
		returnValues[1] = riseAZ;
		returnValues[2] = (double) setTime[0] + (double) setTime[1] / 60.0;
		returnValues[3] = setAZ;

		if (moonRise) {
			returnValues[1] = riseAZ;
		} else {
			returnValues[1] = 0.0;
		}
		if (moonSet) {
			returnValues[3] = setAZ;
		} else {
			returnValues[3] = 0.0;
		}
		return returnValues;
	}

	private double setRiseSetMoon(double k, double t0, double lat, double plx) {
		double[] ha = { 0.0, 0.0, 0.0 };

		if (ran[2] < ran[0]) {
			ran[2] += 2.0 * PI;
		}
		ha[0] = t0 - ran[0] + k * K1;
		ha[2] = t0 - ran[2] + k * K1 + K1;
		ha[1] = (ha[2] + ha[0]) / 2.0;
		dec[1] = (dec[2] + dec[0]) / 2.0;

		double s = Math.sin(this.DR * lat);
		double c = Math.cos(this.DR * lat);
		double z = Math.cos(this.DR * (90.567D - 41.685D / plx));

		if (k <= 0.0D) {
			this.vhz[0] = s * Math.sin(this.dec[0]) + c * Math.cos(this.dec[0]) * Math.cos(ha[0]) - z;
		}
		this.vhz[2] = s * Math.sin(this.dec[2]) + c * Math.cos(this.dec[2]) * Math.cos(ha[2]) - z;
		if (getSign(this.vhz[0]) == getSign(this.vhz[2])) {
			return this.vhz[2];
		} else {
			this.vhz[1] = s * Math.sin(this.dec[1]) + c * Math.cos(this.dec[1]) * Math.cos(ha[1]) - z;
			double a = 2.0D * this.vhz[2] - 4.0D * this.vhz[1] + 2.0D * this.vhz[0];
			double b = 4.0D * this.vhz[1] - 3.0D * this.vhz[0] - this.vhz[2];
			double d = b * b - 4.0D * a * this.vhz[0];
			if (d < 0.0D) {
				return this.vhz[2];
			} else {
				d = Math.sqrt(d);
				double e = (-b + d) / (2.0D * a);
				if (e > 1.0D || e < 0.0D) {
					e = (-b - d) / (2.0D * a);
				}

				double time = k + e + 0.0D;
				int hr = (int) Math.floor(time);
				int min = (int) Math.floor((time - (double) hr) * 60.0D);
				double hz = ha[0] + e * (ha[2] - ha[0]);
				double nz = -Math.cos(this.dec[1]) * Math.sin(hz);
				double dz = c * Math.sin(this.dec[1]) - s * Math.cos(this.dec[1]) * Math.cos(hz);
				double az = Math.atan2(nz, dz) / this.DR;
				if (az < 0.0D) {
					az += 360.0D;
				}

				if (this.vhz[0] < 0.0D && this.vhz[2] > 0.0D) {
					this.riseTime[0] = hr;
					this.riseTime[1] = min;
					this.riseAZ = az;
					this.moonRise = true;
				}

				if (this.vhz[0] > 0.0D && this.vhz[2] < 0.0D) {
					this.setTime[0] = hr;
					this.setTime[1] = min;
					this.setAZ = az;
					this.moonSet = true;
				}

				return this.vhz[2];
			}
		}
	}

	private double getLst(double lon, double jd, double z) {
		double s = 24110.5 + 8640184.813 * jd / 36525.0 + 86636.6 * z + 86400.0 * lon;
		s /= 86400.0;
		s -= Math.floor(s);
		return s * 360.0 * DR;
	}

	// Method to get the sign of a number
	private int getSign(double x) {
		byte rv;
		if (x > 0.0) {
			rv = 1;
		} else if (x < 0.0) {
			rv = -1;
		} else {
			rv = 0;
		}
		return (int) rv;
	}

}
