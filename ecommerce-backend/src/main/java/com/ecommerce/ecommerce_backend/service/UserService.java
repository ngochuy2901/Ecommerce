package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.config.PasswordConfig;
import com.ecommerce.ecommerce_backend.dto.LoginRequest;
import com.ecommerce.ecommerce_backend.dto.LoginResponse;
import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import com.ecommerce.ecommerce_backend.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public User register(User newUser) {

        if (!checkUserValid(newUser)) {
            throw new ServiceException("Invalid user data or user already exists");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        // set mặc định (an toàn)
        newUser.setRole(User.Role.USER);
        newUser.setStatus(User.UserStatus.ACTIVE);

        return userRepository.save(newUser);
    }


    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new ServiceException("Username or password is missing");
        }
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ServiceException("User not found"));
        if (user.getStatus() == User.UserStatus.BLOCKED) {
            throw new ServiceException("User is blocked");
        }
        // 4. Check password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ServiceException("Invalid username or password");
        }
        String token = jwtUtil.generateToken(user.getUsername());

        // 6. Update last login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // 7. Return response
        return new LoginResponse(
                "success",
                "Login successful",
                token
        );
    }


    private boolean checkUserExist(User user) {
        if (user == null) return false;

        if (user.getUsername() != null &&
                userRepository.existsByUsername(user.getUsername())) {
            return true;
        }

        return user.getEmail() != null &&
                userRepository.existsByEmail(user.getEmail());
    }

    //load user profile
    public UserDto getUserProfile(String username) {
        try {
            // Find user by username from token
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ServiceException("User not found"));
            return convertToDto(user);
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve user profile", e);
        }
    }

    //update user profile
    public User updateUserProfile(String username, User newUser) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ServiceException("User not found"));

        // update từng field nếu có giá trị
        if (newUser.getFullName() != null) {
            user.setFullName(newUser.getFullName());
        }

        if (newUser.getEmail() != null &&
                !newUser.getEmail().equals(user.getEmail())) {

            if (userRepository.existsByEmail(newUser.getEmail())) {
                throw new ServiceException("Email already in use");
            }
            user.setEmail(newUser.getEmail());
        }

        if (newUser.getPhone() != null) {
            user.setPhone(newUser.getPhone());
        }

        if (newUser.getAvatarUrl() != null) {
            user.setAvatarUrl(newUser.getAvatarUrl());
        }

        if (newUser.getGender() != null) {
            user.setGender(newUser.getGender());
        }

        if (newUser.getDateOfBirth() != null) {
            user.setDateOfBirth(newUser.getDateOfBirth());
        }

        return userRepository.save(user);
    }


    // Helper method to convert User entity to UserDto
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setFullName(user.getFullName());
        userDto.setAvatarUrl(user.getAvatarUrl());
        userDto.setGender(user.getGender());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private boolean checkUserValid(User user) {
        if (user == null) return false;
        if (checkUserExist(user)) return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return false;
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            return false;
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }
        return !userRepository.existsByEmail(user.getEmail());
    }

}
