package com.natixis.ecommerce.repository;

import com.natixis.ecommerce.model.Cart;
import com.natixis.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    // Custom method to find carts by user ID
    Cart findByUser(User userId);


}
