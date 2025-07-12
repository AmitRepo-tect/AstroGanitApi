package com.astroganit.lib.horo;

import java.util.ArrayList;
import java.util.Calendar;

import com.astroganit.lib.horo.util.HoroEnConstant;
import com.astroganit.lib.horo.util.HoroHiConstant;
import com.astroganit.lib.horo.util.Util;





public class MainHoroNew {
	String lang = "0";
	private int[][] plsig = new int[13][13];
	double[] plnt = new double[26];
	protected double[] plntkp = new double[12];
	private double[] jupc = new double[4];
	private double[] satc = new double[4];
	protected double[] tt = new double[4];
	protected double[] f2 = new double[12];
	private double[] f3 = new double[12];
	protected double[] f4 = new double[12];
	protected double[] f5 = new double[12];
	protected double[] f6 = new double[12];
	protected int[] totalAshtavarga = new int[12];
	// Integer variables
	protected int ayanamsaType = 0;
	private int kpAyanamsaType = 0;
	private int latitudeType = 0;

	// Double variables
	private double ps = 0.0;
	private double pt = 0.0;
	private double z1 = 0.0;
	private double z2 = 0.0;
	private double b6 = 0.0;
	private double s1 = 0.0;

	// Protected Double variables
	protected double lat = 0.0;
	protected double longt = 0.0;
	protected double aya = 0.0;
	protected double kpaya = 0.0;
	protected double obliq = 0.0;
	protected double sidtime = 0.0;
	protected double rise = 0.0;
	protected double set = 0.0;
	protected double sr = 0.0;
	protected double birthTime = 0.0;

	// Private Double variables
	private double h6 = 0.0;
	private double sRA = 0.0;
	private double sdec = 0.0;
	private double customizedAyan = 0.0;

	// Integer arrays with sizes specified
	private int[] r3 = new int[13];
	private int[] s3 = new int[13];

	// Protected arrays with sizes specified
	protected int[] house1 = new int[12];
	protected int[][] varga = new int[13][16];
	protected int[] lagnaArr = new int[13];
	protected int[] horaArr = new int[13];
	protected int[] drekkanaArr = new int[13];
	protected int[] chaturmshaArr = new int[13];
	protected int[] saptamsaArr = new int[13];
	protected int[] navmasaArr = new int[13];
	protected int[] dasamsaArr = new int[13];
	protected int[] dwadasamsaArr = new int[13];
	protected int[] shodasamsaArr = new int[13];
	protected int[] vimshamsaArr = new int[13];
	protected int[] siddhamsaArr = new int[13];
	protected int[] bhamsaArr = new int[13];
	protected int[] trimshamsaArr = new int[13];
	protected int[] khavedamsaArr = new int[13];
	protected int[] akshvedamsaArr = new int[13];
	protected int[] shastiamsaArr = new int[13];

	// Protected string variables
	protected String userName = "";
	protected String sex = "";

	// Public string variable
	public String place = "";

	// Protected integer variables
	protected int d = 0;
	protected int m = 0;
	protected int y = 0;
	protected int horarySeed = 0;
	protected int i = 0;
	protected int h = 0;
	protected int mt = 0;
	protected int s = 0;
	protected int latdeg = 0;
	protected int latmt = 0;
	protected int longdeg = 0;
	protected int longmt = 0;
	protected int dst = 0;
	protected int julDay = 0;
	protected int vedicDay = 0;
	protected int daylord = 0;

	// Private integer variables
	private int longsec = 0;
	private int latsec = 0;

	// Variables equivalent to Kotlin code
	protected char ns = ' ';
	protected char ew = ' ';
	protected float timeZone = 0.0f;
	protected double[] y1 = { 7.0, 20.0, 6.0, 10.0, 7.0, 18.0, 16.0, 19.0, 17.0 };
	protected int f = 0;
	private int[] owner = new int[12];
	protected int[][] ashtakArrayNew = new int[7][12];
	protected int[][][] prastakvarga = new int[8][8][13];
	protected int[][][] prastakvargaNew = new int[8][8][13];
	private int planetNo = 1;
	private int tableNo = 1;
	protected String[] day = new String[7];
	protected String[] daylrd = new String[7];
	protected String[] ras = new String[12];
	protected String[] raslrd = new String[12];
	protected String[] naklrd = new String[9];
	protected String[] tit = new String[30];
	protected String[] nak = new String[27];
	protected String[] yog = new String[27];
	protected String[] x1 = new String[9];
	private String[] graha = new String[13];
	private String[] bhavaNum = new String[12];
	protected String[] karan = new String[60];
	protected String[] paksha = new String[2];
	protected String[] div = new String[16];
	private String[] mangalDosh = new String[4];
	protected String[] gana = new String[3];
	protected String[] vasya = new String[6];
	protected String ayan = "";
	protected String[] paya = new String[4];
	private String[] deb = new String[12];
	private String[] exal = new String[12];
	private String[] aspects = new String[7];
	private String[] planetNameVim = new String[9];
	protected String[] planetName = new String[9];
	private String[] outcomes = new String[6];
	protected String[] nadi = new String[3];
	protected String[] varna = new String[4];
	protected String[] yoni = new String[14];
	private String[] x9 = new String[9];

