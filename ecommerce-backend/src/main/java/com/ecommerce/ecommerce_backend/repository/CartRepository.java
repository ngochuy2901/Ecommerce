package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartByUserId(Long userId);
//    Cart addItemToCart(Long userId, Long productId, int quantity);
//    Cart updateItemQuantity(Long userId, Long productId, int quantity);
//    void removeItem(Long userId, Long productId);
//    void clearCart(Long userId);
    Optional<Cart> findByUserId(Long userId);
}
