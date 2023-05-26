package com.productManagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.Category;
import com.productManagement.demo.entity.Customers;

public interface CustomerRepository  extends JpaRepository<Category, Long>{

    Customers save(Customers customer);



}
