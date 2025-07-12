package com.astroganit.lib.panchang;

import java.util.Arrays;
import java.util.List;

import com.astroganit.lib.horo.util.Util;

public class PanchangCalculation extends BaseCalculation {
	private double z1 = 3.14159265359;
	private double z2 = 0.0;
	private double ps = 0.0;
	private double pt = 0.0;

	public PanchangCalculation() {
		z2 = z1 / 180.0;
	}

	double sun(double b6) {
		double g0 = 360.0 * Util.fract(0.71455 + 99.99826 * b6);
		double h0 = 258.76 + 0.323 * b6;
		double p0 = 0.0;
		double e0 = 0.016751 - 4.2E-5 * b6;
		double q0 = 0.0;
		double a0 = 1.0;
		int pno = 1;
		return planet(g0, h0, p0, e0, q0, a0, pno);
	}

	double moon(double b6) {
		double g1 = 360.0 * Util.fract(0.71455 + 99.99826 * b6);
		double h1 = 258.76 + 0.323 * b6;
		double a0 = 360.0 * Util.fract(0.68882 + 1336.851353 * b6);
		double b0 = 360.0 * Util.fract(0.8663 + 11.298994 * b6 - 3.0E-5 * b6 * b6);
		double c0 = 360.0 * Util.fract(0.65756 - 5.376495 * b6);
		if (c0 < 0.0D) {
			c0 += 360.0D;
		}
		double g0 = z2 * (a0 - b0);
		double e0 = z2 * (g1 - h1);
		double d0 = z2 * (a0 - g1);
		double f0 = z2 * (a0 - c0);
		double l0 = a0 + 6.2888 * Math.sin(g0) + 0.2136 * Math.sin(2.0 * g0) + 0.01 * Math.sin(3.0 * g0)
				+ 1.274 * Math.sin(2.0 * d0 - g0) + 0.0085 * Math.sin(4.0 * d0 - 2.0 * g0);
		l0 = l0 - 0.0347 * Math.sin(d0) + 0.6583 * Math.sin(2.0 * d0) + 0.0039 * Math.sin(4.0 * d0)
				- 0.1856 * Math.sin(e0) - 0.0021 * Math.sin(2.0 * e0) + 0.0052 * Math.sin(g0 - d0);
		l0 = l0 - 0.0588 * Math.sin(2.0 * g0 - 2.0 * d0) + 0.0572 * Math.sin(2.0 * d0 - g0 - e0)
				+ 0.0533 * Math.sin(g0 + 2.0 * d0) + 0.0458 * Math.sin(2.0 * d0 - e0) + 0.041 * Math.sin(g0 - e0)
				- 0.0305 * Math.sin(g0 + e0);
		l0 = l0 - 0.0237 * Math.sin(2.0 * f0 - g0) - 0.0153 * Math.sin(2.0 * f0 - 2.0 * d0)
				+ 0.0107 * Math.sin(4.0 * d0 - g0) - 0.0079 * Math.sin(-g0 + e0 + 2.0 * d0)
				- 0.0068 * Math.sin(e0 + 2.0 * d0) + 0.005 * Math.sin(e0 + d0);
		l0 = l0 - 0.0023 * Math.sin(g0 + d0) + 0.004 * Math.sin(2.0 * g0 + 2.0 * d0)
				+ 0.004 * Math.sin(g0 - e0 + 2.0 * d0) - 0.0037 * Math.sin(3.0 * g0 - 2.0 * d0)
				- 0.0026 * Math.sin(g0 - 2.0 * d0 + 2.0 * f0) + 0.0027 * Math.sin(2.0 * g0 - e0);
		l0 = l0 - 0.0024 * Math.sin(2.0 * g0 + e0 - 2.0 * d0) + 0.0022 * Math.sin(2.0 * d0 - 2.0 * e0)
				- 0.0021 * Math.sin(2.0 * g0 + e0) + 0.0021 * Math.sin(c0 * z2)
				+ 0.0021 * Math.sin(2.0 * d0 - g0 - 2.0 * e0);
		l0 = l0 - 0.0018 * Math.sin(g0 + 2.0 * d0 - 2.0 * f0) + 0.0012 * Math.sin(4.0 * d0 - g0 - e0)
				- 8.0E-4 * Math.sin(3.0 * d0 - g0);
		double r0 = z2 * 2.0 * (l0 - c0);
		double d3 = l0 - 0.1143 * Math.sin(r0) + 0.004;
		if (d3 >= 360.0) {
			d3 -= 360.0;
		}

		if (d3 < 0.0) {
			d3 += 360.0;
		}

		return d3;
	}

