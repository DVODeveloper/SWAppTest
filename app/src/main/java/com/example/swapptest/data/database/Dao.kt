package com.example.swapptest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.swapptest.domain.entity.FilmItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    fun insertFilmItem(item: FilmItem)
    @Query("SELECT * FROM itemsFilmItem")
    fun getFilmItems(): Flow<List<FilmItem>>
}