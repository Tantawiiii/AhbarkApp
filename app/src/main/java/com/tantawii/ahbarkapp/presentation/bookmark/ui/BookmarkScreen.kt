package com.tantawii.ahbarkapp.presentation.bookmark.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.tantawii.ahbarkapp.R
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.presentation.bookmark.data.BookmarkState
import com.tantawii.ahbarkapp.presentation.common.ArticlesList
import com.tantawii.ahbarkapp.presentation.navgraph.Route
import com.tantawii.ahbarkapp.utils.Dimens.MediumPadding1

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(articles = state.article, onClick = {
            navigateToDetails(it)
        })

    }

}