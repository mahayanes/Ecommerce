package com.natixis.ecommerce.mapper;

import com.natixis.ecommerce.dto.request.SupplierRequestDTO;
import com.natixis.ecommerce.dto.response.SupplierResponseDTO;
import com.natixis.ecommerce.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {

    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

     SupplierResponseDTO mapToSupplierResponseDTO(Supplier supplier);
     List<SupplierResponseDTO> mapToSupplierResponseDTO(List<Supplier> suppliers) ;
     Supplier mapToSupplier(SupplierRequestDTO supplierRequestDTO) ;
}