	double newMoon(double jd, double tithi, int opt) {
		double start = 0.0D;
		if (opt == 0) {
			opt = -1;
		}

		if (opt == -1) {
			start = jd - tithi;
		}

		if (opt == 1) {
			start = jd + (30.0D - tithi);
		}

		double[] x = new double[17];

		for (int offset = 0; offset < 17; ++offset) {
			x[offset] = -2.0D + (double) offset / 4.0D;
		}

		double[] y = new double[17];

		for (int i = 0; i < 17; ++i) {
			y[i] = this.lunarPhase(start + x[i]);
		}

		y = this.unwrapAngles(y);
		double y0 = this.inverseLagrange(x, y, 360.0D);
		return start + y0;
	}

	double planet(double pg, double ph, double pp, double pe, double pq, double pa, int pno) {
		double pm = pg - ph;
		if (pm < 0.0D) {
			pm += 360.0D;
		}

		double pb = pm * this.z2;
		double pf = pb + pe * Math.sin(pb);

		double pc;
		double pd;
		do {
			pc = pf - pe * Math.sin(pf) - pb;
			pd = 1.0D - pe * Math.cos(pf);
			pf -= pc / pd;
		} while (Math.abs(pc / pd) > 0.01D);

		double pr = pa * (1.0D - pe * Math.cos(pf));
		double e1 = Math.atan(pe / Math.sqrt(1.0D - pe * pe));
		double e2 = this.z1 / 4.0D - e1 / 2.0D;
		double e3 = Math.tan(e2);
		double e4 = Math.tan(pf / 2.0D);
		double v1 = Math.atan(e4 / e3);
		if (v1 < 0.0D) {
			v1 += this.z1;
		}

		double pv = 2.0D * v1;
		pc = ph * this.z2;
		pd = pp * this.z2;
		pb = pq * this.z2;
		double pj = pv + pc;
		double pk = pj - pd;
		double pl = 1.0D - Math.cos(pb);
		double px = (Math.cos(pj) + Math.sin(pk) * Math.sin(pd) * pl) * pr;
		double py = (Math.sin(pj) - Math.sin(pk) * Math.cos(pd) * pl) * pr;
		if (pno == 1) {
			this.ps = px;
			this.pt = py;
		}

		pc = this.ps + px;
		pd = this.pt + py;
		pm = Math.atan(pd / pc) / this.z2;
		if (pc < 0.0D) {
			pm += 180.0D;
		} else if (pd < 0.0D) {
			pm += 360.0D;
		}

		return pm;
	}

	double sunriseJd(double jd) {
		return jd + getSunRise(jd) / 24.0;
	}

	int solarMonth(double jd) {
		return (int) raasi(jd);
	}

	private double raasi(double jd) {
		return Math.ceil(solarLongitudeNir(jd) / 30.0);
	}

	private double solarLongitudeNir(double jd) {
		return this.sun(this.jdToB6(jd)) % 360.0;
	}

	private double jdToB6(double jd) {
		return (jd - 694025.0 - 1720995.0 - (12.0 + this.place.timezone) / 24.0) / 36525.0;
	}

	int getTithiD(double jd) {
		double tithi = (this.lunarLongitude(jd) - this.solarLongitude(jd)) / 12.0;
		if (tithi < 0.0) {
			tithi += 30.0;
		}
		return (int) tithi + 1;
	}

