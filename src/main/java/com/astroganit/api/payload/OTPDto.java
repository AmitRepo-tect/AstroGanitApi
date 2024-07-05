package com.astroganit.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class OTPDto {
   int id;
   String otp;
   String mobile;
   @JsonProperty
   private Date createdDate;
   @JsonProperty
   private Date updatedDate;
   private int count;

   public void setId(final int id) {
      this.id = id;
   }

   public void setOtp(final String otp) {
      this.otp = otp;
   }

   public void setMobile(final String mobile) {
      this.mobile = mobile;
   }

   @JsonProperty
   public void setCreatedDate(final Date createdDate) {
      this.createdDate = createdDate;
   }

   @JsonProperty
   public void setUpdatedDate(final Date updatedDate) {
      this.updatedDate = updatedDate;
   }

   public void setCount(final int count) {
      this.count = count;
   }

   public int getId() {
      return this.id;
   }

   public String getOtp() {
      return this.otp;
   }

   public String getMobile() {
      return this.mobile;
   }

   public Date getCreatedDate() {
      return this.createdDate;
   }

   public Date getUpdatedDate() {
      return this.updatedDate;
   }

   public int getCount() {
      return this.count;
   }
}
