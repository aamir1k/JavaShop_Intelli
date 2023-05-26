package com.productManagement.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="location")
	private String location;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")
	private Double longitude;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="email")
	private String email;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="customer_type")
	private String customerType;
	
	@Column(name="status")
	private String status;
	
	@Column(name="cart")
	private String cart;
	
	@Column(name="wish_list")
	private String wishList;
	
	@Column(name="userLogin_id")
	private Integer userLoginId;
	
	@Column(name="recent_searches")
	private String recentSearches;
	
	@Column(name="zone")
	private String zone;
	
	@Column(name="verified")
	private Boolean verified;
	
	@Column(name="created_on")
	private Date createdon;
	
	
	public Customers() {}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCart() {
		return cart;
	}


	public void setCart(String cart) {
		this.cart = cart;
	}


	public String getWishList() {
		return wishList;
	}


	public void setWishList(String wishList) {
		this.wishList = wishList;
	}


	public Integer getUserLoginId() {
		return userLoginId;
	}


	public void setUserLoginId(Integer userLoginId) {
		this.userLoginId = userLoginId;
	}


	public String getRecentSearches() {
		return recentSearches;
	}


	public void setRecentSearches(String recentSearches) {
		this.recentSearches = recentSearches;
	}


	public String getZone() {
		return zone;
	}


	public void setZone(String zone) {
		this.zone = zone;
	}


	public Boolean getVerified() {
		return verified;
	}


	public void setVerified(Boolean verified) {
		this.verified = verified;
	}


	public Date getCreatedon() {
		return createdon;
	}


	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	
	
	

}
