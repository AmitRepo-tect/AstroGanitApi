package com.astroganit.api.payload;

public class NumerologyDto {
   private int day;
   private int month;
   private int year;
   private String name;
   private int type;
   private int langCode;

   public int getDay() {
      return this.day;
   }

   public int getMonth() {
      return this.month;
   }

   public int getYear() {
      return this.year;
   }

   public String getName() {
      return this.name;
   }

   public int getType() {
      return this.type;
   }

   public int getLangCode() {
      return this.langCode;
   }

   public void setDay(final int day) {
      this.day = day;
   }

   public void setMonth(final int month) {
      this.month = month;
   }

   public void setYear(final int year) {
      this.year = year;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public void setType(final int type) {
      this.type = type;
   }

   public void setLangCode(final int langCode) {
      this.langCode = langCode;
   }
}
