package com.natixis.ecommerce.dto.response;

import com.natixis.ecommerce.model.CartStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class CartResponseDTO {

    private Long cartId;
    private Date creationDateTime;
    private Date modificationDateTime;
    private CartStatus status;
    private Long userId;
    private List<CartItemResponseDTO> items;

}

