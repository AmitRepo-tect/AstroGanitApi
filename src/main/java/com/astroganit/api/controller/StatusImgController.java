package com.astroganit.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.astroganit.api.payload.Response;
import com.astroganit.api.service.StatusImageService;

@RestController
@RequestMapping({ "/api" })
public class StatusImgController {
	@Autowired
	private StatusImageService statusImageService;

	@GetMapping("/statusimg/{langCode}/{page}/{time}")
	public ResponseEntity<Response> getStatusImages(@PathVariable long time, @PathVariable int langCode,
			@PathVariable int page) {
		// List<String> specialDays = Arrays.asList("Dwadashi", "Motivational");
		return ResponseEntity.ok(statusImageService.findBySpecialDayInAndLang(time, langCode));
	}

	@GetMapping("/getallcategory")
	public ResponseEntity<Response> getAllCategory(@RequestParam() int lang) {
		return ResponseEntity.ok(statusImageService.getAllCategory(lang));
	}

	@GetMapping("/statusimgbyid")
	public ResponseEntity<Response> getImagesByCateId(@RequestParam() long cateId, @RequestParam() int langCode) {
		return ResponseEntity.ok(statusImageService.findByCateIdAndLang(cateId, langCode));
	}

	@GetMapping("/statusimgbysubid")
	public ResponseEntity<Response> getImagesBySubCateId(@RequestParam() int subcateId, @RequestParam() int langCode) {
		return ResponseEntity.ok(statusImageService.findBySubCateIdAndLang(subcateId, langCode));
	}

	@GetMapping("/getAllImages")
	public ResponseEntity<Response> getAllImages(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "50") int size, @RequestParam() long time,
			@RequestParam(defaultValue = "2") int lang) {
		if (page == 0) {
			return ResponseEntity.ok(statusImageService.findBySpecialDayInAndLangOrderByCreateDateDesc(time, lang));
		} else {
			List<String> specialDays = Arrays.asList("Suvichar", "motivational", "Bhagvan");
			return ResponseEntity.ok(statusImageService.getStatusImages(specialDays, page - 1, size));
		}
	}

}
