package com.astroganit.lib.panchang.util;

public class ConstantsHi implements IConstants {
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

	public String[] getDayChogdia() {
		return dayChogadiya;
	}

	public String[] getNightChogdia() {
		return nightChogadiya;
	}

	@Override
	public String[] getCurrentHoraPlanetMeaning() {
		// TODO Auto-generated method stub
		return currentHoraPlaMeaning;
	}

	public String[] getNightChogdiaMeaning() {
		return chogadiyaNightNameMeaning;
	}

	public final String[] masas = { "चैत्र", "वैशाख", "ज्येष्ठ", "आषाढ", "श्रावण", "भाद्रपद", "आश्विन", "कार्तिक",
			"मार्गशीर्ष", "पौष", "माघ", "फाल्गुन" };
	public final String[] adhikMasas = { "चैत्र", "वैशाख", "ज्येष्ठ", "आषाढ", "श्रावण", "भाद्रपद", "आश्विन", "कार्तिक",
			"मार्गशीर्ष", "पौष", "माघ", "फाल्गुन","अधिक" };
	
	// base 0
	public final String[] varas = { "रविवार", "सोमवार", "मंगलवार", "बुधवार", "गुरूवार", "शुक्रवार", "शनिवार" };

	public final String[] vaars_swami = { "NA", "सूर्य", "चन्द्र", "मंगल", "बुध", "बृहस्पति", "शुक्र", "शनि" };

	// base 1
	public final String[] samvats = { "Na", "प्रभव", "विभव", "शुक्ल", "प्रमोद", "प्रजापति", "अंगिरा", "श्री मुख", "भाव",
			"युवा", "धाता", "ईश्वर", "बहुधान्य", "प्रमाथी", "विक्रम", "वृष", "चित्रभानु", "सुभानु", "तारण", "पार्थिव",
			"अव्यय", "सर्वजीत", "सर्वधारी", "विरोधी", "विकृति", "खर", "नंदन", "विजय", "जय", "मन्मथ", "दुर्मुख",
			"हेम्लम्बी", "विलम्बी", "विकारी", "शार्वरी", "प्लव", "शुभकृत", "शोभकृत", "क्रोधी", "विश्वावसु", "पराभव",
			"प्ल्वंग", "कीलक", "सौम्य", "साधारण", "विरोधकृत", "परिधावी", "प्रमादी", "आनंद", "राक्षस", "आनल", "पिंगल",
			"कालयुक्त", "सिद्धार्थी", "रौद्र", "दुर्मति", "दुन्दुभी", "रूधिरोद्गारी", "रक्ताक्षी", "क्रोधन", "अक्षय" };

	// 0 base
	public final String[] ritus = { "वसंत", "ग्रीष्म", "वर्षा", "शरद", "हेमंत", "शिशिर" };

	public final String[] karanas = { "NA", "किन्स्तुघ्ना", "बव", "बालव", "कौलव", "तैतिल", "गर", "वणिज", "विष्टि", "बव",
			"बालव", "कौलव", "तैतिल", "गर", "वणिज", "विष्टि", "बव", "बालव", "कौलव", "तैतिल", "गर", "वणिज", "विष्टि",
			"बव", "बालव", "कौलव", "तैतिल", "गर", "वणिज", "विष्टि", "बव", "बालव", "कौलव", "तैतिल", "गर", "वणिज",
			"विष्टि", "बव", "बालव", "कौलव", "तैतिल", "गर", "वणिज", "विष्टि", "बव", "बालव", "कौलव", "तैतिल", "गर",
			"वणिज", "विष्टि", "बव", "बालव", "कौलव", "तैतिल", "गर", "वणिज", "विष्टि", "शकुन", "चतुष्पाद", "नाग" }; // copied
																													// from
																													// astrosage.com/cloud/util/constant_hi-properties.asp

	public final String[] pakshas = { "शुक्ल", "कृष्ण" };

	public final String[] ayanas = { "उत्तरायण", "दक्षिणायण" };

	public final String[] dishas = { "पूर्व", "पश्चिम", "उत्तर", "दक्षिण" };

	public final String[] nakshatra = { "NA", "अश्विनी", "भरणी", "कृत्तिका", "रोहिणी", "मृगशिरा", "आर्द्रा", "पुनर्वसु",
			"पुष्य", "आश्लेषा", "मघा", "पूर्वा फाल्गुनी", "उत्तरा फाल्गुनी", "हस्त", "चित्रा", "स्वाति", "विशाखा",
			"अनुराधा", "ज्येष्ठा", "मूल", "पूर्वाषाढ़ा", "उत्तराषाढ़ा", "श्रवण", "धनिष्ठा", "शतभिषा", "पूर्वाभाद्रपद",
			"उत्तराभाद्रपद", "रेवती" };

