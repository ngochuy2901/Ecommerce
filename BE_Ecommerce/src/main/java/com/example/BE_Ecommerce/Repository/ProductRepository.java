package com.example.BE_Ecommerce.Repository;


import com.example.BE_Ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductName(String productName);
    Product findById(int id);
}
