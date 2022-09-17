package com.som3a.remote.model

import com.google.gson.annotations.SerializedName

data class WindRemote(
    val speed: Double?,
    @SerializedName("deg")
    val degree: Double?,
    val gust: Double?
)
