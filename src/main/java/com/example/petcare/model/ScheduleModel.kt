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
@Entity(tableName = "schedule")
data class ScheduleModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "date")
    val date: String? = null,

    @ColumnInfo(name = "desc")
    val desc: String? = null
)