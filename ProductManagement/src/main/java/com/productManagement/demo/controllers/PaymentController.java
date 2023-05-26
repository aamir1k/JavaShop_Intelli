package com.productManagement.demo.controllers;
import com.productManagement.demo.entity.User;
import com.productManagement.demo.service.UserService;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private UserService userService;



    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestParam("token") String token,
                                         @RequestParam("amount") int amount
                                        ) {
        Stripe.apiKey = "sk_test_51MqZeDSDgpDtZZvundvcRhZk6QXIgDlSk0mS5gvOkvsiqlXL7C18WGyBnMmz9ey35NHQZD7Gf1PzwLISy2U3O6X000BPFBnF0v";
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("token", token);
             try {
                 Charge charge = Charge.create(params);
                 return new ResponseEntity<>("Payment successful", HttpStatus.OK);
             } catch (Exception e) {
                 return new ResponseEntity<>("Payment failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
             }
    }


//    @PostMapping
//    public void createPaymentIntent(CreatePayment createPayment) {
//           PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
//                   .setCurrency("INR")
//                   .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
//                   .build();
//           // create payment with orderamount and currency
//           PaymentIntent intent=PaymentIntent.create(createParams);
//           CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret()) ;
//                   return gson.toJson(paymentResponse);


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
          List<User> users = userService.findAll();
          if (users.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(users, HttpStatus.OK);
    }


}

