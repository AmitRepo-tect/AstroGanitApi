package com.astroganit.api.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private long userId;
    private Double totalAmount;
    private String paymentId;
    private String orderStatus;
    private LocalDateTime orderDate = LocalDateTime.now();
}
