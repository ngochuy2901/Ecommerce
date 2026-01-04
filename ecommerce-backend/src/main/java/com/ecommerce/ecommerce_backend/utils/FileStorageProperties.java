package com.ecommerce.ecommerce_backend.utils;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


public final class FileStorageProperties {

    // ===== LOCAL STORAGE =====
    public static final String AVATAR_UPLOAD_DIR =
            "src/main/resources/static/images/avatars";

    public static final String PRODUCT_IMAGE_UPLOAD_DIR =
            "src/main/resources/static/images/products";

    // ===== PUBLIC URL =====
    public static final String AVATAR_PUBLIC_URL =
            "/images/avatars";

    public static final String PRODUCT_IMAGE_PUBLIC_URL =
            "/images/products";
}
