package com.som3a.domain.entity

data class WeatherDetailsEntity(
    val longitude: Double?,
    val latitude: Double?,
    val main: String?,
    val description: String?,
    val base: String?,
    val temp: Double?,
    val feelsLike: Double?,
    val minTemp: Double?,
    val maxTemp: Double?,
    val pressure: Double?,
    val humidity: Double?,
    val seaLevel: Double?,
    val groundLevel: Double?,
    val visibility: Long?,
    val speed: Double?,
    val clouds: Double?,
    val dt: Long?,
    val country: String?,
    val sunrise: Long?,
    val sunset: Long?,
    val timezone: Int?,
    val id: Long?,
    val name: String?,
    val code: Int?,
    var photoPath: String?
)
