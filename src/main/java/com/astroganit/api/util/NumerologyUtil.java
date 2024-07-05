package com.astroganit.api.util;

public class NumerologyUtil {
	public static String numerologyfavourableRashi = "मेष, सिंह##कर्क##धनु##मकर##मिथुन, कन्या##वृश्चिक, तुला##मीन##मकर##मेष, वृश्चिक";
	public static String numerologyfavourableAlphabet = "ए, जे, एस##बी, के, टी##सी, एल, यू##डी, एम, वी##इ, एन, डब्लू##एफ, ओ, एक्स##पी, आर, टी##एच, क्यू, ज़ेड##आई, आर";
	public static String numerologyGemstone = "माणिक्य##मोती##पुखराज##नीलम रत्\u200dन/गोमेद##पन्ना##हीरा##लहसुनिया##नीलम##मूंगा";
	public static String numerologyDisha = "पूर्व##वायव्य##ईशान##पूर्व##उत्तर##आग्नेय##पूर्व##पश्चिम##दक्षिण";
	public static String numerologyDays = "रविवार, सोमवार##सोमवार, रविवार, शुक्रवार##बृहस्पतिवार, मंगलवार, शुक्रवार##शनिवार, रविवार, सोमवार##बुधवार, बृहस्पतिवार, शुक्रवार##शुक्रवार, मंगलवार, बृहस्पतिवार##रविवार, सोमवार##शनिवार, रविवार, सोमवार##मंगलवार, बृहस्पतिवार, शुक्रवार";
	public static String numerologyFavDate = "1st, 10th, 19th, 28th##2nd, 11th, 20th, 29th, 1st, 10th, 19th,28th ##1st, 3rd, 12th, 21st, 30th, 10th, 9th, 15th, 18th, 27th##4th, 13th, 22nd, 31st, 1st, 2nd, 7th, 10th, 11th, 16th, 19th, 20th, 25th, 26th##5th, 14th, or 23rd##1st, 4th, 5th, 6th, 10th, 14th, 15th, 16th##7th, 16th, 25th, 1st##3rd, 5th, 7th, 8th, 17th##3rd, 6th, 9th, 12th, 15th, 18th, 21st, 10th";
	public static String numerologyAuspicious_color = "सुनहरा, हरा, भूरा, नारंगी और पीला##फ़िरोज़ा, हरा, पीला, सफेद और सुनहरा##पीला, केसर, भूरा, गुलाबी, सुनहरा और नारंगी##भूरा, खाकी और नीला##हरा##सफेद, हल्का नीला, हल्का और गुलाबी##सफेद, हल्का पीला, आकाश और हरा##गहरा भूरा, काला, गहरा नीला और बैंगनी##लाल और गुलाबी";
	public static String numerologyPlanet = "सूर्य##चंद्र##बृहस्पति##राहु##बुध##शुक्र##केतु##शनि##मंगल";
	public static String numerologyGod = "शिव जी##दुर्गा जी / शिव जी##विष्णु जी##दुर्गा जी##विष्णु जी##दुर्गा जी##गणेश जी##काली जी / भैरव जी##हनुमान जी / कार्तिकेय जी";
	public static String numerologyFast = "रविवार##सोमवार##बृहस्पतिवार##शनिवार##बुधवार##शुक्रवार##मंगलवार##शनिवार##मंगलवार";
	public static String numerologyFavNumber = "1, 4, 7##2##6, 9##4##1, 3, 5##3, 9##1##8##9";

	public static int getMulank(String dOfBirth) {
		int idOfBirth;
		int remainder;
		int division;
		for (idOfBirth = Integer.parseInt(dOfBirth); String.valueOf(idOfBirth).length() > 1; idOfBirth = remainder
				+ division) {
			remainder = idOfBirth / 10;
			division = idOfBirth % 10;
		}

		return idOfBirth;
	}

	public static int getNamank(String name, int typeVal) {
		char[] alphabet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int[] cheiro = new int[] { 1, 2, 3, 4, 5, 8, 3, 5, 1, 1, 2, 3, 4, 5, 7, 8, 1, 2, 3, 4, 6, 6, 6, 5, 1, 7 };
		int[] sepherial = new int[] { 1, 2, 2, 4, 5, 8, 3, 8, 1, 1, 2, 3, 4, 5, 7, 8, 1, 2, 3, 4, 6, 6, 6, 6, 1, 7 };
		int[] modern = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] pythagorian = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8 };
		int[][] type = new int[][] { cheiro, sepherial, modern, pythagorian };
		int namank = 0;
		name = name.toUpperCase();

		for (int i = 0; i < name.length(); ++i) {
			char c = name.charAt(i);

			for (int j = 0; j < alphabet.length; ++j) {
				if (alphabet[j] == c) {
					namank += type[typeVal][j];
				}
			}
		}

		return namank;
	}

	public static int getNameMulank(String name, int typeVal) {
		int namank = getNamank(name, typeVal);
		return getMulank(String.valueOf(namank));
	}

	public static int getDestinyNumber(String fullDOB) {
		byte result = 0;

		try {
			int sumOfDOB = 0;
			char[] charArray = fullDOB.toCharArray();

			int num;
			for (num = 0; num < charArray.length; ++num) {
				char singleChar = charArray[num];
				if (Character.isDigit(singleChar)) {
					num = Character.getNumericValue(singleChar);
					sumOfDOB += num;
				}
			}

			while (sumOfDOB > 9) {
				num = sumOfDOB;

				int sum;
				for (sum = 0; num > 0; num /= 10) {
					sum += num % 10;
				}

				sumOfDOB = sum;
			}

			return sumOfDOB;
		} catch (Exception var7) {
			return result;
		}
	}

	public static boolean isStringOnlyAlphabet(String name) {
		name = name.trim();
		String firstChar = "";
		char getFirstChar = name.charAt(0);
		firstChar = firstChar + getFirstChar;
		return name != null && !name.equals("") && firstChar.matches("^[a-zA-Z]*$");
	}
}
