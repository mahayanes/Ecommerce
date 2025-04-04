package com.natixis.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SupplierRequestDTO {


    @NotNull
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone;
}
