package com.example.tryggaklassenpod.screens


sealed class Screen(val route: String){
    object HomeScreen: Screen("homeScreen")
    object AboutScreen: Screen("aboutScreen")
    object AdminScreen: Screen("adminScreen")
    object UploadPodcast: Screen("uploadPodcast")

    /*
    add your screens here as well as the Navigation.kt file
     */
    object PlayerScreen: Screen("playerScreen")

    object OwnerPage: Screen("OwnerPage")

}