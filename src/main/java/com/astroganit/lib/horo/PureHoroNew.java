package com.astroganit.lib.horo;

import com.astroganit.lib.horo.util.Util;

public class PureHoroNew extends MainHoroNew {
	StringBuffer balance(double moon) {
		StringBuffer var9 = new StringBuffer();
		double var3 = 9.0 * Util.fract(moon / 120.0);
		double var5 = Util.fract(var3);
		int moon1 = (int) var3;
		double var7 = (1.0 - var5) * y1[moon1];
		int var4 = (int) var7;
		int var12 = (int) (Util.fract(var7) * 12.0);
		int var2 = (int) (Util.fract(Util.fract(var7) * 12.0) * 31.0);

		try {

			if (lang.equalsIgnoreCase("0")) {
				var9.append(String.format("%s %s व %s मा %s दि ", x1[moon1], var4, var12, var2));
				//var9.append(String.format("%s %s o %s ek %s fn ", x1[moon1], var4, var12, var2));
			
			} else {
				var9.append(String.format("%s %s Y %s M %s D ", x1[moon1], var4, var12, var2));
			}
		} catch (NullPointerException e) {

		}

		return var9;
	}

	public double getAyanamsa() {
		return aya;
	}

	int getGana() {
		int moonDeg1 = (int) (plnt[2] / 13.333333) + 1;
		byte var2 = -1;
		switch (moonDeg1) {
		case 1:
		case 5:
		case 7:
		case 8:
		case 13:
		case 15:
		case 17:
		case 22:
		case 27: {
			var2 = 0;
			break;
		}
		case 2:
		case 4:
		case 6:
		case 11:
		case 12:
		case 20:
		case 21:
		case 25:
		case 26: {
			var2 = 1;
			break;
		}
		case 3:
		case 9:
		case 10:
		case 14:
		case 16:
		case 18:
		case 19:
		case 23:
		case 24: {
			var2 = 2;
			break;
		}
		}
		return var2;
	}

	private int getHinduWeekday() {
		int vedicDay;
		if (birthTime - rise < 0.0) {
			vedicDay = daylord - 1;
		} else {
			vedicDay = daylord;
		}
		if (vedicDay < 0) {
			return vedicDay + 7;
		} else {
			return vedicDay;
		}
	}

	private int getIndianSunSign() {
		if (plnt[1] >= 360.0) {
			plnt[1] -= 360.0;
		}
		return (int) (plnt[1] / 30.0);
	}

	private int getJulianDay() {
		return julDay + 1720995;
	}

	public double getKPAyanamsaLongitude() {
		return kpaya;
	}

	public double getKPCuspLongitude(int cuspNo) {
		return f4[cuspNo - 1];
	}

	public double getKPFortunaLongitude() {
		return plntkp[1] + f4[0] - plntkp[0];
	}

	public double getKPPlanetLongitude(int planetNo) {
		var var2 = plntkp[planetNo - 1];
		if (var2 >= 360.0) {
			var2 -= 360.0;
		}
		return var2;
	}

