package com.astroganit.lib.panchang.model;

import java.util.ArrayList;

public class ChogdiyaResponse {
	ArrayList<ChogdiyaBean> chogdiyaList;
	ArrayList<ChogdiyaBean> dayChogdiyaList;
	ArrayList<ChogdiyaBean> nightChogdiyaList;

	public ArrayList<ChogdiyaBean> getChogdiyaList() {
		return chogdiyaList;
	}

	public void setChogdiyaList(ArrayList<ChogdiyaBean> chogdiyaList) {
		this.chogdiyaList = chogdiyaList;
	}

	public ArrayList<ChogdiyaBean> getDayChogdiyaList() {
		return dayChogdiyaList;
	}

	public void setDayChogdiyaList(ArrayList<ChogdiyaBean> dayChogdiyaList) {
		this.dayChogdiyaList = dayChogdiyaList;
	}

	public ArrayList<ChogdiyaBean> getNightChogdiyaList() {
		return nightChogdiyaList;
	}

	public void setNightChogdiyaList(ArrayList<ChogdiyaBean> nightChogdiyaList) {
		this.nightChogdiyaList = nightChogdiyaList;
	}

}
