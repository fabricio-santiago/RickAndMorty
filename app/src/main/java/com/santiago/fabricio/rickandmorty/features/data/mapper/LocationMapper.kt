package com.santiago.fabricio.rickandmorty.features.data.mapper

import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location

fun List<Location?>.toRepository() = map { locationResult ->
    Location(
        id = locationResult?.id ?: 0,
        name = locationResult?.name ?: "",
        type = locationResult?.type ?: "",
        residents = locationResult?.residents ?: listOf(),
        url = locationResult?.url ?: "",
        created = locationResult?.created ?: "",
    )
}
