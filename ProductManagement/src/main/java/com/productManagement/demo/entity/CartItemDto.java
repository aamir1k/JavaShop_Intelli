package com.productManagement.demo.entity;

import lombok.Data;

@Data
public class CartItemDto {
    private Long productId;
    private Integer count;
   // private String color;
}
