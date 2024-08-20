package com.santiago.fabricio.rickandmorty.features.data.source

import com.santiago.fabricio.rickandmorty.core.data.remote.paging.CharactersPageSource
import com.santiago.fabricio.rickandmorty.core.data.remote.response.characters.CharactersResponse
import com.santiago.fabricio.rickandmorty.core.data.remote.service.CharactersService
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.rickandmorty.features.domain.source.CharactersRemoteDataSource
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val service: CharactersService,
    private val safeApiCaller: SafeApiCaller
) : CharactersRemoteDataSource {
    override fun getCharactersPageSource(): CharactersPageSource {
        return CharactersPageSource(this, safeApiCaller)
    }

    override suspend fun getCharacters(page: Int): CharactersResponse {
        return service.getCharacters(page)
    }
}