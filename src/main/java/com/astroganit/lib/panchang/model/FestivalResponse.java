package com.astroganit.lib.panchang.model;

import java.util.ArrayList;
import java.util.HashMap;

public class FestivalResponse {

	HashMap<Integer, ArrayList<ArrayList<FestivalDetail>>> hashMap;

	public HashMap<Integer, ArrayList<ArrayList<FestivalDetail>>> getFestDetail() {
		return hashMap;
	}

	public void setFestDetail(HashMap<Integer, ArrayList<ArrayList<FestivalDetail>>> hashMap) {
		this.hashMap = hashMap;
	}
}
