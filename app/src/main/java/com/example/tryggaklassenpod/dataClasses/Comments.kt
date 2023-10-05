package com.example.tryggaklassenpod.dataClasses

import java.util.Date

data class Comments(
    val commentId: Int,
    val comment: String,
    val author: String,

    val approved: Boolean = false,
    val createdAt: Date = Date(),
    val likes: Int = 0
)
