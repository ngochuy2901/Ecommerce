package com.example.BE_Ecommerce.Repository;

import com.example.BE_Ecommerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryName(String categoryName);

    Category findById(int id);
}
