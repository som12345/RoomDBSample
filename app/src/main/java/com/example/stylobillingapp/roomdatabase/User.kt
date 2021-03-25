package com.example.stylobillingapp.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "address") val add: String?,
    @ColumnInfo(name = "phonenumber") val ph: String?
)