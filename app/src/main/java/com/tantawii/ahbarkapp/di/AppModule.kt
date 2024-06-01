package com.tantawii.ahbarkapp.di

import android.app.Application
import com.tantawii.ahbarkapp.data.manger.LocalUserManagerImpl
import com.tantawii.ahbarkapp.domain.manger.LocalUserManger
import com.tantawii.ahbarkapp.domain.usecases.appentry.AppEntryUseCases
import com.tantawii.ahbarkapp.domain.usecases.appentry.ReadAppEntry
import com.tantawii.ahbarkapp.domain.usecases.appentry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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


}