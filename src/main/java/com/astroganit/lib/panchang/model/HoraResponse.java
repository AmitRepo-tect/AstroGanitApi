package com.astroganit.lib.panchang.model;

import java.util.ArrayList;

public class HoraResponse {
	ArrayList<HoraBean> horaList;
	ArrayList<HoraBean> dayHoraList;
	ArrayList<HoraBean> nightHoraList;

	public ArrayList<HoraBean> getHoraList() {
		return horaList;
	}

	public void setHoraList(ArrayList<HoraBean> horaList) {
		this.horaList = horaList;
	}

	public ArrayList<HoraBean> getDayHoraList() {
		return dayHoraList;
	}

	public void setDayHoraList(ArrayList<HoraBean> dayHoraList) {
		this.dayHoraList = dayHoraList;
	}

	public ArrayList<HoraBean> getNightHoraList() {
		return nightHoraList;
	}

	public void setNightHoraList(ArrayList<HoraBean> nightHoraList) {
		this.nightHoraList = nightHoraList;
	}

}
