//package com.example.primeiroapp
//
//import android.content.Context
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//abstract class AppDatabase : RoomDatabase() {
//    abstract val localDao: LocalDao
//
//    companion object {
//        fun getInstace(context: Context) =
//            Room.databaseBuilder(context, AppDatabase::class.java, "local-db")
//                .build()
//    }
//
//
//}