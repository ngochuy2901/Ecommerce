package com.example.BE_Ecommerce.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private int productId;

    @Column(name = "productsku")
    private String productSku;

    @Column(name = "productname")
    private String productName;

    @Column(name = "productprice")
    private String productPrice;

    @Column(name = "productweight")
    private float productWeight;

    @Column(name = "productcartdesc")
    private String productCartDesc;

    @Column(name = "productshortdesc")
    private String productShortDesc;

    @Column(name = "productlongdesc")
    private String productLongDesc;

    @Column(name = "productthumb")
    private String productThumb;

    @Column(name = "productimage")
    private String productImage;

    @Column(name = "productcategoryid")
    private int productCategoryId;

    @Column(name = "productstock")
    private int productStock;

    @Column(name = "productlive")
    private int productLive;
}
