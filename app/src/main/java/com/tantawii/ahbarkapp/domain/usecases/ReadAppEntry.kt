package com.tantawii.ahbarkapp.domain.usecases

import com.tantawii.ahbarkapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(): Flow<Boolean>{
       return localUserManger.readAppEntry()
    }
}