	public double[] nakshatra(double jd) {
		double rise = sunriseJd(jd);
		double[] offsets = new double[] { 0.0D, 0.25D, 0.5D, 0.75D, 1.0D };
		double[] longitudes = new double[offsets.length];
		int i = 0;
		int var18 = offsets.length;
		double nak;

		for (int var17 = 0; var17 < var18; ++var17) {
			nak = offsets[var17];
			longitudes[i] = lunarLongitudeNir(rise + nak) % 360.0D;
			++i;
		}

		nak = Math.ceil(longitudes[0] * 27.0D / 360.0D);
		double[] y = this.unwrapAngles(longitudes);
		double approxEnd = this.inverseLagrange(offsets, y, nak * 360.0D / 27.0D);
		double ends = (rise - jd + approxEnd) * 24.0D;
		double[] answer = new double[] { nak, ends };
		double nakTmrw = Math.ceil(longitudes[longitudes.length - 1] * 27.0D / 360.0D);
		if (nakTmrw < nak) {
			nakTmrw += 27.0D;
		}
		boolean isSkipped = (nakTmrw - nak) % 27.0D > 1.0D;

		if (isSkipped) {

			double leapNak = nak + 1.0D;
			approxEnd = inverseLagrange(offsets, longitudes, leapNak * 360.0 / 27.0);
			double nakEnds = (rise - jd + approxEnd) * 24.0;

			return new double[] { nak, ends, this.mod(leapNak, 27.0D), nakEnds };

		} else {
			return answer;
		}

	}

	int[] masa(double jd) {
		double critical = sunriseJd(jd);
		double ti = (double) this.getTithiD(critical);
		double lastNewMoon = newMoon(critical, ti, -1);
		double nextNewMoon = newMoon(critical, ti, 1);
		double thisSolarMonth = raasi(lastNewMoon);
		double nextSolarMonth = raasi(nextNewMoon);
		int isLeapMonth;
		if (thisSolarMonth == nextSolarMonth) {
			isLeapMonth = 1;
		} else {
			isLeapMonth = 0;
		}
		;
		int maasa = (int) thisSolarMonth + 1;
		if (maasa > 12) {
			maasa %= 12;
		}
		return new int[] { maasa, isLeapMonth };
	}

	public double[] tithi(double jd) {

		double rise = sunriseJd(jd);
		double moonPhase = this.lunarPhase(rise);
		double today = Math.ceil(moonPhase / 12.0D);
		double degrees_left = today * 12.0D - moonPhase;
		double[] offsets = new double[] { 0.25D, 0.5D, 0.75D, 1.0D };
		double[] lunarLongDiff = new double[offsets.length];
		double[] solarLongDiff = new double[offsets.length];
		int i = 0;
		double[] var22 = offsets;
		int var21 = offsets.length;

		for (int var20 = 0; var20 < var21; ++var20) {
			double t = var22[var20];
			lunarLongDiff[i] = this.degreeDifference(this.lunarLongitude(rise + t), this.lunarLongitude(rise));
			solarLongDiff[i] = this.degreeDifference(this.solarLongitude(rise + t), this.solarLongitude(rise));
			++i;
		}

		i = 0;
		double[] relative_motion = new double[offsets.length];
		double[] var23 = offsets;
		int var36 = offsets.length;

		for (var21 = 0; var21 < var36; ++var21) {
			double var10000 = var23[var21];
			relative_motion[i] = lunarLongDiff[i] - solarLongDiff[i];
			++i;
		}

		double approx_end = this.inverseLagrange(offsets, relative_motion, degrees_left);
		double ends = (rise + approx_end - jd) * 24.0D;
		double[] answer = new double[] { today, ends };
		double moon_phase_tmrw = this.lunarPhase(rise + 1.0D);
		double tomorrow = Math.ceil(moon_phase_tmrw / 12.0D);
		if (tomorrow < today) {
			tomorrow += 30.0D;
		}

		boolean isSkipped = (tomorrow - today) % 30.0D > 1.0D;
		if (isSkipped) {
			double leap_tithi = today + 1.0D;
			degrees_left = leap_tithi * 12.0D - moonPhase;
			approx_end = this.inverseLagrange(offsets, relative_motion, degrees_left);
			double ends_leap_tithi = (rise + approx_end - jd) * 24.0D;
			return new double[] { today, ends, this.mod(leap_tithi, 30.0D), ends_leap_tithi };
		} else {
			return answer;
		}
	}

	public int vaara(double jd) {
		return (int) (Math.ceil(jd + 1.0) % 7.0);
	}

