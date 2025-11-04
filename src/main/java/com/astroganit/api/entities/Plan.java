package com.astroganit.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plans")
public class Plan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;
    private String name;
    private String description;
    private Double price;
    private Integer durationDays;
    @Column(columnDefinition = "TEXT")
    private String features;
    private Boolean isActive = true;
}
