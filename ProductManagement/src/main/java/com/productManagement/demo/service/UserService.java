package com.productManagement.demo.service;

import java.util.List;
import java.util.Optional;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.User;

public interface UserService {

	List<User> findAll();


	Optional<User> findById(Integer id);


	List<User> findDistinctByPhone(String phone);


	List<User> getUserDetails(String username);


	User save(User user);


	User getUserById(Integer id);


	User saveProduct(User user);


	List<User> findDistinctByEmail(String email);


	User findByLogin(String issuer);


	User authenticate(User credentialsDto);


	


	

}