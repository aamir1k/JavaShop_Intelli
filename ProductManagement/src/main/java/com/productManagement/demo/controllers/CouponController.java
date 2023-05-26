package com.productManagement.demo.controllers;

import com.productManagement.demo.entity.Coupon;
import com.productManagement.demo.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponRepository couponRepository;



    @PostMapping
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon){
        Coupon coupon1 = couponRepository.save(coupon);
        return ResponseEntity.ok(coupon1);
    }

    @GetMapping
    public ResponseEntity<?> findall(){
    List<Coupon> coupon =  couponRepository.findAll();
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        Coupon coupon = couponRepository.findById(id).get();
        return ResponseEntity.ok(coupon);
    }
}
