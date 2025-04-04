package com.natixis.ecommerce.mapper;

import com.natixis.ecommerce.dto.request.CartItemRequestDTO;
import com.natixis.ecommerce.dto.response.CartItemResponseDTO;
import com.natixis.ecommerce.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartItemMapper {


    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    // Mapping entity to DTO
     CartItemResponseDTO mapToCartItemResponseDTO(CartItem cartItem) ;

     List<CartItemResponseDTO> mapToCartItemsResponseDTO(Collection<CartItem> cartItems);



     CartItem mapToCartItem(CartItemRequestDTO cartItemRequestDTO);
}
