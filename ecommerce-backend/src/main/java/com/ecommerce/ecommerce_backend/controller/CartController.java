package com.ecommerce.ecommerce_backend.controller;


import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.security.JwtUtil;
import com.ecommerce.ecommerce_backend.service.CartService;
import com.ecommerce.ecommerce_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
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

    //update quantity

    //remove item
}