	public int[] getKPPlanetSignification(int planetNo) {

		char[] var2 = "364214635775".toCharArray();
		char[] var3 = "345972861".toCharArray();
		int[] var5 = new int[13];
		int[] var6 = new int[12];
		int[][] var9 = new int[13][13];

		for (int var4 = 1; var4 <= 12; var4++) {
			for (int var8 = 1; var8 <= 12; var8++) {
				var9[var4][var8] = 0;
			}
		}

		for (int var7 = 1; var7 <= 12; var7++) {
			var var16 = 0.0;
			for (int var8 = 0; var8 < 13; var8++) {
				var5[var8] = 0;
			}
			double var12 = f4[var7 - 1];
			double var14;
			if (var7 >= 12) {
				var14 = f4[0];
			} else {
				var14 = f4[var7];
			}
			if (var14 < var12) {
				var16 = var14;
				var14 += 360.0;
			}
			var var4 = (int) (var12 / 30.0);
			var4 = var2[var4] - '0';
			var9[var4][var7] = 1;

			double var18;
			for (int var8 = 1; var8 <= 12; var8++) {
				var18 = plnt[var8];
				if (var16 != 0.0 && var18 < var16) {
					var18 += 360.0;
				}
				if (var18 >= var12 && var18 < var14) {
					var5[var8] = 1;
					var9[var8][var7] = 1;
				}
			}

			var4 = var3[var4 - 1] - '0';
			var12 = (var4 - 1) * 40.0 / 3.0;
			var14 = var4 * 40.0 / 3.0;
			for (int var8 = 1; var8 <= 12; var8++) {
				var18 = plnt[var8];
				if (var18 >= var12 && var18 < var14) {
					var9[var8][var7] = 1;
				}
				if (var18 >= var12 + 120.0 && var18 < var14 + 120.0) {
					var9[var8][var7] = 1;
				}
				if (var18 >= var12 + 240.0 && var18 < var14 + 240.0) {
					var9[var8][var7] = 1;
				}
			}
			for (int var8 = 1; var8 <= 9; var8++) {
				if (var5[var8] == 1) {
					var4 = var3[var8 - 1] - '0';
					var12 = (var4 - 1) * 40.0 / 3.0;
					var14 = var4 * 40.0 / 3.0;
					for (int i = 1; i <= 12; i++) {
						var18 = plnt[i];
						if (var18 > var12 && var18 <= var14) {
							var9[i][var7] = 1;
						}
						if (var18 > var12 + 120.0 && var18 <= var14 + 120.0) {
							var9[i][var7] = 1;
						}
						if (var18 > var12 + 240.0 && var18 <= var14 + 240.0) {
							var9[i][var7] = 1;
						}
					}
				}
			}
		}

		var var7 = 0;
		for (int var8 = 1; var8 <= 12; var8++) {
			if (var9[planetNo][var8] == 1) {
				var6[var7] = var8;
				++var7;
			}
		}
		return var6;
	}

	public int getLagna() {
		return (int) (f2[0] / 30.0);
	}

	private int getNadi() {
		int var3 = 0;
		double var5 = plnt[2] * 0.075;
		tt[1] = (int) (var5 * 100.0) / 100.0;
		int var1 = (int) tt[1] + 1;
		int var2 = (int) (var1 / 3.0 + 0.99);
		var1 -= (var2 - 1) * 3;
		if (var2 % 2 == 0) {
			if (var1 == 1) {
				var3 = 2;
			} else if (var1 == 3) {
				var3 = 0;
			}
		} else if (var1 == 3) {
			var3 = 2;
		} else if (var1 == 1) {
			var3 = 0;
		}
		if (var1 == 2) {
			var3 = 1;
		}
		return var3;
	}

	public int getNakshatra() {
		return (int) (plnt[2] * 0.075);
	}

	private int getNakshatraLord() {
		return (int) ((plnt[2] * 0.075 * 100.0 / 100) % 9);
	}

	private double getObliquity() {
		return obliq;
	}

	private int getPaksha() {
		double var1 = (plnt[2] - plnt[1]) / 12.0000001;
		if (var1 < 0.0) {
			var1 += 30.0;
		}
		tt[0] = Math.round(var1 * 100.0) / 100.0;
		int var3 = ((int) tt[0] + 1) % 15 - 1;
		if (var3 == -1) {
			var3 = 14;
		}
		int var2 = (int) (plnt[2] - plnt[1]);
		if (var2 <= 0) {
			var2 += 360;
		}
		if (var2 >= 181 && var2 <= 359) {
			if (var3 == 15)
				return 0;
			else
				return 0;
		} else {
			return 1;
		}
	}

	int getPaya() {
		byte var1 = 0;
		int var2 = (int) plnt[2] / 30 - (int) f2[0] / 30 + 1;
		if (var2 <= 0) {
			var2 += 12;
		}
		switch (var2) {
		case 1:
		case 6:
		case 11: {
			var1 = 0;
			break;
		}
		case 2:
		case 5:
		case 9: {
			var1 = 1;
			break;
		}
		case 3:
		case 7:
		case 10: {
			var1 = 2;
			break;
		}
		case 4:
		case 8:
		case 12: {
			var1 = 3;
			break;
		}
		}
		return (int) var1;
	}

	private int getPlanetaryRasi(int planetNo) {
		return (int) (plnt[planetNo] / 30.0);
	}

