package com.astroganit.api.payload;

public class DailyHorosocpeSentence {
   private String sentence;

   public DailyHorosocpeSentence() {
   }

   public DailyHorosocpeSentence(String sentence) {
      this.sentence = sentence;
   }

   public String getSentence() {
      return this.sentence;
   }

   public void setSentence(String sentence) {
      this.sentence = sentence;
   }

   public String toString() {
      return "DailyHorosocpeSentence [sentence=" + this.sentence + "]";
   }
}
