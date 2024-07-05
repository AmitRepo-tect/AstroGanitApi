 package com.astroganit.api.controller;

import com.astroganit.api.payload.FeedbackDto;
import com.astroganit.api.payload.NumerologyDto;
import com.astroganit.api.payload.NumerologyResponse;
import com.astroganit.api.service.FeedbackService;
import com.astroganit.api.service.NumerologyService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
   @Autowired
   private FeedbackService feedbackService;
   @Autowired
   private NumerologyService numerologyService;

   @GetMapping({"/"})
   public String home() {
      return "Welcome To Astro Ganit Home Page";
   }

   @PostMapping({"/api/feedback"})
   public ResponseEntity<FeedbackDto> createFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
      FeedbackDto createFeedBack = this.feedbackService.createFeedBack(feedbackDto);
      return new ResponseEntity(createFeedBack, HttpStatus.CREATED);
   }

   @PostMapping({"/api/numerology"})
   public ResponseEntity<NumerologyResponse> getNumerologyDetails(@Valid @RequestBody NumerologyDto numerologyDto) {
      NumerologyResponse numerologyResponse = this.numerologyService.getNumerologyDetail(numerologyDto);
      return new ResponseEntity(numerologyResponse, HttpStatus.OK);
   }
}
