package com.astroganit.api.payload;

public class JwtAuthResponse {
   private String token;

   public String getToken() {
      return this.token;
   }

   public void setToken(final String token) {
      this.token = token;
   }

   public JwtAuthResponse() {
   }

   public JwtAuthResponse(final String token) {
      this.token = token;
   }
}
