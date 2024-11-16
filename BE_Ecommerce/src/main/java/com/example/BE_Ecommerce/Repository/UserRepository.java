package com.example.BE_Ecommerce.Repository;


import com.example.BE_Ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    Optional<User> findByName(String userName);
}
