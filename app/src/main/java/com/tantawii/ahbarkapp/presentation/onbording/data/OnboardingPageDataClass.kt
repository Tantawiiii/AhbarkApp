package com.tantawii.ahbarkapp.presentation.onbording.data

import androidx.annotation.DrawableRes
import com.tantawii.ahbarkapp.R

data class OnboardingPageDataClass(
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int,
)


val OnboardingPages = listOf(
    OnboardingPageDataClass(
        title = "Collection of Newspaper Headlines",
        description = "It is always best to start online if you are looking for newspapers. A growing number of websites provide access to digitised newspapers.",
        imageRes = R.drawable.onboarding1
    ),
    OnboardingPageDataClass(
        title = "Find your Global Articles",
        description = "expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself",
        imageRes = R.drawable.onboarding2
    ),
    OnboardingPageDataClass(
        title = "Breaking News, Latest Updated News",
        description = "News is an energetic fast paced promotional template for After Effects. Broadcast News and Business Report template",
        imageRes = R.drawable.onboarding3
    ),
)