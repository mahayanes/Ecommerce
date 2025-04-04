package com.natixis.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class UserRequestDTO {

    @NotNull
    @NotBlank(message = "Username is required")
    private String username;
    private String name;
    @Email(message = "Email should be valid",
            regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;
    private String password;
    private String role; // Assuming role is represented as a String (e.g., "ADMIN", "USER")
    private Date creationDateTime;
}
