package com.natixis.ecommerce.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryResponseDTO {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
}
