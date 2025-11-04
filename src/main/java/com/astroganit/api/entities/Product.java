package com.astroganit.api.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String name;
	private String description;
	private String category;
	private Double price;
	private Integer stock;
	private String imageUrl;
	private Boolean isActive = true;
	private LocalDateTime createdAt = LocalDateTime.now();
}
