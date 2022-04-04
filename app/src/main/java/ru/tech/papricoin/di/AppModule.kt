package ru.tech.papricoin.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.tech.papricoin.common.constants.Constants.BASE_URL
import ru.tech.papricoin.data.local.room.Converters
import ru.tech.papricoin.data.local.room.PapriCoinDatabase
import ru.tech.papricoin.data.remote.api.PapriCoinApi
import ru.tech.papricoin.data.repository.PapriCoinRepositoryImpl
import ru.tech.papricoin.data.utils.MoshiParser
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
    fun providePapriCoinRepository(api: PapriCoinApi, db: PapriCoinDatabase): PapriCoinRepository =
        PapriCoinRepositoryImpl(
            api,
            db.coinDao,
            db.overviewDao,
            db.coinDetailDao,
            db.coinCurrencyDao
        )

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): PapriCoinDatabase = Room.databaseBuilder(
        app, PapriCoinDatabase::class.java, "coin_database"
    ).addTypeConverter(Converters(MoshiParser(Moshi.Builder().build()))).build()

}