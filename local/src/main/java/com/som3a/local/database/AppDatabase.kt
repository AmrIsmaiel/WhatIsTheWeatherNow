package com.som3a.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.som3a.local.model.WeatherDetailsLocal

@Database(
    entities = [WeatherDetailsLocal::class],
    version = 1,
    exportSchema = false
) // We need migration if increase version
abstract class AppDatabase : RoomDatabase() {

    abstract fun comicsDAO(): WeatherDetailsDAO
}