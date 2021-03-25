package com.example.stylobillingapp.roomdatabase

interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

    suspend fun deleteUser(user: User)

}