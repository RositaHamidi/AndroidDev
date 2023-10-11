package com.example.tryggaklassenpod.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryggaklassenpod.dataClasses.Episode
import com.example.tryggaklassenpod.ui.components.ErrorScreen
import com.example.tryggaklassenpod.ui.components.LoadingScreen


@Composable
fun HomeScreen(podcastUiState: State<PodcastUiState>, navController: NavController){
    when (val podcast = podcastUiState.value) {
        is PodcastUiState.Loading -> LoadingScreen()
        is PodcastUiState.Success -> SuccessScreen(podcast.episodes, navController)
        is PodcastUiState.Error -> ErrorScreen(errorMessage = "Something went wrong. Please try again.",
            onRetry = { /* TODO somehow??? */ })
    }
}

@Composable
fun SuccessScreen(episodes: List<Episode>, navController: NavController) {
    LazyColumn {
        items(episodes) {item ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .height(150.dp)
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(
                            route = "${Screen.PlayerScreen.route}/${item.id}"
                        ).toString()
                    }
            ) {
                Text(
                    text = item.title,
                    fontSize = 50.sp
                )
            }
        }
    }
}