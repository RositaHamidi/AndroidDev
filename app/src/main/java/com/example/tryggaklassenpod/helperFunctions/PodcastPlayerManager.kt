package com.example.tryggaklassenpod.helperFunctions

import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

class PodcastPlayerManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0
    private var episodeDuration = 0
    private var currentEpisodeUrl: String? = null


    fun preloadEpisode(url: String) {
        Log.d("preloadEpisode", "Episode preload called .........................")
        Log.d("duration", "current $currentPosition.........")

//        url != currentEpisodeUrl &&
        if (currentEpisodeUrl == null) {

            Log.d("preloadEpisode", "inside episode preload .....x..x....x...x..x.........")
            currentEpisodeUrl = url
            mediaPlayer?.release()
            try {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(url)
                    prepareAsync()
                }
            } catch (e: IOException) {
                Log.d("preloadEpisode", "exception .....x..x....x...x..x.........")
                releasePlayer()
            }
        }
    }


    fun playEpisode(url: String) {
        Log.d("playEpisode", "playEpisode called .........................*****")
        try {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.pause()
                    currentPosition = it.currentPosition / 1000
                } else {
                    it.start()
                    it.seekTo(currentPosition * 1000)
//                    episodeDuration = it.duration / 1000
                }
            }

        } catch (e: IOException) {
            Log.d("playEpisode", "exception .....x..x....x...x..x.........")
            releasePlayer()
        }
    }

    fun pauseEpisode() {
        try {
            mediaPlayer?.let {
                currentPosition = it.currentPosition / 1000
                it.pause()
            }
        } catch (e: IOException) {
            Log.d("pausePlayer", "exception .....x..x....x...x..x.........")
            releasePlayer()
        }
    }

    fun stopEpisode() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            currentPosition = 0
        } catch (e: IOException) {
            Log.d("stopPlayer", "exception .....x..x....x...x..x.........")
            releasePlayer()
        }
    }

    fun releasePlayer() {
        Log.d("Release", "REALSESE MEEEE .........................")
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
        currentPosition = 0
        currentEpisodeUrl = null
        episodeDuration = 0
    }

    fun seekTo(position: Int) {
        try {
            mediaPlayer?.seekTo(position*1000)
            currentPosition = position
        } catch (e: IOException) {
            Log.d("seekTo", "exception .....x..x....x...x..x.........")
            releasePlayer()
        }
    }

    fun replay10Seconds() {
        try {
            mediaPlayer?.let {

                val newPosition = it.currentPosition - 10000
                currentPosition = if (newPosition >= 0) (
                        newPosition / 1000
                ) else {
                    0
                }
                it.seekTo(currentPosition*1000)
            }
        } catch (e: IOException) {
            Log.d("replay10", "exception .....x..x....x...x..x.........")
            releasePlayer()
        }
    }

    fun forward30Seconds() {
        try {
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
        } catch (e: IOException) {
            Log.d("forward30", "exception .....x..x....x...x..x.........")
            releasePlayer()
        }
    }

    fun getCurrentInSeconds(): Int {
        try {
            mediaPlayer?.let {
                if (it.isPlaying) {
                     currentPosition = it.currentPosition / 1000
                }
            }
            return currentPosition
        } catch (e: IOException) {
            Log.d("getCurrentTime", "exception .....x..x....x...x..x.........")
            releasePlayer()
        }
        return currentPosition
    }

    fun canPlayerStart():Boolean {
        return mediaPlayer != null
    }

    fun playAgain() {
        currentPosition = 0
    }
}
