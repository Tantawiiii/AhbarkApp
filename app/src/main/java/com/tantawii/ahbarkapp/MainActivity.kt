package com.tantawii.ahbarkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.tantawii.ahbarkapp.presentation.onbording.ui.OnboardingScreen

import com.tantawii.ahbarkapp.ui.theme.AhbarkAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            AhbarkAppTheme {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    OnboardingScreen()
                }
            }
        }
    }
}

