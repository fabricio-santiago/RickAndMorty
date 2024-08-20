package com.santiago.fabricio.rickandmorty.core.data.remote.response.locations

import com.google.gson.annotations.SerializedName
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Info
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location

data class LocationsResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Location>,
)