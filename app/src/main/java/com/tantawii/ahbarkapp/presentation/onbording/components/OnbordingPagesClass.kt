package com.tantawii.ahbarkapp.presentation.onbording.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.tantawii.ahbarkapp.R
import com.tantawii.ahbarkapp.utils.Dimens.MediumPadding1
import com.tantawii.ahbarkapp.utils.Dimens.MediumPadding2
import com.tantawii.ahbarkapp.presentation.onbording.data.OnboardingPageDataClass
import com.tantawii.ahbarkapp.presentation.onbording.data.OnboardingPages
import com.tantawii.ahbarkapp.ui.theme.AhbarkAppTheme
import com.tantawii.ahbarkapp.utils.Dimens.MediumPadding0

@Composable
fun OnboardingPagesClass(
    modifier: Modifier = Modifier,
    page: OnboardingPageDataClass,
) {

    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MediumPadding0))
        Text(
            text = page.title,
            modifier = Modifier.padding(MediumPadding1),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(MediumPadding1),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = false)
@Composable
fun OnboardingPagePreview() {
    AhbarkAppTheme {
        OnboardingPagesClass(page = OnboardingPages[0])
    }
}