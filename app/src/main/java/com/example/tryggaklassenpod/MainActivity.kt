package com.example.tryggaklassenpod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.tryggaklassenpod.navigations.Navigation
import com.example.tryggaklassenpod.ui.theme.TryggaKlassenPodTheme
import com.example.tryggaklassenpod.veiwModel.GeneralViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: GeneralViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)
        setContent {
            TryggaKlassenPodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(viewModel)
                }
            }
        }
    }
}

