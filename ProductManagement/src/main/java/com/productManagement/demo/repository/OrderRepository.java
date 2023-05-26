package com.productManagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
