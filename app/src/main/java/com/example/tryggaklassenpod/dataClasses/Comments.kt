package com.example.tryggaklassenpod.dataClasses

import java.util.Date

data class Comments(
    val comment: String,
    val author: String,

    val createdAt: Date,
    val approved: Boolean = false,

    val likes: Int = 0
)

