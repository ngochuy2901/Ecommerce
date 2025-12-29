package com.ecommerce.ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nhiều address thuộc 1 user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(nullable = false, length = 15)
    private String phone;

    private String province;
    private String district;
    private String ward;
    private String street;

    @Column(name = "is_default")
    private Boolean isDefault;
}