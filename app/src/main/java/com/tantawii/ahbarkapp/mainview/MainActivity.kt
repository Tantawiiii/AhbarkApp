package com.tantawii.ahbarkapp.mainview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tantawii.ahbarkapp.presentation.common.EmptyScreen
import com.tantawii.ahbarkapp.presentation.navgraph.NavGraph

import com.tantawii.ahbarkapp.ui.theme.AhbarkAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModeL by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = {
                viewModeL.splashCondition.value
            }
            )
        }

        setContent {
            AhbarkAppTheme(dynamicColor = false) {

                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme
                                .colorScheme
                                .background
                        )
                        .fillMaxSize()
                ) {
                    NavGraph(startDestination = viewModeL.startDestination.value)
                  //  EmptyScreen()
                }


            }
        }
    }
}

