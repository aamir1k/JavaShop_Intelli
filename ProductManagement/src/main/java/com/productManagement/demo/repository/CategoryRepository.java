package com.productManagement.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByActiveTrue();

}
