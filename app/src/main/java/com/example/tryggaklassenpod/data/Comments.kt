package com.example.tryggaklassenpod.data

import java.util.Date

data class Comment(
    val commentId: Int,
    val comment: String,
    val author: String,

    val approved: Boolean = false,
    val createdAt: Date = Date(),
    val likes: Int = 0
)
