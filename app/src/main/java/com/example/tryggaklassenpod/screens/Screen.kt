package com.example.tryggaklassenpod.screens

sealed class Screen(val route: String){
    object HomeScreen: Screen("homeScreen")
    object AboutScreen: Screen("aboutScreen")
    object PlayerScreen: Screen("playerScreen")
    object LoginScreen: Screen("LoginScreen")
    object AdminScreen: Screen("adminScreen")
    object UploadPodcast: Screen("uploadPodcast")
}