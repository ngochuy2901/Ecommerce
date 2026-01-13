package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.entity.SellerProfile;
import com.ecommerce.ecommerce_backend.service.SellerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("seller_profile")
@RequiredArgsConstructor
public class SellerProfileController {
    private final SellerProfileService sellerProfileService;

    public ResponseEntity<SellerProfile> signUpForSeller(@RequestHeader("Authorization") String authHeader, @RequestBody SellerProfile sellerProfile) {
        return ResponseEntity.ok(sellerProfileService.save(sellerProfile));
    }
}