	public int[] getPositionForShodasvarg(int position) {
		int[] var2 = new int[13];

		switch (position) {
		case 0: {
			var2 = lagnaArr;
			break;
		}

		case 1: {
			var2 = horaArr;
			break;
		}

		case 2: {
			var2 = drekkanaArr;
			break;
		}

		case 3: {
			var2 = chaturmshaArr;
			break;
		}

		case 4: {
			var2 = saptamsaArr;
			break;
		}

		case 5: {
			var2 = navmasaArr;
			break;
		}

		case 6: {
			var2 = dasamsaArr;
			break;
		}

		case 7: {
			var2 = dwadasamsaArr;
			break;
		}

		case 8: {
			var2 = shodasamsaArr;
			break;
		}

		case 9: {
			var2 = vimshamsaArr;
			break;
		}

		case 10: {
			var2 = siddhamsaArr;
			break;
		}

		case 11: {
			var2 = bhamsaArr;
			break;
		}

		case 12: {
			var2 = trimshamsaArr;
			break;
		}

		case 13: {
			var2 = khavedamsaArr;
			break;
		}

		case 14: {
			var2 = akshvedamsaArr;
			break;
		}

		case 15: {
			var2 = shastiamsaArr;
			break;
		}
		}

		return var2;
	}

	public int getPrastharashtakvargaTables(int tableNo1, int planetNo1, int bindu) {
		return prastakvargaNew[tableNo1 - 1][planetNo1 - 1][bindu - 1];
	}

	int getRasi() {
		return (int) (plnt[2] / 30.0);
	}

	int getRasiLord() {
		return (int) (plnt[2] / 30.0);
	}

	private double getSiderealTime() {
		return sidtime;
	}

	private double getSunRiseTime() {
		calculateSunAndRiseSet(y, m, d, longt, lat);
		return rise;
	}

	private double getSunSetTime() {
		calculateSunAndRiseSet(y, m, d, longt, lat);
		return set;
	}

	private int getSunSign() {
		var var1 = plnt[1] + plnt[0];
		if (var1 >= 360.0) {
			var1 -= 360.0;
		}
		return (int) (var1 / 30.0);
	}

	int getTithi() {
		double var1 = (plnt[2] - plnt[1]) / 12.0000001;
		if (var1 < 0.0) {
			var1 += 30.0;
		}
		return (int) var1;
	}

	int[] getTotalAshtakVargaValue() {
		return totalAshtavarga;
	}

	private int getVarna() {
		double var1 = (int) plnt[2] / 30.0000001;
		int[] var3 = { 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0 };
		int var4 = (int) Math.round(var1 * 100.0) / 100;
		return var3[var4];
	}

	int getVasya(double moonDeg) throws Exception {
		if (moonDeg < 60.0 || moonDeg >= 255.0 && moonDeg < 285.0) {
			return 0;
		} else if ((moonDeg >= 60.0 && moonDeg < 90.0 || (moonDeg >= 150.0) && moonDeg < 210.0 || moonDeg >= 240.0)
				&& moonDeg < 255.0 || moonDeg >= 300.0 && moonDeg < 330.0) {
			return 1;
		} else if ((moonDeg < 90.0 || moonDeg >= 120.0) && (moonDeg < 285.0 || moonDeg >= 300.0)
				&& (moonDeg < 330.0 || moonDeg >= 360.0)) {
			if (moonDeg >= 120.0 && moonDeg < 150.0) {
				return 3;
			} else if (moonDeg >= 210.0 && moonDeg < 240.0) {
				return 4;
			} else {
				throw new Exception();
			}
		} else {
			return 2;
		}
	}

	private int getYoga() {
		double var1 = (plnt[2] + plnt[1]) * 0.075;
		if (var1 > 27.0) {
			var1 -= 27.0;
		}
		return (int) var1;
	}

