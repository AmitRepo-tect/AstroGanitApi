package com.astroganit.lib.panchang.util;

public class ConstantsEn implements IConstants {
	@Override
	public String getMasas(int index) {
		// TODO Auto-generated method stub
		return masas[index];
	}

	@Override
	public String getVaras(int index) {
		// TODO Auto-generated method stub
		return varas[index];
	}

	@Override
	public String getVarasSwami(int index) {
		// TODO Auto-generated method stub
		return vaars_swami[index];
	}

	@Override
	public String getSamvats(int index) {
		// TODO Auto-generated method stub
		return samvats[index];
	}

	@Override
	public String getRitus(int index) {
		// TODO Auto-generated method stub
		return ritus[index];
	}

	@Override
	public String getKaranas(int index) {
		// TODO Auto-generated method stub
		return karanas[index];
	}

	@Override
	public String getPakshas(int index) {
		// TODO Auto-generated method stub
		return pakshas[index];
	}

	@Override
	public String getAyanas(int index) {
		// TODO Auto-generated method stub
		return ayanas[index];
	}

	@Override
	public String getDishas(int index) {
		// TODO Auto-generated method stub
		return dishas[index];
	}

	@Override
	public String getNakshatra(int index) {
		// TODO Auto-generated method stub
		return nakshatra[index];
	}

	@Override
	public String getYoga(int index) {
		// TODO Auto-generated method stub
		return yoga[index];
	}

	@Override
	public String getMoonSign(int index) {
		// TODO Auto-generated method stub
		return rashi[index];
	}

	@Override
	public String getTithi(int index) {
		// TODO Auto-generated method stub
		return tithi[index];
	}

	@Override
	public String getChandraBala(int index) {
		// TODO Auto-generated method stub
		return rashi[index];
	}

	@Override
	public String getTaraBala(int index) {
		// TODO Auto-generated method stub
		return nakshatra[index];
	}

	@Override
	public String[] getChandraBala() {
		// TODO Auto-generated method stub
		return rashi;
	}

	@Override
	public String[] getTaraBala() {
		// TODO Auto-generated method stub
		return nakshatra;
	}

	@Override
	public String getMonthName(int index) {
		// TODO Auto-generated method stub
		return monthname[index];
	}

	@Override
	public String getMonthNameSort(int index) {
		// TODO Auto-generated method stub
		return monthnameSort[index];
	}

	@Override
	public String getDayName(int index) {
		// TODO Auto-generated method stub
		return dayname[index];
	}

	@Override
	public String getChoghadiaDayName(int index) {
		return choghadiaDayName[index];
	}

	@Override
	public String getChoghadiaNightName(int index) {
		return choghadiaNightName[index];
	}

	@Override
	public String getExString(int index) {
		// TODO Auto-generated method stub
		return exstring[index];
	}

	@Override
	public String getLagna(int index) {
		return rashi2[index];
	}

	@Override
	public String getLagnaNature(int index) {
		return rashiNature[index];
	}

	@Override
	public String getRashi(int index) {
		// TODO Auto-generated method stub
		return rashi2[index];
	}

	@Override
	public String[] getHoraPlanetName() {
		// TODO Auto-generated method stub
		return horaPlaName;
	}

	@Override
	public String[] getHoraPlanetMeaning() {
		// TODO Auto-generated method stub
		return horaPlaMeaning;
	}

	@Override
	public String[] getCurrentHoraPlanetMeaning() {
		// TODO Auto-generated method stub
		return currentHoraPlaMeaning;
	}

	public String[] getDayChogdia() {
		return dayChogadiya;
	}

	public String[] getNightChogdia() {
		return nightChogadiya;
	}

	public String[] getNightChogdiaMeaning() {
		return chogadiyaNightNameMeaning;
	}

	public final String[] masas = { "Chaitra", "Vaisakha", "Jyeshtha", "Ashadha", "Shravan", "Bhadrapada", "Ashwin",
			"Kartika", "Margashirsha", "Pausha", "Magha", "Phalguna" };
	public final String[] adhikMasas = { "Chaitra", "Vaisakha", "Jyeshtha", "Ashadha", "Shravan", "Bhadrapada", "Ashwin",
			"Kartika", "Margashirsha", "Pausha", "Magha", "Adhik" };

	// base 0
	public final String[] varas = { "Ravivara", "Somavara", "Mangalavara", "Buddhavara", "Guruvara", "Shukravara",
			"Shanivara" };

	public final String[] vaars_swami = { "NA", "sun", "moon", "mars", "mercury", "jupiter", "venus", "saturn" };

