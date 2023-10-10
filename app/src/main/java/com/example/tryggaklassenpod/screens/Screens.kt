package com.example.tryggaklassenpod.screens


sealed class Screen(val route: String){
    object HomeScreen: Screen("homeScreen")
    object AboutScreen: Screen("aboutScreen")

    /*
    add your screens here as well as the Navigation.kt file
     */

}