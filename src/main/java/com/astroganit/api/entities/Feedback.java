package com.astroganit.api.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(
   name = "feedback"
)
public class Feedback {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   private String name;
   private String email;
   @Column(
      name = "mobile_number"
   )
   private long mobileNumber;
   private String message;
   @Column(
      name = "created_date"
   )
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
