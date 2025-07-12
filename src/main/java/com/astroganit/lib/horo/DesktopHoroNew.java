package com.astroganit.lib.horo;

import com.astroganit.lib.horo.model.BirthDetailBean;
import com.astroganit.lib.horo.util.HoroHiConstant;

public class DesktopHoroNew extends PureHoroNew {
	private String[] eee = new String[10];
	private String[] z = new String[7];
	private String[] A = new String[7];
	private String hourString = null;
	private String yearsString = null;
	private String hrString = null;
	private String entTimeString = null;
	private String dayString = null;
	private String nightString = null;
	private String exitTimeString = null;
	private String planetString = null;
	private String dayLrdString = null;
	private String rahuKaalString = null;
	private String amString = null;
	private String pmString = null;
	protected String toString = null;
	private String[] Jaagrat = new String[3];
	private String[] Baladi = new String[5];
	private String[] Deepta = new String[10];
	private String[] karaka = new String[7];

	void setBirthDetail(BirthDetailBean birthDetailBean) {
		userName = birthDetailBean.getName();
		place = birthDetailBean.getPlaceDetail().getPlace();
		timeZone = Float.parseFloat(birthDetailBean.getPlaceDetail().getTimezone());
		sex = birthDetailBean.getSex();
		s = Integer.parseInt(birthDetailBean.getDateTimeBean().getSec());
		mt = Integer.parseInt(birthDetailBean.getDateTimeBean().getMin());
		h = Integer.parseInt(birthDetailBean.getDateTimeBean().getHrs());
		d = Integer.parseInt(birthDetailBean.getDateTimeBean().getDay());
		m = Integer.parseInt(birthDetailBean.getDateTimeBean().getMonth() );
		y = Integer.parseInt(birthDetailBean.getDateTimeBean().getYear());
		latdeg = Integer.parseInt(birthDetailBean.getPlaceDetail().getLatDeg());
		latmt = Integer.parseInt(birthDetailBean.getPlaceDetail().getLatMin());
		longdeg = Integer.parseInt(birthDetailBean.getPlaceDetail().getLongDeg());
		longmt = Integer.parseInt(birthDetailBean.getPlaceDetail().getLongMin());
		ew = birthDetailBean.getPlaceDetail().getLongEW().charAt(0);
		ns = birthDetailBean.getPlaceDetail().getLatNS().charAt(0);
		lang = birthDetailBean.getLanguageCode();
		dst = Integer.parseInt(birthDetailBean.getDst());
		horarySeed = Integer.parseInt(birthDetailBean.getKphn());
		ayanamsaType = Integer.parseInt(birthDetailBean.getAyanamsa());
	}

	public String getBalanceOfDasaString() {
		return String.valueOf(balance(plnt[2]));
	}

	void initialize() throws Exception {
		super.initialize();
		initializesArrayValues();
	}

	public int getAyanamsaType() {
		return this.ayanamsaType;
	}
	public String getBirthDate() {
		String[] months= HoroHiConstant.monthShortName;
		return d + " - " + months[m] + " - " + y;
	}

	public String getBirthTime() {
		return h + ":" + mt + ":" + s;
	}
	public String getPlace() {
		return this.place;
	}

	void initializesArrayValues() throws Exception {
		super.initializesArrayValues();
		String[] eee = { "युति", "पंच", "तृती", "नवां", "अष्टा", "सप्त", "चतृर्थ ", "अर्धद्वितीय", "पंचा", "पष्ठ" };
		this.eee = eee;
		String[] z = { "उद्वेग", "चल", "लाभ", "अम्रत", "काल", "शुभ", "रोग" };
		this.z = z;
		String[] A = { "शुभ", "अम्रत", "चल", "रोग", "काल", "लाभ", "उद्वेग" };
		this.A = A;
		String[] Jaagrat = { "जाग्रत", "स्वप्न", "सुसुप्त" };
		this.Jaagrat = Jaagrat;
		String[] Baladi = { "बाल", "कुमार", "युवा", "वृद्ध", "मृत" };
		this.Baladi = Baladi;
		String[] Deepta = { "शान्त", "दीप्त", "खल", "स्वत", "मुदित", "दीन", "पीड्य", "सक्त", "विकल", "भीत" };
		this.Deepta = Deepta;
		String[] karaka = { "आत्म", "अमात्य", "भ्रातृ", "मातृ", "पुत्र", "ग्नति", "दारा" };
		this.karaka = karaka;
		hrString = "घन्टा";
		hourString = "घन्टा";
		yearsString = "वर्ष";
		entTimeString = "प्रवेश काल";
		dayString = "दिन";
		nightString = "रात";
		exitTimeString = "निकास काल";
		planetString = "ग्रह";
		dayLrdString = "दिन का स्वामी";
		rahuKaalString = "राहु काल";
		amString = "प्रातः";
		pmString = "रात्रि";
		toString = "से";
	}

}
