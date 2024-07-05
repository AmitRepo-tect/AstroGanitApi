package com.astroganit.api.payload;

public class SuvicharURL {
   private int id;
   private String url;

   public void setId(final int id) {
      this.id = id;
   }

   public void setUrl(final String url) {
      this.url = url;
   }

   public int getId() {
      return this.id;
   }

   public String getUrl() {
      return this.url;
   }

   public SuvicharURL() {
   }

   public SuvicharURL(final int id, final String url) {
      this.id = id;
      this.url = url;
   }
}
