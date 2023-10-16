package com.example.tryggaklassenpod.helperFunctions

import android.media.MediaPlayer
import android.util.Log

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
            prepareAsync() // prepareAsync, does the preparation on a background thread.
        }

        mediaPlayer?.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.seekTo(currentPosition * 1000)
            mediaPlayer.start()
            episodeDuration = mediaPlayer.duration / 1000

        }
    }

    fun pauseEpisode() {
        mediaPlayer?.let {
            currentPosition = it.currentPosition / 1000
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
        mediaPlayer?.seekTo(position*1000)
        currentPosition = position
    }

    fun replay10Seconds() {
        mediaPlayer?.let {

            val newPosition = it.currentPosition - 10000
            currentPosition = if (newPosition >= 0) (
                    newPosition / 1000
            ) else {
                0
            }
            it.seekTo(currentPosition*1000)
        }
    }

    fun forward30Seconds() {
        mediaPlayer?.let {
            val newPosition = it.currentPosition + 30000
            val duration = it.duration

            if (newPosition < duration) {
                currentPosition = newPosition / 1000
                it.seekTo(newPosition)
            } else {
                currentPosition = duration / 1000
                it.seekTo(duration)
            }
        }
    }

    fun getCurrentInSeconds(): Int {
        mediaPlayer?.let {
            if (it.isPlaying) {
                 currentPosition = it.currentPosition / 1000
            }
        }
        return currentPosition
    }

    fun canPlayerStart():Boolean {
        return mediaPlayer != null
    }
}

