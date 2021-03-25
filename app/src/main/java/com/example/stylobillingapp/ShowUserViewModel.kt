package com.example.stylobillingapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylobillingapp.roomdatabase.DatabaseBuilder
import com.example.stylobillingapp.roomdatabase.DatabaseHelperImpl
import com.example.stylobillingapp.roomdatabase.User
import kotlinx.coroutines.launch

class ShowUserViewModel(var showCustomerActivity: ShowCustomerActivity) : ViewModel() {

     val users = MutableLiveData<List<User>>()
     val isDelete = MutableLiveData<Boolean>()
    fun getData() {
        viewModelScope.launch {
            users.postValue(
                DatabaseHelperImpl(DatabaseBuilder.getInstance(showCustomerActivity)).getUsers()
            )
        }
    }


    fun deleteUser(user: User): Boolean {
        viewModelScope.launch {
            try {
                DatabaseHelperImpl(DatabaseBuilder.getInstance(showCustomerActivity)).deleteUser(user)
                isDelete.postValue(true)
            } catch (e: Exception) {
                isDelete.postValue(false)
            }
        }
        return true
    }

}
