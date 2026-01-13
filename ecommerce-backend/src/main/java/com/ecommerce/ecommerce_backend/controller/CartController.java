package com.ecommerce.ecommerce_backend.controller;


import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.security.JwtUtil;
import com.ecommerce.ecommerce_backend.service.CartItemService;
import com.ecommerce.ecommerce_backend.service.CartService;
import com.ecommerce.ecommerce_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    //add to cart

    //get cart by user
    @GetMapping("get_user_cart")
    public ResponseEntity<Cart> getUserCart(@RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);
        UserDto userDto = userService.getUserProfile(username);
        Cart cart = cartService.getCartByUserId(userDto.getId());
        if (cart == null) {
            cartService.createCart(userDto.getUsername());
        }
        return ResponseEntity.ok(cartService.getCartByUserId(userDto.getId()));
    }

    //add product to cart
//    @PostMapping("add_product_to_cart")
//    public ResponseEntity<CartItem> addProductToCart(@RequestBody CartItem cartItem) {
//        return ResponseEntity.ok(cartItemService.save(cartItem));
//    }
    @PostMapping("add_product_to_cart")
    public ResponseEntity<CartItem> addProductToCart(@RequestHeader("Authorization") String authHeader, @RequestBody Product product) {
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);
        UserDto userDto = userService.getUserProfile(username);
        Cart cart = cartService.getCartByUserId(userDto.getId());
        CartItem cartItem = cartItemService.findByCartAndProduct(cart, product);
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
        } else {
            Integer quantity = cartItem.getQuantity();
            if (quantity == null) {
                cartItem.setQuantity(1);
            } else {
                cartItem.setQuantity(cartItem.getQuantity()+1);
            }
        }
        return ResponseEntity.ok(cartItemService.save(cartItem));
    }

    //
    @GetMapping("get_user_cart_item_list")
    public ResponseEntity<List<CartItem>> getUserCartItemList(@RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);
        UserDto userDto = userService.getUserProfile(username);
        Cart cart = cartService.getCartByUserId(userDto.getId());
        List<CartItem> listCartItems = cartItemService.getCartItemByCart(cart);
        return ResponseEntity.ok(listCartItems);
    }
    //update quantity

    //remove item
}
