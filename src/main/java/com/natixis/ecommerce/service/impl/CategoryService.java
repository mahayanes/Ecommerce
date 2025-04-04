package com.natixis.ecommerce.service.impl;

import com.natixis.ecommerce.dto.request.CategoryRequestDTO;
import com.natixis.ecommerce.dto.response.CategoryResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.CategoryMapper;
import com.natixis.ecommerce.model.Category;
import com.natixis.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {

     CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
     List<CategoryResponseDTO> getAllCategories();
     CategoryResponseDTO getCategoryById(Long categoryId);

     CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO);

    // Method to delete a category by ID
     void deleteCategory(Long categoryId) ;
}
