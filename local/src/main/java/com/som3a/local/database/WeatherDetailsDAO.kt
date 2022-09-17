package com.som3a.local.database

import androidx.room.*
import com.som3a.local.model.WeatherDetailsLocal

@Dao
interface WeatherDetailsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWeatherDetails(item: WeatherDetailsLocal): Long

    @Query("SELECT * FROM weather WHERE responseId = :id")
    suspend fun getWeatherDetailsById(id: Long): WeatherDetailsLocal

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWeatherDetailsItems(itemList: List<WeatherDetailsLocal>): List<Long>

    @Query("SELECT * FROM weather")
    suspend fun getWeatherDetailsItems(): List<WeatherDetailsLocal>

    @Update
    suspend fun updateWeatherDetailsItem(item: WeatherDetailsLocal): Int

    @Query("DELETE FROM weather WHERE responseId = :id")
    suspend fun deleteWeatherDetailsItemById(id: Int): Int

    @Delete
    suspend fun deleteWeatherDetailsItem(item: WeatherDetailsLocal): Int

    @Query("DELETE FROM weather")
    suspend fun clearCachedItems(): Int
}