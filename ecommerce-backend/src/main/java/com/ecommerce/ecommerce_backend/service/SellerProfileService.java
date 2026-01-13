package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.SellerProfile;
import com.ecommerce.ecommerce_backend.repository.SellerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerProfileService {
    private final SellerProfileRepository sellerProfileRepository;

    public SellerProfile save(SellerProfile sellerProfile) {
        return sellerProfileRepository.save(sellerProfile);
    }
}
