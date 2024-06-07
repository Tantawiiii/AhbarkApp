package com.tantawii.ahbarkapp.di

import android.app.Application
import androidx.room.Room
import com.tantawii.ahbarkapp.data.local.NewsDao
import com.tantawii.ahbarkapp.data.local.NewsDatabase
import com.tantawii.ahbarkapp.data.local.NewsTypeConvertor
import com.tantawii.ahbarkapp.data.manger.LocalUserManagerImpl
import com.tantawii.ahbarkapp.data.remote.NewsApi
import com.tantawii.ahbarkapp.data.repository.NewsRepositoryImpl
import com.tantawii.ahbarkapp.domain.manger.LocalUserManger
import com.tantawii.ahbarkapp.domain.repository.NewsRepository
import com.tantawii.ahbarkapp.domain.usecases.appentry.AppEntryUseCases
import com.tantawii.ahbarkapp.domain.usecases.appentry.ReadAppEntry
import com.tantawii.ahbarkapp.domain.usecases.appentry.SaveAppEntry
import com.tantawii.ahbarkapp.domain.usecases.news.GetNews
import com.tantawii.ahbarkapp.domain.usecases.news.NewsUseCases
import com.tantawii.ahbarkapp.domain.usecases.news.SearchNews
import com.tantawii.ahbarkapp.utils.Constants.BASE_URL
import com.tantawii.ahbarkapp.utils.Constants.NEWS_DATABASE_NAME
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
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }


    @Provides
    @Singleton
    fun  provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao


}