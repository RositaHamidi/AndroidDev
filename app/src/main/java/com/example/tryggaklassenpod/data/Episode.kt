package com.example.tryggaklassenpod.data

import kotlin.time.Duration
data class Episode(
    val id: Int,
    val imageUrl: String,
    val audio: String,
    val duration: Duration,

    val title: String,
    val description: String,
    val comments: List<Comment> = emptyList(),
    val categories: List<String> = emptyList()
)
