package com.yangyinxu.finitude.di

import com.yangyinxu.finitude.data.remote.ArchtreeApi
import com.yangyinxu.finitude.domain.repository.ArchtreeRepository
import com.yangyinxu.finitude.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Defines how we construct dependencies
 *
 * SingletonComponent means dependencies
 * we have in app module will live as long
 * as the application does
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArchTreeRepository(
        api: ArchtreeApi
    ) = ArchtreeRepository(api)

    @Singleton
    @Provides
    fun provideArchtreeApi(): ArchtreeApi {
        val inspector = HttpLoggingInterceptor()
        inspector.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(inspector)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_ARCHTREE_URL)
            .client(client)
            .build()
            .create(ArchtreeApi::class.java)
    }
}