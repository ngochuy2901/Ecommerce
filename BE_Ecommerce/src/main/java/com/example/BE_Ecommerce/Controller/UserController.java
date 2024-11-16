package com.example.BE_Ecommerce.Controller;


import com.example.BE_Ecommerce.Entity.User;
import com.example.BE_Ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("adduser")
    public User addUser(@RequestBody User user) {
//        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userService.addUser(user);
    }
}
