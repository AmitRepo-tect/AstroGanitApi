package com.astroganit.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Suvichar {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   private String url;
   private String dayNight;
   private String dayValue;
   private String godNameCode;
   private int langCode;

   public int getId() {
      return this.id;
   }

   public String getUrl() {
      return this.url;
   }

   public String getDayNight() {
      return this.dayNight;
   }

   public String getDayValue() {
      return this.dayValue;
   }

   public String getGodNameCode() {
      return this.godNameCode;
   }

   public int getLangCode() {
      return this.langCode;
   }

   public void setId(final int id) {
      this.id = id;
   }

   public void setUrl(final String url) {
      this.url = url;
   }

   public void setDayNight(final String dayNight) {
      this.dayNight = dayNight;
   }

   public void setDayValue(final String dayValue) {
      this.dayValue = dayValue;
   }

   public void setGodNameCode(final String godNameCode) {
      this.godNameCode = godNameCode;
   }

   public void setLangCode(final int langCode) {
      this.langCode = langCode;
   }

   public Suvichar() {
   }

   public Suvichar(final int id, final String url, final String dayNight, final String dayValue, final String godNameCode, final int langCode) {
      this.id = id;
      this.url = url;
      this.dayNight = dayNight;
      this.dayValue = dayValue;
      this.godNameCode = godNameCode;
      this.langCode = langCode;
   }
}
