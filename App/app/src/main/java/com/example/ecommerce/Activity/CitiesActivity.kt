package com.example.ecommerce.Activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.Adapter.CityAdapter
import com.example.ecommerce.Data.Entity.City
import com.example.ecommerce.Network.ApiClient
import com.example.ecommerce.R
import retrofit2.*

class CitiesActivity : AppCompatActivity() {
    lateinit var citiesList:RecyclerView
    private lateinit var cityAdapter: CityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cities)
        init()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        citiesList = findViewById(R.id.list_cities)
        citiesList.layoutManager = LinearLayoutManager(this)
        fetchCities()
    }

    private fun fetchCities() {
        val apiClient = ApiClient()
        apiClient.apiService.getCities().enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cityAdapter = CityAdapter(it)
                        citiesList.adapter = cityAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                Log.e("MainActivity", "Failed to fetch cities: ${t.message}")
            }
        })
    }
}