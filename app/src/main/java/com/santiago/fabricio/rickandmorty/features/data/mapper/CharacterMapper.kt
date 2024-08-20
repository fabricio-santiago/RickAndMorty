package com.santiago.fabricio.rickandmorty.features.data.mapper

import com.santiago.fabricio.rickandmorty.core.data.remote.model.OriginLocation
import com.santiago.fabricio.rickandmorty.core.data.remote.response.characters.CharactersResponse
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character

fun List<Character?>.toRepository() = map { characterResult ->
    Character(
        id = characterResult?.id ?: 0,
        name = characterResult?.name ?: "",
        status = characterResult?.status ?: "",
        species = characterResult?.species ?: "",
        type = characterResult?.type ?: "",
        gender = characterResult?.gender ?: "",
        origin = characterResult?.origin ?: OriginLocation(),
        location = characterResult?.location ?: OriginLocation(),
        image = characterResult?.image ?: "",
        episode = characterResult?.episode ?: listOf(),
        url = characterResult?.url ?: "",
        created = characterResult?.created ?: "",
    )
}