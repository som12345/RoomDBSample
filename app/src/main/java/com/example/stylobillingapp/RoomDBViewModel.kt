package com.example.stylobillingapp

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylobillingapp.roomdatabase.DatabaseBuilder
import com.example.stylobillingapp.roomdatabase.DatabaseHelperImpl
import com.example.stylobillingapp.roomdatabase.User
import kotlinx.coroutines.launch


class RoomDBViewModel(var addCustomerActvity: AddCustomerActvity) : ViewModel() {
   var dbHelper =  DatabaseHelperImpl(DatabaseBuilder.getInstance(addCustomerActvity))
    fun addCustomerData(pk:Int,cname: String, cadd: String, cphone: String) {
        val usersToInsertInDB = mutableListOf<User>()
        viewModelScope.launch {
            try {
                val user = User(
                    id = pk,
                    cname,
                    cadd,
                    cphone
                )
                usersToInsertInDB.add(user)
                dbHelper.insertAll(usersToInsertInDB)
                Toast.makeText(addCustomerActvity, "user is added", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(addCustomerActvity, "something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
