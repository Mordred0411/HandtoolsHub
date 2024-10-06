package com.example.handtools

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.handtools.iu.NotesScreen

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "menu") {
        composable("menu") { MenuPrincipal(navHostController) }
        composable("crear") { PantallaMenuCrear( navController = navHostController) }
        composable("notas") { NotesScreen() }
        composable("ajustes") {PantallaAjustes() }
        composable("acerca_de") { PantallaAcercaDe() }
        composable("apoyar") { PantallaApoyar() }
    }
}