	private double lunarPhase(double jd) {
		double solarLong = solarLongitude(jd);
		double lunarLong = lunarLongitude(jd);
		var moonPhase = (lunarLong - solarLong) % 360.0;
		if (moonPhase < 0.0) {
			moonPhase += 360.0;
		}

		return moonPhase;
	}

	private double lunarLongitude(double jd) {
		return (this.moon(jdToB6(jd)) + this.ayan(jd)) % 360.0;
	}

	private double solarLongitude(double jd) {
		return (sun(jdToB6(jd)) + this.ayan(jd)) % 360.0;
	}

	double ayan(double jd) {
		double b6 = jdToB6(jd);
		return 22.460148 + 1.396042 * b6 + 3.08E-4 * b6 * b6;
	}

	private double lunarLongitudeNir(double jd) {
		return moon(jdToB6(jd)) % 360.0;
	}

	private double[] unwrapAngles(double[] angles) {
		for (int i = 1; i < angles.length; i++) {
			if (angles[i] < angles[i - 1]) {
				angles[i] += 360.0;
			}
		}

		return angles;
	}

	public double[] yoga(double jd) {

		double rise = sunriseJd(jd);
		double lunarLong = this.lunarLongitudeNir(rise) % 360.0D;
		double solarLong = this.solarLongitudeNir(rise) % 360.0D;
		double total = (lunarLong + solarLong) % 360.0D;
		double yog = Math.ceil(total * 27.0D / 360.0D);
		double degreesLeft = yog * 13.333333333333334D - total;
		double[] offsets = new double[] { 0.25D, 0.5D, 0.75D, 1.0D };
		double[] lunarLongDiff = new double[offsets.length];
		double[] solarLongDiff = new double[offsets.length];
		int i = 0;
		double[] var24 = offsets;
		int var23 = offsets.length;

		for (int var22 = 0; var22 < var23; ++var22) {
			double t = var24[var22];
			lunarLongDiff[i] = this.degreeDifference(this.lunarLongitude(rise + t), this.lunarLongitude(rise));
			solarLongDiff[i] = this.degreeDifference(this.solarLongitude(rise + t), this.solarLongitude(rise));
			++i;
		}

		i = 0;
		double[] totalMotion = new double[offsets.length];
		double[] var25 = offsets;
		int var43 = offsets.length;

		for (var23 = 0; var23 < var43; ++var23) {
			double var10000 = var25[var23];
			totalMotion[i] = lunarLongDiff[i] + solarLongDiff[i];
			++i;
		}

		double approxEnd = this.inverseLagrange(offsets, totalMotion, degreesLeft);
		double ends = (rise + approxEnd - jd) * 24.0D;
		double[] answer = new double[] { yog, ends };
		double lunarLongTmrw = this.lunarLongitudeNir(rise + 1.0D) % 360.0D;
		double solarLongTmrw = this.solarLongitudeNir(rise + 1.0D) % 360.0D;
		double totalTmrw = (lunarLongTmrw + solarLongTmrw) % 360.0D;
		double tomorrow = Math.ceil(totalTmrw * 27.0D / 360.0D);
		if (tomorrow < yog) {
			tomorrow += 27.0D;
		}

		boolean isSkipped = (tomorrow - yog) % 27.0D > 1.0D;
		if (isSkipped) {
			double leap_yog = yog + 1.0D;
			degreesLeft = leap_yog * 13.333333333333334D - total;
			approxEnd = this.inverseLagrange(offsets, totalMotion, degreesLeft);
			double endsLeapYoga = (rise + approxEnd - jd) * 24.0D;
			return new double[] { yog, ends, this.mod(leap_yog, 27.0D), endsLeapYoga };
		} else {
			return answer;
		}
	}

