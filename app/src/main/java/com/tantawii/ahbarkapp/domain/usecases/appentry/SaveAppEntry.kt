package com.tantawii.ahbarkapp.domain.usecases

import com.tantawii.ahbarkapp.domain.manger.LocalUserManger

class SaveAppEntry (
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}