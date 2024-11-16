package com.example.ecommerce.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerce.Data.Auth.Auth
import com.example.ecommerce.Data.Entity.User
import com.example.ecommerce.R
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var firstName:TextInputEditText
    private lateinit var lastName:TextInputEditText
    private lateinit var phoneNumber:TextInputEditText
    private lateinit var email:TextInputEditText
    private lateinit var password:TextInputEditText
    private lateinit var btnRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        init()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        phoneNumber = findViewById(R.id.phoneNumber)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener(View.OnClickListener {
            createNewUser()
        })
    }
    fun createNewUser() {
        val auth = Auth()
        val user = User(email.text.toString(), phoneNumber.text.toString(), password.text.toString(), firstName.text.toString(), lastName.text.toString(), "", "")
        auth.createNewUser(user)
    }
}