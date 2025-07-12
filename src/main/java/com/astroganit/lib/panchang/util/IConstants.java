package com.astroganit.lib.panchang.util;

public interface IConstants {
	public String baisakhi = "baisakhi";
	public String onam = "ओणम";
	public String sankranti = "संक्रांति";
	public String uttarayanam = "उत्तरायनम";
	public String makarSankranti = "मकर संक्रांति";
	public String pongal = "पोंगल";
	public String lohri = "लोहड़ी";
	public String ashadhiEkadashi = "आषाढ़ी एकादशी";
	public String purnima = "पूर्णिमा";
	public String purnimaVrat = "पूर्णिमा व्रत";
	public String amavasya = "अमावस्या";
	public String sankashtiChaturthi = "संकष्टी चतुर्थी";
	public String masikShivratri = "मासिक शिवरात्रि";
	public String pradoshS = "प्रदोष व्रत (शुक्ल)";
	public String pradoshK = "प्रदोष व्रत (कृष्ण)";
	public String hinduNewYear = "हिन्दू नव वर्ष";
	public String ugadi = "उगाड़ी";
	public String gudiPadwa = "गुड़ी पड़वा";
	public String chetiChand = "चेटी चंद";
	public String chaitraNavratri = "चैत्र नवरात्री";
	public String chaitraNavratriGhatasthapana = "चैत्र नवरात्री घटस्थापना";
	public String chaitraNavratriParana = "चैत्र नवरात्री परना";
	public String ramNavami = "राम नवमी";
	public String chaitraHanumanJayanti = "हनुमान जयंती";
	public String akshayaTritiya = "अक्षय तृतीया";
	public String jagannathRathYatra = "जगन्नाथ रथ यात्रा";
	public String guruPurnima = "गुरु पूर्णिमा ";
	public String hariyaliTeej = "हरियाली तीज";
	public String kajariTeej = "कजरी तीज";
	public String nagPanchami = "नाग पंचमी";
	public String rakshaBandhan = "रक्षा बंधन";
	public String janmashtami = "जन्माष्टमी";
	public String ganeshChaturthi = "गणेश चतुर्थी";
	public String hartalikaTeej = "हरतालिका तीज";
	public String anantChaturdashi = "अनंत चतुर्दशी";
	public String ashwinNavratriGhataSthapana = "शरद नवरात्री घटस्थापना";
	public String ashwinNavratri = "शरद नवरात्री";
	public String ashwinNavratriParana = "शरद नवरात्री परना";
	public String ashwinNavratriDurgaVisarjan = "शरद नवरात्री दुर्गा विसर्जन";
	public String navPatrika = "नव पत्रिका";
	public String kalparambha = "कल्पारम्भा";
	public String durgaPujaCalendar = "दुर्गा पूजा कैलेंडर";
	public String durgaPujaNavami = "दुर्गा महा नवमी पूजा";
	public String vijayaDashami = "दशहरा";
	public String karvaChauth = "करवा चौथ";
	public String dhanteras = "धनतेरस";
	public String diwaliPujaCalendar = "दिवाली पूजा कैलेंडर";
	public String narakChaturdashi = "नरक चतुर्दशी";
	public String diwali = "दिवाली";
	public String govardhan = "गोवर्धन पूजा";
	public String bhaiDooj = "भाई दूज";
	public String mahaShivratri = "महा शिवरात्रि";
	public String vasantPanchami = "वसंत पंचमी";
	public String saraswatiPuja = "सरस्वती पूजा";
	public String holikaDahan = "होलिका दहन ";
	public String holi = "होली";
	public String merryChristmas = "मेरी क्रिसमस";
	public String englishNewYear = "नव वर्ष ";
	public String childrenDay = "बाल दिवस ";
	public String gandhiJayanti = "गाँधी जयंती";
	public String independenceDay = "स्वतंत्रता दिवस";
	public String ambedkarJayanti = "आंबेडकर जयंती ";
	public String banksHoliday = "बैंक अवकाश";
	public String republicDay = "गणतंत्र दिवस";
	public String subhasChandraBoseJayanti = "सुभाष चंद्र बोस जयंती";
	public String durgaPujaAstmi = "दुर्गा महा अष्टमी पूजा";

	/// <summary>
	///
	/// </summary>
	public String getMasas(int index);

	public String getVaras(int index);

	public String getVarasSwami(int index);

	public String getSamvats(int index);

	public String getRitus(int index);

	public String getKaranas(int index);

	public String getPakshas(int index);

	public String getAyanas(int index);

	public String getDishas(int index);

	public String getNakshatra(int index);

	public String getYoga(int index);

	public String getMoonSign(int index);

	public String getTithi(int index);

	public String getChandraBala(int index);

	public String getTaraBala(int index);

	public String getMonthName(int index);

	public String getMonthNameSort(int index);

	public String getDayName(int index);

	public String getExString(int index);

	public String[] getChandraBala();

	public String[] getTaraBala();

	public String getChoghadiaDayName(int index);

	public String getChoghadiaNightName(int index);

	public String getLagna(int index);

	public String getLagnaNature(int index);

	public String getRashi(int index);

	public String[] getRashiList();

	public String[] getHoraPlanetName();

	public String[] getHoraPlanetMeaning();

	public String[] getCurrentHoraPlanetMeaning();

	public String[] getDayChogdia();

	public String[] getNightChogdia();

	public String[] getNightChogdiaMeaning();

	public String[] getPaharNameList();

	public String[] getDoGhatiPauranikMuhuratList();

	public String[] getDoGhatiNakshtra();

	public String[] getDoGhatiNameListMeaningSecond();

	public String[] getDoGhatiNameListMeaning();

	public String[] getDoGhatiMuhurat();

	public String[] getEkadashiK();

	public String[] getEkadashiS();
	
	public String[] getAdhikMasas();

}
