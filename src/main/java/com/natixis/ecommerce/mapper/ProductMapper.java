package com.natixis.ecommerce.mapper;

import com.natixis.ecommerce.dto.request.ProductRequestDTO;
import com.natixis.ecommerce.dto.response.ProductResponseDTO;
import com.natixis.ecommerce.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    // Mapping entity to DTO
     ProductResponseDTO mapToProductResponseDTO(Product product) ;

     List<ProductResponseDTO> mapToProductResponseDTO(List<Product> categories) ;



     Product mapToProduct(ProductRequestDTO productRequestDTO) ;
}
