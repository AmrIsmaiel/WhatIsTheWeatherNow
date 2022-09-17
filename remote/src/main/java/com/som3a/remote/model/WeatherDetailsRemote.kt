package com.som3a.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherDetailsRemote(
    @SerializedName("coord")
    val coordinatorRemote: CoordinatorRemote?,
    val weather: WeatherRemote?,
    val base: String?,
    val main: MainPropertiesRemote?,
    val visibility: Long?,
    val wind: WindRemote?,
    val clouds: CloudsRemote?,
    val dt: Long?,
    val sys: SystemRemote?,
    val timezone: Int?,
    val id: Long?,
    val name: String?,
    val code: Int?,
)
