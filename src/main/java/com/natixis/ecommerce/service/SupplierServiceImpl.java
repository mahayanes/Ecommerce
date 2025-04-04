package com.natixis.ecommerce.service;

import com.natixis.ecommerce.dto.request.SupplierRequestDTO;
import com.natixis.ecommerce.dto.response.SupplierResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.SupplierMapper;
import com.natixis.ecommerce.model.Supplier;
import com.natixis.ecommerce.repository.SupplierRepository;
import com.natixis.ecommerce.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public List<SupplierResponseDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return SupplierMapper.INSTANCE.mapToSupplierResponseDTO(suppliers);
    }

    public SupplierResponseDTO getSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier with ID " + supplierId + " not found."));
        return SupplierMapper.INSTANCE.mapToSupplierResponseDTO(supplier);
    }

    public SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO) {
        // Check if supplier with the same name already exists
        if (supplierRepository.existsBySupplierName(supplierRequestDTO.getSupplierName())) {
            throw new AlreadyExistsException("Supplier with name " + supplierRequestDTO.getSupplierName() + " already exists.");
        }

        // Map request DTO to Supplier entity
        Supplier supplier = SupplierMapper.INSTANCE.mapToSupplier(supplierRequestDTO);

        // Save the new supplier
        supplier = supplierRepository.save(supplier);

        // Return response DTO
        return SupplierMapper.INSTANCE.mapToSupplierResponseDTO(supplier);
    }

    public SupplierResponseDTO updateSupplier(Long supplierId, SupplierRequestDTO supplierRequestDTO) {


        // Find the existing supplier throw exception if not found
        Supplier existingSupplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier with id " + supplierId + " does not exist."));

        // Check if supplier with the same name already exists
        if (supplierRepository.existsBySupplierName(supplierRequestDTO.getSupplierName())) {
            throw new AlreadyExistsException("Supplier with name " + supplierRequestDTO.getSupplierName() + " already exists.");
        }
        // Update the supplier details
        existingSupplier.setSupplierName(supplierRequestDTO.getSupplierName());
        existingSupplier.setSupplierAddress(supplierRequestDTO.getSupplierAddress());
        existingSupplier.setSupplierPhone(supplierRequestDTO.getSupplierPhone());

        // Save the updated supplier
        existingSupplier = supplierRepository.save(existingSupplier);

        // Return response DTO
        return SupplierMapper.INSTANCE.mapToSupplierResponseDTO(existingSupplier);
    }

    public void deleteSupplier(Long supplierId) {
        // Find the existing supplier throw exception if not found
        Supplier existingSupplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier with id " + supplierId + " does not exist."));

        // Delete the supplier
        supplierRepository.delete(existingSupplier);


    }
}
