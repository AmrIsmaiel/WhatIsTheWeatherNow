package com.som3a.remote.source

import com.som3a.data.model.WeatherDetailsDTO
import com.som3a.data.repository.RemoteDataSource
import com.som3a.remote.api.ApiService
import com.som3a.remote.mapper.WeatherDetailsRemoteMapper
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val weatherDetailsRemoteMapper: WeatherDetailsRemoteMapper
) : RemoteDataSource {
    override suspend fun getWeatherDetails(lat: Double, long: Double): WeatherDetailsDTO {
        val networkData = apiService.getWeatherDetails(lat, long)
        return weatherDetailsRemoteMapper.map(networkData)
    }
}