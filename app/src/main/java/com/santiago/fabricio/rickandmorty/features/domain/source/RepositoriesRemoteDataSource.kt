package com.santiago.fabricio.rickandmorty.features.domain.source

import com.santiago.fabricio.rickandmorty.core.data.remote.paging.CharactersPageSource
import com.santiago.fabricio.rickandmorty.core.data.remote.paging.LocationsPageSource
import com.santiago.fabricio.rickandmorty.core.data.remote.response.characters.CharactersResponse
import com.santiago.fabricio.rickandmorty.core.data.remote.response.locations.LocationsResponse

interface CharactersRemoteDataSource {
    fun getCharactersPageSource(): CharactersPageSource
    suspend fun getCharacters(page: Int): CharactersResponse
}

interface LocationsRemoteDataSource {
    fun getLocationsPageSource(): LocationsPageSource
    suspend fun getLocations(page: Int): LocationsResponse
}