package com.example.BE_Ecommerce.Service;


import com.example.BE_Ecommerce.Entity.Category;
import com.example.BE_Ecommerce.Entity.Product;
import com.example.BE_Ecommerce.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public Category updateCategory(int id, Category categoryDetail) {
        Category categoryExisting = categoryRepository.findById(id);

        categoryExisting.setCategoryName(categoryDetail.getCategoryName());
        categoryExisting.setCategoryImgUrl(categoryDetail.getCategoryImgUrl());
        return categoryRepository.save(categoryExisting);
    }
}
