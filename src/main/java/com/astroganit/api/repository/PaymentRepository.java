package com.astroganit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroganit.api.entities.Payment;

import java.util.Optional;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Optional<Payment> findByPaymentId(String paymentId); // Razorpay payment ID

	Optional<Payment> findByOrderId(String orderId); // Razorpay order ID

	List<Payment> findByUserId(Long userId);
}
