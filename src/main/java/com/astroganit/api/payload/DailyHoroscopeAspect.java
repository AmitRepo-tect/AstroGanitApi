package com.astroganit.api.payload;

public class DailyHoroscopeAspect {
   private String love;
   private String wealth;
   private String family;
   private String carrer;
   private String health;
   private int luckyNumber;

   public DailyHoroscopeAspect() {
   }

   public DailyHoroscopeAspect(String love, String wealth, String family, String carrer, String health, int luckyNumber) {
      this.love = love;
      this.wealth = wealth;
      this.family = family;
      this.carrer = carrer;
      this.health = health;
      this.luckyNumber = luckyNumber;
   }

   public int getLuckyNumber() {
      return this.luckyNumber;
   }

   public void setLuckyNumber(int luckyNumber) {
      this.luckyNumber = luckyNumber;
   }

   public String getLove() {
      return this.love;
   }

   public void setLove(String love) {
      this.love = love;
   }

   public String getWealth() {
      return this.wealth;
   }

   public void setWealth(String wealth) {
      this.wealth = wealth;
   }

   public String getFamily() {
      return this.family;
   }

   public void setFamily(String family) {
      this.family = family;
   }

   public String getCarrer() {
      return this.carrer;
   }

   public void setCarrer(String carrer) {
      this.carrer = carrer;
   }

   public String getHealth() {
      return this.health;
   }

   public void setHealth(String health) {
      this.health = health;
   }
}
