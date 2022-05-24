package com.fahmi.assesmen2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class MahasiswaDatabase : RoomDatabase() {

    abstract fun userDao(): MahasiswaDao

    companion object {
        @Volatile
        private var INSTANCE: MahasiswaDatabase? = null

        fun getDatabase(context: Context): MahasiswaDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MahasiswaDatabase::class.java,
                    "tabel_mahasiswa"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}