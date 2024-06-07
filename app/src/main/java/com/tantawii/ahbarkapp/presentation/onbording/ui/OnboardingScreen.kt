package com.tantawii.ahbarkapp.presentation.onbording.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tantawii.ahbarkapp.utils.Dimens.MediumPadding2
import com.tantawii.ahbarkapp.utils.Dimens.PageIndicatorWidth
import com.tantawii.ahbarkapp.presentation.common.NewsButton
import com.tantawii.ahbarkapp.presentation.common.NewsTextButton
import com.tantawii.ahbarkapp.presentation.onbording.components.OnboardingPagesClass
import com.tantawii.ahbarkapp.presentation.onbording.components.PageIndicator
import com.tantawii.ahbarkapp.presentation.onbording.data.OnboardingPages
import com.tantawii.ahbarkapp.presentation.onbording.viewmodel.OnBoardingEvents
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onEvent: (OnBoardingEvents) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        val pagerState = rememberPagerState(initialPage = 0) {
            OnboardingPages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnboardingPagesClass(page = OnboardingPages[index])
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            PageIndicator(
                modifier = Modifier
                    .width(PageIndicatorWidth)
                    .padding(bottom = 15.dp),
                pageSize = OnboardingPages.size,
                selectedPage = pagerState.currentPage
            )


            Row(
                //  modifier = Modifier.padding(bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                NewsButton(text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                onEvent(OnBoardingEvents.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }


        }
    }

}