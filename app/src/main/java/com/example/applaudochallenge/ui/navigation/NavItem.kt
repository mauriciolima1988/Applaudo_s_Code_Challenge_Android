package com.example.applaudochallenge.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.applaudochallenge.ui.Constants.DETAILS_SCREEN
import com.example.applaudochallenge.ui.Constants.MAIN_SCREEN
import com.example.applaudochallenge.ui.Constants.TVSHOW_ARGS_ID
import com.example.applaudochallenge.ui.Constants.PROFILE_SCREEN
import com.example.applaudochallenge.ui.Constants.SEARCH_SCREEN
import com.example.applaudochallenge.ui.Constants.SEASON_ARGS_ID
import com.example.applaudochallenge.ui.Constants.SEASON_SCREEN

enum class NavArgs(
    val key: String,
    val navigationType: NavType<*>
) {
    InfoId(TVSHOW_ARGS_ID, NavType.IntType),
    SeasonId(SEASON_ARGS_ID, NavType.IntType)
}

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArgs> = emptyList()
) {
    val route = run {
        val argKeys = navArgs
            .map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(name = it.key) {
            type = it.navigationType
        }
    }

    object Main : NavItem(MAIN_SCREEN)
    object Search : NavItem(SEARCH_SCREEN)
    object Profile : NavItem(PROFILE_SCREEN)
    object Details : NavItem(DETAILS_SCREEN, listOf(NavArgs.InfoId)) {
        fun createRoute(id: Int) = "$baseRoute/$id"
    }
    object Season : NavItem(SEASON_SCREEN, listOf(NavArgs.InfoId, NavArgs.SeasonId)) {
        fun createRoute(id: Int, seasonNumber: Int) = "$baseRoute/$id/$seasonNumber"
    }
}
