package com.productManagement.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Customers;
import com.productManagement.demo.repository.CustomerRepository;
import com.productManagement.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository cs;

    @Override
    public Customers addCustomer(Customers customer) {
        return cs.save(customer);
    }



}
