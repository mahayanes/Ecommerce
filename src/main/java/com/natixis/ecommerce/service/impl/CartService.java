package com.natixis.ecommerce.service.impl;

import com.natixis.ecommerce.dto.request.CartItemRequestDTO;
import com.natixis.ecommerce.dto.response.CartResponseDTO;


public interface CartService {
     CartResponseDTO createCart() ;
     CartResponseDTO getCartById(Long cartId);
     CartResponseDTO getCartByUserId() ;
     CartResponseDTO updateCart() ;
     void emptyCart() ;

}
