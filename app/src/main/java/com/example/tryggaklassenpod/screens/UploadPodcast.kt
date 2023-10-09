package com.example.tryggaklassenpod.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tryggaklassenpod.dataClasses.Episode
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UploadPodcast(navController: NavController) {
    var podcastName by remember { mutableStateOf("") }
    var podcastDescription by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.getReference("podcasts")

    Column(
        modifier = Modifier.padding(16.dp)
    )
    {
        Text(
            text = "Add the title/name of the podcast",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = podcastName,
            onValueChange = { newValue ->
                podcastName = newValue
            },
            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Add the summary/description of the podcast",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = podcastDescription,
            onValueChange = { newValue ->
                podcastDescription = newValue
            },
            label = { Text("Description ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Log.d("YourTag", "podcastName: $podcastName")
        Log.d("YourTag", "podcastDescription: $podcastDescription")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
            }
        ) {
            Text(text = "Upload podcast")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    val newEpisodeReference = databaseReference
                        .child("episodes")
                        .push()

                    // Create episode data
                    val episodeData = Episode(
                        id = 112,
                        episodeUrl = "djkjsadhkasdhkadhka",
                        duration = 60,
                        imageUrl = "hhhihohohohh",
                        title = podcastName,
                        description = podcastDescription,
                        // Add more fields as needed
                    )

                    // Set episode data
                    newEpisodeReference.setValue(episodeData).addOnSuccessListener {
                    podcastName = ""
                    podcastDescription = ""
                    }.addOnFailureListener {exception ->
                        Log.e("YourTag", "Error adding episode: ${exception.message}")
                    }

                }
            ) {
                Text(text = "Done")
            }
        }
    }
}

