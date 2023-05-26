package com.productManagement.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.repository.OrderRepository;
import com.productManagement.demo.service.OrderServices;

@Service
public class OrderServiceImpl implements OrderServices {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order save(Order order) {
		
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getCustomerOrders(Long customerId, String status, Pageable pageable) {
		//List<Order> orderMain = orderRepository.findByCustomerId(customerId, pageable);
		List<Order> filteredOrders = new ArrayList<>();
		return null;
	}

	@Override
	public String getCustomerOrderStatus(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return (order.isPresent())?order.get().getOrderStatus():"NOT_FOUND";
		
	}

	@Override
	public Order updateOrderDetailsForOrders(Long id, Order order) {
		Order updateOrder = orderRepository.findById(id).get();
		return orderRepository.save(updateOrder);
	}

	}


