package com.ecommerce.ecommerce_backend.dto;
import com.ecommerce.ecommerce_backend.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String fullName;
    private String avatarUrl;
    private User.Gender gender;
    private LocalDate dateOfBirth;
    private User.Role role;
}
