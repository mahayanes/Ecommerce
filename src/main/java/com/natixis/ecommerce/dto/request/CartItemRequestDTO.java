package com.natixis.ecommerce.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@Getter
@Setter
public class CartItemRequestDTO {
    @NotNull
    private Long productId;   // ID of the product being added to the cart
    private Integer quantity; // Quantity of the product being added to the cart

    // Optionally, you can include other fields like custom price or custom notes
}
