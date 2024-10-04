package com.example.handtools

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "menu") {
        composable("menu") { MenuPrincipal(navHostController) }
        composable("crear") { PantallaMenuCrear(navegarA = {route -> navHostController.navigate(route) }) }
        composable("ajustes") {PantallaAjustes() }
        composable("acerca_de") { PantallaAcercaDe() }
        composable("apoyar") { PantallaApoyar() }
    }
}