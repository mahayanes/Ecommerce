package com.natixis.ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="cart_Item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    @ManyToOne
    //@JoinColumn(name = "cart_id")
    private Cart cart;
    @OneToOne
    private Product product;
    private int quantity;

}
