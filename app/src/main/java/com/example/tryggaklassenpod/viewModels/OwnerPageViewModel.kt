package com.example.tryggaklassenpod.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tryggaklassenpod.dataClasses.AdminDataClass
import com.example.tryggaklassenpod.sealed.InsertAdminDataState
import com.example.tryggaklassenpod.sealed.FetchingAdminDataState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class OwnerPageViewModel : ViewModel() {
    private val _message = mutableStateOf<InsertAdminDataState?>(null)
    val message: State<InsertAdminDataState?> = _message

    val response: MutableState<FetchingAdminDataState> = mutableStateOf(FetchingAdminDataState.Empty)

    init{
        fetchAdmins()
    }

    private fun fetchAdmins() {
        val tempList = mutableListOf<AdminDataClass>()
        response.value = FetchingAdminDataState.Loading
        FirebaseDatabase.getInstance().getReference("admins")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnap in snapshot.children) {
                        val adminItem = dataSnap.getValue(AdminDataClass::class.java)
                        if (adminItem != null)
                            tempList.add(adminItem)
                    }
                    response.value = FetchingAdminDataState.Success(tempList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = FetchingAdminDataState.Failure(error.message)
                }

            })
    }


    fun addNewAdmin(username:String, school:String, password:String, permissions: Map<String, Boolean>) {
        if(username != "" && school != "" && password != "" ){
            try {
                // Your data to be inserted
                val admin = AdminDataClass(
                    username = username,
                    password = password,
                    school = school,
                    role = "admin", // Automatically set the role as "admin"
                    permissions = permissions
                )

                val database = FirebaseDatabase.getInstance()
                val adminsRef = database.getReference("admins")

                // Generate a new child location with a unique key
                val newAdminRef = adminsRef.push()

                newAdminRef.setValue(admin).addOnSuccessListener {
                    // Data has been successfully inserted with an automatically generated ID
                    val generatedKey = newAdminRef.key // Get the generated key
                    println("Data has been inserted to admins with ID: $generatedKey")
                    _message.value = InsertAdminDataState.Success("Admin added successfully")
                }.addOnFailureListener { error ->
                    // Handle the error if the data insertion fails
                    println("Error inserting data: $error")
                    _message.value = InsertAdminDataState.Failure("Admin couldn't be added")
                }
            } catch (e: Exception) {
                _message.value = InsertAdminDataState.Failure("An error occurred.")
            }
        } else {
            _message.value = InsertAdminDataState.Failure("Fill all the fields please")
        }



    }

    /*private fun addAdminPermissions(adminId:String) {
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
    }*/
}