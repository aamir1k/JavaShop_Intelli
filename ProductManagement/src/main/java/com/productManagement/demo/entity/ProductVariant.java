package com.productManagement.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_variant")
public class ProductVariant {
	
	/*
	 * public interface ProductVariantRepository extends
	 * JpaRepository<ProductVariant, Long> {
	 * 
	 * @Query("SELECT pv.size, SUM(pv.quantity) FROM ProductVariant pv JOIN pv.product p WHERE p.id = :productId GROUP BY pv.size"
	 * ) List<Object[]> findSizesAndQuantitiesByProductId(@Param("productId") Long
	 * productId); }
	 */
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String size;
      private Double weight;
      private Integer quantity;
      
      @ManyToOne
      @JoinColumn(name = "product_id")
      private Product product;
      
      
      public ProductVariant() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public Double getWeight() {
		return weight;
	}


	public void setWeight(Double weight) {
		this.weight = weight;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	
      
      

}
