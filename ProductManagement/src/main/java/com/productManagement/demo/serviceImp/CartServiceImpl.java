package com.productManagement.demo.serviceImp;

import com.productManagement.demo.entity.*;
import com.productManagement.demo.repository.CartItemRepository;
import com.productManagement.demo.repository.CartRepository;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.repository.UserRepository;
import com.productManagement.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Error;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {
     @Autowired
    ProductRepository productRepository;
     @Autowired
    CartRepository cartRepository;
     @Autowired
     private UserRepository userRepository;
    @Autowired
     CartItemRepository cartItemRepository;



    @Override
    public Cart createCart(String email, List<CartItemDto> cartItems) {
        System.out.println("aaaaaaa"+cartItems.get(0));
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Error("User not found");
        }

        Cart existingCart = cartRepository.findByOrderby(user.getId().toString());
        if (existingCart != null) {
            cartRepository.delete(existingCart);
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setOrderby(user.getId().toString());
        cartRepository.save(cart);
        double total = 0;
        List<CartItem> items = new ArrayList<>();

        for (CartItemDto item : cartItems) {
            Product product = productRepository.getProductById(item.getProductId());
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCount(item.getCount());
            //cartItem.setColor(item.getColor());
            cartItem.setCart(cart);
            items.add(cartItem);
            cartItemRepository.save(cartItem);
            total += product.getPrice() * item.getCount();
        }

        cart.setCartItems(items);
        cart.setTotal(total);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByOrderby(String.valueOf(userId));
    }

    @Override
    public void emptyCart(Long userId) {
        Cart cart = cartRepository.findByOrderby(userId.toString());
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        cartRepository.delete(cart);
    }

}


