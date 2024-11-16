package com.example.BE_Ecommerce.Controller;


import com.example.BE_Ecommerce.Entity.City;
import com.example.BE_Ecommerce.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PostMapping("addcity")
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }
}
