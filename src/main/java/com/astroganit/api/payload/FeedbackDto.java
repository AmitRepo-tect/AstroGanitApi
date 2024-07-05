package com.astroganit.api.payload;

import java.util.Date;

public class FeedbackDto {
   private int id;
   private String name;
   private String email;
   private long mobileNumber;
   private String message;
   private Date createdDate;

   public int getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getEmail() {
      return this.email;
   }

   public long getMobileNumber() {
      return this.mobileNumber;
   }

   public String getMessage() {
      return this.message;
   }

   public Date getCreatedDate() {
      return this.createdDate;
   }

   public void setId(final int id) {
      this.id = id;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public void setEmail(final String email) {
      this.email = email;
   }

   public void setMobileNumber(final long mobileNumber) {
      this.mobileNumber = mobileNumber;
   }

   public void setMessage(final String message) {
      this.message = message;
   }

   public void setCreatedDate(final Date createdDate) {
      this.createdDate = createdDate;
   }
}
