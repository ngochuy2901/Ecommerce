package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.Brand;
import com.ecommerce.ecommerce_backend.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    //add new brand
    public Brand saveNewBrand(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    //get all brands
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    //get brand by id


    //get brands by name

    //update brand

    //delete brand
}
