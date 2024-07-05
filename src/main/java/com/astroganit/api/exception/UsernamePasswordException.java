package com.astroganit.api.exception;

public class UsernamePasswordException extends RuntimeException {
   String username;
   String password;
   String error;

   public UsernamePasswordException(String username, String password, String error) {
      super(String.format("%s For username -> %s and password -> %s ", error, username, password));
      this.username = username;
      this.password = password;
      this.error = error;
   }

   public String getUsername() {
      return this.username;
   }

   public String getPassword() {
      return this.password;
   }

   public String getError() {
      return this.error;
   }

   public void setUsername(final String username) {
      this.username = username;
   }

   public void setPassword(final String password) {
      this.password = password;
   }

   public void setError(final String error) {
      this.error = error;
   }
}
