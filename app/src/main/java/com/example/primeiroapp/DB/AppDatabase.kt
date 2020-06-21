package com.example.primeiroapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.primeiroapp.DB.Local
import com.example.primeiroapp.DB.LocalDAO

@Database(entities = [Local::class],version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract val localDao: LocalDAO

    companion object {
        fun getInstace(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "local-db")
                .build()
    }


}