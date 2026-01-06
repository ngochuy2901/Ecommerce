package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.Category;
import com.ecommerce.ecommerce_backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category addNewCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    //update

    //delete
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }
}
