package com.example.tryggaklassenpod.helperFunctions

import android.util.Log
import com.example.tryggaklassenpod.dataClasses.Episode
import com.google.firebase.database.FirebaseDatabase

class EpisodeFetcher {
    private val database = FirebaseDatabase.getInstance()
    private val databaseReference = database.getReference("podcast")

    fun fetchEpisodes(callback: (List<Episode>) -> Unit) {
        val episodes: MutableList<Episode> = mutableListOf()
        databaseReference.child("episodes").get().addOnSuccessListener { snapshot ->
            snapshot.children.mapNotNull {
                val episode = it.getValue(Episode::class.java)
                episode?.id = it.key?.toInt() ?: 0
                if (episode != null) {
                    episodes.add(episode)
                }
                Log.d("episodes", "episodes $episodes")
            }
            callback(episodes)
        }.addOnFailureListener { exception ->
            // Handle the failure
        }
    }
}


