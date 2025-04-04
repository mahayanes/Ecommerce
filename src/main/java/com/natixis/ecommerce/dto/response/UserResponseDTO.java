package com.natixis.ecommerce.dto.response;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long userId;
    private String username;
    private String name;
    private String email;
    private String role; // Assuming role is represented as a String in the response
    private Date creationDateTime;
}