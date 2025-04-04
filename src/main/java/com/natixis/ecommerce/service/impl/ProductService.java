package com.natixis.ecommerce.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) ;
 List<ProductResponseDTO> getAllProducts() ;
ProductResponseDTO getProductById(Long productId) ;
ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO);
   void deleteProduct(Long productId);
}
