package com.example.petcare.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.petcare.model.ScheduleModel
import com.example.petcare.model.UserModel

/**
 * DAO = Database access object
 */
@Dao
interface AppDao {

    /**
     * untuk menambahkan user
     */
    @Insert
    suspend fun insertUser(user: UserModel)

    /**
     * untuk mendapatkan user berdasarkan email
     */
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserModel

    /**
     * untuk mendambahkan data schedule
     */
    @Insert
    suspend fun insertSchedule(schedule: ScheduleModel)


    /**
     * untuk mendapatkan semua schedule
     */
    @Query("SELECT * FROM schedule")
    suspend fun getAllSchedule(): List<ScheduleModel>


    /**
     * untuk menghapus schedule
     */
    @Delete
    suspend fun deleteSchedule(schedule: ScheduleModel)
}