	private int getYoni() {
		double var1 = plnt[2] * 0.075;
		tt[1] = (double) Math.round(var1 * 100.0) / 100.0;

		int var2;
		switch (((int) tt[1] + 1)) {
		case 1:
		case 24: {
			var2 = 0;
			break;
		}
		case 2:
		case 27: {
			var2 = 1;
			break;
		}
		case 3:
		case 8: {
			var2 = 2;
			break;
		}
		case 4:
		case 5: {
			var2 = 3;
			break;
		}
		case 6:
		case 19: {
			var2 = 4;
			break;
		}
		case 7:
		case 9: {
			var2 = 5;
			break;
		}
		case 10:
		case 11: {
			var2 = 6;
			break;
		}
		case 12:
		case 26: {
			var2 = 7;
			break;
		}
		case 13:
		case 15: {
			var2 = 8;
			break;
		}
		case 14:
		case 16: {
			var2 = 9;
			break;
		}
		case 17:
		case 18: {
			var2 = 10;
			break;
		}
		case 20:
		case 22: {
			var2 = 11;
			break;
		}
		case 21: {
			var2 = 13;
			break;
		}
		case 23:
		case 25: {
			var2 = 12;
			break;
		}
		default: {
			var2 = 0;
		}
		}
		return var2;
	}

	void initialize() throws Exception {
		super.initialize();
		initBhav();
		misc();
		calculateSunAndRiseSet(y, m, d, longt, lat);
		birthTime = h + mt / 60.0 + s / 3600.0;
		setHinduWeekday();
		if (horarySeed != 0) {
			calculateKPHorary(horarySeed);
		} else {
			calculateCusp();
		}
		calculateKPPlanet();
		calculateShodashvarga();
		calculateAshtakNew();

	}

	public int getAshtakvargaBinduForSignAndPlanet(int planetNo, int signNo) {
		return ashtakArrayNew[planetNo][signNo];
	}

	public String getAyanamsaDms(String language) {
		return Util.dms(getAyanamsa(), language);
	}

