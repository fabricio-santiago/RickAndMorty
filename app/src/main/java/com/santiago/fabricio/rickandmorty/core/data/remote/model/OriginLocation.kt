package com.santiago.fabricio.rickandmorty.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class OriginLocation(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
)