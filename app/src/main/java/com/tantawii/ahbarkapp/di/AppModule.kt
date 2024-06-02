package com.tantawii.ahbarkapp.di

import android.app.Application
import com.tantawii.ahbarkapp.data.manger.LocalUserManagerImpl
import com.tantawii.ahbarkapp.data.remote.NewsApi
import com.tantawii.ahbarkapp.data.remote.repository.NewsRepositoryImpl
import com.tantawii.ahbarkapp.domain.manger.LocalUserManger
import com.tantawii.ahbarkapp.domain.repository.NewsRepository
import com.tantawii.ahbarkapp.domain.usecases.appentry.AppEntryUseCases
import com.tantawii.ahbarkapp.domain.usecases.appentry.ReadAppEntry
import com.tantawii.ahbarkapp.domain.usecases.appentry.SaveAppEntry
import com.tantawii.ahbarkapp.domain.usecases.news.GetNews
import com.tantawii.ahbarkapp.domain.usecases.news.NewsUseCases
import com.tantawii.ahbarkapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )


    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }

}