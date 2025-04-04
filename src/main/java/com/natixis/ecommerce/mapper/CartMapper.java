package com.natixis.ecommerce.mapper;

import com.natixis.ecommerce.dto.request.CartRequestDTO;
import com.natixis.ecommerce.dto.response.CartResponseDTO;
import com.natixis.ecommerce.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartResponseDTO mapToCartResponseDTO(Cart cart) ;



    List<CartResponseDTO> mapToCartResponseDTO(List<Cart> categories) ;

    Cart mapToCart(CartRequestDTO cartRequestDTO);
}
