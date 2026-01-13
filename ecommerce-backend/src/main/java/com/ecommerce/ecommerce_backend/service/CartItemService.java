package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItemByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public CartItem findByCartAndProduct(Cart cart, Product product) {
        return cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElse(null);
    }
}
