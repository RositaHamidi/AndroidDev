package com.example.tryggaklassenpod.dataClasses

data class Episode(
    val title: String,
    val description: String,

    val imageUrl: String,
    val episodeUrl: String,
    val duration: Int,

    val comments: List<Comments>,
    val categories: List<String>
)

