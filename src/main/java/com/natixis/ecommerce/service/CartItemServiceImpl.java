package com.natixis.ecommerce.service;

import com.natixis.ecommerce.dto.request.CartItemRequestDTO;
import com.natixis.ecommerce.dto.response.CartItemResponseDTO;
import com.natixis.ecommerce.dto.response.CartResponseDTO;
import com.natixis.ecommerce.dto.response.ProductResponseDTO;
import com.natixis.ecommerce.exceptions.AlreadyExistsException;
import com.natixis.ecommerce.exceptions.NotFoundException;
import com.natixis.ecommerce.mapper.CartItemMapper;
import com.natixis.ecommerce.mapper.ProductMapper;
import com.natixis.ecommerce.model.Cart;
import com.natixis.ecommerce.model.CartItem;
import com.natixis.ecommerce.model.Product;
import com.natixis.ecommerce.model.User;
import com.natixis.ecommerce.repository.CartItemRepository;
import com.natixis.ecommerce.repository.CartRepository;
import com.natixis.ecommerce.repository.ProductRepository;
import com.natixis.ecommerce.service.impl.CartItemService;
import com.natixis.ecommerce.service.impl.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {


    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartItemServiceImpl(
            CartItemRepository cartItemRepository,
            CartRepository cartRepository,
            ProductRepository productRepository,
            UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;  //
    }

    public CartItemResponseDTO addProductToCartItem(CartItemRequestDTO cartItemRequestDTO) {
        User user = userService.getCurrentUser();
        if (user.getCart() == null) {
            throw new AlreadyExistsException("User with name " + user.getUsername() + " does not have a cart.");
        }
        Cart cart = user.getCart();
        Product product = productRepository.findById(cartItemRequestDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
        ProductResponseDTO productResponseDTO = null;
        CartItemResponseDTO cartItemResponseDTO = null;
        CartResponseDTO cartResponseDTO = null;
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequestDTO.getQuantity());
            if (existingCartItem.isPresent()) {
                productResponseDTO = ProductMapper.INSTANCE.mapToProductResponseDTO(existingCartItem.get().getProduct());
            }

            cartItemRepository.save(cartItem);
            cartItemResponseDTO = CartItemMapper.INSTANCE.mapToCartItemResponseDTO(cartItem);
            cartItemResponseDTO.setProduct(productResponseDTO);
            return cartItemResponseDTO;
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(cartItemRequestDTO.getQuantity());
            if (newCartItem != null) {
                productResponseDTO = ProductMapper.INSTANCE.mapToProductResponseDTO(newCartItem.getProduct());
            }
            cartItemRepository.save(newCartItem);
            cartItemResponseDTO = CartItemMapper.INSTANCE.mapToCartItemResponseDTO(newCartItem);
            cartItemResponseDTO.setProduct(productResponseDTO);
            return cartItemResponseDTO;
        }
    }

    @Override
    public CartItemResponseDTO updateCartItemQuantity(CartItemRequestDTO cartItemRequestDTO) {
        User user = userService.getCurrentUser();
        if (user.getCart() == null) {
            throw new AlreadyExistsException("User with name " + user.getUsername() + " does not have a cart.");
        }
        Cart cart = user.getCart();
        Product product = productRepository.findById(cartItemRequestDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequestDTO.getQuantity());
            cartItemRepository.save(cartItem);
            return CartItemMapper.INSTANCE.mapToCartItemResponseDTO(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(cartItemRequestDTO.getQuantity());
            cartItemRepository.save(newCartItem);
            return CartItemMapper.INSTANCE.mapToCartItemResponseDTO(newCartItem);
        }
    }

    public void removeItemFromCart(Long cartItemId) {
        User user = userService.getCurrentUser();
        if (user.getCart() == null) {
            throw new AlreadyExistsException("User with name " + user.getUsername() + " does not have a cart.");
        }
        Cart cart = user.getCart();

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cart item not found"));

        cart.getItems().remove(cartItem);
        cart.setModificationDateTime(new Date());
        Cart updateCart = cartRepository.save(cart);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItemResponseDTO getCartItem(Long cartItemId) {
        User user = userService.getCurrentUser();
        if (user.getCart() == null) {
            throw new AlreadyExistsException("User with name " + user.getUsername() + " does not have a cart.");
        }
        Cart cart = user.getCart();

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cart item not found"));

        return CartItemMapper.INSTANCE.mapToCartItemResponseDTO(cartItem);
    }

}
