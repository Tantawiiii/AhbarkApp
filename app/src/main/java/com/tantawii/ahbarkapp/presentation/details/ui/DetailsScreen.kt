package com.tantawii.ahbarkapp.presentation.details.ui

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tantawii.ahbarkapp.R
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.domain.model.Source
import com.tantawii.ahbarkapp.presentation.details.components.DetailsTopBar
import com.tantawii.ahbarkapp.presentation.details.viewmodel.DetailsEvent
import com.tantawii.ahbarkapp.ui.theme.AhbarkAppTheme
import com.tantawii.ahbarkapp.utils.Dimens.ArticleImageHeight
import com.tantawii.ahbarkapp.utils.Dimens.MediumPadding0
import com.tantawii.ahbarkapp.utils.Dimens.MediumPadding1


@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.type = "text/plain"
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.saveArticles)
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )
                Spacer(modifier = Modifier.height(MediumPadding0))
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )

            }
        }

    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    AhbarkAppTheme(dynamicColor = false) {
        DetailsScreen(article = Article(
            author = "Vinamrata Chaturvedi, Quartz",
            content = "On January 3, 2009, Bitcoins creator, Satoshi Nakamoto, mined the first block of the Bitcoin blockchain, known as the Genesis Block, which contained a reward of 50 Bitcoin. The technical foundations … [+1156 chars]",
            description = "On May 22, 2010, a man in Florida paid 10,000 Bitcoin for pizza.Read more...",
            publishedAt = "2024-05-20T13:20:00Z",
            source = Source(
                id = "cnn",
                name = "Gizmodo.com"
            ),
            title = "Everything to Know About Bitcoin Pizza Day",
            url = "https://gizmodo.com/bitcoin-pizza-day-date-origin-history-cryptocurrency-1851487831",
            urlToImage =  "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/98aec6479bad523f5c89763f4acf0cf9.jpg",
        ),
            event = {}) {


        }
    }
}
