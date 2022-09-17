package com.som3a.domain.repository

import com.som3a.common.Resource
import com.som3a.domain.entity.WeatherDetailsEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getWeatherDetailsList(): Flow<Resource<List<WeatherDetailsEntity>>>
    suspend fun getWeatherDetails(lat: Double, long: Double): Flow<Resource<WeatherDetailsEntity>>
}