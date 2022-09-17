package com.som3a.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDetailsUiModel(
    val main: String?,
    val description: String?,
    val temp: Double?,
    val feelsLike: Double?,
    val pressure: Double?,
    val humidity: Double?,
    val seaLevel: Double?,
    val speed: Double?,
    val clouds: Double?,
    val country: String?,
    val sunrise: Long?,
    val sunset: Long?,
    val timezone: Int?,
    val id: Long?,
    val name: String?,
    val photoPath: String?
) : Parcelable
