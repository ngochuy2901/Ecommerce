package com.ecommerce.ecommerce_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(unique = true, length = 15)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "last_login")
    private String lastLogin;

    @Column(name = "created_at", updatable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now().toString();
        updatedAt = LocalDateTime.now().toString();
        status = UserStatus.ACTIVE;
        role = Role.USER;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().toString();
    }

    public enum Role {
        USER,
        SELLER,
        ADMIN
    }

    public enum UserStatus {
        ACTIVE,
        BLOCKED
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}

