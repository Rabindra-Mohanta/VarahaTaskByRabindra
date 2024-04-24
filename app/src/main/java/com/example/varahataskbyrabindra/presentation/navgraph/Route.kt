package com.example.varahataskbyrabindra.presentation.navgraph

import com.example.varahataskbyrabindra.util.Constants.detailsScreen
import com.example.varahataskbyrabindra.util.Constants.homeScreen

sealed class Route(val route:String) {
    object HomeScreen:Route(homeScreen)
    object DetailsScreen:Route(detailsScreen)
}