	// base 1
	public final String[] samvats = { "Na", "Prabhava", "Vibhava", "Shukla", "Pramoda", "Prajothpatti", "Āngirasa",
			"Shrīmukha", "Bhāva", "Yuva", "Dhāta", "Īshvara", "Bahudhānya", "Pramāthi", "Vikrama", "Vrusha",
			"Chitrabhānu", "Svabhānu", "Tārana", "Pārthiva", "Vyaya", "Sarvajit", "Sarvadhāri", "Virodhi", "Vikruti",
			"Khara", "Nandana", "Vijaya", "Jaya", "Manmatha", "Durmukhi", "Hevilambi", "Vilambi", "Vikāri", "Shārvari",
			"Plava", "Shubhakruth", "Shobhakruth", "Krodhi", "Vishvāvasu", "Parābhava", "Plavanga", "Kīlaka", "Saumya",
			"Sādhārana", "Virodhikruth", "Paridhāvi", "Pramādeecha", "Ānanda", "Rākshasa", "Nala", "Pingala",
			"Kālayukthi", "Siddhārthi", "Raudra", "Durmati", "Dundubhi", "Rudhirodgāri", "Raktākshi", "Krodhana",
			"Akshaya" };

	// 0 base
	public final String[] ritus = { "Vasanta", "Grishma", "Varsha", "Sharad", "Hemant", "Shishir" };

	public final String[] karanas = { "NA", "Kintudhhana", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti",
			"Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar",
			"Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav",
			"Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav",
			"Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij",
			"Vishti", "Sakuni", "Chatushpada", "Naaga" };

	public final String[] pakshas = { "Shukla", "Krishna" };

	public final String[] ayanas = { "Uttarayana", "Dakshinayana" };

	public final String[] dishas = { "East", "West", "North", "South" };

	public final String[] nakshatra = { "NA", "Ashwini", "Bharani", "Kritika", "Rohini", "Mrigashirsha", "Ardra",
			"Punarvasu", "Pushya", "Ashlesha", "Magha", "Poorva Phalguni", "Uttara Phalguni", "Hasta", "Chitra",
			"Swati", "Vishakha", "Anuradha", "Jyeshta", "Moola", "Poorva Ashadha", "Uttara Ashadha", "Shravana",
			"Dhanishta", "Satabisha", "Poorva Bhadrapada", "Uttara Bhadrapada", "Revati" };

	public final String[] yoga = { "NA", "Vishkambha", "Priti", "Ayushman", "Saubhagya", "Sobhana", "Atiganda",
			"Sukarma", "Dhriti", "Soola", "Ganda", "Vriddha", "Dhruva", "Vyagatha", "Harshana", "Vajra", "Siddhi",
			"Vyatipata", "Variyan", "Parigha", "Siva", "Siddha", "Sadhya", "Subha", "Shukla", "Brahma", "Indra",
			"Vaidhriti" };

	public final String[] tithi = { "NA", "Prathama", "Dvitiya", "Tritiya", "Chaturthi", "Panchami", "Shashti",
			"Saptami", "Ashtami", "Navami", "Dashami", "Ekadashi", "Dwadashi", "Trayodashi", "Chaturdashi", "Poornima",
			"Prathama", "Dvitiya", "Tritiya", "Chaturthi", "Panchami", "Shashti", "Saptami", "Ashtami", "Navami",
			"Dashami", "Ekadashi", "Dwadashi", "Trayodashi", "Chaturdashi", "Amavasya" };

