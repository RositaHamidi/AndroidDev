package com.example.tryggaklassenpod.helperFunctions

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.ui.platform.LocalContext


class PodcastPlayerManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0
    private var episodeDuration = 0

    fun playEpisode(url: String) {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.reset()
            }
        }
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
        }

        mediaPlayer?.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.seekTo(currentPosition)
            mediaPlayer.start()
            episodeDuration = mediaPlayer.duration
        }
        currentPosition = 0
    }

    fun pauseEpisode() {
        mediaPlayer?.let {
            currentPosition = it.currentPosition
            it.pause()
        }
    }

    fun stopEpisode() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        currentPosition = 0
    }

    fun releasePlayer() {
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
        currentPosition = 0
    }

    fun seekTo(position: Int) {
        val pos = position*1000
        Log.d("MEDIA-PLAYER TESTING currentPosition", "$currentPosition, $position,  $pos")
        mediaPlayer?.seekTo(pos)
        currentPosition = position
    }

    fun replay10Seconds() {
        mediaPlayer?.let {
            val newPosition = it.currentPosition - 10000
//            Log.d("MEDIA-PLAYER TESTING currentPosition", "$currentPosition, $position,  $pos")

            currentPosition = if (newPosition >= 0) {
                newPosition
            } else {
                0
            }
            it.seekTo(currentPosition)
        }
    }

    fun forward30Seconds() {
        mediaPlayer?.let {
            val newPosition = it.currentPosition + 30000
            val duration = it.duration
//            Log.d("MEDIA-PLAYER TESTING currentPosition", "$currentPosition, $position,  $pos")

            if (newPosition < duration) {
                currentPosition = newPosition
                it.seekTo(currentPosition)
            } else {
                currentPosition = duration
                it.seekTo(currentPosition)
            }
        }
    }

    fun getEpisodeDuration(): Int {
        return episodeDuration / 1000
    }

    fun getCurrentPosition(): Int {
        mediaPlayer?.let {
            if (it.isPlaying) {
                currentPosition = it.currentPosition
            }
        }
        return currentPosition
    }
}

