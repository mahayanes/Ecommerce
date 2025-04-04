package com.natixis.ecommerce.mapper;

import com.natixis.ecommerce.dto.request.CategoryRequestDTO;
import com.natixis.ecommerce.dto.response.CategoryResponseDTO;
import com.natixis.ecommerce.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {


    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponseDTO mapToCategoryResponseDTO(Category category);

     List<CategoryResponseDTO> mapToCategoryResponseDTO(List<Category> categories);

     Category mapToCategory(CategoryRequestDTO categoryRequestDTO) ;
}
