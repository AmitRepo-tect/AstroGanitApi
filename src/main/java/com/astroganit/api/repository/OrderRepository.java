package com.astroganit.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.astroganit.api.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);
}
