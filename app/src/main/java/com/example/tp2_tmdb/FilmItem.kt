package com.example.tp2_tmdb

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class FilmItem(val title: String, val rating: Float)
