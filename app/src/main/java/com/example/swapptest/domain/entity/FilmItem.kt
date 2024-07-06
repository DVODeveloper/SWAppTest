package com.example.swapptest.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "itemsFilmItem")
data class FilmItem(

    @ColumnInfo(name = "title")
    var title: String,

    @PrimaryKey(autoGenerate = false)
    val episode_id: Int? = null,

  //  val opening_crawl: String? = null,

    @ColumnInfo(name = "director")
    var director: String? = null,

    @ColumnInfo(name = "producer")
    val producer: String? = null,

    @ColumnInfo(name = "release_date")
    var release_date: String? = null


//    val characters: List<String>? = null,
//    val planets: List<String>? = null,
//    val starships: List<String>? = null,
//    val vehicles: List<String>? = null,
//    val species: List<String>? = null,
//    val created: String? = null,
//    val edited: String? = null,
//    val url: String? = null

    )
