package com.example.swapptest.data.api

import com.example.swapptest.domain.entity.CharactersListSelectedFilmResult
import com.example.swapptest.domain.entity.FilmListResults
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("films/")
    suspend fun getFilmList(): FilmListResults


    @GET
    suspend fun getCharacterList(@Url characterUrl: String): CharactersListSelectedFilmResult
}