package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.dto.LoginRequest;
import com.ecommerce.ecommerce_backend.dto.LoginResponse;
import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.security.JwtUtil;
import com.ecommerce.ecommerce_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    //register
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    //login
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    //logout

    //load user profile
    @GetMapping("get_user_profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        // Extract token from Authorization header
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(userService.getUserProfile(username));
    }

    //update user profile
    @PostMapping("update_user_profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody User user, @RequestHeader("Authorization") String authHeader) {
        // Extract token from Authorization header
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(userService.updateUserProfile(username, user));
    }

    //upload avatar
    @PostMapping("upload_user_avatar")
    public ResponseEntity<?> uploadUserAvatar(@RequestHeader("Authorization") String authHeader, @RequestPart("file") MultipartFile file) {
        String token = jwtUtil.getTokenFromAuthHeader(authHeader);
        String username = jwtUtil.extractUsername(token);
        return userService.uploadUserAvatar(username, file);
    }

    //change password

    //reset password

    //forgot password
}
