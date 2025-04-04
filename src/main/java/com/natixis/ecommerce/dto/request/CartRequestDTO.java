package com.natixis.ecommerce.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class CartRequestDTO {

    @NotNull
    private Long userId; // Assuming you will pass the user ID





}
