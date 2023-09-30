package com.example.tryggaklassenpod.data

import java.time.Duration

data class Episode(
    val imageUrl: String,
    val audio: String,
    val duration: Duration,

    val title: String,
    val description: String,

    val comments: List<Comments>,
    val categories: List<String>
)
