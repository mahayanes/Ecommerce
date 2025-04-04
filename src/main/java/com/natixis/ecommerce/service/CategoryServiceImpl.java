package com.natixis.ecommerce.service;

import com.natixis.ecommerce.dto.request.CategoryRequestDTO;
import com.natixis.ecommerce.dto.response.CategoryResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.CategoryMapper;
import com.natixis.ecommerce.model.Category;
import com.natixis.ecommerce.repository.CategoryRepository;
import com.natixis.ecommerce.repository.ProductRepository;
import com.natixis.ecommerce.repository.SupplierRepository;
import com.natixis.ecommerce.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Method to create a new category
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        // Check if category with the same name already exists
        if (categoryRepository.existsByCategoryName(categoryRequestDTO.getCategoryName())) {
            throw new AlreadyExistsException("Category with name " + categoryRequestDTO.getCategoryName() + " already exists.");
        }

        // Map request DTO to Category entity
        Category category = CategoryMapper.INSTANCE.mapToCategory(categoryRequestDTO);

        // Save the new category
        category = categoryRepository.save(category);

        // Return response DTO
        return CategoryMapper.INSTANCE.mapToCategoryResponseDTO(category);
    }

    // Method to get all categories
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryMapper.INSTANCE.mapToCategoryResponseDTO(categories);
    }

    // Method to get category by ID
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with ID " + categoryId + " not found."));
        return CategoryMapper.INSTANCE.mapToCategoryResponseDTO(category);
    }

    // Method to update an existing category
    public CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        // Check if the category exists
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with ID " + categoryId + " not found."));


        // Check if category with the same name already exists
        if (categoryRepository.existsByCategoryName(categoryRequestDTO.getCategoryName())) {
            throw new AlreadyExistsException("Category with name " + categoryRequestDTO.getCategoryName() + " already exists.");
        }


        // Update the existing category
        existingCategory.setCategoryName(categoryRequestDTO.getCategoryName());
        existingCategory.setCategoryDescription(categoryRequestDTO.getCategoryDescription());
        existingCategory.setProducts(new ArrayList<>());
        // Save updated category
        Category updatedCategory = categoryRepository.save(existingCategory);

        // Return the updated category as a response DTO
        return CategoryMapper.INSTANCE.mapToCategoryResponseDTO(updatedCategory);
    }

    // Method to delete a category by ID
    public void deleteCategory(Long categoryId) {
        // Check if category exists
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with ID " + categoryId + " not found."));

        // Delete the category
        categoryRepository.delete(category);
    }
}
