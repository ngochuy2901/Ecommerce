package com.example.BE_Ecommerce.Repository;


import com.example.BE_Ecommerce.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
