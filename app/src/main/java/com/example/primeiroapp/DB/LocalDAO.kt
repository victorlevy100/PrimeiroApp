package com.example.primeiroapp.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocalDAO {
    @Insert
    fun insert(local: Local)

    @Query("SELECT * FROM local")
    fun selectAll(): List<Local>

}