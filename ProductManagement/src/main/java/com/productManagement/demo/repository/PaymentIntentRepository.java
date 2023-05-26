package com.productManagement.demo.repository;

import com.productManagement.demo.payment.PaymentIntent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentIntentRepository extends JpaRepository<PaymentIntent, Long> {
}
