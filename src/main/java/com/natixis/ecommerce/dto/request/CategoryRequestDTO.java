package com.natixis.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryRequestDTO {

    @NotNull
    private String categoryName;

    private String categoryDescription;
}