	public double[] karana(double jd) {

		double rise = sunriseJd(jd);
		double moonPhase = this.lunarPhase(rise);
		double today = Math.ceil(moonPhase / 6.0D);
		double degreesLeft = today * 6.0D - moonPhase;
		double[] offsets = new double[] { 0.25D, 0.5D, 0.75D, 1.0D };
		double[] lunarLongDiff = new double[offsets.length];
		double[] solarLongDiff = new double[offsets.length];
		int i = 0;
		double[] var20 = offsets;
		int var19 = offsets.length;

		for (int var18 = 0; var18 < var19; ++var18) {
			double t = var20[var18];
			lunarLongDiff[i] = this.degreeDifference(this.lunarLongitude(rise + t), this.lunarLongitude(rise));
			solarLongDiff[i] = this.degreeDifference(this.solarLongitude(rise + t), this.solarLongitude(rise));
			++i;
		}

		i = 0;
		double[] relativeMotion = new double[offsets.length];
		double[] var21 = offsets;
		int var36 = offsets.length;

		for (var19 = 0; var19 < var36; ++var19) {
			double var10000 = var21[var19];
			relativeMotion[i] = lunarLongDiff[i] - solarLongDiff[i];
			++i;
		}

		double approxEnd = this.inverseLagrange(offsets, relativeMotion, degreesLeft);
		double ends = (rise + approxEnd - jd) * 24.0D;
		double[] answer = new double[] { today, ends };
		double moonPhaseTmrw = this.lunarPhase(rise + 1.0D);
		double tomorrow = Math.ceil(moonPhaseTmrw / 6.0D);
		if (tomorrow < today) {
			tomorrow += 60.0D;
		}

		boolean isSkipped = (tomorrow - today) % 60.0D > 1.0D;
		if (isSkipped) {
			double leap_tithi = today + 1.0D;
			degreesLeft = leap_tithi * 6.0D - moonPhase;
			approxEnd = this.inverseLagrange(offsets, relativeMotion, degreesLeft);
			double endsLeapTithi = (rise + approxEnd - jd) * 24.0D;
			return new double[] { today, ends, this.mod(leap_tithi, 60.0D), endsLeapTithi };
		} else {
			return answer;
		}
	}

	public int getPaksha(double jd) {
		double rise = sunriseJd(jd);
		double phase = lunarPhase(rise);
		return (phase < 180.0) ? 0 : 1;
	}

	public int rituDrik(double jd) {
		double critical = sunriseJd(jd);
		int masaNum = (int) raasiSayan(critical);
		int rituNum = masaNum / 2;
		if (place.latitude >= 0.0) {
			if (rituNum > 5) {
				return 0;
			}
		} else {
			rituNum += 3;
			if (rituNum > 5) {
				return rituNum - 6;
			}
		}
		return rituNum;
	}

	public double[] moonsign(double jd) {

		double rise = sunriseJd(jd);
		double[] offsets = new double[] { 0.0D, 0.6D, 1.2D, 1.8D, 2.4D };
		double[] longitudes = new double[offsets.length];
		int i = 0;
		int var18 = offsets.length;
		double nak;
		for (int var17 = 0; var17 < var18; ++var17) {
			nak = offsets[var17];
			longitudes[i] = this.lunarLongitudeNir(rise + nak) % 360.0D;
			++i;
		}
		nak = Math.ceil(longitudes[0] * 12.0D / 360.0D);
		double[] y = unwrapAngles(longitudes);
		double approxEnd = inverseLagrange(offsets, y, nak * 360.0 / 12.0);
		double ends = (rise - jd + approxEnd) * 24.0;
		double[] answer = new double[] { nak, ends };
		return answer;
	}

	public int[] masaPurnimanta(double jd) {
		double critical = sunriseJd(jd);
		double ti = (double) getTithiD(critical);
		double lastNewMoon = newMoon(critical, ti, -1);
		double nextNewMoon = newMoon(critical, ti, 1);
		double thisSolarMonth = raasi(lastNewMoon);
		double nextSolarMonth = raasi(nextNewMoon);
		double lastFullMoon = fullMoon(jd, ti, -1);
		double tithiFullMoon = (double) getTithiD(lastFullMoon);
		double newMoonAfterFullMoon = newMoon(lastFullMoon, tithiFullMoon, 1);
		double solarMonthNewMoonAfterFullMoon = raasi(newMoonAfterFullMoon);
		int isLeapMonth;
		if (thisSolarMonth == nextSolarMonth) {
			isLeapMonth = 1;
		} else {
			isLeapMonth = 0;
		}
		;

		int maasa = (int) thisSolarMonth + 1;
		if (maasa > 12) {
			maasa %= 12;
		}
		int maasaPurnimanta = (int) solarMonthNewMoonAfterFullMoon + 1;
		if (maasaPurnimanta > 12) {
			maasaPurnimanta %= 12;
		}
		return new int[] { maasa, isLeapMonth, maasaPurnimanta };
	}

