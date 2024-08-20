package com.santiago.fabricio.rickandmorty.core.data.remote.service

import com.santiago.fabricio.rickandmorty.core.data.remote.response.locations.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsService {

    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int
    ): LocationsResponse
}