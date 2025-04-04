package com.natixis.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@ToString(exclude = {"user", "items"})
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDateTime;
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    Collection<CartItem> items = new ArrayList<>();

}
