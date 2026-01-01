package com.ecommerce.ecommerce_backend.dto;

import lombok.*;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String status;
    private String message;
    private String token;
}
