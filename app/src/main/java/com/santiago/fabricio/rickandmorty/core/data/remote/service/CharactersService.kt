package com.santiago.fabricio.rickandmorty.core.data.remote.service

import com.santiago.fabricio.rickandmorty.core.data.remote.response.characters.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersResponse

}