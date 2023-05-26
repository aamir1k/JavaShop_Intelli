package com.productManagement.demo.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInCartDto {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private String color;
}
