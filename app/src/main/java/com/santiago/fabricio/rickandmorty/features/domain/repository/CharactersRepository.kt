package com.santiago.fabricio.rickandmorty.features.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(pagingConfig: PagingConfig): Flow<PagingData<Character>>
}