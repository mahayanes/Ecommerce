package com.natixis.ecommerce.service;

import com.natixis.ecommerce.dto.request.ProductRequestDTO;
import com.natixis.ecommerce.dto.response.ProductResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.MissingFieldException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.ProductMapper;
import com.natixis.ecommerce.model.Category;
import com.natixis.ecommerce.model.Product;
import com.natixis.ecommerce.model.Supplier;
import com.natixis.ecommerce.repository.CategoryRepository;
import com.natixis.ecommerce.repository.ProductRepository;
import com.natixis.ecommerce.repository.SupplierRepository;
import com.natixis.ecommerce.service.impl.ProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        // Check if product with the same name already exists
        if (productRepository.existsByProductName(productRequestDTO.getProductName())) {
            throw new AlreadyExistsException("Product with name " + productRequestDTO.getProductName() + " already exists.");
        }

        // Check if category and supplier exist
        if (productRequestDTO.getCategoryId() == null) {
            throw new MissingFieldException("Category ID must be provided.");
        } else if (productRequestDTO.getSupplierId() == null) {
            throw new MissingFieldException("Supplier ID must be provided.");
        }


        // Map request DTO to Product entity
        Product product = ProductMapper.INSTANCE.mapToProduct(productRequestDTO);
        product.setCategory(categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category with ID " + productRequestDTO.getCategoryId() + " not found.")));
        product.setSupplier(supplierRepository.findById(productRequestDTO.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier with ID " + productRequestDTO.getSupplierId() + " not found.")));
        product.setCreationDateTime(new Date());
        // Save the new product
        product = productRepository.save(product);

        // Return response DTO
        return ProductMapper.INSTANCE.mapToProductResponseDTO(product);
    }

    // Method to get all categories
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.INSTANCE.mapToProductResponseDTO(products);
    }

    // Method to get product by ID
    public ProductResponseDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with ID " + productId + " not found."));


        return ProductMapper.INSTANCE.mapToProductResponseDTO(product);
    }

    // Method to update an existing product
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        // Check if the product exists
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with ID " + productId + " not found."));

// Check if product with the same name already exists
        if (productRepository.existsByProductName(productRequestDTO.getProductName())) {
            throw new AlreadyExistsException("Product with name " + productRequestDTO.getProductName() + " already exists.");
        }

        // Check if category and supplier exist
        if (productRequestDTO.getCategoryId() == null) {
            throw new MissingFieldException("Category ID must be provided.");
        } else if (productRequestDTO.getSupplierId() == null) {
            throw new MissingFieldException("Supplier ID must be provided.");
        }

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category with ID " + productRequestDTO.getCategoryId() + " not found."));
        Supplier supplier = supplierRepository.findById(productRequestDTO.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier with ID " + productRequestDTO.getSupplierId() + " not found."));

        Product updatedProduct = ProductMapper.INSTANCE.mapToProduct(productRequestDTO);
        updatedProduct.setCategory(category);
        updatedProduct.setSupplier(supplier);
        updatedProduct.setCreationDateTime(existingProduct.getCreationDateTime());
        updatedProduct.setModificationDateTime(new Date());


        // Save updated product
        updatedProduct = productRepository.save(updatedProduct);

        // Return the updated product as a response DTO
        return ProductMapper.INSTANCE.mapToProductResponseDTO(updatedProduct);
    }

    // Method to delete a product by ID
    public void deleteProduct(Long productId) {
        // Check if product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with ID " + productId + " not found."));

        // Delete the product
        productRepository.delete(product);
    }
}
