package com.ecommerce.ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "seller_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "shop_description")
    private String shopDescription;

    @Column(name = "shop_address")
    private String shopAddress;

    @Column(name = "identity_number", nullable = false)
    private String identityNumber; // CCCD / CMND

    @Enumerated(EnumType.STRING)
    private SellerStatus status;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "approved_at")
    private String approvedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now().toString();
        status = SellerStatus.PENDING;
    }

    public enum SellerStatus {
        PENDING,   // chờ duyệt
        APPROVED,  // đã duyệt
        REJECTED
    }
}

