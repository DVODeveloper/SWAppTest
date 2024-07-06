package com.example.swapptest.data.api

import com.example.swapptest.domain.entity.CharacterResponse
import com.example.swapptest.domain.entity.CharactersListSelectedFilmResult
import com.example.swapptest.domain.entity.FilmListResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @GET("films/")
    suspend fun getFilmList(): FilmListResults


    @GET("people/{characterUrl}")
    suspend fun getCharacters(@Path("characterUrl") characterUrl: String): CharacterResponse
}