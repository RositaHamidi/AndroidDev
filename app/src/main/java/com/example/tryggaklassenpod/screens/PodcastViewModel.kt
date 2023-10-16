package com.example.tryggaklassenpod.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tryggaklassenpod.dataClasses.Episode
import com.example.tryggaklassenpod.helperFunctions.PodcastPlayerManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

sealed interface PodcastUiState {
    data class Success(val episodes: List<Episode>): PodcastUiState
    object Error : PodcastUiState
    object Loading: PodcastUiState
}

class PodcastViewModel: ViewModel() {

    private val _podcastUiState = mutableStateOf<PodcastUiState>(PodcastUiState.Loading)
    val podcastUiState: State<PodcastUiState> get() = _podcastUiState

    private val episodeMap = mutableMapOf<Int, Episode>()


    init {
        fetchPodcastData()
    }

    private fun fetchPodcastData() {
        val epList = mutableListOf<Episode>()
        _podcastUiState.value = PodcastUiState.Loading

        FirebaseDatabase.getInstance().getReference("podcast/episodes")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    for (dataSnap in snapshot.children) {

                        val episodeItem = dataSnap.getValue(Episode::class.java)

                        if (episodeItem != null)
                            epList.add(episodeItem)

                        episodeItem?.id?.let { episodeId ->
                            episodeMap[episodeId] = episodeItem
                        }
                    }
                    _podcastUiState.value = PodcastUiState.Success(epList)
                }

                override fun onCancelled(error: DatabaseError) {
                    _podcastUiState.value = PodcastUiState.Error
                }
            })
    }


    fun getEpisodeById(episodeId: Int): Episode? {
        return episodeMap[episodeId]
    }

    val player = PodcastPlayerManager()
    var episodeUrl:String by mutableStateOf("")
    var isPlaying: Boolean by mutableStateOf(false)
    var newPosition: Int by mutableIntStateOf(0)
    var sliderPosition: Float by mutableFloatStateOf(0F)

}