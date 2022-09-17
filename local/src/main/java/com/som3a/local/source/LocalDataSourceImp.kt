package com.som3a.local.source

import com.som3a.data.model.WeatherDetailsDTO
import com.som3a.data.repository.LocalDataSource
import com.som3a.local.database.WeatherDetailsDAO
import com.som3a.local.mapper.WeatherDetailsLocalDataMapper
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val weatherDetailsDAO: WeatherDetailsDAO,
    private val weatherDetailsLocalDataMapper: WeatherDetailsLocalDataMapper
) : LocalDataSource {
    override suspend fun addItem(weatherDetailsDTO: WeatherDetailsDTO): Long {
        val weatherDetailsLocal = weatherDetailsLocalDataMapper.reversMap(weatherDetailsDTO)
        return weatherDetailsDAO.addWeatherDetails(weatherDetailsLocal)
    }

    override suspend fun getItemById(weatherId: Long): WeatherDetailsDTO {
        val weatherDetailsLocal = weatherDetailsDAO.getWeatherDetailsById(weatherId)
        return weatherDetailsLocalDataMapper.map(weatherDetailsLocal)
    }

    override suspend fun getItems(): List<WeatherDetailsDTO> {
        val list = weatherDetailsDAO.getWeatherDetailsItems()
        return weatherDetailsLocalDataMapper.mapList(list)
    }

    override suspend fun updateItem(weatherDetailsDTO: WeatherDetailsDTO): Int {
        val weatherDetailsLocal = weatherDetailsLocalDataMapper.reversMap(weatherDetailsDTO)
        return weatherDetailsDAO.updateWeatherDetailsItem(weatherDetailsLocal)
    }
}