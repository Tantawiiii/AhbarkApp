package com.tantawii.ahbarkapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.tantawii.ahbarkapp.presentation.home.HomeScreen
import com.tantawii.ahbarkapp.presentation.home.HomeViewModel
import com.tantawii.ahbarkapp.presentation.onbording.ui.OnboardingScreen
import com.tantawii.ahbarkapp.presentation.onbording.viewmodel.OnBoardingViewModeL
import com.tantawii.ahbarkapp.presentation.search.SearchScreen
import com.tantawii.ahbarkapp.presentation.search.SearchViewModel


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {

                val viewModel: OnBoardingViewModeL = hiltViewModel()
                OnboardingScreen(
                    onEvent = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {

                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})

        
            }
        }


    }


}