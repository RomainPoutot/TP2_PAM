package com.example.tp2_tmdb

import kotlinx.serialization.Serializable

@Serializable
data class Response(val page: Int, val results: List<FilmItem>, val total_pages: Int, val total_results: Int)