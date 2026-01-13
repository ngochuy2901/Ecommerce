package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.utils.FileStorageProperties;
import com.ecommerce.ecommerce_backend.dto.LoginRequest;
import com.ecommerce.ecommerce_backend.dto.LoginResponse;
import com.ecommerce.ecommerce_backend.dto.UserDto;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import com.ecommerce.ecommerce_backend.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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
            return new LoginResponse(
                    "failed",
                    "user is blocked",
                    null
            );
        }
        // 4. Check password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(
                    "failed",
                    "wrong username or password",
                    null
            );
        }
        String token = jwtUtil.generateToken(user.getUsername());

        // 6. Update last login
        user.setLastLogin(LocalDateTime.now().toString());
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

    //upload user avatar
    public ResponseEntity<?> uploadUserAvatar(String username, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new ServiceException("File is empty");
        }

        // Validate content type
        String contentType = file.getContentType();
        if (contentType == null ||
                (!contentType.startsWith("image/"))) {
            throw new ServiceException("Only image files are allowed");
        }

        // Find user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ServiceException("User not found"));

        try {
            // Đường dẫn tuyệt đối tới static/images/avatars
            String uploadDir = FileStorageProperties.AVATAR_UPLOAD_DIR;
            Files.createDirectories(Paths.get(uploadDir));

            // Tên file an toàn
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = "";

            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex > 0) {
                fileExtension = originalFilename.substring(dotIndex);
            }

            String newFileName = UUID.randomUUID() + fileExtension;

            Path filePath = Paths.get(uploadDir, newFileName);
            Files.write(filePath, file.getBytes());

            // URL public
            String avatarUrl = "/images/avatars/" + newFileName;

            // Update DB
            user.setAvatarUrl(avatarUrl);
            userRepository.save(user);

            return ResponseEntity.ok().body(
                    java.util.Map.of(
                            "message", "Upload avatar successfully",
                            "avatarUrl", avatarUrl
                    )
            );

        } catch (IOException e) {
            throw new ServiceException("Failed to upload avatar", e);
        }
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
