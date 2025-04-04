package com.natixis.ecommerce.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    private String productName;
    private String description;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = false, message = "Price must be greater than 0")
    private double price;
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private int stock;
    private Date creationDateTime;
    private Date modificationDateTime;
    @NotNull(message = "Category ID is required")
    private Long categoryId; // Assuming Category is related by ID
    @NotNull(message = "Supplier ID is required")
    private Long supplierId; // Assuming Supplier is related by ID
}