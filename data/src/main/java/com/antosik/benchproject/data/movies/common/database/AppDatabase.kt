package com.antosik.benchproject.data.movies.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getDao()
    companion object {
        private var databaseInstance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext, AppDatabase::class.java, "MyDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return databaseInstance!!
        }
    }
}