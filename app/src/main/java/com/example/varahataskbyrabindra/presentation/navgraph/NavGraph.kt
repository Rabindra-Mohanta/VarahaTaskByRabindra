package com.example.varahataskbyrabindra.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.varahataskbyrabindra.presentation.details.DetailsScreen
import com.example.varahataskbyrabindra.presentation.home.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route)
        {
            HomeScreen()
        }
        composable(route = Route.DetailsScreen.route)
        {
            DetailsScreen()
        }
    }
}

