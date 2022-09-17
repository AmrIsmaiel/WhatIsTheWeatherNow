package com.som3a.remote.model

import com.google.gson.annotations.SerializedName
data class CoordinatorRemote(
    @SerializedName("lon")
    val longitude : Double?,
    @SerializedName("lat")
    val latitude : Double?
)
