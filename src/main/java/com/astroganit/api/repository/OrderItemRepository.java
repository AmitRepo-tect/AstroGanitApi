package com.astroganit.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.astroganit.api.entities.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderId(int orderId);
}
