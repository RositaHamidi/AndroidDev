package com.example.tryggaklassenpod.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tryggaklassenpod.dataClasses.AdminDataClass
import com.example.tryggaklassenpod.dataClasses.PermissionsDataClass
import com.example.tryggaklassenpod.sealed.DatabaseResult
import com.google.firebase.database.FirebaseDatabase


class OwnerPageViewModel : ViewModel() {
    private val _message = mutableStateOf<DatabaseResult?>(null)
    val message: State<DatabaseResult?> = _message

    fun addNewAdmin(name:String, school:String, pass:String) {
        if(name != "" && school != "" && pass != "" ){
            try {
                val database = FirebaseDatabase.getInstance()
                val adminsRef = database.getReference("admins")
                // Your data to be inserted
                val data = AdminDataClass(name, school, pass)

                // Generate a new child location with a unique key
                val newAdminRef = adminsRef.push()

                newAdminRef.setValue(data).addOnSuccessListener {
                    // Data has been successfully inserted with an automatically generated ID
                    val generatedKey = newAdminRef.key // Get the generated key
                    if (generatedKey != null) {
                        addAdminPermissions(generatedKey)
                    }
                    println("Data has been inserted to admins with ID: $generatedKey")
                    _message.value = DatabaseResult.Success("Admin added successfully")
                }.addOnFailureListener { error ->
                    // Handle the error if the data insertion fails
                    println("Error inserting data: $error")
                    _message.value = DatabaseResult.Failure("Admin couldn't be added")
                }
            } catch (e: Exception) {
                _message.value = DatabaseResult.Failure("An error occurred.")
            }
        } else {
            _message.value = DatabaseResult.Failure("Fill all the fields please")
        }



    }

    private fun addAdminPermissions(adminId:String) {
        val database = FirebaseDatabase.getInstance()
        val adminsPerRef = database.getReference("permissions")

        // Your data to be inserted
        val data = PermissionsDataClass(adminId)

        // Generate a new child location with a unique key
        val newAdminPerRef = adminsPerRef.push()

        // Set the key-value pairs under the generated key
        newAdminPerRef.setValue(data).addOnSuccessListener {
            // Data has been successfully inserted with an automatically generated ID
            val generatedKey = newAdminPerRef.key // Get the generated key
            println("Data has been inserted to permissions with ID: $generatedKey")

        }.addOnFailureListener { error ->
            // Handle the error if the data insertion fails
            println("Error inserting data: $error")
        }
    }
}