package com.example.tryggaklassenpod.helperFunctions

import android.media.MediaPlayer


class PodcastPlayerManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0
    private var duration = 0

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
        }
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

    fun getFullDuration(): Int {
        return duration
    }

//    fun currentDuration(): Float {
//        return currentPosition
//    }
}