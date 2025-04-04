package com.natixis.ecommerce.service.impl;

import com.natixis.ecommerce.dto.request.SupplierRequestDTO;
import com.natixis.ecommerce.dto.response.SupplierResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.SupplierMapper;
import com.natixis.ecommerce.model.Supplier;
import com.natixis.ecommerce.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SupplierService {
    List<SupplierResponseDTO> getAllSuppliers();
    SupplierResponseDTO getSupplierById(Long supplierId);
    SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO);
    SupplierResponseDTO updateSupplier(Long supplierId, SupplierRequestDTO supplierRequestDTO);

    void deleteSupplier(Long supplierId);
}
