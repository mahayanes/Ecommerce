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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    @OneToMany
    private Collection<Product> products;

}
