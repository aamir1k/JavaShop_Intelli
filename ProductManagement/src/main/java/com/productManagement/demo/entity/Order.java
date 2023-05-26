package com.productManagement.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.productManagement.demo.payment.PaymentIntent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="total")
    private double total;
    
    @Column(name="order_name")
    private String orderName;
    
    @Column(name="customer_id")
    private Long customerId;
    
    @Column(name="payment_mode")
    private String paymentMode;
    
    @Column(name="delivery_charges")
    private Float deliveryCharges;
    
    @Column(name="order_status")
    private String orderStatus;
    
    @Column(name="order_date")
    private String orderDate;
    
    @Column(name="delivery_date")
    private Date deliveryDate;
    
    @Column(name="collection_time")
    private Date collectionTime;
    
    @Column(name="delivering_to")
    private String deliveringTo;
    
    @Column(name="transaction")
    private String transaction;
    
    @Column(name="total_DISCOUNT")
    private Float totalDiscount;
    
    @Column(name="total_MRP")
    private Float totalMrpAmount;
    
    @Column(name="total_payable_amount")
    private Float totalPayableAmount;
    
    @Column(name="final_Amount")
    private Float finalAmount;


     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "user_id")
     @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	  private User user;


    @ManyToMany
    private List<Product> product;

    @OneToOne
    private PaymentIntent paymentIntent;

}
