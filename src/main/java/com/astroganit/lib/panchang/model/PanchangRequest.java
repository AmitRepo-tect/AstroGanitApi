package com.astroganit.lib.panchang.model;

public class PanchangRequest {
	private PanchangInputModel panchangInputModel;
	private int langCode;

	PanchangRequest(PanchangInputModel panchangInputModel, int langCode) {
		this.panchangInputModel = panchangInputModel;
		this.langCode = langCode;
	}

	public PanchangInputModel getPanchangInputModel() {
		return panchangInputModel;
	}

	public void setPanchangInputModel(PanchangInputModel panchangInputModel) {
		this.panchangInputModel = panchangInputModel;
	}

	public int getLangCode() {
		return langCode;
	}

	public void setLangCode(int langCode) {
		this.langCode = langCode;
	}

}
