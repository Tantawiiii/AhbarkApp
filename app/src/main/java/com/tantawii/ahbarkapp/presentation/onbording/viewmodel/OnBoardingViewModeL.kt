package com.tantawii.ahbarkapp.presentation.onbording.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tantawii.ahbarkapp.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModeL @Inject constructor(
    private  val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun onEvent(events: OnBoardingEvents){
        when(events){
            is OnBoardingEvents.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private  fun saveAppEntry(){
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }

}