	private void ashtakComputation(int plaNo, ArrayList<int[]> arrayList) {
		int[][] arr = new int[8][13];
		int var14 = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			int startPoint;
			if (i == arrayList.size() - 1) {
				startPoint = 12 - lagnaArr[0] + 1;
			} else {
				startPoint = 12 - lagnaArr[i + 1] + 1;
			}
			int inc = startPoint;
			for (int b = 0; b <= 11; b++) {
				if (inc >= 12) {
					inc -= 12;
				}
				arr[i][b] = arrayList.get(i)[inc];
				var14 += arrayList.get(i)[inc];
				inc++;
			}
			arr[i][12] = var14;
		}
		prastakvargaNew[plaNo] = arr;
	}

	protected void calculateAshtakNew() {
		ArrayList<int[]> auspiciousPointList1 = new ArrayList<int[]>();
		ArrayList<int[]> auspiciousPointList2 = new ArrayList<int[]>();
		ArrayList<int[]> auspiciousPointList3 = new ArrayList<int[]>();
		ArrayList<int[]> auspiciousPointList4 = new ArrayList<int[]>();
		ArrayList<int[]> auspiciousPointList5 = new ArrayList<int[]>();
		ArrayList<int[]> auspiciousPointList6 = new ArrayList<int[]>();
		ArrayList<int[]> auspiciousPointList7 = new ArrayList<int[]>();

		auspiciousPointList1.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0 });
		auspiciousPointList1.add(new int[] { 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0 });
		auspiciousPointList1.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0 });
		auspiciousPointList1.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1 });
		auspiciousPointList1.add(new int[] { 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0 });
		auspiciousPointList1.add(new int[] { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1 });
		auspiciousPointList1.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0 });
		auspiciousPointList1.add(new int[] { 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1 });
		ashtakComputation(0, auspiciousPointList1);

		auspiciousPointList2.add(new int[] { 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0 });
		auspiciousPointList2.add(new int[] { 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0 });
		auspiciousPointList2.add(new int[] { 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0 });
		auspiciousPointList2.add(new int[] { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 });
		auspiciousPointList2.add(new int[] { 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1 });
		auspiciousPointList2.add(new int[] { 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0 });
		auspiciousPointList2.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0 });
		auspiciousPointList2.add(new int[] { 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0 });
		ashtakComputation(1, auspiciousPointList2);

		auspiciousPointList3.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0 });
		auspiciousPointList3.add(new int[] { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0 });
		auspiciousPointList3.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0 });
		auspiciousPointList3.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0 });
		auspiciousPointList3.add(new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1 });
		auspiciousPointList3.add(new int[] { 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1 });
		auspiciousPointList3.add(new int[] { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0 });
		auspiciousPointList3.add(new int[] { 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0 });
		ashtakComputation(2, auspiciousPointList3);

		auspiciousPointList4.add(new int[] { 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1 });
		auspiciousPointList4.add(new int[] { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0 });
		auspiciousPointList4.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0 });
		auspiciousPointList4.add(new int[] { 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1 });
		auspiciousPointList4.add(new int[] { 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1 });
		auspiciousPointList4.add(new int[] { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0 });
		auspiciousPointList4.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0 });
		auspiciousPointList4.add(new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0 });
		ashtakComputation(3, auspiciousPointList4);

		auspiciousPointList5.add(new int[] { 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0 });
		auspiciousPointList5.add(new int[] { 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0 });
		auspiciousPointList5.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0 });
		auspiciousPointList5.add(new int[] { 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0 });
		auspiciousPointList5.add(new int[] { 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0 });
		auspiciousPointList5.add(new int[] { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0 });
		auspiciousPointList5.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 });
		auspiciousPointList5.add(new int[] { 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0 });
		ashtakComputation(4, auspiciousPointList5);

		auspiciousPointList6.add(new int[] { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1 });
		auspiciousPointList6.add(new int[] { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1 });
		auspiciousPointList6.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1 });
		auspiciousPointList6.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0 });
		auspiciousPointList6.add(new int[] { 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0 });
		auspiciousPointList6.add(new int[] { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0 });
		auspiciousPointList6.add(new int[] { 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0 });
		auspiciousPointList6.add(new int[] { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0 });
		ashtakComputation(5, auspiciousPointList6);

		auspiciousPointList7.add(new int[] { 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0 });
		auspiciousPointList7.add(new int[] { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0 });
		auspiciousPointList7.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1 });
		auspiciousPointList7.add(new int[] { 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1 });
		auspiciousPointList7.add(new int[] { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1 });
		auspiciousPointList7.add(new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1 });
		auspiciousPointList7.add(new int[] { 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0 });
		auspiciousPointList7.add(new int[] { 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0 });
		ashtakComputation(6, auspiciousPointList7);

		for (int k = 0; k <= 6; k++) {
			for (int i = 0; i <= 11; i++) {
				int var1 = 0;
				for (int j = 0; j <= 7; j++) {
					var1 += prastakvargaNew[k][j][i];
				}
				ashtakArrayNew[k][i] = var1;
			}
		}
	}

	private void calculateAyan() {
		plnt[0] = 22.460148 + 1.396042 * b6 + 3.08E-4 * b6 * b6;
		aya = plnt[0];
	}

	public double getAsc() {
		return f2[0];
	}

	private void bahvgnl(double j0, double k0, int u) {
		for (int var6 = 0; var6 < 3; var6++) {
			var var8 = j0 + k0 * (double) var6;
			if (var8 >= 360.0) {
				var8 -= 360.0;
			}
			int var7 = u + var6 - 1;
			f2[var7] = var8;
		}
	}

	private void calcSidreal() {
		double var1 = 24.0 * Util.fract(0.2769 + 100.00214 * b6);
		double var3 = h6 * 24.0 + 12.0;
		double var5 = longt / 15.0;
		sidtime = 24.0 * Util.fract((var1 + var3 + var5) / 24.0);
		if (sidtime < 0.0) {
			sidtime += 24.0;
		}
	}

	private void calcObliq() {
		obliq = 23.452294 - 0.0130125 * b6;
	}

	protected void initBhav() {
		aya = plnt[0];

		double var1 = bhavspl(sidtime, lat);
		double var3 = bhavspl(sidtime - 6.0, 0.0);
		var var5 = (var3 + 180.0 - var1) / 3.0;
		if (var3 > var1) {
			var5 -= 120.0;
		}
		double var7 = 60.0 - var5;
		bahvgnl(var1, var5, 1);
		bahvgnl(var3 + 180.0, var7, 4);
		bahvgnl(var1 + 180.0, var5, 7);
		bahvgnl(var3, var7, 10);
		f3[0] = (f2[11] + f2[0]) / 2.0;
		if (f2[0] < f2[11]) {
			f3[0] += 180.0;
		}

		if (f3[0] >= 360.0) {
			f3[0] -= 360.0;
		}

		for (int var9 = 1; var9 < 12; var9++) {
			f3[var9] = (f2[var9 - 1] + f2[var9]) / 2.0;
			if (f2[var9] < f2[var9 - 1]) {
				f3[var9] += 180.0;
			}

			if (f3[var9] > 360.0) {
				f3[var9] -= 360.0;
			}
		}
	}

	private double bhavspl(double a0, double c0) {
		double var5 = aya;
		double var7 = obliq * z2;
		var var9 = a0 * 15.0 + 90.0;
		if (var9 >= 360.0) {
			var9 -= 360.0;
		}

		double a0Var = a0 * z1 / 12.0;
		double c0Var = c0 * z2;
		if (a0Var == 0.0 && c0Var == 0.0) {
			return 90.0;
		} else {
			var var11 = Math
					.atan(-Math.cos(a0Var)
							/ (Math.sin(c0Var) * Math.sin(var7) / Math.cos(c0Var) + Math.sin(a0Var) * Math.cos(var7)))
					/ z2;
			if (var11 < 0.0) {
				var11 += 180.0;
			}

			if (var9 - var11 > 75.0) {
				var11 += 180.0;
			}
			var11 -= var5;
			if (var11 < 0.0) {
				var11 += 360.0;
			}

			if (var11 > 360.0) {
				var11 -= 360.0;
			}

			return var11;
		}
	}

	protected void calculateSunAndRiseSet(int yy, int mm, int dd, double lon, double lat) {
		double var8 = (367L * yy - (7 * (yy + (mm + 9) / 12) / 4) + (mm * 275 / 9) + dd - 730530L + 0.5 - lon / 360.0);
		double var16 = revolution(gmsto(var8) + 180.0 + lon);
		sunRADec(var8);
		double var14 = 12.0 - rev180(var16 - sRA) / 15.0;
		double var10 = 0.2666 / sr;
		double var12;
		double var20 = ((Math.sin((-0.5833333333333334 - var10) * 0.017453292519943295)
				- Math.sin(lat * 0.017453292519943295) * Math.sin(sdec * 0.017453292519943295))
				/ (Math.cos(lat * 0.017453292519943295) * Math.cos(sdec * 0.017453292519943295)));
		if (var20 >= 1.0) {
			var12 = 0.0;
		} else if (var20 <= -1.0) {
			var12 = 12.0;
		} else {
			var12 = 57.29577951308232 * Math.acos(var20) / 15.0;
		}
		rise = var14 - var12 + timeZone;
		set = var14 + var12 + timeZone;
	}

	void calculateShodashvarga() {

		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				lagnaArr[var1] = getLagnaArr(f2[var1]);
			} else {
				lagnaArr[var1] = getLagnaArr(plnt[var1]);
			}
		}
		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				horaArr[var1] = getHora(f2[var1]);
			} else {
				horaArr[var1] = getHora(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				drekkanaArr[var1] = getDrekkana(f2[var1]);
			} else {
				drekkanaArr[var1] = getDrekkana(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {

			if (var1 == 0) {
				chaturmshaArr[var1] = getChaturtamsa(f2[var1]);
			} else {
				chaturmshaArr[var1] = getChaturtamsa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				saptamsaArr[var1] = getSaptamsa(f2[var1]);
			} else {
				saptamsaArr[var1] = getSaptamsa(plnt[var1]);
			}
		}
		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				navmasaArr[var1] = getNavmasa(f2[var1]);
			} else {
				navmasaArr[var1] = getNavmasa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				dasamsaArr[var1] = getDasamsa(f2[var1]);
			} else {
				dasamsaArr[var1] = getDasamsa(plnt[var1]);
			}
		}
		for (int var1 = 0; var1 < 13; var1++) {

			if (var1 == 0) {
				dwadasamsaArr[var1] = getDwadasamsa(f2[var1]);
			} else {
				dwadasamsaArr[var1] = getDwadasamsa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {

			if (var1 == 0) {
				shodasamsaArr[var1] = getShodasamsa(f2[var1]);
			} else {
				shodasamsaArr[var1] = getShodasamsa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				vimshamsaArr[var1] = getVimshamsa(f2[var1]);
			} else {
				vimshamsaArr[var1] = getVimshamsa(plnt[var1]);
			}
		}
		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				siddhamsaArr[var1] = getSiddhamsa(f2[var1]);
			} else {
				siddhamsaArr[var1] = getSiddhamsa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {
			if (var1 == 0) {
				bhamsaArr[var1] = getBhamsa(f2[var1]);
			} else {
				bhamsaArr[var1] = getBhamsa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {

			if (var1 == 0) {
				trimshamsaArr[var1] = getTrimshamsa(f2[var1]);
			} else {
				trimshamsaArr[var1] = getTrimshamsa(plnt[var1]);
			}
		}
		for (int var1 = 0; var1 < 13; var1++) {

			if (var1 == 0) {
				khavedamsaArr[var1] = getKhavedamsa(f2[var1]);
			} else {
				khavedamsaArr[var1] = getKhavedamsa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {

			if (var1 == 0) {
				akshvedamsaArr[var1] = getAkshvedamsa(f2[var1]);
			} else {
				akshvedamsaArr[var1] = getAkshvedamsa(plnt[var1]);
			}
		}

		for (int var1 = 0; var1 < 13; var1++) {

			if (var1 == 0) {
				shastiamsaArr[var1] = getShastiamsa(f2[var1]);
			} else {

				shastiamsaArr[var1] = getShastiamsa(plnt[var1]);
			}
		}

	}

	private int getLagnaArr(double deg) {
		return ((int) deg) / 30 + 1;
	}

	private int getHora(double deg) {
		int var3 = 0;
		int var4 = 0;
		double var5 = deg;
		int deg1 = ((int) deg / 30 + 1) % 2;

		if (deg1 == 0) {
			var4 = 0;
		}
		if (deg1 > 0) {
			var4 = 1;
		}

		deg1 = ((int) var5 % 30);

		if (deg1 < 15 && var4 == 0) {
			var3 = 4;
		}
		if (deg1 < 15 && var4 > 0) {
			var3 = 5;
		}
		if (deg1 >= 15 && var4 == 0) {
			var3 = 5;
		}
		if (deg1 >= 15 && var4 > 0) {
			var3 = 4;
		}

		return var3;
	}

	private int getChaturtamsa(double deg) {
		double var1 = deg % 30.0;
		int var2 = (int) (deg / 30.0) + 1;
		var result = 0;
		if (0 <= var1 && var1 < 7.5) {
			result = var2;
		} else if (7.5 <= var1 && var1 < (2 * 7.5)) {
			result = var2 + 3;
		} else if ((2 * 7.5) <= var1 && var1 < (3 * 7.5)) {
			result = var2 + 6;
		} else if ((3 * 7.5) <= var1 && var1 < (4 * 7.5)) {
			result = var2 + 9;
		}
		result %= 12;
		if (result == 0) {
			result = 12;
		}
		return result;
	}

	private int getDasamsa(double deg) {
		int result;
		int var4 = ((int) deg / 30 + 1);
		int deg1 = (int) deg % 30;
		int var2 = 0;

		double var8 = 0.0;
		while (var8 <= 10.0) {
			if (var8 * 3.0 <= deg1 && deg1 < (var8 + 1.0) * 3.0) {
				var2 = (int) var8;
				break;
			}
			var8++;
		}
		if (var4 % 2 == 0) {
			result = var4 + 8 + var2;
		} else {
			result = var4 + var2;
		}
		result %= 12;
		if (result == 0) {
			result = 12;
		}
		return result;
	}

	private int getDrekkana(double deg) {
		int var3 = 0;
		int var6 = (int) deg;
		double var4 = deg % 30.0;
		var6 /= 30;
		if (0.0 <= var4 && var4 < 10.0) {
			var3 = var6 + 1;
		} else if (10.0 <= var4 && var4 < 20.0) {
			var3 = var6 + 5;
		} else if (var4 >= 20.0 && var4 <= 30.0) {
			var3 = var6 + 9;
		}

		var3 %= 12;
		if (var3 == 0) {
			var3 = 12;
		}
		return var3;
	}

	private int getDwadasamsa(double deg) {
		int var3 = ((int) deg / 30) + 1;
		double var5 = deg % 30.0;
		int deg1 = 0;

		double var8 = 0.0;
		while (var8 <= 12.0) {
			if (var8 * 2.5 <= var5 && var5 < (var8 + 1.0) * 2.5) {
				deg1 = (int) var8;
				break;
			}
			var8++;
		}

		deg1 = (var3 + deg1) % 12;
		if (deg1 == 0) {
			deg1 = 12;
		}

		return deg1;
	}

	private int getKhavedamsa(double deg) {
		int var3 = 0;
		double var4 = deg % 30.0;
		int deg1 = (((int) deg / 30) + 1) % 2;

		int var6 = 0;
		for (int var2 = 0; var2 < 40; var2++) {
			if ((var2 * 0.75) <= var4 && var4 < ((var2 + 1) * 0.75)) {
				var6 = var2;
			}
		}

		switch (deg1) {
		case 0:
			var3 = var6 + 7;
			break;
		case 1:
			var3 = var6 + 1;
			break;
		default:
			// Optional: handle any other cases (if needed)
			break;
		}

		var3 %= 12;
		if (var3 == 0) {
			var3 = 12;
		}

		return var3;
	}

	private int getSaptamsa(double deg) {
		int result;
		int var4 = ((int) deg / 30) + 1;
		int deg1 = (int) deg % 30;
		int var2 = 0;

		for (int var8 = 0; var8 < 8; var8++) {
			double var8Double = (double) var8;
			if (var8Double * 4.2857138 <= deg1 && deg1 < (var8Double + 1.0) * 4.2857138) {
				var2 = var8;
				break;
			}
		}
		if (var4 % 2 == 0) {
			result = (var4 + 6 + var2);
		} else {
			result = (var4 + var2);
		}
		result %= 12;
		if (result == 0) {
			result = 12;
		}

		return result;
	}

	private int getNavmasa(double deg) {
		int result;
		int var4 = ((int) deg / 30) + 1;
		double deg1 = deg % 30;
		int var2 = 0;
		for (int var8 = 0; var8 < 9; var8++) {
			if (var8 * 3.3333333 <= deg1 && deg1 < (var8 + 1.0) * 3.3333333) {
				var2 = var8;
				break;
			}
		}
		switch (var4) {
		case 1:
		case 5:
		case 9:
			var4 = 1;
			break;
		case 2:
		case 6:
		case 10:
			var4 = 10;
			break;
		case 3:
		case 7:
		case 11:
			var4 = 7;
			break;
		case 4:
		case 8:
		case 12:
			var4 = 4;
			break;
		default:
			// Optional: handle any other cases
			break;
		}

		result = (var4 + var2) % 12;
		if (result == 0) {
			result = 12;
		}

		return result;
	}

	private int getVimshamsa(double deg) {
		int var3 = 0;
		double var4 = deg % 30.0;
		int var6 = 0;
		int deg1 = (int) (deg / 30.0) + 1;
		for (int var2 = 0; var2 <= 19; var2++) {
			if ((double) var2 * 1.5 <= var4 && var4 < (double) (var2 + 1) * 1.5) {
				var6 = var2;
			}
		}
		switch (deg1) {
		case 1:
		case 4:
		case 7:
		case 10:
			var3 = var6 + 1;
			break;
		case 2:
		case 5:
		case 8:
		case 11:
			var3 = var6 + 9;
			break;
		case 3:
		case 6:
		case 9:
		case 12:
			var3 = var6 + 5;
			break;
		default:
			// Optional: handle any other cases if necessary
			break;
		}

		var3 %= 12;
		if (var3 == 0) {
			var3 = 12;
		}
		return var3;
	}

	private int getSiddhamsa(double deg) {
		int var3;
		double var4 = deg % 30.0;
		int var6 = 0;
		int deg1 = ((int) (deg / 30.0) + 1) % 2;

		for (int var2 = 0; var2 < 24; var2++) {
			if (var2 * 1.25 <= var4 && var4 < (var2 + 1) * 1.25) {
				var6 = var2;
			}
		}

		if (deg1 == 0) {
			var3 = var6 + 4;
		} else if (deg1 == 1) {
			var3 = var6 + 5;
		} else {
			var3 = 0;
		}

		var3 %= 12;
		if (var3 == 0) {
			var3 = 12;
		}

		return var3;
	}

	private int getBhamsa(double deg) {
		int var3;
		double var4 = deg % 30.0;
		int var6 = 0;
		int deg1 = (int) (deg / 30.0) + 1;

		for (int var2 = 0; var2 < 27; var2++) {
			if (var2 * 1.111111111111 <= var4 && var4 < (var2 + 1) * 1.111111111111) {
				var6 = var2;
			}
		}

		switch (deg1) {
		case 1:
		case 5:
		case 9:
			var3 = var6 + 1;
			break;
		case 2:
		case 6:
		case 10:
			var3 = var6 + 4;
			break;
		case 3:
		case 7:
		case 11:
			var3 = var6 + 7;
			break;
		default:
			var3 = var6 + 10;
			break;
		}

		if ((var3 % 12) == 0) {
			return 12;
		} else {
			return var3 % 12;
		}
	}

	private int getTrimshamsa(double deg) {
		int var3 = 0;
		int var4 = (((int) deg / 30) + 1) % 2;
		double var6 = deg % 30.0;
		int deg1 = 0;
		double start = 0.0;
		double end = 30.0;
		double step = 1.0;

		double var9 = start;
		while (var9 <= end) {
			if (var9 <= var6 && var6 < var9 + 1.0) {
				deg1 = (int) var9;
				break;
			}
			var9 += step;
		}

		if (var4 > 0) {
			if (deg1 < 5) {
				var3 = 1;
			} else if (deg1 >= 5 && deg1 < 10) {
				var3 = 11;
			} else if (deg1 >= 10 && deg1 < 18) {
				var3 = 9;
			} else if (deg1 >= 18 && deg1 < 25) {
				var3 = 3;
			} else if (deg1 >= 25 && deg1 < 30) {
				var3 = 7;
			}
		} else if (var4 == 0) {
			if (deg1 < 5) {
				var3 = 2;
			} else if (deg1 >= 5 && deg1 < 12) {
				var3 = 6;
			} else if (deg1 >= 12 && deg1 < 20) {
				var3 = 12;
			} else if (deg1 >= 20 && deg1 < 25) {
				var3 = 10;
			} else if (deg1 >= 25 && deg1 < 30) {
				var3 = 8;
			}
		}

		return var3;
	}

	private int getAkshvedamsa(double deg) {
		int var3;
		double var4 = deg % 30.0;
		int var6 = 0;
		int deg1 = (int) (deg / 30.0) + 1;

		for (int var2 = 0; var2 < 45; var2++) {
			if (var2 * 0.666666666667 <= var4 && var4 < (var2 + 1) * 0.666666666667) {
				var6 = var2;
			}
		}

		switch (deg1) {
		case 1:
		case 4:
		case 7:
		case 10:
			var3 = var6 + 1;
			break;
		case 2:
		case 5:
		case 8:
		case 11:
			var3 = var6 + 5;
			break;
		default:
			var3 = var6 + 9;
			break;
		}
		var3 %= 12;
		if (var3 == 0) {
			var3 = 12;
		}

		return var3;
	}

	private int getShastiamsa(double deg) {
		double var4 = deg * 2.0;
		int var3 = (int) var4;
		var deg1 = ((int) deg / 30 + 1 + var3) % 12;
		if (deg1 == 0) {
			deg1 = 12;
		}
		return deg1;
	}

	private int getShodasamsa(double deg) {
		int var3 = 0;
		int var4 = ((int) deg / 30) + 1;
		double var6 = deg % 30.0;
		int deg1 = 0;

		var var9 = 0.0;
		while (var9 <= 16.0) {
			if (var9 * 1.875 <= var6 && var6 < (var9 + 1.0) * 1.875) {
				deg1 = (int) var9;
				break;
			}
			var9++;
		}

		switch (var4) {
		case 1:
		case 4:
		case 7:
		case 10:
			var3 = 1;
			break;
		case 2:
		case 5:
		case 8:
		case 11:
			var3 = 5;
			break;
		case 3:
		case 6:
		case 9:
		case 12:
			var3 = 9;
			break;
		default:
			// Optionally handle default case if needed
			break;
		}

		deg1 = (var3 + deg1) % 12;
		if (deg1 == 0) {
			deg1 = 12;
		}

		return deg1;
	}

	protected void calculateCusp() {
		double var8 = obliq * z2;
		double var10 = aya;
		plnt[0] = kpaya;
		aya = kpaya;

		double var2 = Math.asin(Math.tan(var8) * Math.tan(lat * z2));
		double var4 = Math.atan(Math.sin(var2 / 3.0) / Math.tan(var8));
		double var6 = Math.atan(Math.sin(var2 * 2.0 / 3.0) / Math.tan(var8));
		var4 /= z2;
		var6 /= z2;

		f4[0] = bhavspl(sidtime, lat);
		f4[9] = bhavspl(sidtime - 6.0, 0.0);
		f4[10] = bhavspl(sidtime - 4.0, var4);
		f4[11] = bhavspl(sidtime - 2.0, var6);
		f4[1] = bhavspl(sidtime + 2.0, var6);
		f4[2] = bhavspl(sidtime + 4.0, var4);
		for (int var1 = 0; var1 < 3; var1++) {
			f4[var1 + 3] = f4[var1 + 9] - 180.0;
			f4[var1 + 6] = f4[var1] + 180.0;
		}

		boolean var12 = false;
		int var13;
		double[] var10000;
		var13 = 0;
		while (var13 < 12) {
			if (java.lang.Double.isNaN(f4[var13])) {
				var12 = true;
				break;
			}
			if (f4[var13] < 0.0) {
				var10000 = f4;
				var10000[var13] += 360.0;
			} else if (f4[var13] > 360.0) {
				var10000 = f4;
				var10000[var13] -= 360.0;
			}
			++var13;
		}
		if (var12) {
			var13 = 1;
			while (var13 < 12) {
				f4[var13] = f4[0] + 30.0 * (double) var13;
				if (f4[var13] >= 360.0) {
					var10000 = f4;
					var10000[var13] -= 360.0;
				}
				++var13;
			}
		}
		aya = var10;
		plnt[0] = var10;
	}

	private void customizedAyan() {
		double var1 = plnt[0] - customizedAyan;
		for (int var3 = 1; var3 < 26; var3++) {
			if (var3 != 13) {
				plnt[var3] += var1;
				if (plnt[var3] >= 360.0) {
					plnt[var3] -= 360.0;
				}
				if (plnt[var3] < 0.0) {
					plnt[var3] += 360.0;
				}
			}
		}
		plnt[0] = customizedAyan;
		aya = customizedAyan;
	}

	int getKaran() {
		var var1 = plnt[2] - plnt[1];
		if (var1 < 0.0) {
			var1 += 360.0;
		}
		return (int) (var1 / 6.0);
	}

	private double gmsto(double d) {
		return revolution(818.9874 + d * 0.985647352);
	}

	private void initSatAndJup() {
		double var1 = b6 / 5.0 + 0.1;
		double var3 = 2.0 * z1 * Util.fract(0.65965 + 8.43029 * b6);
		double var5 = 2.0 * z1 * Util.fract(0.73866 + 3.39476 * b6);
		double var7 = 2.0 * z1 * Util.fract(0.67644 + 1.19019 * b6);
		double var9 = var5 * 5.0 - var3 * 2.0;
		double var11 = var7 - var5;
		double var13 = var5 - var3;
		double var15 = Math.sin(var9);
		double var17 = Math.sin(var9 * 2.0);
		double var19 = Math.cos(var9);
		double var21 = Math.cos(var9 * 2.0);
		double var23 = Math.sin(var13);
		double var25 = Math.sin(var13 * 2.0);
		double var27 = Math.sin(var13 * 3.0);
		double var29 = Math.cos(var13);
		double var31 = Math.cos(var13 * 2.0);
		double var33 = Math.cos(var13 * 3.0);
		double var35 = Math.sin(var5);
		double var37 = Math.sin(var5 * 2.0);
		double var39 = Math.sin(var5 * 3.0);
		double var41 = Math.cos(var5);
		double var43 = Math.cos(var5 * 2.0);
		double var45 = Math.cos(var5 * 3.0);
		double var47 = Math.sin(var11 * 3.0);

		double var49 = (0.331 - var1 * 0.01) * var15 - var1 * 0.064 * var19 + var23 * 0.014
				+ (var25 * 0.018 - var29 * 0.034 * var35 - var23 * 0.036 * var41);
		double var51 = var15 * 0.007 - var19 * 0.02 + var35 * (var23 * 0.007 + var29 * 0.034 + var31 * 0.006)
				+ var41 * (var23 * 0.038 + var25 * 0.006 - var29 * 0.007) + var37 * (var23 * -0.005 + var29 * 0.004)
				+ var43 * (var23 * 0.004 + var29 * 0.006);
		double var53 = (var15 * 3606.0 + (1289.0 - var1 * 580.0) * var19
				+ var35 * (var23 * -6764.0 - var25 * 1110.0 - 204.0 + var29 * 1248.0)
				+ var41 * (var23 * 1460.0 - 817.0 + var29 * 6074.0 + var31 * 992.0 + var33 * 508.0)
				+ var37 * (var23 * -956.0 - var29 * 997.0 + var31 * 480.0)
				+ var43 * (var23 * -956.0 + var25 * 490.0 + 179.0 + var29 * 1024.0 - var31 * 437.0)) * 1.0E-7;
		double var55 = (var19 * -263.0 + var29 * 205.0 + var31 * 693.0 + var33 * 312.0
				+ var35 * (var23 * 299.0 + var31 * 181.0) + var41 * (var25 * 204.0 + var27 * 111.0 - var31 * 111.0))
				* 1.0E-6;
		double var57 = (-0.814 + var1 * 0.018 - var1 * 0.017 * var1) * var15
				+ ((-0.01 + var1 * 0.161) * var19 - var23 * 0.149 - var25 * 0.041 - var27 * 0.015)
				+ var35 * (-0.006 - var25 * 0.017 + var29 * 0.081 + var31 * 0.015)
				+ var41 * (var23 * 0.086 + var29 * 0.025 + var31 * 0.014 + var33 * 0.006);
		double var59 = (0.077 + var1 * 0.007) * var15 + (0.046 - var1 * 0.015) * var19 - var23 * 0.007
				- var35 * (var23 * 0.076 + var25 * 0.025 + var27 * 0.009)
				+ var41 * (-0.073 - var29 * 0.15 + var31 * 0.027 + var33 * 0.01)
				+ var45 * (var23 * -0.014 - var29 * 0.008 + var31 * 0.014)
				+ var43 * (var23 * -0.014 + var25 * 0.012 + var29 * 0.015 - var31 * 0.013);
		double var61 = ((-7927.0 + var1 * 2548.0) * var15 + (13381.0 + var1 * 1226.0) * var19 + var17 * 248.0
				- var21 * 305.0 + var25 * 412.0
				+ var35 * (12415.0 + (390.0 - var1 * 617.0) * var23 + var29 * 26599.0 - var31 * 4687.0 - var33 * 1870.0
						- 821.0 * Math.cos(var13 * 4.0))
				+ var41 * (163.0 - var1 * 611.0 - var23 * 12696.0 - var25 * 4200.0 - var27 * 1503.0
						- 619.0 * Math.sin(var13 * 4.0) - (282.0 + var1 * 1306.0) * var29)
				+ var37 * (-350.0 + var23 * 2211.0 - var25 * 2208.0 - var27 * 568.0 - var29 * 2780.0 + var31 * 2022.0)
				+ var43 * (-490.0 - var23 * 2842.0 - var29 * 1594.0 + var31 * 2162.0 + var33 * 561.0 + var47 * 469.0))
				* 1.0E-7;
		double var63 = (var15 * 572.0 + var19 * 2933.0 + var29 * 33629.0 - var31 * 3081.0 - var33 * 1423.0
				- 671.0 * Math.cos(var13 * 4.0)
				+ var35 * (1098.0 - var23 * 2812.0 + var25 * 688.0 - var27 * 393.0 + var29 * 2138.0 - var31 * 999.0
						- var33 * 642.0)
				+ var41 * (-890.0 + var23 * 2206.0 - var25 * 1590.0 - var27 * 647.0 + var29 * 2285.0 + var31 * 2172.0
						+ var33 * 296.0)
				+ var37 * (var25 * -267.0 - var29 * 778.0 + var31 * 495.0 + var33 * 250.0)
				+ var43 * (var23 * -856.0 + var25 * 441.0 + var31 * 296.0 + var33 * 211.0)
				+ var39 * (var23 * -427.0 + var27 * 398.0) + var45 * (var29 * 344.0 - var33 * 427.0)) * 1.0E-6;
		jupc[0] = var49;
		jupc[1] = var51;
		jupc[2] = var53;
		jupc[3] = var55;
		satc[0] = var57;
		satc[1] = var59;
		satc[2] = var61;
		satc[3] = var63;
	}

	void initialize() throws Exception {
		initializesArrayValues();
		ps = 0.0;
		pt = 0.0;
		z1 = 3.14159265359;
		z2 = z1 / 180.0;
		modifyLatLng();
		adjustDst();
		calculateH6AndB6();
		calcSidreal();
		calcObliq();
		setAyanType(ayanamsaType);
		calculateAyan();
		switch (kpAyanamsaType) {
		case 0: {
			calcKPAyanNew((double) d, (double) m, (double) y);
			break;
		}
		case 1: {
			calcKPAyan((double) d, (double) m, (double) y);
			break;
		}
		default: {
			calcKPAyanKhu((double) d, (double) m, (double) y);
			break;
		}
		}
		initSatAndJup();
		calculatePlaDeg();
		switch (ayanamsaType) {
		case 1:
			initKPAyan();
			break;
		case 2:
			ramanAyan();
			break;
		case 3:
			sayan();
			break;
		case 4:
			customizedAyan();
			break;
		}
	}

	private void modifyLatLng() {
		lat = (double) latdeg + (double) latmt / 60.0 + (double) latsec / 3600;
		if (latitudeType == 1) {
			lat = returnGeoCentricLat(lat);
		}
		if (ns == 'S' || ns == 's') {
			lat = -lat;
		}
		longt = (double) longdeg + (double) longmt / 60.0 + (double) longsec / 3600;
		if (ew == 'W' || ew == 'w') {
			longt = -longt;
		}
	}

	private void adjustDst() {
		if (dst != 0) {
			setUserTime();
		}
	}

	private void calculateH6AndB6() {
		julDay = getJD(d, m, y);
		h6 = (((double) h + (double) mt / 60.0 + (double) s / 3600.0 - (12.0 + (double) timeZone)) / 24.0);
		b6 = ((double) (julDay - 694025) + h6) / 36525.0;
		daylord = (julDay + 4) % 7;
	}

	private void calculatePlaDeg() {
		// initSun()
		var params = getParams(b6, 1);
		plnt[1] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 1);
		// initMoon()
		plnt[2] = initMoonDeg(b6);
		// initMars()
		params = getParams(b6, 3);
		plnt[3] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 3);
		// initMer()
		params = getParams(b6, 4);
		plnt[4] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 4);
		// initJup()
		params = getParams(b6, 5);
		plnt[5] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 5);
		// initVen()
		params = getParams(b6, 6);
		plnt[6] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 6);
		// initSat()
		params = getParams(b6, 7);
		plnt[7] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 7);
		// initRahu()
		plnt[8] = initRahuDeg(b6);
		// initKetu()
		plnt[9] = initKetuDeg(b6);
		// initUra()
		params = getParams(b6, 10);
		plnt[10] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 10);
		// initNep()
		params = getParams(b6, 11);
		plnt[11] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 11);
		// initPlu()
		params = getParams(b6, 12);
		plnt[12] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 12);
		// ret = 1
		// b6 += 1.1407711613050422E-6
		// initSun()
		params = getParams(b6 + 1.1407711613050422E-6, 1);
		plnt[14] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 1);
		// initMoon()
		plnt[15] = initMoonDeg(b6 + 1.1407711613050422E-6);
		// initMars()
		params = getParams(b6 + 1.1407711613050422E-6, 3);
		plnt[16] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 3);
		// initMer()
		params = getParams(b6 + 1.1407711613050422E-6, 4);
		plnt[17] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 4);
		// initJup()
		params = getParams(b6 + 1.1407711613050422E-6, 5);
		plnt[18] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 5);
		// initVen()
		params = getParams(b6 + 1.1407711613050422E-6, 6);
		plnt[19] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 6);
		// initSat()
		params = getParams(b6 + 1.1407711613050422E-6, 7);
		plnt[20] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 7);
		// initRahu()
		plnt[21] = initRahuDeg(b6 + 1.1407711613050422E-6);
		// initKetu()
		plnt[22] = initKetuDeg(b6 + 1.1407711613050422E-6);
		// initUra()
		params = getParams(b6 + 1.1407711613050422E-6, 10);
		plnt[23] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 10);
		// initNep()
		params = getParams(b6 + 1.1407711613050422E-6, 11);
		plnt[24] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 11);
		// initPlu()
		params = getParams(b6 + 1.1407711613050422E-6, 12);
		plnt[25] = planet(params[0], params[1], params[2], params[3], params[4], params[5], 12);
	}

	private double[] getParams(double b6, int plaNo) {
		double[] arr = new double[6];
		switch (plaNo) {
		case 1: {
			// sun
			arr[0] = 360.0 * Util.fract(0.71455 + 99.99826 * b6);
			arr[1] = 258.76 + 0.323 * b6;
			arr[2] = 0.0;
			arr[3] = 0.016751 - 4.2E-5 * b6;
			arr[4] = 0.0;
			arr[5] = 1.0;
			break;
		}

		case 3: {
			// mars
			arr[0] = 360.0 * Util.fract(0.75358 + 53.16751 * b6);
			arr[1] = 311.76 + 0.445 * b6;
			arr[2] = 26.33 - 0.625 * b6;
			arr[3] = 0.093313 - 9.2E-5 * b6;
			arr[4] = 1.850333 + 6.75E-4 * b6;
			arr[5] = 1.5237;
			break;
		}

		case 4: {
			// mer
			arr[0] = 360.0 * Util.fract(0.43255 + 415.20187 * b6);
			arr[1] = 53.44 + 0.159 * b6;
			arr[2] = 24.69 - 0.211 * b6;
			arr[3] = 0.205614 - 2.0E-5 * b6;
			arr[4] = 7.00288 + 0.001861 * b6;
			arr[5] = 0.3871;
			break;
		}

		case 5: {
			// jup
			arr[0] = 360.0 * Util.fract(0.59886 + 8.43029 * b6) + jupc[0];
			arr[3] = 0.048335 - 1.64E-4 * b6 + jupc[2];
			arr[1] = 350.26 + 0.214 * b6 + jupc[1] / arr[3];
			arr[2] = 76.98 - 0.386 * b6;
			arr[4] = 1.308376 - 0.005696 * b6;
			arr[5] = 5.2026 + jupc[3];
			break;
		}

		case 6: {
			// ven
			arr[0] = 360.0 * Util.fract(0.88974 + 162.54949 * this.b6);
			arr[1] = 107.7 + 0.012 * this.b6;
			arr[2] = 53.22 - 0.496 * this.b6;
			arr[3] = 0.00682 - 4.8E-5 * this.b6;
			arr[4] = 3.39363 + 0.001 * this.b6;
			arr[5] = 0.72333;
			break;
		}

		case 7: {
			// sat
			arr[0] = 360.0 * Util.fract(0.67807 + 3.39476 * this.b6) + this.satc[0];
			arr[3] = 0.055892 - 3.46E-4 * this.b6 + this.satc[2];
			arr[1] = 68.64 + 0.562 * this.b6 + this.satc[1] / arr[3];
			arr[2] = 90.33 - 0.523 * this.b6;
			arr[4] = 2.49252 - 0.00392 * this.b6;
			arr[5] = 9.5547 + this.satc[3];
			break;
		}

		case 10: {
			// ura
			arr[0] = 360.0 * Util.fract(0.61372 + 1.190191 * this.b6) - 0.166
					* Math.sin((360.0 * Util.fract(0.61372 + 1.190191 * this.b6) + 50.0 + this.plnt[0]) * this.z2);
			arr[1] = 149.09 + 0.088 * this.b6;
			arr[2] = 51.02 - 0.897 * this.b6;
			arr[3] = 0.046344 - 2.7E-5 * this.b6;
			arr[4] = 0.772464 + 6.25E-4 * this.b6;
			arr[5] = 19.218;
			break;
		}

		case 11: {
			// nep
			double var1 = 360.0 * Util.fract(0.17361 + 0.60692 * b6);
			arr[0] = var1 + 0.1 - 0.1 * Math.sin((var1 / 2.0 - 90.0 + plnt[0]) * z2) + 0.166 * Math.sin(b6 - 1.0);
			arr[1] = 24.27 + 0.028 * b6;
			arr[2] = 108.22 - 0.297 * b6;
			arr[3] = 0.009 - 6.0E-6 * b6;
			arr[4] = 1.779242 + 0.009544 * b6;
			arr[5] = 30.11;
			break;
		}

		case 12: {
			// Plu
			double tempVar1 = 360.0 * Util.fract(0.19434 + 0.40254 * b6);
			arr[0] = tempVar1 - 0.1 * Math.sin((tempVar1 + plnt[0]) * z2);
			arr[1] = 200.02 + 0.002 * b6;
			arr[2] = 86.49 - 0.038 * b6;
			arr[3] = 0.248644;
			arr[4] = 17.146778 - 0.005531 * b6;
			arr[5] = 39.52;
			break;
		}
		}
		return arr;
	}

	private double initMoonDeg(double b6) {
		double var1 = 360.0 * Util.fract(0.71455 + 99.99826 * b6);
		double var3 = 258.76 + 0.323 * b6;
		double var5 = 360.0 * Util.fract(0.68882 + 1336.851353 * b6);
		double var7 = 360.0 * Util.fract(0.8663 + 11.298994 * b6 - 3.0E-5 * b6 * b6);
		double var9 = 360.0 * Util.fract(0.65756 - 5.376495 * b6);
		if (var9 < 0.0) {
			var9 += 360.0;
		}

		double var11 = z2 * (var5 - var7);
		double var13 = z2 * (var1 - var3);
		double var15 = z2 * (var5 - var1);
		double var17 = z2 * (var5 - var9);
		double var19 = var5 + 6.2888 * Math.sin(var11) + 0.2136 * Math.sin(2.0 * var11) + 0.01 * Math.sin(3.0 * var11)
				+ 1.274 * Math.sin(2.0 * var15 - var11) + 0.0085 * Math.sin(4.0 * var15 - 2.0 * var11)
				- 0.0347 * Math.sin(var15) + 0.6583 * Math.sin(2.0 * var15) + 0.0039 * Math.sin(4.0 * var15)
				- 0.1856 * Math.sin(var13) - 0.0021 * Math.sin(2.0 * var13) + 0.0052 * Math.sin(var11 - var15)
				- 0.0588 * Math.sin(2.0 * var11 - 2.0 * var15) + 0.0572 * Math.sin(2.0 * var15 - var11 - var13)
				+ 0.0533 * Math.sin(var11 + 2.0 * var15) + 0.0458 * Math.sin(2.0 * var15 - var13)
				+ 0.041 * Math.sin(var11 - var13) - 0.0305 * Math.sin(var11 + var13)
				- 0.0237 * Math.sin(2.0 * var17 - var11) - 0.0153 * Math.sin(2.0 * var17 - 2.0 * var15)
				+ 0.0107 * Math.sin(4.0 * var15 - var11) - 0.0079 * Math.sin(-var11 + var13 + 2.0 * var15)
				- 0.0068 * Math.sin(var13 + 2.0 * var15) + 0.005 * Math.sin(var13 + var15)
				- 0.0023 * Math.sin(var11 + var15) + 0.004 * Math.sin(2.0 * var11 + 2.0 * var15)
				+ 0.004 * Math.sin(var11 - var13 + 2.0 * var15) - 0.0037 * Math.sin(3.0 * var11 - 2.0 * var15)
				- 0.0026 * Math.sin(var11 - 2.0 * var15 + 2.0 * var17) + 0.0027 * Math.sin(2.0 * var11 - var13)
				- 0.0024 * Math.sin(2.0 * var11 + var13 - 2.0 * var15) + 0.0022 * Math.sin(2.0 * var15 - 2.0 * var13)
				- 0.0021 * Math.sin(2.0 * var11 + var13) + 0.0021 * Math.sin(var9 * z2)
				+ 0.0021 * Math.sin(2.0 * var15 - var11 - 2.0 * var13)
				- 0.0018 * Math.sin(var11 + 2.0 * var15 - 2.0 * var17) + 0.0012 * Math.sin(4.0 * var15 - var11 - var13)
				- 8.0E-4 * Math.sin(3.0 * var15 - var11);
		double var21 = z2 * 2.0 * (var19 - var9);
		double var23 = var19 - 0.1143 * Math.sin(var21) + 0.004;
		if (var23 >= 360.0) {
			var23 -= 360.0;
		}

		if (var23 < 0.0) {
			var23 += 360.0;
		}
		return var23;
	}

	private double initRahuDeg(double b6) {
		var var9 = 360.0 * Util.fract(0.65756 - 5.376495 * b6);
		if (var9 < 0.0) {
			var9 += 360.0;
		}
		return var9;
	}

	private double initKetuDeg(double b6) {
		var var9 = 360.0 * Util.fract(0.65756 - 5.376495 * b6);
		if (var9 < 0.0) {
			var9 += 360.0;
		}
		var var27 = var9 + 180.0;
		if (var27 >= 360.0) {
			var27 -= 360.0;
		}
		return var27;
	}

	public double getJupitor() {
		return plnt[5];
	}

	public double getKetu() {
		return plnt[9];
	}

	public double getMars() {
		return plnt[3];
	}

	public double getMercury() {
		return plnt[4];
	}

	public double getMoon() {
		return plnt[2];
	}

	public double getNeptune() {
		return plnt[11];
	}

	public double getPluto() {
		return plnt[12];
	}

	public double getRahu() {
		return plnt[8];
	}

	public double getSaturn() {
		return plnt[7];
	}

	public double getSun() {
		return this.plnt[1];
	}

	public double getUranus() {
		return plnt[10];
	}

	public double getVenus() {
		return plnt[6];
	}

	void initializesArrayValues() throws Exception {
		// util.languageCode = "0"/*this.getLanguageCode()*/
		if (lang.equals("0")) {

			day = HoroHiConstant.day;
			daylrd = HoroHiConstant.daylrd;
			ras = HoroHiConstant.ras;
			raslrd = HoroHiConstant.raslrd;
			naklrd = HoroHiConstant.naklrd;
			tit = HoroHiConstant.tit;
			nak = HoroHiConstant.nak;
			yog = HoroHiConstant.yog;
			x1 = HoroHiConstant.x1;
			graha = HoroHiConstant.graha;
			bhavaNum = HoroHiConstant.bhavaNum;
			karan = HoroHiConstant.karan;
			paksha = HoroHiConstant.paksha;
			div = HoroHiConstant.div;
			mangalDosh = HoroHiConstant.mangalDosh;
			gana = HoroHiConstant.gana;
			vasya = HoroHiConstant.vasya;
			paya = HoroHiConstant.paya;
			deb = HoroHiConstant.deb;
			exal = HoroHiConstant.exal;
			aspects = HoroHiConstant.aspects;
			planetNameVim = HoroHiConstant.planetNameVim;
			planetName = HoroHiConstant.planetName;
			outcomes = HoroHiConstant.outcomes;
			ayan = HoroHiConstant.ayan;
			x9 = HoroHiConstant.x9;
			varna = HoroHiConstant.varna;
			yoni = HoroHiConstant.yoni;
			nadi = HoroHiConstant.nadi;
		} else {
			day = HoroEnConstant.day;
			daylrd = HoroEnConstant.daylrd;
			ras = HoroEnConstant.ras;
			raslrd = HoroEnConstant.raslrd;
			naklrd = HoroEnConstant.naklrd;
			tit = HoroEnConstant.tit;
			nak = HoroEnConstant.nak;
			yog = HoroEnConstant.yog;
			x1 = HoroEnConstant.x1;
			graha = HoroEnConstant.graha;
			bhavaNum = HoroEnConstant.bhavaNum;
			karan = HoroEnConstant.karan;
			paksha = HoroEnConstant.paksha;
			div = HoroEnConstant.div;
			mangalDosh = HoroEnConstant.mangalDosh;
			gana = HoroEnConstant.gana;
			vasya = HoroEnConstant.vasya;
			paya = HoroEnConstant.paya;
			deb = HoroEnConstant.deb;
			exal = HoroEnConstant.exal;
			aspects = HoroEnConstant.aspects;
			planetNameVim = HoroEnConstant.planetNameVim;
			planetName = HoroEnConstant.planetName;
			outcomes = HoroEnConstant.outcomes;
			ayan = HoroEnConstant.ayan;
			x9 = HoroEnConstant.x9;
			varna = HoroEnConstant.varna;
			yoni = HoroEnConstant.yoni;
			nadi = HoroEnConstant.nadi;
		}
	}

	private int getJD(int d, int m, int y) {
		int month = m;
		int year = y;
		if (month < 3) {
			month += 12;
			year--;
		}
		int var4 = year / 100;
		double m1 = 30.6f * (month + 1);
		return year * 365 + year / 4 + (int) m1 + 2 - var4 + var4 / 4 + d;
	}

	private void initKPAyan() {
		for (int var1 = 1; var1 <= 25; var1++) {
			if (var1 != 13) {
				plnt[var1] = plnt[var1] + aya - kpaya;
				if (plnt[var1] >= 360.0) {
					plnt[var1] -= 360.0;
				}
			}
		}
		aya = kpaya;
		plnt[0] = kpaya;
	}

	private void calcKPAyan(double dd, double mm, double yy) {
		kpaya = Util.kpayan(dd, mm, yy);
	}

	private double calcKPAyanNew(double dd, double mm, double yy) {
		double var7 = Util.kpayannew(dd, mm, yy);
		kpaya = var7;
		return var7;
	}

	private double calcKPAyanKhu(double dd, double mm, double yy) {
		double var7 = Util.kpayankhu(dd, mm, yy);
		kpaya = var7;
		return var7;
	}

	protected void calculateKPHorary(int hrSeed) {
		int seed = hrSeed;
		horarySeed = seed;
		double var2 = convertSeedToDegree(seed) + kpaya;
		double var4 = lat;
		double var22 = 0.0;
		if (var2 >= 360.0) {
			var2 -= 360.0;
		}
		f4[0] = var2;
		double var6;

		double result = Util.atand(Util.tand(var2) * Util.cosd(23.445));
		var6 = result;
		if (result < 0.0) {
			var6 += 180.0;
		}
		if (var2 > 180.0) {
			var6 += 180.0;
		}
		double var10 = Util.asind(Util.sind(var2) * Util.sind(23.445));
		double var8 = Util.asind(Util.tand(var4) * Util.tand(var10));
		double var12;
		var12 = var6 - var8 - 90.0;
		if (var12 < 0.0) {
			var12 += 360.0;
		}
		double var14;

		result = Util.atand(Util.tand(var12) / Util.cosd(23.445));
		var14 = result; // Assign the result to var14

		if (result < 0.0) {
			var14 += 180.0;
		}
		if (var12 > 180.0) {
			var14 += 180.0;
		}
		f4[9] = var14;

		result = Util.atand(Util.tand(var14) * Util.cosd(23.445));
		var12 = result; // Assign the result to var12

		if (result < 0.0) {
			var12 += 180.0;
		}
		if (var14 > 180.0) {
			var12 += 180.0;
		}
		int var24;
		var24 = 1;
		while (var24 <= 4) {
			var var16 = var14;
			seed = 1;
			while (seed <= 5) {
				double var18 = Util.sind(var16) * Util.sind(23.445);
				double var20 = 90.0 + Util.asind(Util.tand(var4) * var18 / Math.sqrt(1.0 - var18 * var18));
				switch (var24) {
				case 1:
				case 2: {
					var22 = var12 + (double) var24 * var20 / 3.0;
					break;
				}
				case 3:
				case 4: {
					var22 = var12 + (double) (5 - var24) * var20 / 3.0 + (double) ((var24 - 2) * 60);
					break;
				}
				}
				if (var22 >= 360.0) {
					var22 -= 360.0;
				}

				result = Util.atand(Util.tand(var22) / Util.cosd(23.445));
				var16 = result; // Assign the result to var16

				if (result < 0.0) {
					var16 += 180.0;
				}
				if (var22 >= 180.0) {
					var16 += 180.0;
				}
				switch (var24) {
				case 1:
				case 2: {
					f4[var24 + 9] = var16;
					break;
				}
				case 3:
				case 4: {
					f4[var24 - 2] = var16;
					break;
				}
				}
				++seed;
			}
			++var24;
		}
		double[] var10000;
		seed = 3;
		while (seed <= 8) {

			var24 = 6; // Assign 6 to var24
			if (seed + var24 > 11) {
				var24 -= 12;
			}
			f4[seed] = f4[var24] + 180.0;
			if (f4[seed] >= 360.0) {
				var10000 = f4;
				var10000[seed] -= 360.0;
			}
			++seed;
		}
		var24 = 0;
		while (var24 <= 11) {
			var10000 = f4;
			var10000[var24] -= kpaya;
			if (f4[var24] < 0.0) {
				double[] var10001 = f4;
				var10001[var24] += 360.0;
			}
			++var24;
		}
	}

	protected void calculateKPPlanet() {
		for (int var1 = 1; var1 <= 12; var1++) {
			plntkp[var1 - 1] = plnt[var1] + aya - kpaya;
			if (plntkp[var1 - 1] >= 360.0) {
				plntkp[var1 - 1] -= 360.0;
			}
			if (plntkp[var1 - 1] < 0.0) {
				plntkp[var1 - 1] += 360.0;
			}
		}
	}

	protected void misc() {
		var var1 = (plnt[2] - plnt[1]) / 12.0000001;
		if (var1 < 0.0) {
			var1 += 30.0;
		}
		tt[0] = Math.round(var1 * 100.0) / 100.0;

		double var3 = plnt[2] * 0.075;
		tt[1] = Math.round(var3 * 100.0) / 100.0;

		var var5 = (plnt[2] + plnt[1]) * 0.075;
		if (var5 > 27.0) {
			var5 -= 27.0;
		}
		tt[2] = Math.round(var5 * 100.0) / 100.0;

		double var7 = plnt[2] / 30.0000001;
		tt[3] = Math.round(var7 * 100.0) / 100.0;
	}

	double planet(double pg, double ph, double pp, double pe, double pq, double pa, int pno) {
		var var14 = pg - ph;
		if (var14 < 0.0) {
			var14 += 360.0;
		}

		double var16 = var14 * z2;
		double var18 = var16 + pe * Math.sin(var16);

		double var20;
		double var22;
		do {
			var20 = var18 - pe * Math.sin(var18) - var16;
			var22 = 1.0 - pe * Math.cos(var18);
			var18 -= var20 / var22;
		} while (Math.abs(var20 / var22) > 0.01);

		double var24 = pa * (1.0 - pe * Math.cos(var18));
		double var26 = Math.atan(pe / Math.sqrt(1.0 - pe * pe));
		double var30 = Math.tan(z1 / 4.0 - var26 / 2.0);
		double var34 = Math.atan(Math.tan(var18 / 2.0) / var30);
		if (var34 < 0.0) {
			var34 += z1;
		}

		double var36 = var34 * 2.0;
		var20 = ph * z2;
		var22 = pp * z2;
		var16 = pq * z2;
		double var38 = var36 + var20;
		double var40 = var38 - var22;
		double var42 = 1.0 - Math.cos(var16);
		double var44 = (Math.cos(var38) + Math.sin(var40) * Math.sin(var22) * var42) * var24;
		double var46 = (Math.sin(var38) - Math.sin(var40) * Math.cos(var22) * var42) * var24;
		if (pno == 1) {
			ps = var44;
			pt = var46;
		}

		var20 = ps + var44;
		var22 = pt + var46;
		var14 = Math.atan(var22 / var20) / z2;
		if (var20 < 0.0) {
			var14 += 180.0;
		} else if (var22 < 0.0) {
			var14 += 360.0;
		}

		return var14;
	}

	private void ramanAyan() {
		double var1 = 21.013972 + 1.398191 * this.b6;
		double var3 = this.plnt[0] - var1;
		for (int var5 = 1; var5 < 26; var5++) {
			if (var5 != 13) {
				this.plnt[var5] += var3;
				if (this.plnt[var5] >= 360.0) {
					this.plnt[var5] -= 360.0;
				}
			}
		}
		this.plnt[0] = var1;
		this.aya = var1;
	}

	private double returnGeoCentricLat(double lat) {
		return Math.atan(Math.tan(lat * z1 / 180.0) * 0.99330546) * 180.0 / z1;
	}

	protected int returnMangalDoshPoints(int lag, int marRasi) {
		int lagn = marRasi - lag;
		byte var3 = 0;
		if (lagn < 0) {
			lagn += 12;
		}
		++lagn;
		if (lagn == 1 || lagn == 4 || lagn == 7 || lagn == 8 || lagn == 12) {
			var3 = 33;
		}
		return (int) var3;
	}

	private double rev180(double x) {
		return x - 360.0 * Math.floor(x * 0.002777777777777778 + 0.5);
	}

	private double revolution(double x) {
		return x - 360.0 * Math.floor(x * 0.002777777777777778);
	}

	private void sayan() {
		for (int var1 = 1; var1 < 26; var1++) {
			if (var1 != 13) {
				this.plnt[var1] += this.plnt[0];
				if (this.plnt[var1] >= 360.0) {
					this.plnt[var1] -= 360.0;
				}
			}
		}

		this.aya = 0.0;
		this.plnt[0] = 0.0;
	}

	private double convertSeedToDegree(int s) {
		int[] var4 = { 7, 20, 6, 10, 7, 18, 16, 19, 17 };
		var var5 = 0.0;
		int[] var2 = { 229, 189, 146, 106, 63, 23 };
		double[] var7 = { 330.0, 270.0, 210.0, 150.0, 90.0, 30.0 };

		var sAdjusted = s;
		// var var3: Int
		for (int var3 = 0; var3 < 6; var3++) {
			if (sAdjusted == var2[var3]) {
				return var7[var3];
			}
			if (sAdjusted > var2[var3]) {
				sAdjusted--;
			}
		}

		sAdjusted--;
		var var8 = sAdjusted / 9;

		for (int var3 = 0; var3 < var8; var3++) {
			var5 += 13.333333333333334;
			sAdjusted -= 9;
		}

		var8 %= 9;

		for (int var3 = 0; var3 < sAdjusted; var3++) {
			var5 += var4[(var8 + var3) % 9] / 120.0 * 13.333333333333334;
		}

		return var5 + 1.0E-13;
	}

	private void setAyanType(int ayanamsaType) {
		switch (ayanamsaType) {
		case 1: {
			this.ayanamsaType = 1;
		}

		case 2: {
			this.ayanamsaType = 1;
			this.kpAyanamsaType = 1;
		}

		case 3: {
			this.ayanamsaType = 2;
		}

		case 4: {
			this.ayanamsaType = 1;
			this.kpAyanamsaType = 2;
		}

		case 5: {
			this.ayanamsaType = 3;
		}

		case 6: {
			this.ayanamsaType = 4;
		}

		default: {
		}
		}
	}

	public int getAyan() {
		int ayanamsaType = this.ayanamsaType; // Assuming 'ayanamsaType' is a class member
		switch (ayanamsaType) {
		case 0:
			return 0;
		case 1:
			switch (kpAyanamsaType) {
			case 0:
				return 1;
			case 1:
				return 2;
			case 2:
				return 4;
			default:
				return ayanamsaType; // If 'kpAyanamsaType' doesn't match 0, 1, or 2
			}
		case 2:
			return 3;
		case 3:
			return 5;
		case 4:
			return 6;
		default:
			return ayanamsaType; // If 'ayanamsaType' doesn't match any of 0, 1, 2, 3, 4
		}
	}

	public void setCustomizedAyanamsa(String ayanamsa) {
		try {
			customizedAyan = Double.parseDouble(ayanamsa);
		} catch (NumberFormatException e) {
			customizedAyan = 0.0;
		}
	}

	void setHinduWeekday() {
		if (this.birthTime - this.rise < 0.0) {
			this.vedicDay = this.daylord - 1;
		} else {
			this.vedicDay = this.daylord;
		}
		if (this.vedicDay < 0) {
			this.vedicDay += 7;
		}
	}

	private void setUserTime() {
		try {
			Calendar calendar = (Calendar) Calendar.getInstance().clone();
			calendar.set(y, m - 1, d, h, mt, s);
			int dst = -this.dst;
			calendar.add(Calendar.HOUR_OF_DAY, dst);
			d = calendar.get(Calendar.DAY_OF_MONTH);
			m = calendar.get(Calendar.MONTH) + 1;
			y = calendar.get(Calendar.YEAR);
			h = calendar.get(Calendar.HOUR_OF_DAY);
			mt = calendar.get(Calendar.MINUTE);
			s = calendar.get(Calendar.SECOND);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.toString());
		}
	}

	private void sunRADec(double d) {
		double var11 = sunPosition(d);
		double var5 = sr * Math.cos(var11 * 0.017453292519943295);
		double var7 = sr * Math.sin(var11 * 0.017453292519943295);
		double var3 = 23.4393 - d * 3.563E-7;
		double var9 = var7 * Math.sin(var3 * 0.017453292519943295);
		double var17 = Math.cos(var3 * 0.017453292519943295);
		double var15 = var7 * var17;
		sRA = 57.29577951308232 * Math.atan2(var15, var5);
		sdec = 57.29577951308232 * Math.atan2(var9, Math.sqrt(var5 * var5 + var15 * var15));
	}

	private double sunPosition(double d) {
		double var3 = revolution(356.047 + d * 0.9856002585);
		double var5 = 282.9404 + d * 4.70935E-5;
		double var7 = 0.016709 - d * 1.151E-9;
		double var9 = var3 + var7 * 57.29577951308232 * Math.sin(var3 * 0.017453292519943295)
				* (1.0 + var7 * Math.cos(var3 * 0.017453292519943295));
		double var11 = Math.cos(var9 * 0.017453292519943295) - var7;
		double var13 = Math.sqrt(1.0 - var7 * var7) * Math.sin(var9 * 0.017453292519943295);
		sr = Math.sqrt(var11 * var11 + var13 * var13);
		double var15 = 57.29577951308232 * Math.atan2(var13, var11);
		double var17 = var15 + var5;
		if (var17 >= 360.0) {
			var17 -= 360.0;
		}
		return var17;
	}
}
