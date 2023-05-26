package com.productManagement.demo.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Embeddable
@Entity
@Table(name = "payment")
public class PaymentIntent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String method;
    private Double amount;
    private String status;
    private Date created;
    private String currency;


}
