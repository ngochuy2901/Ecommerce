package com.ecommerce.ecommerce_backend.service;


import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.entity.SellerProfile;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import com.ecommerce.ecommerce_backend.repository.SellerProfileRepository;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import com.ecommerce.ecommerce_backend.utils.FileStorageProperties;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SellerProfileRepository sellerProfileRepository;

    //add new product
    public Product saveNewProduct(String username, Product product, MultipartFile file) {

        // 1. Check user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid user"));

        // 2. Check seller
        SellerProfile sellerProfile = sellerProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("You're not seller"));

        // 3. Xử lý upload ảnh
        if (file != null && !file.isEmpty()) {
            try {
                // Tạo tên file unique
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

                // Đường dẫn lưu file
                Path uploadPath = Paths.get(FileStorageProperties.PRODUCT_IMAGE_UPLOAD_DIR);

                // Tạo thư mục nếu chưa tồn tại
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lưu file
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);

                // Set URL public cho product
                String imageUrl = FileStorageProperties.PRODUCT_IMAGE_PUBLIC_URL + "/" + fileName;
                product.setThumbnail(imageUrl);

            } catch (IOException e) {
                throw new RuntimeException("Failed to store product image", e);
            }
        }

        // 4. Gắn seller cho product
        product.setSellerId(sellerProfile.getId());

        // 5. Set giá trị mặc định (an toàn)
        if (product.getStock() == null) {
            product.setStock(0);
        }

        if (product.getStatus() == null) {
            product.setStatus(Product.ProductStatus.ACTIVE);
        }

        return productRepository.save(product);
    }


    //get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //get product by id
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ServiceException("product is not exits!");
        }
        return product.get();
    }

    //get products by sellerid
    public List<Product> getProductBySellerId(Long sellerId) {
        List<Product> productsList = productRepository.findBySellerId(sellerId);
        if (productsList.isEmpty()) {
            throw new ServiceException("product is not exits!");
        }
        return productsList;
    }

    //find by category
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    //get products by name
    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    //update product by id

    //delete product by id
    public boolean  deleteProductByProductId(String username, Product product) {
        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isEmpty()) {
//            throw new ServiceException("User is not exits!");
//        }
        Optional<SellerProfile> seller = sellerProfileRepository.findByUser(user.get());
        if(seller.isEmpty()) {
            throw new ServiceException("You're not own this product!");
        }
        productRepository.delete(product);
        return true;
    }

    //products active for client
    public List<Product> getActiveProductForClient() {
        return productRepository.findByStatus(Product.ProductStatus.ACTIVE);
    }

    //products best seller

    //filter

    //product images

    //stock

    //review
}
