package com.som3a.feature.mapper

import com.som3a.common.Mapper
import com.som3a.domain.entity.WeatherDetailsEntity
import com.som3a.feature.model.WeatherDetailsUiModel
import javax.inject.Inject

class WeatherDetailsDomainUiMapper @Inject constructor() :
    Mapper<WeatherDetailsEntity, WeatherDetailsUiModel> {
    override fun map(input: WeatherDetailsEntity): WeatherDetailsUiModel {
        return WeatherDetailsUiModel(
            main = input.main,
            description = input.description,
            temp = input.temp,
            feelsLike = input.feelsLike,
            pressure = input.pressure,
            humidity = input.humidity,
            seaLevel = input.seaLevel,
            speed = input.speed,
            clouds = input.clouds,
            country = input.country,
            sunrise = input.sunrise,
            sunset = input.sunset,
            timezone = input.timezone,
            id = input.id,
            name = input.name,
            photoPath = input.photoPath
        )
    }

    override fun reversMap(input: WeatherDetailsUiModel): WeatherDetailsEntity {
        return WeatherDetailsEntity(
            longitude = null,
            latitude = null,
            main = input.main,
            description = input.description,
            base = null,
            temp = input.temp,
            feelsLike = input.feelsLike,
            minTemp = null,
            maxTemp = null,
            pressure = input.pressure,
            humidity = input.humidity,
            seaLevel = input.seaLevel,
            groundLevel = null,
            visibility = null,
            speed = input.speed,
            clouds = input.clouds,
            dt = null,
            country = input.country,
            sunrise = input.sunrise,
            sunset = input.sunset,
            timezone = input.timezone,
            id = input.id,
            name = input.name,
            code = null,
            photoPath = input.photoPath
        )
    }
}