package com.tantawii.ahbarkapp.presentation.bottom_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.tantawii.ahbarkapp.R
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.presentation.bookmark.ui.BookmarkScreen
import com.tantawii.ahbarkapp.presentation.bookmark.viewmodel.BookmarkViewModel
import com.tantawii.ahbarkapp.presentation.bottom_navigator.components.BottomNavigation
import com.tantawii.ahbarkapp.presentation.bottom_navigator.components.BottomNavigationItem
import com.tantawii.ahbarkapp.presentation.details.ui.DetailsScreen
import com.tantawii.ahbarkapp.presentation.details.viewmodel.DetailsEvent
import com.tantawii.ahbarkapp.presentation.details.viewmodel.DetailsViewModel
import com.tantawii.ahbarkapp.presentation.home.HomeScreen
import com.tantawii.ahbarkapp.presentation.home.HomeViewModel
import com.tantawii.ahbarkapp.presentation.navgraph.Route
import com.tantawii.ahbarkapp.presentation.search.SearchScreen
import com.tantawii.ahbarkapp.presentation.search.SearchViewModel


@Composable
fun Navigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.ic_home,
                text = "Home"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_search,
                text = "Search"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_bookmark,
                text = "Bookmark"
            ),
        )
    }


    val navController = rememberNavController()
    val backStackEntryState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember(key1 = backStackEntryState) {
        when (backStackEntryState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }


    val isBottomBarVisible = remember(key1 = backStackEntryState) {
        backStackEntryState?.destination?.route == Route.HomeScreen.route ||
                backStackEntryState?.destination?.route == Route.SearchScreen.route ||
                backStackEntryState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->

                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }

                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            modifier = Modifier.padding(bottom = bottomPadding),
            startDestination = Route.HomeScreen.route
        ) {

            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable( route = Route.SearchScreen.route ) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    }

                )
            }

            composable( route = Route.DetailsScreen.route ) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current, viewModel.sideEffect,Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(article = article, event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }

            composable( route = Route.BookmarkScreen.route ){
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                
                BookmarkScreen(state = state, navigateToDetails = {article ->
                    navigateToDetails(
                        navController = navController,
                        article = article
                    )
                } )
            }
        }
    }

}

fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}












