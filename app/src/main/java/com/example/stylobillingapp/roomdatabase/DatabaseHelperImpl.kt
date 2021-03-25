package com.example.stylobillingapp.roomdatabase

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<User> = appDatabase.userDao().getAll()

    override suspend fun insertAll(users: List<User>) = appDatabase.userDao().insertAll(users)
    override suspend fun deleteUser(user: User) = appDatabase.userDao().delete(user)

}