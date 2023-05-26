package com.productManagement.demo.service;

import com.productManagement.demo.entity.Cart;
import com.productManagement.demo.entity.CartItemDto;

import java.util.List;

public interface CartService {
    Cart createCart(String username, List<CartItemDto> cartItems);

    Cart getCartByUserId(Long userId);
    void emptyCart(Long userId);
}
