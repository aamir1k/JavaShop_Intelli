package com.productManagement.demo.controllers;

import java.math.MathContext;
import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productManagement.demo.entity.*;
import com.productManagement.demo.payment.PaymentIntent;
import com.productManagement.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.productManagement.demo.service.OrderServices;

@RequestMapping("/orders")
@RestController
public class OrderController {
    @Autowired
    private OrderServices orderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PaymentIntentRepository paymentIntentRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/checkoutOrders")
    public ResponseEntity<?> checkOutOrders(@RequestBody Order order, HttpServletRequest request,
                                            HttpServletResponse response) {
        Order result = orderService.save(order);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{customerId}/getCustomerOrders/{status}")
    public ResponseEntity<?> getCustomerOrders(HttpServletResponse response, HttpServletRequest request,
                                               @PathVariable("customerId") Long customerId, @PathVariable("status") String status, @RequestParam Integer pageNumber) {
        pageNumber = pageNumber == null ? 0 : pageNumber;
        Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("id").descending());
        List<Order> result = orderService.getCustomerOrders(customerId, status, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}/getCustomerOrderStatus")
    public ResponseEntity<?> getCustomerOrderStatus(HttpServletRequest request, HttpServletResponse response,
                                                    @PathVariable Long id) {
        String result = orderService.getCustomerOrderStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}/updateOrderDetailsForOrders")
    public ResponseEntity<?> updateOrderDetailsForOrders(@PathVariable Long id, @RequestBody Order order, HttpServletRequest request, HttpServletResponse response) {
        Order result = orderService.updateOrderDetailsForOrders(id, order);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody OrderRequest request) {
        try {
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            Cart cart = cartRepository.findByUser(user);

            ArrayList<Product>cartProds=new ArrayList<>();
            for (CartItem items: cart.getCartItems()) {
                cartProds.add(items.getProduct());
            }
            double finalAmount = request.isCouponApplied() && cart.getTotalAfterDiscount() != null ? cart.getTotalAfterDiscount() : cart.getTotal();

            Order order = new Order();
            order.setProduct(cartProds);
            PaymentIntent payment = new PaymentIntent();
            payment.setAmount(finalAmount);
            payment.setCreated(new Date());
            payment.setStatus("COD");
            paymentIntentRepository.save(payment);
            order.setPaymentIntent(payment);
            order.setUser(user);
            order.setOrderStatus("Cash on Delivery");

            orderRepository.save(order);


            for (CartItem item :cart.getCartItems()) {
                Product product = item.getProduct();
                int quantity = product.getQuantity() + item.getCount();
                int sold = product.getSold() + item.getCount();
                product.setQuantity(quantity);
                product.setSold(sold);
                productRepository.save(product);
            }

            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            for (Order odr:orders) {
          odr.getProduct().forEach(item-> productRepository.findById(item.getProduct().getId()));;
            }
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            Optional<Order> orderOptional = orderRepository.findById(id);
            if (orderOptional.isPresent()) {
                return ResponseEntity.ok(orderOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
