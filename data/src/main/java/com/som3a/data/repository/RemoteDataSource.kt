package com.som3a.data.repository

import com.som3a.data.model.WeatherDetailsDTO

interface RemoteDataSource {
    suspend fun getWeatherDetails(lat: Double, long: Double): WeatherDetailsDTO
}