package com.som3a.remote.model

import com.google.gson.annotations.SerializedName

data class MainPropertiesRemote(
    val temp: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("temp_min")
    val minTemp: Double?,
    @SerializedName("temp_max")
    val maxTemp: Double?,
    val pressure: Double?,
    val humidity: Double?,
    @SerializedName("sea_level")
    val seaLevel: Double?,
    @SerializedName("grnd_level")
    val groundLevel: Double?
)
