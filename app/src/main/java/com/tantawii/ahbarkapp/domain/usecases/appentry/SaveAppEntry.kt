package com.tantawii.ahbarkapp.domain.usecases.appentry

import com.tantawii.ahbarkapp.domain.manger.LocalUserManger

class SaveAppEntry (
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}