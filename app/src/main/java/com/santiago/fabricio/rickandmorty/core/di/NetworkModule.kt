package com.santiago.fabricio.rickandmorty.core.di

import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.ParamsInterceptor
import com.santiago.fabricio.rickandmorty.core.data.remote.service.LocationsService
import com.santiago.fabricio.rickandmorty.core.data.remote.service.CharactersService
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.SafeApiCaller
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideSafeApiCaller(backgroundDispatcher: CoroutineDispatcher): SafeApiCaller {
        return SafeApiCaller(backgroundDispatcher)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Provides
    fun provideParamsInterceptor(): ParamsInterceptor {
        return ParamsInterceptor()
    }

    @Provides
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor, loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(paramsInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideCharactersService(
        client: OkHttpClient, converterFactory: GsonConverterFactory
    ): CharactersService {
        return Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").client(client)
            .addConverterFactory(converterFactory).build().create(CharactersService::class.java)
    }

    @Provides
    fun provideLocationsService(
        client: OkHttpClient, converterFactory: GsonConverterFactory
    ): LocationsService {
        return Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").client(client)
            .addConverterFactory(converterFactory).build().create(LocationsService::class.java)
    }
}