package com.santiago.fabricio.rickandmorty.core.data.remote.paging

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.onEmpty
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.onError
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.onSuccess
import com.santiago.fabricio.rickandmorty.features.data.mapper.toRepository
import com.santiago.fabricio.rickandmorty.features.domain.source.LocationsRemoteDataSource

class LocationsPageSource(
    private val remoteDataSource: LocationsRemoteDataSource,
    private val safeApiCaller: SafeApiCaller
) : PagingSource<Int, Location>() {

    companion object {
        const val LIMIT_PAGES = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT_PAGES) ?: anchorPage?.nextKey?.minus(LIMIT_PAGES)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val pageNumber = params.key ?: 1
        safeApiCaller.safeApiCall {
            remoteDataSource.getLocations(pageNumber)
        }.onSuccess { response ->
            return LoadResult.Page(
                data = response.results.toRepository(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (response.results.isEmpty()) null else pageNumber + 1
            )
        }.onEmpty {
            return LoadResult.Error(Throwable())
        }.onError {
            return LoadResult.Error(Throwable())
        }
        return LoadResult.Error(Throwable())
    }
}