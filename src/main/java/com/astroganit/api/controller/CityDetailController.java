package com.astroganit.api.controller;

import com.astroganit.api.payload.CityDetailsDto;
import com.astroganit.api.service.CityDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/place"})
public class CityDetailController {
   @Autowired
   private CityDetailService cityDetailService;

   @GetMapping({"/city/{place}"})
   public ResponseEntity<List<CityDetailsDto>> getCityByPlace(@PathVariable String place) {
      List<CityDetailsDto> cities = this.cityDetailService.getCities(place);
      return ResponseEntity.ok(cities);
   }
}
