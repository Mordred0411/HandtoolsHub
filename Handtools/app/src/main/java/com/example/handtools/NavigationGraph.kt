package com.example.handtools

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.handtools.data.NoteRepository
import com.example.handtools.iu.NotesScreen
import com.example.handtools.viewmodel.NoteViewModel
import com.example.handtools.viewmodel.NoteViewModelFactory

@Composable
fun NavigationGraph(navHostController: NavHostController, repository: NoteRepository) {
    NavHost(navController = navHostController, startDestination = "menu") {
        composable("menu") { MenuPrincipal(navHostController) }
        composable("crear") { PantallaMenuCrear( navController = navHostController) }
        composable("notas") {
            val noteViewModel: NoteViewModel = viewModel(
                factory = NoteViewModelFactory(repository)
            )
            NotesScreen(noteViewModel = noteViewModel)
        }
        composable("ajustes") {PantallaAjustes() }
        composable("acerca_de") { PantallaAcercaDe() }
        composable("apoyar") { PantallaApoyar() }
    }
}