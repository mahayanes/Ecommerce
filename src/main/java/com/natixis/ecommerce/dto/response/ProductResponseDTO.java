package com.natixis.ecommerce.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ProductResponseDTO {

    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private Date creationDateTime;
    private Date modificationDateTime;
    private CategoryResponseDTO category; // Assuming you have a CategoryResponseDTO
    private SupplierResponseDTO supplier; // Assuming you have a SupplierResponseDTO
}