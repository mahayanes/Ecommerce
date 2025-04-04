package com.natixis.ecommerce.service.impl;

import com.natixis.ecommerce.dto.request.CartItemRequestDTO;
import com.natixis.ecommerce.dto.response.CartItemResponseDTO;
import com.natixis.ecommerce.dto.response.CartResponseDTO;
import com.natixis.ecommerce.model.CartItem;

public interface CartItemService {
    CartItemResponseDTO addProductToCartItem(CartItemRequestDTO cartItemRequestDTO);
    CartItemResponseDTO updateCartItemQuantity(CartItemRequestDTO cartItemRequestDTO); // More specific
    void removeItemFromCart(Long cartItemId); // Changed return type
    CartItemResponseDTO getCartItem(Long cartItemId);
}
