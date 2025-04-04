package com.natixis.ecommerce.service;

import com.natixis.ecommerce.dto.response.CartResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.CartMapper;
import com.natixis.ecommerce.model.Cart;
import com.natixis.ecommerce.model.CartStatus;
import com.natixis.ecommerce.model.User;
import com.natixis.ecommerce.repository.CartItemRepository;
import com.natixis.ecommerce.repository.CartRepository;
import com.natixis.ecommerce.service.impl.CartService;
import com.natixis.ecommerce.service.impl.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            UserService userService) {  // Add userService parameter
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;  // Now properly initialized
    }


    // Method to create a new cart
    public CartResponseDTO createCart() {
        User user = userService.getCurrentUser();
        Cart cart = null;
        if (user.getCart() != null) {
            throw new AlreadyExistsException("User with name " + user.getUsername() + " already has cart.");
        } else {
            cart = new Cart();
            cart.setCreationDateTime(new Date());
            cart.setStatus(CartStatus.ACTIVE);
            cart.setUser(user);

            cart = cartRepository.save(cart);
        }

        return CartMapper.INSTANCE.mapToCartResponseDTO(cart);
    }


    // Method to get cart by ID
    public CartResponseDTO getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart with ID " + cartId + " not found."));
        User user = userService.getCurrentUser();
        if (user.getCart() != null) {
            if (!cart.getCartId().equals(user.getCart().getCartId())) {
                throw new AlreadyExistsException("Cart with id " + cartId + " is not related to this user wwith name ." + user.getUsername());
            }
        } else {
            throw new NotFoundException("User with name " + user.getUsername() + " does not have a cart.");
        }
        return CartMapper.INSTANCE.mapToCartResponseDTO(cart);
    }

    //getCartsByUserId
    public CartResponseDTO getCartByUserId() {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new NotFoundException("User is not authenticated.");
        }
        if (user.getCart() == null) {
            throw new NotFoundException("User with name " + user.getUsername() + " does not have a cart.");
        }

        return CartMapper.INSTANCE.mapToCartResponseDTO(user.getCart());
    }

    public CartResponseDTO updateCart() {
        User user = userService.getCurrentUser();
        if (user.getCart() == null) {
            throw new AlreadyExistsException("User with name " + user.getUsername() + " does not have a cart.");
        }

        Cart existingCart = user.getCart();
        existingCart.setModificationDateTime(new Date());
        // Save updated cart
        Cart updatedCart = cartRepository.save(existingCart);

        // Return the updated cart as a response DTO
        return CartMapper.INSTANCE.mapToCartResponseDTO(updatedCart);
    }

    @Transactional
    public void emptyCart() {
        User user = userService.getCurrentUser();
        if (user.getCart() == null) {
            throw new AlreadyExistsException("User with name " + user.getUsername() + " does not have a cart.");
        }
        Cart cart = user.getCart();

        cart.getItems().stream().forEach(cartItem -> {
            cartItemRepository.delete(cartItem);
        });
        cart.getItems().clear();
        cart.setModificationDateTime(new Date());
        cartRepository.save(cart);
    }

}
