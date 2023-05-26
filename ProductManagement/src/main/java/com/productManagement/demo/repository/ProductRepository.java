package com.productManagement.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.ProductImages;

public interface ProductRepository extends JpaRepository<Product, Long>{

	//List<ProductImages> findByProductId(long id);

	@Query("select i from ProductImages i where i.product.id=:id")
	List<ProductImages> findByProductId(@Param("id") Long id);

	void save(ProductImages pp);

	 @Query("SELECT p FROM Product p LEFT JOIN FETCH p.images i WHERE p.id = :id")
	  Product findByIdWithImages(@Param("id") Long id);


    Product getProductById(Long productId);
}
