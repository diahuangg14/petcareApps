package com.example.petcare.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.petcare.model.ScheduleModel
import com.example.petcare.model.UserModel


/**
 * inisialisasi database
 */
@Database(entities = [UserModel::class, ScheduleModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun appDao() : AppDao
}