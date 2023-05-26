package com.productManagement.demo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private Double price;

	
	@Column(name="status")
	private String status;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="reviews")
	private String reviews;
	
	@Column(name="rating")
	private String rating;
	
	@Column(name="details")
	private String details;

	private Date createdAt;
	private int quantity;
	private String brand;
	private String color;
	private int sold;
	private int totalRatings;
	private Integer count;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Rating> ratings ;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	

	@Column(name="images")
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	 private List<Images> images = new ArrayList<>();
	
	@Column(name="variants")
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<ProductVariant> variants;
	
	
	public Product() {
		
	}


	  @JsonIgnore
	  public Product getProduct() {
	    return this;
	  }

	public List<Images> getImages() {
		return images;
	}




	public void setImages(List<Images> images) {
		this.images = images;
	}




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}





	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public String getReviews() {
		return reviews;
	}



	public void setReviews(String reviews) {
		this.reviews = reviews;
	}



	public String getRating() {
		return rating;
	}



	public void setRating(String rating) {
		this.rating = rating;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}
	


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}

	


	public List<ProductVariant> getVariants() {
		return variants;
	}


	public void setVariants(List<ProductVariant> variants) {
		this.variants = variants;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", status=" + status + ", comments=" + comments + ", reviews=" + reviews + ", rating="
				+ rating + ", details=" + details + "]";
	}





	
	
	
}
