package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.SellerProfile;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.security.JwtUtil;
import com.ecommerce.ecommerce_backend.service.SellerProfileService;
import com.ecommerce.ecommerce_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("seller_profile")
@RequiredArgsConstructor
public class SellerProfileController {
    private final SellerProfileService sellerProfileService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    @PostMapping("sign_up_for_seller")
    public ResponseEntity<SellerProfile> signUpForSeller(@RequestHeader("Authorization") String authHeader, @RequestBody SellerProfile sellerProfile) {
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);
        Optional<User> user = userService.getUserByUserName(username);
        if (user.isPresent()) {
            sellerProfile.setUser(user.get());
            sellerProfile.setStatus(SellerProfile.SellerStatus.PENDING);
        } else {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(sellerProfileService.save(sellerProfile));
    }
}
