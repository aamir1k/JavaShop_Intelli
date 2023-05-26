package com.productManagement.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long storeOrderId;
	private Double totalMrp;
	private Double deliveryCharges;
	private Double totalOrderPrice;
	private Double totalDiscount;
	private Integer itemNumber;
	private Integer quantity;
	private Long productId;
	private String displayName;
	private LocalDateTime estimatedDeliveryTime;
	private String itemUnit;
	private Integer discount;
	private Integer orderQuantity;
	private Integer storePrice;
	private String itemDiscription;
	private Long categoryId;
	private Boolean cancelled;
	private Boolean returned;
	private Double mrp;
	private String orderDetailsStatus;
	private Boolean notDeliverable;
	
	
	
	public OrderDetails() {}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getStoreOrderId() {
		return storeOrderId;
	}



	public void setStoreOrderId(Long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}



	public Double getTotalMrp() {
		return totalMrp;
	}



	public void setTotalMrp(Double totalMrp) {
		this.totalMrp = totalMrp;
	}



	public Double getDeliveryCharges() {
		return deliveryCharges;
	}



	public void setDeliveryCharges(Double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}



	public Double getTotalOrderPrice() {
		return totalOrderPrice;
	}



	public void setTotalOrderPrice(Double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}



	public Double getTotalDiscount() {
		return totalDiscount;
	}



	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}



	public Integer getItemNumber() {
		return itemNumber;
	}



	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public String getDisplayName() {
		return displayName;
	}



	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	public LocalDateTime getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}



	public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}



	public String getItemUnit() {
		return itemUnit;
	}



	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}



	public Integer getDiscount() {
		return discount;
	}



	public void setDiscount(Integer discount) {
		this.discount = discount;
	}



	public Integer getOrderQuantity() {
		return orderQuantity;
	}



	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}



	public Integer getStorePrice() {
		return storePrice;
	}



	public void setStorePrice(Integer storePrice) {
		this.storePrice = storePrice;
	}



	public String getItemDiscription() {
		return itemDiscription;
	}



	public void setItemDiscription(String itemDiscription) {
		this.itemDiscription = itemDiscription;
	}



	public Long getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}



	public Boolean getCancelled() {
		return cancelled;
	}



	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}



	public Boolean getReturned() {
		return returned;
	}



	public void setReturned(Boolean returned) {
		this.returned = returned;
	}



	public Double getMrp() {
		return mrp;
	}



	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}



	public String getOrderDetailsStatus() {
		return orderDetailsStatus;
	}



	public void setOrderDetailsStatus(String orderDetailsStatus) {
		this.orderDetailsStatus = orderDetailsStatus;
	}



	public Boolean getNotDeliverable() {
		return notDeliverable;
	}



	public void setNotDeliverable(Boolean notDeliverable) {
		this.notDeliverable = notDeliverable;
	}
	
	

}
