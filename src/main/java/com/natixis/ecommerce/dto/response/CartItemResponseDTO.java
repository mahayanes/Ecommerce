package com.natixis.ecommerce.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CartItemResponseDTO {

    private Long cartItemId; // ID of the cart item
    private Integer quantity; // Quantity of this item in the cart
    private BigDecimal price; // Price of this item
    private ProductResponseDTO product; // Name of the product

}
