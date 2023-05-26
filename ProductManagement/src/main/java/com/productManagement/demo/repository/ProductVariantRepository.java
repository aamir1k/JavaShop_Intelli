package com.productManagement.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.productManagement.demo.entity.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

	//List<Object[]> findSizesAndQuantitiesByProductId(Long id);
	
//	   @Query("SELECT ProductVariant.size, ProductVariant.quantity FROM ProductVariant pv WHERE pv.product.id = :id")
//	    List<Object[]> findByProductId(@Param("id") Long id);

}
