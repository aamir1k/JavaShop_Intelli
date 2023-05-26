package com.productManagement.demo.repository;

import com.productManagement.demo.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByName(String couponName);
}