	public int[] elapsedYear(double jd, int maasaNum) {
		double siderealYear = 365.25636;
		double ahar = this.ahargana(jd);
		int solarNum = (int) raasi(sunriseJd(jd));
		int kali = (int) ((ahar + (double) ((4 - maasaNum) * 30)) / siderealYear);
		int kaliSolar = (int) ((ahar + (double) ((4 - solarNum) * 30)) / siderealYear) + 1;
		int saka = kali - 3179;
		int vikrama = saka + 135;
		return new int[] { kali, saka, vikrama, kaliSolar };
	}

	public double[] bhadraStartEndTime(double jd) {
		List<Integer> bhList = Arrays.asList(8, 15, 22, 29, 36, 43, 50, 57);
		double start = 0.0;
		double end = 0.0;
		double karanStartDate = 0.0;
		double[] bhadrArray = this.karanaNew(jd);
		int karanaNum = (int) bhadrArray[0];
		if (bhList.contains(karanaNum)) {
			end = bhadrArray[1];
			bhadrArray = this.karanaNew(jd - 1.0);
			if ((int) bhadrArray[0] == karanaNum - 1) {
				start = bhadrArray[1];
			} else if (bhadrArray.length > 2) {
				if ((int) bhadrArray[2] == karanaNum - 1) {
					start = bhadrArray[3];
				} else if (bhadrArray.length > 4 && (int) bhadrArray[4] == karanaNum - 1) {
					start = bhadrArray[5];
				}
			}
			if (start < 24.0) {
				karanStartDate = -1.0;
			}
		} else if (bhadrArray.length > 2) {
			karanaNum = (int) bhadrArray[2];
			if (bhList.contains(karanaNum)) {
				start = bhadrArray[1];
				end = bhadrArray[3];
			}
			if (bhadrArray.length > 4) {
				karanaNum = (int) bhadrArray[4];
				if (bhList.contains(karanaNum)) {
					start = bhadrArray[3];
					end = bhadrArray[5];
				}
			}
		}
		if (start > 24.0) {
			start -= 24.0;
		}
		return new double[] { start, end, karanStartDate };
	}

	private double[] karanaNew(double jd) {
		double rise = sunriseJd(jd);
		double moonPhase = lunarPhase(rise);
		double today = Math.ceil(moonPhase / 6.0);
		double degreesLeft = today * 6.0 - moonPhase;
		double[] offsets = new double[] { 0.25D, 0.5D, 0.75D, 1.0D };
		double[] lunarLongDiff = new double[offsets.length];
		double[] solarLongDiff = new double[offsets.length];
		int i = 0;
		int var19 = offsets.length;
		for (int var18 = 0; var18 < var19; ++var18) {
			double t = offsets[var18];
			lunarLongDiff[i] = this.degreeDifference(this.lunarLongitude(rise + t), this.lunarLongitude(rise));
			solarLongDiff[i] = this.degreeDifference(this.solarLongitude(rise + t), this.solarLongitude(rise));
			++i;
		}
		i = 0;
		double[] relativeMotion = new double[offsets.length];
		int var44 = offsets.length;
		var19 = 0;

		for (var19 = 0; var19 < var44; ++var19) {
			double var10000 = offsets[var19];
			relativeMotion[i] = lunarLongDiff[i] - solarLongDiff[i];
			++i;
		}
		double approxEnd = inverseLagrange(offsets, relativeMotion, degreesLeft);
		double ends = (rise + approxEnd - jd) * 24.0;
		double[] answer = new double[] { today, ends };
		double moonPhaseTmrw = lunarPhase(rise + 1.0);
		double tomorrow = Math.ceil(moonPhaseTmrw / 6.0);
		if (tomorrow < today) {
			tomorrow += 60.0;
		}
		boolean isSkipped = (tomorrow - today) % 60.0 > 1.0;
		if (isSkipped) {
			double leapTithi = today + 1.0;
			degreesLeft = leapTithi * 6.0 - moonPhase;
			approxEnd = inverseLagrange(offsets, relativeMotion, degreesLeft);
			double endsLeapTithi = (rise + approxEnd - jd) * 24.0;
			double todaySecond = mod(leapTithi, 60.0);
			boolean isSkipped2 = (tomorrow - todaySecond) % 60.0 > 1.0;
			if (isSkipped2) {
				double leapTithiSkip = todaySecond + 1.0;
				degreesLeft = leapTithiSkip * 6.0 - moonPhase;
				approxEnd = inverseLagrange(offsets, relativeMotion, degreesLeft);
				double endsLeapTithiSkip = (rise + approxEnd - jd) * 24.0;
				double todayThird = mod(leapTithiSkip, 60.0);
				return new double[] { today, ends, todaySecond, endsLeapTithi, todayThird, endsLeapTithiSkip };

			} else {
				return new double[] { today, ends, todaySecond, endsLeapTithi };
			}
		} else {
			return answer;
		}
	}

