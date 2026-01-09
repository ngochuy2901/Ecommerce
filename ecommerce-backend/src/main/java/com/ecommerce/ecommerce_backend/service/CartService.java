package com.ecommerce.ecommerce_backend.service;


import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.CartRepository;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    public Cart createCart(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Cart newCart = new Cart();
        newCart.setUser(user.get());
        return cartRepository.save(newCart);
    }

    //get cart by user id
    public Cart getCartByUserId(Long userId) {
        return cartRepository.getCartByUserId(userId);
    }
}
