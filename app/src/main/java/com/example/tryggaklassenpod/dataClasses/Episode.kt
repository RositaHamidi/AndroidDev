package com.example.tryggaklassenpod.dataClasses

data class Episode(


    val description: String = "",
    val duration: Int =0,
    val episodeUrl: String? = "",
    val id: Int = 0,
    val imageUrl: String = "",
    val title: String = "",

//    val comments: List<Comments> = emptyList(),
//    val categories: List<String> = emptyList()
)

