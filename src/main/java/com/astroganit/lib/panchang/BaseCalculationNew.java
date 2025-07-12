package com.astroganit.lib.panchang;

import com.astroganit.lib.panchang.model.PlaceInfo;
import com.astroganit.lib.panchang.util.Place;

public class BaseCalculationNew {
	PanchangCalculation panchangCalculation = new PanchangCalculation();
	MuhuratCalculation muhuratCalculation = new MuhuratCalculation();
	MoonCalculation moonCalculation = new MoonCalculation();

	void setPlace(Place place) {
		panchangCalculation.initPlace(place);
		muhuratCalculation.initPlace(place);
		moonCalculation.initPlace(place);
	}

	void setPlace(PlaceInfo placeInfo) {
		Place place = new Place(placeInfo.getLatitude(), placeInfo.getLongitude(), placeInfo.getTimezone());
		setPlace(place);
	}
}
