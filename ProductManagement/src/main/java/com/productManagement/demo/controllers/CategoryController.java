package com.productManagement.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Category;
import com.productManagement.demo.service.CategoryService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategories")
    public ResponseEntity<?> categories() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping("/addUpdateCategory")
    public ResponseEntity<?> updateUser(@RequestBody Category category, HttpServletRequest request,
                                        HttpServletResponse response) {
        if (category != null && category.getId() != null) {
            Category categoryEntity = categoryService.getCategoryById(category.getId());
            categoryEntity.setId(category.getId());
            categoryEntity.setCategoryName(category.getCategoryName());
            categoryEntity.setDescription(category.getDescription());
            categoryEntity.setActive(category.isActive());

            Category result = categoryService.saveCategory(categoryEntity);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else if (category != null && category.getId() == null) {
            Category categoryEntity = new Category();
            categoryEntity.setCategoryName(category.getCategoryName());
            categoryEntity.setDescription(category.getDescription());
            categoryEntity.setActive(category.isActive());

            Category newUser = categoryService.saveCategory(categoryEntity);

            return ResponseEntity.status(HttpStatus.OK).body(newUser);

        } else {
            return ResponseEntity.badRequest().body("User Details Are missing");
        }
    }

    @DeleteMapping("deleteCategory/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
