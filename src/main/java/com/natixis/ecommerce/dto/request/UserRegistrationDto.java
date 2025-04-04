package com.natixis.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRegistrationDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;

}