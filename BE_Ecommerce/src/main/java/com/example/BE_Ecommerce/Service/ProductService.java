package com.example.BE_Ecommerce.Service;


import com.example.BE_Ecommerce.Entity.Product;
import com.example.BE_Ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(int id) {
        return productRepository.findById(id);
    }
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product updateProduct(int id, Product productDetail) {
        Product productExisting = productRepository.findById(id);
        // Cập nhật thông tin sản phẩm
        productExisting.setProductSku(productDetail.getProductSku());
        productExisting.setProductName(productDetail.getProductName());
        productExisting.setProductPrice(productDetail.getProductPrice());
        productExisting.setProductWeight(productDetail.getProductWeight());
        productExisting.setProductCartDesc(productDetail.getProductCartDesc());
        productExisting.setProductShortDesc(productDetail.getProductShortDesc());
        productExisting.setProductThumb(productDetail.getProductThumb());
        productExisting.setProductImage(productDetail.getProductImage());
        productExisting.setProductCategoryId(productDetail.getProductCategoryId());
        productExisting.setProductStock(productDetail.getProductStock());
        productExisting.setProductLive(productDetail.getProductLive());
        return productRepository.save(productExisting);
    }
}
