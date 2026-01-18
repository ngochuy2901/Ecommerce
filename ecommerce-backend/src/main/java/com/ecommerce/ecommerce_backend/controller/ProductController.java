package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.entity.SellerProfile;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.security.JwtUtil;
import com.ecommerce.ecommerce_backend.service.ProductService;
import com.ecommerce.ecommerce_backend.service.SellerProfileService;
import com.ecommerce.ecommerce_backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final SellerProfileService sellerProfileService;
    @PostMapping(
            value = "/add_new_product",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Product> saveNewProduct(
            @RequestHeader("Authorization") String authHeader,
            @RequestPart("product") String productJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productJson, Product.class);

        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);

        return ResponseEntity.ok(
                productService.saveNewProduct(username, product, file)
        );
    }

    @GetMapping("get_products_by_shop")
    public ResponseEntity<List<Product>> getProductsByShop(
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);

        User user = userService.getUserByUserName(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        SellerProfile seller = sellerProfileService
                .findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("User chưa đăng ký bán hàng"));

        return ResponseEntity.ok(
                productService.getProductBySellerId(seller.getId())
        );
    }


    @GetMapping("get_product_by_id/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable("product_id") Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("get_all_products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("get_products_by_name")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @GetMapping("get_products_active")
    public ResponseEntity<List<Product>> getProductsActive() {
        return ResponseEntity.ok(productService.getActiveProductForClient());
    }

    @GetMapping("get_products_by_categorty_id/{categoryId}")
    public ResponseEntity<List<Product>> findProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }
}
