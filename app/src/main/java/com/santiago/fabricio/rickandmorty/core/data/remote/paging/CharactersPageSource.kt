package com.santiago.fabricio.rickandmorty.core.data.remote.paging

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.onEmpty
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.onError
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.onSuccess
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character
import com.santiago.fabricio.rickandmorty.features.data.mapper.toRepository
import com.santiago.fabricio.rickandmorty.features.domain.source.CharactersRemoteDataSource

class CharactersPageSource(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val safeApiCaller: SafeApiCaller
) : PagingSource<Int, Character>() {

    companion object {
        const val LIMIT_PAGES = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT_PAGES) ?: anchorPage?.nextKey?.minus(LIMIT_PAGES)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        safeApiCaller.safeApiCall {
            remoteDataSource.getCharacters(pageNumber)
        }.onSuccess { response ->
            Log.e("", response.results.toString());
            return LoadResult.Page(
                data = response.results.toRepository(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (response.results.isEmpty()) null else pageNumber + 1
            )
        }.onEmpty {
            return LoadResult.Error(Throwable())
        }.onError { a ->
            Log.e("", a.apiException.httpStatusCode.toString())
            return LoadResult.Error(Throwable())
        }
        return LoadResult.Error(Throwable())
    }
}