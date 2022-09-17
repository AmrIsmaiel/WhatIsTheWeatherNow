package com.som3a.remote.api

import com.som3a.remote.BuildConfig
import com.som3a.remote.model.WeatherDetailsRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeatherDetails(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appid: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherDetailsRemote
}