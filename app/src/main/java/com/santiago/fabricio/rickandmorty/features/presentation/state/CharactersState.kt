package com.santiago.fabricio.rickandmorty.features.presentation.state

import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersState(
    var characters: Flow<PagingData<Character>> = emptyFlow()
)

