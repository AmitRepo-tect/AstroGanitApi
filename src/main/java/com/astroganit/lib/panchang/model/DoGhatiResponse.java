package com.astroganit.lib.panchang.model;

import java.util.ArrayList;

public class DoGhatiResponse {
	ArrayList<DoGhatiBean> doGhatiList;
	ArrayList<DoGhatiBean> dayDoGhatiList;
	ArrayList<DoGhatiBean> nightDoGhatiList;
	
	public ArrayList<DoGhatiBean> getDoGhatiList() {
		return doGhatiList;
	}
	public void setDoGhatiList(ArrayList<DoGhatiBean> doGhatiList) {
		this.doGhatiList = doGhatiList;
	}
	public ArrayList<DoGhatiBean> getDayDoGhatiList() {
		return dayDoGhatiList;
	}
	public void setDayDoGhatiList(ArrayList<DoGhatiBean> dayDoGhatiList) {
		this.dayDoGhatiList = dayDoGhatiList;
	}
	public ArrayList<DoGhatiBean> getNightDoGhatiList() {
		return nightDoGhatiList;
	}
	public void setNightDoGhatiList(ArrayList<DoGhatiBean> nightDoGhatiList) {
		this.nightDoGhatiList = nightDoGhatiList;
	}
	
}
