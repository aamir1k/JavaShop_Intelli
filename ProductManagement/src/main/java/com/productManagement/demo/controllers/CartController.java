package com.productManagement.demo.controllers;

import com.productManagement.demo.entity.*;
import com.productManagement.demo.repository.*;
import com.productManagement.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CouponRequestRepository couponRequestRepository;


    @PostMapping
    public ResponseEntity<Cart> createUserCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody List<CartItemDto> cartItems) {
        String email = userDetails.getUsername();
        Cart cart = cartService.createCart(email, cartItems);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}")
    public void emptyCart(@PathVariable Long userId) {
        cartService.emptyCart(userId);
    }

    @PostMapping("/applyCoupon")
    public ResponseEntity<?> applyCoupon(@RequestBody CouponRequest couponRequest) {
        String couponName = couponRequest.getCoupon();
        User user = userRepository.findById(couponRequest.getUser().getId()).get();
        Cart cart = cartRepository.findByUser(user);

        Coupon coupon = couponRepository.findByName(couponName);
        if (coupon == null) {
            return ResponseEntity.badRequest().body("Invalid Coupon");
        }

        double cartTotal = cart.getTotal();
        double totalAfterDiscount = (cartTotal - (cartTotal * coupon.getDiscount() / 100));
        cart.setTotalAfterDiscount(totalAfterDiscount);
        cartRepository.save(cart);

        // Create a new CouponRequest entity and save it to the database
        CouponRequest newRequest = new CouponRequest();
        newRequest.setCoupon(couponName);
        newRequest.setUser(user);
        couponRequestRepository.save(newRequest);

        return ResponseEntity.ok(totalAfterDiscount);

    }
}
