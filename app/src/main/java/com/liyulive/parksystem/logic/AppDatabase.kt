package com.liyulive.parksystem.logic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.liyulive.parksystem.logic.dao.CarDao
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.logic.model.QueueCars

@Database(version = 1, entities = [Cars::class, QueueCars::class])

abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "park_datebase"
            ).allowMainThreadQueries().build().apply { instance = this }
        }
    }
}