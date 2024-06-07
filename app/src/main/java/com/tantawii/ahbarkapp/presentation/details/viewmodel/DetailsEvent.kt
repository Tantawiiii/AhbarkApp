package com.tantawii.ahbarkapp.presentation.details.viewmodel

import com.tantawii.ahbarkapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()

}
