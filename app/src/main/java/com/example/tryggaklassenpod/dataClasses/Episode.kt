package com.example.tryggaklassenpod.dataClasses

import org.w3c.dom.Comment

data class Episode(
    val id: Int,
    val imageUrl: String,
    val episodeUrl: String,
    val duration: Int,

    val title: String,
    val description: String,
    val comments: List<Comments> = emptyList(),
    val categories: List<String> = emptyList()
)
