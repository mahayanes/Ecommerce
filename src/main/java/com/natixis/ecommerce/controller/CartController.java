package com.natixis.ecommerce.controller;

import com.natixis.ecommerce.dto.request.CartItemRequestDTO;
import com.natixis.ecommerce.dto.request.CartRequestDTO;
import com.natixis.ecommerce.dto.response.CartResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.service.impl.CartService;
import com.natixis.ecommerce.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Endpoint to create a new cart
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCart() {
        try {
            CartResponseDTO cartResponseDTO = cartService.createCart();
            return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/{cartId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCartById(@PathVariable Long cartId) {
        try {
            CartResponseDTO cartResponseDTO = cartService.getCartById(cartId);
            return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCartsByUserId() {
        try {
            CartResponseDTO response = cartService.getCartByUserId();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{cartId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateCart(@PathVariable Long cartId,
                                        @RequestBody CartRequestDTO cartRequestDTO) {
        try {
            CartResponseDTO updatedCart = cartService.updateCart();
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @DeleteMapping("/{cartId}/items")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> emptyCart(@PathVariable Long cartId) {
        try {
            cartService.emptyCart();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
