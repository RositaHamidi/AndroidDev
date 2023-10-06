package com.example.tryggaklassenpod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.tryggaklassenpod.dataClasses.episodesList
import com.example.tryggaklassenpod.navigations.Navigation
import com.example.tryggaklassenpod.screens.PlayerScreen
import com.example.tryggaklassenpod.ui.theme.TryggaKlassenPodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryggaKlassenPodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayerScreen(
                        episodesList[0],
                        goBack = { }
                    )
                }
            }
        }
    }
}

