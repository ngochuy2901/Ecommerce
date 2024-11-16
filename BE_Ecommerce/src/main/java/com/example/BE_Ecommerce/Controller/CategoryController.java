package com.example.BE_Ecommerce.Controller;


import com.example.BE_Ecommerce.Entity.Category;
import com.example.BE_Ecommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("findbycategoryname")
    public List<Category> findByCategoryName(@RequestParam  String categoryName) {
        return categoryService.findByCategoryName(categoryName);
    }

    @PostMapping("addcategory")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("updatecategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category categoryDetail) {
        Category categoryUpdate = categoryService.updateCategory(id, categoryDetail);
        return ResponseEntity.ok(categoryUpdate);
    }

    @DeleteMapping("deletecategorybyid/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
