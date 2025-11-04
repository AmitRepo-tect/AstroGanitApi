package com.astroganit.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.astroganit.api.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(String category);

    List<Product> findByIsActiveTrue();
}
