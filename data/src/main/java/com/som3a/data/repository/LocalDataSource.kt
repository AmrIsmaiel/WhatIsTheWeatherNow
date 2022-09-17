package com.som3a.data.repository

import com.som3a.data.model.WeatherDetailsDTO

interface LocalDataSource {

    suspend fun addItem(weatherDetailsDTO: WeatherDetailsDTO): Long

    suspend fun getItemById(weatherId: Long): WeatherDetailsDTO

    suspend fun getItems(): List<WeatherDetailsDTO>

    suspend fun updateItem(weatherDetailsDTO: WeatherDetailsDTO): Int
}