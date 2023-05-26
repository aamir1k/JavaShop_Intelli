package com.productManagement.demo.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.ProductImages;

@Service
public interface ProductService {
	
	boolean save(Product product);

	

	Product getProductById(Long id);

	Product saveProduct(Product product);

	void deleteProductById(Long id);



	List<Product> findAll();



	List<ProductImages> findByProductId(Long id);

	/*---------------------------------------------------
	*/
	 public void init();



	  public void deleteAll();



	



	Product findById(Long id);



	Product findByIdWithImages(Long id);






}
