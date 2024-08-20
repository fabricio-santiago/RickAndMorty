package com.santiago.fabricio.rickandmorty.core.domain.model

import com.santiago.fabricio.rickandmorty.core.data.remote.model.Info
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location
import com.santiago.fabricio.rickandmorty.core.data.remote.response.locations.LocationsResponse

class LocationsFactory {

    companion object {
        fun create() = run {
            LocationsResponse(
                info = Info(
                    count = 10,
                    pages = 1,
                    next = "",
                    prev = ""
                ),
                results = listOf(
                    Location(
                        id = 361,
                        name = "Citadel of Ricks",
                        type = "Space station",
                        residents = listOf(
                            "https://rickandmortyapi.com/api/character/8",
                            "https://rickandmortyapi.com/api/character/14",
                        ),
                        url = "https://rickandmortyapi.com/api/location/3",
                        created = "2018-01-10T18:20:41.703Z"

                    )
                )
            )
        }
    }
}