	private double raasiSayan(double jd) {
		return Math.ceil(solarLongitude(jd) / 30.0);
	}

	double fullMoon(double jd, double tithi, int opt) {
		double start = 0.0D;
		if (opt == 0) {
			opt = -1;
		}

		if (opt == -1) {
			start = tithi <= 15.0D ? jd - tithi - 15.0D : jd - (tithi - 15.0D);
		}

		if (opt == 1) {
			start = tithi <= 15.0D ? jd + 15.0D - tithi : jd + (30.0D - tithi) + 15.0D;
		}

		double[] x = new double[17];

		for (int offset = 0; offset < 17; ++offset) {
			x[offset] = -2.0D + (double) offset / 4.0D;
		}

		double[] y = new double[17];

		for (int i = 0; i < 17; ++i) {
			y[i] = this.lunarPhase(start + x[i]);
		}

		y = this.unwrapAngles(y);
		double y0 = this.inverseLagrange(x, y, 180.0D);
		return start + y0;
	}

	private double ahargana(double jd) {
		return jd - 588465.5;
	}

	public double[] panchakStartEndTime(double jd) {
		List<Integer> nakList = Arrays.asList(23, 24, 25, 26, 27);
		double start = 0.0D;
		double end = 0.0D;
		double panchakStartDate = 0.0D;
		double panchakEndDate = 0.0D;
		double[] panchakArray = this.nakshatra(jd);
		int nakshatraNum = (int) panchakArray[0];
		int nakshatraNumStart = (int) panchakArray[0];
		int nakshatraNumEnd = (int) panchakArray[0];
		double jdStart = jd;
		double jdEnd = jd;
		double[] panchakArrayEnd = panchakArray;
		if (nakList.contains(nakshatraNum)) {
			int i;
			double[] temp;
			for (i = 1; i < 7; ++i) {
				temp = this.nakshatra(--jdStart);
				if (!nakList.contains((int) temp[0])) {
					++jdStart;
					break;
				}
				nakshatraNumStart = (int) temp[0];
				--panchakStartDate;
			}

			for (i = 1; i < 7; ++i) {
				temp = this.nakshatra(++jdEnd);
				if (!nakList.contains((int) temp[0])) {
					--jdEnd;
					break;
				}

				panchakArrayEnd = temp;
				nakshatraNumEnd = (int) temp[0];
				++panchakEndDate;
			}
		}

		if (nakList.contains(nakshatraNumStart)) {
			double[] moonArray = this.moonsign(jdStart - 1.0D);
			if ((int) moonArray[0] == 10) {
				start = moonArray[1];
				if (start < 24.0D) {
					--panchakStartDate;
				}
			} else {
				moonArray = this.moonsign(jdStart);
				start = moonArray[1];
			}
		}

		if (nakList.contains(nakshatraNumEnd)) {
			end = panchakArrayEnd[1];
		} else if (panchakArrayEnd.length > 2) {
			nakshatraNumEnd = (int) panchakArrayEnd[2];
			if (nakList.contains(nakshatraNumEnd)) {
				end = panchakArrayEnd[3];
			}
		}

		if (start > 24.0D) {
			start -= 24.0D;
		}

		return new double[] { start, end, panchakStartDate, panchakEndDate };
	}
}
