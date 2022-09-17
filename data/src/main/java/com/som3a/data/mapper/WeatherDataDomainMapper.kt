package com.som3a.data.mapper

import com.som3a.common.Mapper
import com.som3a.data.model.WeatherDetailsDTO
import com.som3a.domain.entity.WeatherDetailsEntity
import javax.inject.Inject

class WeatherDataDomainMapper @Inject constructor() :
    Mapper<WeatherDetailsDTO, WeatherDetailsEntity> {
    override fun map(input: WeatherDetailsDTO): WeatherDetailsEntity {
        return WeatherDetailsEntity(
            longitude = input.longitude,
            latitude = input.latitude,
            main = input.main,
            description = input.description,
            base = input.base,
            temp = input.temp,
            feelsLike = input.feelsLike,
            minTemp = input.minTemp,
            maxTemp = input.maxTemp,
            pressure = input.pressure,
            humidity = input.humidity,
            seaLevel = input.seaLevel,
            groundLevel = input.groundLevel,
            visibility = input.visibility,
            speed = input.speed,
            clouds = input.clouds,
            dt = input.dt,
            country = input.country,
            sunrise = input.sunrise,
            sunset = input.sunset,
            timezone = input.timezone,
            id = input.id,
            name = input.name,
            code = input.code,
            photoPath = input.photoPath
        )
    }

    override fun reversMap(input: WeatherDetailsEntity): WeatherDetailsDTO {
        return WeatherDetailsDTO(
            longitude = input.longitude,
            latitude = input.latitude,
            main = input.main,
            description = input.description,
            base = input.base,
            temp = input.temp,
            feelsLike = input.feelsLike,
            minTemp = input.minTemp,
            maxTemp = input.maxTemp,
            pressure = input.pressure,
            humidity = input.humidity,
            seaLevel = input.seaLevel,
            groundLevel = input.groundLevel,
            visibility = input.visibility,
            speed = input.speed,
            clouds = input.clouds,
            dt = input.dt,
            country = input.country,
            sunrise = input.sunrise,
            sunset = input.sunset,
            timezone = input.timezone,
            id = input.id,
            name = input.name,
            code = input.code,
            photoPath = input.photoPath
        )
    }
}