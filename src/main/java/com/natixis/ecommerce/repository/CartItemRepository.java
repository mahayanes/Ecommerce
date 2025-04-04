package com.natixis.ecommerce.repository;

import com.natixis.ecommerce.model.Cart;
import com.natixis.ecommerce.model.CartItem;
import com.natixis.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product); // Custom method to find a cartItem by cart and product
}
