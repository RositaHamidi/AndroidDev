package com.example.tryggaklassenpod.sealed

sealed class DatabaseResult {
    data class Success(val message: String) : DatabaseResult()
    data class Failure(val error: String) : DatabaseResult()
}
