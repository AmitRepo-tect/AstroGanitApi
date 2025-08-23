package com.astroganit.lib.panchang.model;

import java.util.ArrayList;
import java.util.HashMap;

public class FestivalResponseNew {
	ArrayList<ArrayList<FestivalDetail>> festivals;

	public ArrayList<ArrayList<FestivalDetail>> getFestDetail() {
		return festivals;
	}

	public void setFestDetail(ArrayList<ArrayList<FestivalDetail>> festivals) {
		this.festivals = festivals;
	}
}
