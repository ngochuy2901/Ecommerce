package com.example.ecommerce.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.Data.Entity.City
import com.example.ecommerce.R

class CityAdapter(private val cities:List<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>(){
    class CityViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.city_name)
        val cityType: TextView = view.findViewById(R.id.city_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.city_layout,parent,false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.cityName.text = city.cityName
        holder.cityType.text = city.cityType
    }
}