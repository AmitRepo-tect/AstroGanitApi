package com.astroganit.api.controller;

import com.astroganit.api.payload.Response;
import com.astroganit.api.service.JSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/json"})
public class JSONController {
   @Autowired
   private JSONService jsonService;

   @GetMapping({"/baalkand/{sId}"})
   public ResponseEntity<Response> baalKandBySlok(@PathVariable String sId) {
      Response baalKandBySlok = this.jsonService.getBaalKandBySlok(sId);
      return ResponseEntity.ok(baalKandBySlok);
   }

   @GetMapping({"/aranyakand/{sId}"})
   public ResponseEntity<Response> aranyaKandBySlok(@PathVariable String sId) {
      Response aranyaKandBySlok = this.jsonService.getAranyaKandBySlok(sId);
      return ResponseEntity.ok(aranyaKandBySlok);
   }

   @GetMapping({"/ayodhyakand/{sId}"})
   public ResponseEntity<Response> ayodhyaKandBySlok(@PathVariable String sId) {
      Response ayodhyaKandBySlok = this.jsonService.getAyodhyaKandBySlok(sId);
      return ResponseEntity.ok(ayodhyaKandBySlok);
   }

   @GetMapping({"/kisgandhakand/{sId}"})
   public ResponseEntity<Response> kisgandhaKandBySlok(@PathVariable String sId) {
      Response kisgandhaKandBySlok = this.jsonService.getKisgandhaKandBySlok(sId);
      return ResponseEntity.ok(kisgandhaKandBySlok);
   }

   @GetMapping({"/lankakand/{sId}"})
   public ResponseEntity<Response> lankakandBySlok(@PathVariable String sId) {
      Response lankaKandBySlok = this.jsonService.getLankaKandBySlok(sId);
      return ResponseEntity.ok(lankaKandBySlok);
   }

   @GetMapping({"/uttrakand/{sId}"})
   public ResponseEntity<Response> uttraKandBySlok(@PathVariable String sId) {
      Response uttraKandBySlok = this.jsonService.getUttraKandBySlok(sId);
      return ResponseEntity.ok(uttraKandBySlok);
   }
}
