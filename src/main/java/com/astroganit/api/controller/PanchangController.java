package com.astroganit.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.astroganit.api.model.muhurat.MuhuratResponse;
import com.astroganit.api.payload.Response;
import com.astroganit.api.service.PanchangService;
import com.astroganit.lib.panchang.model.BhadraResponse;
import com.astroganit.lib.panchang.model.FestivalResponse;
import com.astroganit.lib.panchang.model.FestivalResponseNew;
import com.astroganit.lib.panchang.model.PanchakResponse;
import com.astroganit.lib.panchang.model.PanchangRequest;
import com.astroganit.lib.panchang.model.PanchangResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://64.227.131.121")
public class PanchangController {
	@Autowired(required = true)
	PanchangService panchangService;

	@PostMapping("/panchang")
	public ResponseEntity<PanchangResponse> receivePlaceAndDateTime(@RequestBody PanchangRequest request) {
		// Retrieve place and datetime from the request object
		/*
		 * PlaceInfo place = request.getPlace(); DateTimeInfo dateTimeInfo =
		 * request.getDateTimeInfo(); PanchangInputModel panchangInputModel=new
		 * PanchangInputModel(place, dateTimeInfo);
		 */
		PanchangResponse panchangResponse = null;
		try {
			panchangResponse = this.panchangService.getPanchang(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok(panchangResponse);

	}

	@PostMapping("/panchak")
	public ResponseEntity<PanchakResponse> getPanchakResponse(@RequestBody PanchangRequest request) {

		PanchakResponse panchakResponse = null;
		try {
			panchakResponse = this.panchangService.getPanchakData(request);
		} catch (IOException e) { // TODO
			e.printStackTrace();
		}

		return ResponseEntity.ok(panchakResponse);

	}

	@PostMapping("/bhadra")
	public ResponseEntity<BhadraResponse> getBhadraResponse(@RequestBody PanchangRequest request) {

		BhadraResponse bhadraResponse = null;
		try {
			bhadraResponse = this.panchangService.getBhadraData(request);
		} catch (IOException e) { // TODO
			e.printStackTrace();
		}

		return ResponseEntity.ok(bhadraResponse);
	}
	

	@GetMapping({ "/muhurat/{sId}" })
	public ResponseEntity<MuhuratResponse> getMuhurats(@PathVariable String sId) {
		MuhuratResponse response = null;
		try {
			response = this.panchangService.getMuhurats(sId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/festivals")
	public ResponseEntity<FestivalResponse> getFestivalList(@RequestBody PanchangRequest request) {
		FestivalResponse festivalResponse = null;
		try {
			festivalResponse = this.panchangService.getFestDetail(request);
		} catch (IOException e) { // TODO
			e.printStackTrace();
		}
		return ResponseEntity.ok(festivalResponse);
	}
	@GetMapping("/festivalsNew/{year}/{language}")
	public ResponseEntity<FestivalResponseNew> getFestivalListNew(@PathVariable int year,@PathVariable int language) {
		FestivalResponseNew festivalResponse = null;
		try {
			festivalResponse = this.panchangService.getFestDetailNew(year,language);
		} catch (IOException e) { // TODO
			e.printStackTrace();
		}
		return ResponseEntity.ok(festivalResponse);
	}
}
