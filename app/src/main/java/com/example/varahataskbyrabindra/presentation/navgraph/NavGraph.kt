package com.example.varahataskbyrabindra.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.varahataskbyrabindra.presentation.home.HomeScreen

//nav graph starting and screens list
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route)
        {
            HomeScreen()
        }
    }
}