	public final String[] rashi = { "NA", "Mesha", "Vrishabha", "Mithuna", "Karka", "Simha", "Kanya", "Tula",
			"Vrishchika", "Dhanu", "Makara", "Kumbha", "Meena" };
	public final String[] rashi2 = { "NA", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio",
			"Sagittarius", "Capricorn", "Aquarius", "Pisces" };
	public final String[] rashiNature = { "NA", "Movable", "Fixed", "Dual", "Movable", "Fixed", "Dual", "Movable",
			"Fixed", "Dual", "Movable", "Fixed", "Dual" };
	public final String[] monthname = { "NA", "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	public final String[] monthnameSort = { "NA", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" };
	public final String[] dayname = { "NA", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	public final String[] choghadiaDayName = { "Udveg", "Chal", "Laabh", "Amrut", "Kaal", "Shubh", "Rog" };

	public final String[] choghadiaNightName = { "Shubh", "Amrut", "Chal", "Rog", "Kaal", "Laabh", "Udveg" };

	public final String[] exstring = { "None", "No Moon Rise", "No Moon Set", "(Adhik)" };

	public final String[] horaPlaName = { "sun", "moon", "mars", "mercury", "jupiter", "venus", "saturn" };
	public final String[] horaPlaMeaning = { "hora_meaning1", "hora_meaning2", "hora_meaning3", "hora_meaning4 ",
			"hora_meaning5", "hora_meaning6", "hora_meaning7" };
	public final String[] currentHoraPlaMeaning = { "pla_mean1", "pla_mean2", "pla_mean3 ", "pla_mean4", "pla_mean5",
			"pla_mean6", "pla_mean7" };
	String[] dayChogadiya = { "udveg", "chal", "laabh", "amrut", "kaal", "shubh", "rog" };
	String[] nightChogadiya = { "shubh", "amrut", "chal", "rog", "kaal", "laabh", "udveg" };
	String[] chogadiyaNightNameMeaning = { "auspicious", "auspicious", "Good", "inauspicious", "inauspicious",
			"auspicious", "inauspicious" };

	String[] paharName = { "pratah", "sangav", "madhyahna", "aparahna", "sayan", "pradosh", "ratri", "nishita", "ratri",
			"arunodaya" };
	String[] doGhatiMuhuratList = { "raudra", "sit ", "maitra", "chaarmath ", "savitra   ", "vairaj  ", "gandharvas ",
			"abhijit ", "rohini ", "bala ", "vijay ", "nairrit ", "indra ", "varuna ", "bhaga ", "raudra ",
			"gandharvas ", "paksha ", "chaaran ", "vayu ", "agni ", "rakshasas ", "brahma ", "saumya ", "kamalaj ",
			"guru ", "paushna ", "vaikuntha ", "sameer ", "nirriti " };
	String[] doGhatiNakshtra = { "Ardra", "Ashlesha", "Anuradha ", "Magha ", "Dhanishta", "Poorvashadha",
			"Uttarashadha ", "Abhijit", "Rohini ", "Jyeshtha", "Vishakha", "Moola", "Shatabhisha", "Uttara Phalguni ",
			"Purva Phalgun", "Ardra", "Poorva Bhadrapada ", "Uttara Bhadrapada", "Revati", "Ashwini", "Bharani",
			"Kritika", "Rohini", "Mrigashira", "Punarvasu", "Pushya", "Shravana", "Hasta", "Chitra", "Swati", };
	String[] doGhatiNameListMeaningSecond = { "Shiva", "Sarpa ", "Mitra ", "Pitar ", "Vasu ", "Jala ", "Vishwadeva ",
			"Brahma ", "Surya ", "Indra ", "Agni ", "Rakshasas ", "Varuna ", "Aryama ", "Bhaga ", "Shiva	",
			"Ajapad ", "Ahirbudhnya ", "Pusa ", "Ashwini_Kumar ", "Yama ", "Agni ", "Brahma ", "Chandra ", "Aditi ",
			"Bṛhaspati ", "Visnu ", "Surya ", "Twashtra  ", "Vayu " };
	String[] doGhatiNameListMeaning = { "rudra", "ahi", "mitra", "pitru", "vasu", "vara", "visvadeva", "vidhi",
			"satamukhi", "puruhuta", "vahini", "naktancara", "varuna", "aryama", "bhaga", "girisha", "ajapad",
			"ahirbudhnya", "pusa", "aswini", "yama", "agni", "vidhatr", "kanda", "aditi", "jiva", "visnu",
			"yumigadyuti", "brahma", "samudram", };
	String[] doGhatiMuhurat = { "", "", "", "", "", "", "", "Abhijit_Muhurat", "", "", "Vijay_Muhurat", "", "", "", "",
			"Sayan_Sandhya", "Sayan_Sandhya_half", "", "", "", "", "", "Mahanishita_Muhurat", "", "", "", "", "",
			"Pratah_Sandhya_half", "Pratah_Sandhya" };
	String[] ekadashiK = { "पापमोचिनी एकादशी", "वरुथिनी एकादशी", "अपरा एकादशी", "योगिनी एकादशी", "कामिका एकादशी",
			"अन्नदा(अजा) एकादशी", "इंद्रा एकादशी", "रमा एकादशी", "उत्पन्न एकादशी", "सफला एकादशी", "षटतिला एकादशी",
			"विजय एकादशी", "परमा एकादशी" };
	String[] ekadashiS = { "कामदा एकादशी", "मोहिनी एकादशी", "निर्जला एकादशी", "देवशयनी एकादशी", "श्रवण पुत्रदा एकादशी",
			"परिवर्तनी(पार्श्व) एकादशी", "पापांकुशा एकादशी", "देवउठनी(प्रबोधिनी) एकादशी", "मोक्षदा एकादशी",
			"पौष पुत्रदा एकादशी", "भैमी(जया) एकादशी", "आमलकी एकादशी", "पद्मिनी एकादशी" };
	@Override
	public String[] getPaharNameList() {
		// TODO Auto-generated method stub
		return paharName;
	}

	@Override
	public String[] getDoGhatiPauranikMuhuratList() {
		// TODO Auto-generated method stub
		return doGhatiMuhuratList;
	}

	@Override
	public String[] getDoGhatiNakshtra() {
		// TODO Auto-generated method stub
		return doGhatiNakshtra;
	}

	@Override
	public String[] getDoGhatiNameListMeaningSecond() {
		// TODO Auto-generated method stub
		return doGhatiNameListMeaningSecond;
	}

	@Override
	public String[] getDoGhatiNameListMeaning() {
		// TODO Auto-generated method stub
		return doGhatiNameListMeaning;
	}

	@Override
	public String[] getDoGhatiMuhurat() {
		// TODO Auto-generated method stub
		return doGhatiMuhurat;
	}

	@Override
	public String[] getRashiList() {
		return rashi;
	}

	@Override
	public String[] getEkadashiK() {
		return ekadashiK;
	}
	@Override
	public String[] getEkadashiS() {
		return ekadashiS;
	}
	@Override
	public String[] getAdhikMasas () {
		return adhikMasas ;
	}
}
