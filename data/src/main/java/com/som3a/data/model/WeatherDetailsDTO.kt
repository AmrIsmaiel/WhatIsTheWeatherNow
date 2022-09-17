package com.som3a.data.model

data class WeatherDetailsDTO(
    val longitude: Double? = null,
    val latitude: Double? = null,
    val main: String? = null,
    val description: String? = null,
    val base: String? = null,
    val temp: Double? = null,
    val feelsLike: Double? = null,
    val minTemp: Double? = null,
    val maxTemp: Double? = null,
    val pressure: Double? = null,
    val humidity: Double? = null,
    val seaLevel: Double? = null,
    val groundLevel: Double? = null,
    val visibility: Long? = null,
    val speed: Double? = null,
    val clouds: Double? = null,
    val dt: Long? = null,
    val country: String? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val timezone: Int? = null,
    val id: Long? = null,
    val name: String? = null,
    val code: Int? = null,
    val photoPath: String? = null
)
