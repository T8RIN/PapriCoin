package ru.tech.papricoin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.tech.papricoin.common.constants.Constants.BASE_URL
import ru.tech.papricoin.data.remote.api.PapriCoinApi
import ru.tech.papricoin.data.repository.PapriCoinRepositoryImpl
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePapriCoinApi(): PapriCoinApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PapriCoinApi::class.java)

    @Provides
    @Singleton
    fun providePapriCoinRepository(api: PapriCoinApi): PapriCoinRepository =
        PapriCoinRepositoryImpl(api)

}