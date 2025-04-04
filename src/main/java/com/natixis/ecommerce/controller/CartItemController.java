package com.natixis.ecommerce.controller;

import com.natixis.ecommerce.dto.request.CartItemRequestDTO;
import com.natixis.ecommerce.dto.response.CartItemResponseDTO;
import com.natixis.ecommerce.dto.response.CartResponseDTO;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.model.CartItem;

import com.natixis.ecommerce.service.impl.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartItems")
public class CartItemController {
    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCartItemById(@PathVariable Long cartItem) {
        try {
            CartItemResponseDTO cartItemResponseDTO = cartItemService.getCartItem(cartItem);
            return new ResponseEntity<>(cartItemResponseDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addProductToCart( @RequestBody CartItemRequestDTO cartItemRequest) {
        try {
            CartItemResponseDTO cartItem = cartItemService.addProductToCartItem(
                    cartItemRequest
            );
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateCartItemQuantity(@RequestBody CartItemRequestDTO cartItemRequest){
        try {
            CartItemResponseDTO updatedCartItem = cartItemService.updateCartItemQuantity(
                    cartItemRequest);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteCartItem(
            @RequestBody Long cartItemId){
        try {
            cartItemService.removeItemFromCart(cartItemId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
