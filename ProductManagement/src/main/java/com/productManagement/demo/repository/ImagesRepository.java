package com.productManagement.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.Images;
import com.productManagement.demo.entity.Product;

public interface ImagesRepository extends JpaRepository<Images, Long> {

	List<Images> findByProduct(Product productEntity);

	List<Images> findByProduct_Id(Long productId);

	//Images findByProduct_Id(Long productId);


	// findByProduct(Product productEntity);



	

}
