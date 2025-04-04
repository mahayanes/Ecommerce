package com.natixis.ecommerce.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SupplierResponseDTO {

    private Long supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone;
}
