package com.example.tryggaklassenpod.helperFunctions

import android.net.Uri
import com.google.firebase.storage.StorageReference

class AudioUploader(
    private val storageRef: StorageReference,
    private val authentication: Authentication
) {
    fun uploadAudio(name: String, email: String, password: String, audioUri: Uri, callback: (String?) -> Unit) {
        authentication.signInWithEmailAndPassword(email, password) { success ->
            if (success) {
                // Generate a unique storage reference for the audio file
                val audioStorageRef = storageRef.child("audios/${audioUri.lastPathSegment}")

                // Upload the audio file to Firebase Storage
                val uploadTask = audioStorageRef.putFile(audioUri)

                // Register observers to listen for when the upload is done or if it fails
                uploadTask.addOnSuccessListener { taskSnapshot ->
                    // Audio file uploaded successfully
                    val downloadUrl = taskSnapshot.metadata?.reference?.downloadUrl.toString()
                    callback(downloadUrl)
                }.addOnFailureListener { exception ->
                    callback(null) // Handle the failure case by passing null
                }
            } else {
                callback(null) // Handle authentication failure by passing null
            }
        }
    }
}
