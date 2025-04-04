package com.natixis.ecommerce.repository;

import com.natixis.ecommerce.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsBySupplierName(String supplierName);

}
