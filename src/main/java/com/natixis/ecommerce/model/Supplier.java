package com.natixis.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone;

    @OneToMany
    private Collection<Product> products;


}
