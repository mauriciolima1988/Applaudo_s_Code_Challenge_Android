package com.example.applaudochallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applaudochallenge.ui.profile.ProfileScreen
import com.example.applaudochallenge.ui.search.SearchScreen

@Composable
fun MainNavigation(
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main) {
            MainScreen(
                onSearchClick = { navController.safeNavigate(NavItem.Search.route) },
                onProfileClick = { navController.safeNavigate(NavItem.Profile.route) },
                onCardClick = { navController.safeNavigate(NavItem.Details.createRoute(it)) }
            )
        }
        composable(NavItem.Search) {
            SearchScreen {
                navController.popBackStack()
            }
        }
        composable(NavItem.Profile) {
            ProfileScreen {
                navController.popBackStack()
            }
        }
        composable(NavItem.Details) {
            DetailsScreen {
                navController.popBackStack()
            }
        }
    }
}

private fun NavHostController.safeNavigate(route: String) {
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(route = navItem.route, arguments = navItem.args) {
        content(it)
    }
}