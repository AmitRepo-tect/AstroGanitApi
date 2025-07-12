package com.astroganit.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroganit.api.service.KundliService;
import com.astroganit.lib.horo.model.BirthDetailBean;
import com.astroganit.lib.horo.model.DateTimeBean;
import com.astroganit.lib.horo.model.KundliBean;
import com.astroganit.lib.horo.model.PlaceDetail;




@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://64.227.131.121")

public class KundliController {
	@Autowired(required = true)
	KundliService kundliService;
	@PostMapping("/kundli")
	public ResponseEntity<KundliBean> receivePlaceAndDateTime(@RequestBody BirthDetailBean request) {
		// Retrieve place and datetime from the request object
		
		KundliBean kundliBean = null;
		try {
			kundliBean = this.kundliService.getKundliData(getBirthDetailModel(request));
			 //System.out.println("getPlace: " + kundliBean.getPlanetDegree()); 
		} catch (IOException e) { 
			e.printStackTrace();
		}

		return ResponseEntity.ok(kundliBean);

	}
	BirthDetailBean getBirthDetailModel(BirthDetailBean request) {
		PlaceDetail place = request.getPlaceDetail();
		DateTimeBean dateTimeInfo = request.getDateTimeBean();
		
		BirthDetailBean birthDetailModel = new BirthDetailBean();
		PlaceDetail placeDetailModel = new PlaceDetail();
		
		placeDetailModel.setPlace(place.getPlace());
		// System.out.println("getPlace: " + place.getPlace()); 
		placeDetailModel.setCountry(place.getCountry());
		// System.out.println("getCountry: " + place.getCountry()); 
		placeDetailModel.setState(place.getState());
		 //System.out.println("getState: " + place.getState()); 
		placeDetailModel.setLatDeg(place.getLatDeg());
		// System.out.println("getLatDeg: " + place.getLatDeg()); 
		placeDetailModel.setLatMin(place.getLatMin());
		// System.out.println("getLatMin: " + place.getLatMin()); 
		placeDetailModel.setLatNS(place.getLatNS());
		// System.out.println("getLatNS: " + place.getLatNS()); 
		placeDetailModel.setLongDeg(place.getLongDeg());
		// System.out.println("getLongDeg: " + place.getLongDeg()); 
		placeDetailModel.setLongMin(place.getLongMin());
		// System.out.println("getLongMin: " + place.getLongMin()); 
		placeDetailModel.setLongEW(place.getLongEW());
		// System.out.println("getLongEW: " + place.getLongEW()); 
		placeDetailModel.setTimezoneStr(place.getTimezoneStr());
		// System.out.println("getTimezoneStr: " + place.getTimezone()); 
		placeDetailModel.setTimezone(place.getTimezone());
		// System.out.println("getTimezone: " + place.getTimezone()); 
		DateTimeBean dateTimeModel = new DateTimeBean();
		dateTimeModel.setHrs(dateTimeInfo.getHrs());
		//System.out.println("getHrs: " + dateTimeInfo.getHrs()); 
		dateTimeModel.setMin(dateTimeInfo.getMin());
		//System.out.println("getMin: " + dateTimeInfo.getMin()); 
		dateTimeModel.setSec(dateTimeInfo.getSec());
		//System.out.println("getSec: " + dateTimeInfo.getSec()); 
		dateTimeModel.setDay(dateTimeInfo.getDay());
		//System.out.println("getDay: " + dateTimeInfo.getDay()); 
		dateTimeModel.setMonth(dateTimeInfo.getMonth());
		///System.out.println("getMonth: " + dateTimeInfo.getMonth()); 
		dateTimeModel.setYear(dateTimeInfo.getYear());
		//System.out.println("getYear: " + dateTimeInfo.getYear()); 
		birthDetailModel.setName(request.getName());
		//System.out.println("Name: " + request.getName()); 
		birthDetailModel.setSex("M");
		//System.out.println("Name: " + request.getSex()); 
		birthDetailModel.setDst(request.getDst());
		//System.out.println("getDst: " + request.getDst()); 
		birthDetailModel.setKphn(request.getKphn());
		//System.out.println("getKphn: " + request.getKphn()); 
		birthDetailModel.setAyanamsa(request.getAyanamsa());
		//System.out.println("getAyanamsa: " + request.getAyanamsa()); 
		birthDetailModel.setLanguageCode(request.getLanguageCode());
		//System.out.println("getLanguageCode: " + request.getLanguageCode()); 
		birthDetailModel.setDateTimeBean(dateTimeModel);
		birthDetailModel.setPlaceDetail(placeDetailModel);
		return birthDetailModel;
	}
}
