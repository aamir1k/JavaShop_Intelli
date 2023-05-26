package com.productManagement.demo.repository;

import com.productManagement.demo.entity.Cart;
import com.productManagement.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
   // void deleteByUser(User user);

  //  Cart findByUser(User user);

    //@Query("SELECT c FROM Cart c WHERE c.user.id = :userId")


    @Query("SELECT c FROM Cart c WHERE c.orderby = :userId")
    Cart findByOrderby(@Param("userId") String userId);

    @Query("SELECT c FROM Cart c WHERE c.user = :user")
    Cart findByUser(@Param("user") User user);

   //Cart findByUser(User user);

    // Cart findByUserId(Long userId);
}
