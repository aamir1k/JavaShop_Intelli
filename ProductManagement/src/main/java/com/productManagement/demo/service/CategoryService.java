package com.productManagement.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Category;

@Service
public interface CategoryService {

	boolean save(Category category);

	Category getCategoryById(Long id);

	Category saveCategory(Category category);

	List<Category> getAllCategory();

	void deleteCategoryById(Long id);
}
