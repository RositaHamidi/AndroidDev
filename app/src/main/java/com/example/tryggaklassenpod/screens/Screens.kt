package com.example.tryggaklassenpod.screens


sealed class Screen(val route: String){
    object HomeScreen: Screen("homeScreen")
    object AboutScreen: Screen("aboutScreen")
<<<<<<< HEAD
    object LoginScreen: Screen("LoginScreen")

    /*
    add your screens here as well as the Navigation.kt file
     */

=======
    object PlayerScreen: Screen("playerScreen")
>>>>>>> ea271c278f392ec91d37d577c5a186ab15542f4c
}