package com.som3a.remote.mapper

import com.som3a.common.Mapper
import com.som3a.data.model.WeatherDetailsDTO
import com.som3a.remote.model.WeatherDetailsRemote
import javax.inject.Inject

class WeatherDetailsRemoteMapper @Inject constructor() :
    Mapper<WeatherDetailsRemote, WeatherDetailsDTO> {
    override fun map(input: WeatherDetailsRemote): WeatherDetailsDTO {
        return WeatherDetailsDTO(
            longitude = input.coordinatorRemote?.longitude,
            latitude = input.coordinatorRemote?.latitude,
            main = input.weather?.main,
            description = input.weather?.description,
            base = input.base,
            temp = input.main?.temp,
            feelsLike = input.main?.feelsLike,
            minTemp = input.main?.minTemp,
            maxTemp = input.main?.maxTemp,
            pressure = input.main?.pressure,
            humidity = input.main?.humidity,
            seaLevel = input.main?.seaLevel,
            groundLevel = input.main?.groundLevel,
            visibility = input.visibility,
            speed = input.wind?.speed,
            clouds = input.clouds?.all,
            dt = input.dt,
            country = input.sys?.country,
            sunrise = input.sys?.sunrise,
            sunset = input.sys?.sunset,
            timezone = input.timezone,
            id = input.id,
            name = input.name,
            code = input.code
        )
    }

    override fun reversMap(input: WeatherDetailsDTO): WeatherDetailsRemote {
        return WeatherDetailsRemote(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }
}