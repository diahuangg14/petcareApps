package com.example.petcare.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * membuat tabel database
 * entity = entitas / tabel
 * primaryKey = PK
 * columnInfo = nama kolom
 */
@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String? = null
)
