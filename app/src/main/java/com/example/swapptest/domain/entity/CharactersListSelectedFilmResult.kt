package com.example.swapptest.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersListSelectedFilmResult (
    val characters: List<Character>
): Parcelable