package com.example.tryggaklassenpod.navigations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import com.example.tryggaklassenpod.screens.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tryggaklassenpod.screens.AboutScreen
import com.example.tryggaklassenpod.screens.HomeScreen
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.tryggaklassenpod.screens.LoginScreen

=======
=======
import com.example.tryggaklassenpod.screens.AdminScreen
import com.example.tryggaklassenpod.screens.UploadPodcast
>>>>>>> bd5112751105b8ed9a9022352eadec84b66c2816
import com.example.tryggaklassenpod.screens.PlayerScreen
import com.example.tryggaklassenpod.screens.PodcastViewModel
>>>>>>> ea271c278f392ec91d37d577c5a186ab15542f4c



@Composable
fun Navigation() {
    val navController = rememberNavController()
<<<<<<< HEAD
<<<<<<< HEAD
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route ){

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
=======
=======

>>>>>>> bd5112751105b8ed9a9022352eadec84b66c2816
    val podcastViewModel: PodcastViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
>>>>>>> ea271c278f392ec91d37d577c5a186ab15542f4c

        composable(route = Screen.HomeScreen.route){
            HomeScreen(podcastUiState = podcastViewModel.podcastUiState, navController = navController)
        }

        composable(route = Screen.AboutScreen.route){
            AboutScreen(navController = navController)
        }

        composable(route = Screen.AdminScreen.route){
            AdminScreen(navController = navController)
        }

        composable(route = Screen.UploadPodcast.route){
            UploadPodcast(navController = navController)
        }


        composable(
            route = "${Screen.PlayerScreen.route}/{episodeId}",
            arguments = listOf(
                navArgument(name = "episodeId") {
                    type = NavType.IntType
                }
            )
        ) {index ->
            val episodeId = index.arguments?.getInt("episodeId")
            PlayerScreen(
                episodeId = episodeId,
                viewModel = podcastViewModel,
                goBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}