	public final String[] yoga = { "NA", "विश्कुम्भ", "प्रीति", "आयुष्मान", "सौभाग्य", "शोभन", "अतिगंड", "सुकर्मा",
			"धृति", "शूल", "गण्ड", "वृद्धि", "घ्रुव", "व्याघात", "हर्शण", "वज्र", "सिद्धि", "व्यतीपात", "वरियान",
			"परिघ", "शिव", "सिद्ध", "साघ्य", "शुभ", "शुक्ल", "ब्रह्म", "एन्द्र", "वैधृति" };

	public final String[] tithi = { "NA", "प्रतिपदा", "द्वितीया", "तृतीया", "चतुर्थी", "पंचमी", "षष्ठी", "सप्तमी",
			"अष्टमी", "नवमी", "दशमी", "एकादशी", "द्वादशी", "त्रयोदशी", "चतुर्दशी", "पूर्णिमा", "प्रतिपदा", "द्वितीया",
			"तृतीया", "चतुर्थी", "पंचमी", "षष्ठी", "सप्तमी", "अष्टमी", "नवमी", "दशमी", "एकादशी", "द्वादशी", "त्रयोदशी",
			"चतुर्दशी", "अमावस्या" };

	public final String[] rashi = { "NA", "मेष", "वृषभ", "मिथुन", "कर्क", "सिंह", "कन्या", "तुला", "वृश्चिक", "धनु",
			"मकर", "कुम्भ", "मीन" };
	public final String[] rashi2 = { "NA", "मेष", "वृषभ", "मिथुन", "कर्क", "सिंह", "कन्या", "तुला", "वृश्चिक", "धनु",
			"मकर", "कुम्भ", "मीन" };
	public final String[] rashiNature = { "NA", "चर", "स्थिर", "द्विस्वाभाव", "चर", "स्थिर", "द्विस्वाभाव", "चर",
			"स्थिर", "द्विस्वाभाव", "चर", "स्थिर", "द्विस्वाभाव" };
	public final String[] monthname = { "NA", "जनवरी", "फरवरी", "मार्च", "अप्रैल", "मई", "जून", "जुलाई", "अगस्त",
			"सितंबर", "अक्टूबर", "नवंबर", "दिसंबर" };
	public final String[] monthnameSort = { "NA", "जनवरी", "फरवरी", "मार्च", "अप्रैल", "मई", "जून", "जुलाई", "अगस्त",
			"सितंबर", "अक्टूबर", "नवंबर", "दिसंबर" };
	public final String[] dayname = { "NA", "रविवार", "सोमवार", "मंगलवार", "बुधवार", "गुरुवार", "शुक्रवार", "शनिवार" };

	public final String[] choghadiaDayName = { "उद्वेग", "चल", "लाभ", "अमृत", "काल", "शुभ", "रोग" };

	public final String[] choghadiaNightName = { "शुभ", "अमृत", "चल", "रोग", "काल", "लाभ", "उद्वेग" };

	public final String[] exstring = { "कोई नहीं", "चन्द्रोदय नहीं", "चन्द्रास्त नहीं", "(अधिक)" };

	public final String[] horaPlaName = { "सूर्य", "चंद्र", "मंगल", "बुध", "गुरु", "शुक्र", "शनि" };
	public final String[] horaPlaMeaning = { "hora_meaning1", "hora_meaning2", "hora_meaning3", "hora_meaning4 ",
			"hora_meaning5", "hora_meaning6", "hora_meaning7" };
	public final String[] currentHoraPlaMeaning = { "pla_mean1", "pla_mean2", "pla_mean3 ", "pla_mean4", "pla_mean5",
			"pla_mean6", "pla_mean7" };
	String[] dayChogadiya = { "उद्वेग", "चल", "लाभ", "अमृत", "काल", "शुभ", "रोग" };
	String[] nightChogadiya = { "शुभ", "अमृत", "चल", "रोग", "काल", "लाभ", "उद्वेग" };
	String[] chogadiyaNightNameMeaning = { "शुभ", "शुभ", "अच्छा", "अशुभ", "अशुभ", "शुभ", "अशुभ" };

	String[] paharName = { "प्रातः", "संगव", "मध्यान्ह", "अपराह्न", "सायं", "प्रदोष", "रात्रि", "निशिता", "रात्रि",
			"अरुणोदय" };
	String[] doGhatiPauranikMuhuratList = { "रौद्र", "सित", "मैत्र", "चारमठ", "सावित्र", "वैराज", "गंधर्व", "अभिजित",
			"रोहिणी", "बल", "विजय", "नैऋत", "इंद्र", "वरुण", "भग", "रौद्र", "गंधर्व", "पक्ष", "चारण", "वायु", "अग्नि",
			"राक्षस", "ब्रह्मा", "सौम्य", "कमलज", "गुरु", "पौष्ण", "वैकुण्ठ", "समीर", "निर्ऋति" };
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
	public String baisakhi = "बैसाखी";
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
		return doGhatiPauranikMuhuratList;
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