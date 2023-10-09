package com.example.tryggaklassenpod.navigations

import androidx.compose.runtime.Composable
import com.example.tryggaklassenpod.screens.Screen


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tryggaklassenpod.screens.AboutScreen
import com.example.tryggaklassenpod.screens.HomeScreen
import com.example.tryggaklassenpod.screens.LoginScreen



@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route ){

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }

        composable(route = Screen.AboutScreen.route){
            AboutScreen(navController = navController)
        }



    }
}
