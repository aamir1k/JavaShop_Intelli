package com.productManagement.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.productManagement.demo.entity.Order;

public interface OrderServices {

	Order save(Order order);

	List<Order> getCustomerOrders(Long customerId, String status, Pageable pageable);

	String getCustomerOrderStatus(Long id);

	Order updateOrderDetailsForOrders(Long id, Order order);

	

}
