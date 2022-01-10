package com.example.tp2_tmdb
import android.os.Bundle
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
@Serializable
data class FilmItem(val title: String, val rating: Float)

class HttpManager {
    suspend fun requestAPI(query: String) {
        val apiKey = "a66d566f5e158fd47d2876326b8754a2"
        val client = HttpClient()
        val response: HttpResponse = client.request("https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$query") {
            // Configure request parameters exposed by HttpRequestBuilder
        }
        val stringBody: String = response.receive()
        //TODO : traiter body, le parser tout ca tout ca la famille
        Log.i("coucou", stringBody)

    }

}