package com.example.tryggaklassenpod.helperFunctions

import android.net.Uri
import com.google.firebase.storage.StorageReference

class AudioUploader(
    private val storageRef: StorageReference,
) {
    // Define a functional interface (callback) to handle the upload result
    interface UploadCallback {
        fun onSuccess(downloadUrl: String?)
        fun onFailure(exception: Exception)
    }

    fun uploadAudio(name: String, audioUri: Uri, callback: (Any) -> Unit) {
        // Generate a unique storage reference for the audio file
        val audioStorageRef = storageRef.child("audios/${audioUri.lastPathSegment}")

        // Upload the audio file to Firebase Storage
        val uploadTask = audioStorageRef.putFile(audioUri)

        // Register observers to listen for when the upload is done or if it fails
        uploadTask.addOnSuccessListener { taskSnapshot ->
            // Audio file uploaded successfully
            val downloadUrl = taskSnapshot.metadata?.reference?.downloadUrl.toString()
//            callback.onSuccess(downloadUrl)
        }.addOnFailureListener { exception ->
//            callback.onFailure(exception)
        }
    }
}
