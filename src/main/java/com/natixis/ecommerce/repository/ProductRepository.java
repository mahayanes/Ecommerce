package com.natixis.ecommerce.repository;

import com.natixis.ecommerce.model.Category;
import com.natixis.ecommerce.model.Product;
import com.natixis.ecommerce.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductName(String productName);

    Product findByCategory(Category category);

    Product findBySupplier(Supplier supplier);

}
