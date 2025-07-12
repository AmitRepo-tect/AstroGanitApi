package com.astroganit.lib.panchang;

public class MuhuratCalculation extends BaseCalculation {
	public double[] getDayDivisons(double jd, double startingPoint, int noOfdivisions) {
		double[] muhurtas = new double[noOfdivisions + 1];

		double muhuratDuration = dayDuration(jd) / (double) noOfdivisions;
		double sunrise = startingPoint;
		muhurtas[0] = startingPoint;
		for (int i = 0; i < noOfdivisions; i++) {
			sunrise += muhuratDuration;
			muhurtas[i + 1] = sunrise;
		}

		return muhurtas;
	}

	public double dayDuration(double jd) {
		return getSunSet(jd) - getSunRise(jd);
	}

	public double[] getNightDivisons(double jd, double startingPoint, int noOfdivisions) {
		double[] muhurtas = new double[noOfdivisions + 1];

		double muhuratDuration = nightDuration(jd) / (double) noOfdivisions;
		double sunset = startingPoint;
		muhurtas[0] = startingPoint;
		for (int i = 0; i < noOfdivisions; i++) {
			sunset += muhuratDuration;
			muhurtas[i + 1] = sunset;
		}

		return muhurtas;
	}

	private double nightDuration(double jd) {
		return getSunRise(jd + 1.0) - getSunSet(jd) + 24.0;
	}

	public int[] getRahuYamaGulikaKaal(double jd) {
		int[] rahukaal = { 8, 2, 7, 5, 6, 4, 3 };
		int[] gulikakaal = { 7, 6, 5, 4, 3, 2, 1 };
		int[] yamaganda = { 5, 4, 3, 2, 1, 7, 6 };
		int vaara = vaara(jd);
		return new int[] { rahukaal[vaara], gulikakaal[vaara], yamaganda[vaara] };
	}

	public int[] getDushtaMuhurta(double jd) {
		int[][] day = { { 14 }, { 9, 12 }, { 4 }, { 8 }, { 6, 12 }, { 4, 9 }, { 1, 2 } };
		int vaara = vaara(jd);
		return day[vaara];
	}

	public int[] getKulikaKantakaKalavelaYama(double jd) {
		int[] kulika = { 14, 12, 10, 8, 6, 4, 2 };
		int[] kantak = { 6, 4, 2, 14, 12, 10, 8 };
		int[] kalavela = { 8, 6, 4, 2, 14, 12, 10 };
		int[] yamakantaka = { 10, 8, 6, 4, 2, 14, 12 };
		int vaara = vaara(jd);
		return new int[] { kulika[vaara], kantak[vaara], kalavela[vaara], yamakantaka[vaara] };
	}

	public double[] getFifteenMuhurtaForDay(double jd) {
		return getDayDivisons(jd, getSunRise(jd), 15);
	}

	public int getDishaShoola(double jd) {
		int[] shoola = { 2, 1, 3, 3, 4, 2, 1 };
		int vaara = vaara(jd);
		return shoola[vaara];
	}

	public int[] getTaraBaliNakshatra(int todaysNak) {
		int[] taraBaliNakshatras = new int[18];

		int position = 0;
		for (int birthNakshatra = 1; birthNakshatra <= 27; birthNakshatra++) {
			int distance = (todaysNak - birthNakshatra) % 9 + 1;
			if (distance <= 0) {
				distance += 9;
			}
			if (distance != 3 && distance != 5 && distance != 7) {
				taraBaliNakshatras[position++] = birthNakshatra;
			}
		}

		return taraBaliNakshatras;
	}

	public int[] getChandraBaliRasi(int todaysRasi) {
		int[] chandraBaliRasi = new int[6];

		var position = 0;
		for (int birthRasi = 1; birthRasi <= 12; birthRasi++) {
			int distance = (todaysRasi - birthRasi) % 12 + 1;
			if (distance <= 0) {
				distance += 12;
			}
			if (distance != 2 && distance != 4 && distance != 5 && distance != 8 && distance != 9 && distance != 12) {
				chandraBaliRasi[position++] = birthRasi;
			}
		}

		return chandraBaliRasi;
	}

	public int vaara(double jd) {
		return (int) (Math.ceil(jd + 1.0) % 7.0);
	}
}
