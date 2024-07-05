package com.astroganit.api.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Role implements Serializable {
   @Id
   private int id;
   private String name;

   public int getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public void setId(final int id) {
      this.id = id;
   }

   public void setName(final String name) {
      this.name = name;
   }
}
