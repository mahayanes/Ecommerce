package com.natixis.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column (unique = true)
    private String username;
    private String name;
    private String email;
    private String password;
    private Role role;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;
}
