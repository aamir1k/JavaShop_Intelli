package com.productManagement.demo.repository;

import com.productManagement.demo.entity.CouponRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRequestRepository extends JpaRepository<CouponRequest, Long> {
}
