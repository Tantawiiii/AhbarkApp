package com.tantawii.ahbarkapp.presentation.bookmark.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tantawii.ahbarkapp.domain.usecases.news.NewsUseCases
import com.tantawii.ahbarkapp.presentation.bookmark.data.BookmarkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getBookmarkArticles()
    }
    private fun getBookmarkArticles(){

        newsUseCases.selectedArticles().onEach {
            _state.value = _state.value.copy(article = it.asReversed())
        }.launchIn(viewModelScope)

    }




}