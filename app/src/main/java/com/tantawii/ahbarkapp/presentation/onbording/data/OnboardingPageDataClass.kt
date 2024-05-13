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
        title = "But I must explain to you how all mistaken",
        description = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system,",
        imageRes = R.drawable.onboarding1
    ),
    OnboardingPageDataClass(
        title = "At vero eos et accusamus et iusto",
        description = "expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself",
        imageRes = R.drawable.onboarding2
    ),
    OnboardingPageDataClass(
        title = "when our power of choice",
        description = "because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful",
        imageRes = R.drawable.onboarding3
    ),
)