package com.example.BE_Ecommerce.Controller;


import com.example.BE_Ecommerce.Entity.Product;
import com.example.BE_Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("getallproducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("findproductbyname")
    public List<Product> findProductByname(@RequestParam String productName) {
        return productService.findByProductName(productName);
    }

    @GetMapping("findproductbyid")
    public Product findProductById(@RequestParam int id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("deleteproductbyid")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("addproduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
