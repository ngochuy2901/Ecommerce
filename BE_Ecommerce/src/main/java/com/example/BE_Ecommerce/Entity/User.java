package com.example.BE_Ecommerce.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;

    @Column(name = "UserEmail")
    private String userEmail;

    @Column(name = "UserPassword")
    private String userPassword;

    @Column(name = "UserFirstName")
    private String userFirstName;

    @Column(name = "UserLastName")
    private String userLastName;

    @Column(name = "UserPhone")
    private String userPhone;

    @Column(name = "UserAddress1")
    private String userAddress1;

    @Column(name = "UserAddress2")
    private String userAddress2;
}
