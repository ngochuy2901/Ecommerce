package com.example.BE_Ecommerce.Service;


import com.example.BE_Ecommerce.Entity.User;
import com.example.BE_Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User addUser(User user) {
        return userRepository.save(user);
    }
}
