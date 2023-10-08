package com.example.tryggaklassenpod.screens

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
                        val podcastData = mapOf(
                            "title" to podcastName,
                            "description" to podcastDescription
                            // Add more fields as needed
                        )
                        // Push data to Firebase
                        val newPodcastReference = databaseReference.push()
                        newPodcastReference.setValue(podcastData)

                        // Clear input fields
                        podcastName = ""
                        podcastDescription = ""

                    }
                ) {
                    Text(text = "Done")
                }
            }
        }
    }

