package com.example.tryggaklassenpod.navigations

import androidx.compose.runtime.Composable
import com.example.tryggaklassenpod.screens.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tryggaklassenpod.screens.AboutScreen
import com.example.tryggaklassenpod.screens.HomeScreen
import com.example.tryggaklassenpod.screens.AdminScreen
import com.example.tryggaklassenpod.screens.UploadPodcast


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AdminScreen.route ){


        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
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


    }
}
