package com.natixis.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;


    private String productName;

    private String description;

    private BigDecimal price;
    private int stock;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDateTime;

    @ManyToOne
    @NotNull(message = "Category is required")
    private Category category;
    @ManyToOne
    @NotNull(message = "Supplier is required")
    private Supplier supplier;

}
