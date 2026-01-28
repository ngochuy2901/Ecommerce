package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.SellerProfile;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.SellerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerProfileService {
    private final SellerProfileRepository sellerProfileRepository;

    public SellerProfile save(SellerProfile sellerProfile) {
        return sellerProfileRepository.save(sellerProfile);
    }
    public Optional<SellerProfile> findByUserId(Long userId) {
        return sellerProfileRepository.findByUser_Id(userId);
    }
    public Optional<SellerProfile> getSellerProfileByUserId(Long userId) {
        return sellerProfileRepository.findByUser_Id(userId);
    }

}
