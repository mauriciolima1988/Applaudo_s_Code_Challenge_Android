package com.example.applaudochallenge.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.applaudochallenge.Constants.INFO_SCREEN
import com.example.applaudochallenge.Constants.MAIN_SCREEN
import com.example.applaudochallenge.Constants.INFO_ARGS_ID
import com.example.applaudochallenge.Constants.PROFILE_SCREEN
import com.example.applaudochallenge.Constants.SEARCH_SCREEN

enum class NavArgs(
    val key: String,
    val navigationType: NavType<*>
) {
    InfoId(INFO_ARGS_ID, NavType.IntType)
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
    object Details : NavItem(INFO_SCREEN, listOf(NavArgs.InfoId)) {
        fun createRoute(id: Int) = "$baseRoute/$id"
    }
}
