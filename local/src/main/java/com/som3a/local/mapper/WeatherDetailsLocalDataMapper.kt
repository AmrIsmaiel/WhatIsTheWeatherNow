package com.som3a.local.mapper

import com.som3a.common.Mapper
import com.som3a.data.model.WeatherDetailsDTO
import com.som3a.local.model.WeatherDetailsLocal
import javax.inject.Inject

class WeatherDetailsLocalDataMapper @Inject constructor() :
    Mapper<WeatherDetailsLocal, WeatherDetailsDTO> {
    override fun map(input: WeatherDetailsLocal): WeatherDetailsDTO {
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
            id = input.responseId,
            name = input.name,
            code = input.code,
            photoPath = input.photoPath
        )
    }

    override fun reversMap(input: WeatherDetailsDTO): WeatherDetailsLocal {
        return WeatherDetailsLocal(
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
            id = 0,
            responseId = input.id,
            name = input.name,
            code = input.code,
            photoPath = input.photoPath
        )
    }
}