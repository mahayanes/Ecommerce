package com.natixis.ecommerce.controller;

import com.natixis.ecommerce.dto.request.SupplierRequestDTO;
import com.natixis.ecommerce.dto.response.SupplierResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController (SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierResponseDTO> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSupplier(@RequestBody SupplierRequestDTO supplierRequestDTO) {
        try{
            SupplierResponseDTO supplier = supplierService.createSupplier(supplierRequestDTO);
            return new ResponseEntity<>(supplier, HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{supplierId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSupplier(@PathVariable Long supplierId, @RequestBody SupplierRequestDTO supplierRequestDTO) {
        try{
            SupplierResponseDTO supplier = supplierService.updateSupplier(supplierId, supplierRequestDTO);
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (NotFoundException | AlreadyExistsException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{supplierId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long supplierId) {
        try{
            supplierService.deleteSupplier(supplierId);
            return  ResponseEntity.status(HttpStatus.OK).body("Supplier deleted successfully"); // 200 OK if supplier deleted
        } catch (NotFoundException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to get a category by ID
    @GetMapping("/{supplierId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getSupplierById(@PathVariable Long supplierId) {
        try {
            SupplierResponseDTO supplierResponseDTO = supplierService.getSupplierById(supplierId);
            return new ResponseEntity<>(supplierResponseDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
