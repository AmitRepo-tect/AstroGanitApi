package com.astroganit.api.payload;

public class SuvicharDto {
   private int id;
   private String sentence;

   public void setId(final int id) {
      this.id = id;
   }

   public void setSentence(final String sentence) {
      this.sentence = sentence;
   }

   public int getId() {
      return this.id;
   }

   public String getSentence() {
      return this.sentence;
   }

   public SuvicharDto() {
   }

   public SuvicharDto(final int id, final String sentence) {
      this.id = id;
      this.sentence = sentence;
   }
}