	public String getGanaName() {
		String var1 = "";
		try {
			var1 = gana[getGana()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getHinduWeekdayName() {
		String var1 = "";
		try {
			var1 = day[getHinduWeekday()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getIndianSunSignName() {
		String result = "";
		try {
			result = ras[getIndianSunSign()];
		} catch (NullPointerException e) {
		}
		return result;
	}

	public String getJulianDayValue() {
		return String.valueOf(getJulianDay());
	}

	public String getKaranName() {
		String result = "";
		try {
			result = karan[getKaran()];
		} catch (NullPointerException e) {

		}
		return result;
	}

	String getKPDayLordName() {
		String var1 = "";

		try {
			var1 = daylrd[vedicDay];
		} catch (NullPointerException e) {

		}

		return var1;
	}

	public String getLagnaLordName() {
		String var1 = "";
		try {
			var1 = raslrd[getLagna()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	String getLagnaSign() {
		String var1 = "";
		try {
			var1 = ras[getLagna()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	int getMarsInBhavForMoonChart() {
		int var1 = getPlanetaryRasi(3);
		int var2 = getPlanetaryRasi(2);
		var1 -= var2;
		if (var1 < 0) {
			var1 += 12;
		}
		return var1 + 1;
	}

	public String getNadiName() {
		String var1 = "";
		try {
			var1 = nadi[getNadi()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getNakshatraLordName() {
		String var1 = "";
		try {
			var1 = naklrd[getNakshatraLord()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getNakshatraName() {
		String var1 = "";
		try {
			var1 = nak[getNakshatra()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getObliquityDms(String language) {
		return Util.dms(getObliquity(), language);
	}

	public String getPakshaName() {
		String var1 = "";
		try {
			var1 = paksha[getPaksha()];
			System.out.println(var1);
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getPayaName() {
		String var1 = "";
		try {
			var1 = paya[getPaya()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	int[] getPlanetInBhav() {
		int[] var1 = new int[12];
		int[] var2 = getPositionForShodasvarg(0);
		for (int var3 = 1; var3 <= 12; var3++) {
			var1[var3 - 1] = var2[var3] - var2[0] + 1;
			if (var1[var3 - 1] <= 0) {
				var1[var3 - 1] += 12;
			}
		}
		return var1;
	}

	public String getRasiLordName() {
		var var1 = "";
		try {
			var1 = raslrd[getRasiLord()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getRasiName() {
		String var1 = "";
		try {
			var1 = ras[getRasi()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getSiderealTimeHms() {
		return Util.hms(getSiderealTime());
	}

	public String getSunRiseTimeHms() {
		return Util.hms(getSunRiseTime());
	}

	public String getSunSetTimeHms() {
		return Util.hms(getSunSetTime());
	}

	public String getSunSignName() {
		String var1 = "";
		try {

			var1 = ras[getSunSign()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getTithiName() {
		String var1 = "";
		try {
			var1 = tit[getTithi()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getVarnaName() {
		String var1 = "";
		try {
			var1 = varna[getVarna()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getVasyaName() {
		String var1 = "";
		try {
			var1 = vasya[getVasya(getMoon())];
		} catch (Exception e) {

		}
		return var1;
	}

	public String getYoganame() {
		String var1 = "";
		try {
			var1 = yog[getYoga()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	public String getYoniName() {
		String var1 = "";
		try {
			var1 = yoni[getYoni()];
		} catch (NullPointerException e) {

		}
		return var1;
	}

	boolean isMangalDosh() {
		int lagna = getLagna();
		int marsRasi = getPlanetaryRasi(3);
		boolean isMangalDosh = false;
		int var1 = marsRasi - lagna;
		if (var1 < 0) {
			var1 += 12;
		}
		var1++;
		if (var1 == 1 || var1 == 4 || var1 == 7 || var1 == 8 || var1 == 12) {
			isMangalDosh = true;
		}
		return isMangalDosh;
	}

	boolean isMangalDoshForMoonChart() {
		getLagna();
		int marsRasi = getPlanetaryRasi(3);
		int moonRasi = getPlanetaryRasi(2);
		boolean isMangalDosh = false;
		int var1 = marsRasi - moonRasi;
		if (var1 < 0) {
			var1 += 12;
		}
		var1++;
		if (var1 == 1 || var1 == 4 || var1 == 7 || var1 == 8 || var1 == 12) {
			isMangalDosh = true;
		}
		return isMangalDosh;
	}

	boolean isCombust(int planetNumber) {
		var var2 = false;
		var var3 = plnt[1] - plnt[planetNumber + 1];
		if (var3 < 0.0) {
			var3 = -var3;
		}
		switch (planetNumber + 1) {
		case 2:
			if (var3 <= 12.0) {
				var2 = true;
			}
			break;

		case 3:
			if (var3 <= 17.0) {
				var2 = true;
			}
			break;

		case 4:
			if (isRetrograde(planetNumber)) {
				if (var3 <= 12.0) {
					var2 = true;
				}
			} else if (var3 <= 14.0) {
				var2 = true;
			}
			break;

		case 5:
			if (var3 <= 11.0) {
				var2 = true;
			}
			break;

		case 6:
			if (isRetrograde(planetNumber)) {
				if (var3 <= 8.0) {
					var2 = true;
				}
			} else if (var3 <= 10.0) {
				var2 = true;
			}
			break;

		case 7:
			if (var3 <= 16.0) {
				var2 = true;
			}
			break;
		}
		return var2;
	}

	boolean isRetrograde(int planetNumber) {
		boolean var2 = false;
		double var3 = plnt[planetNumber + 1] + aya;
		double var5 = plnt[planetNumber + 1 + 13] + aya;
		if (var3 > 360.0) {
			var3 -= 360.0;
		}
		if (var5 > 360.0) {
			var5 -= 360.0;
		}
		switch (planetNumber) {
		case 3:
		case 4:
		case 5:
		case 6:
		case 7: {
			if (var3 > var5) {
				var2 = true;
			}
			return var2;
		}

		default:
			return var2;
		}
	}

	boolean isPlanetDirect(int planetNo) {
		double var2 = plnt[planetNo];
		double var4 = plnt[planetNo + 13];
		double var7 = var4 - var2;
		boolean var10000;
		boolean planetNo1;
		if (planetNo != 1 && planetNo != 2) {
			if (Math.abs(var7) >= 3.0) {
				if (var7 < 0.0) {
					var4 += 360.0;
				} else {
					var2 += 360.0;
				}
				planetNo1 = var4 >= var2;
				return planetNo1;
			}
			var10000 = var4 >= var2;
		} else {
			var10000 = true;
		}
		planetNo1 = var10000;
		return planetNo1;
	}

	public double getFortuna() {
		double var1;
		if ((var1 = this.plntkp[1] + this.f4[0] - this.plntkp[0]) < 0.0D) {
			var1 += 360.0D;
		}

		return var1;
	}
}
