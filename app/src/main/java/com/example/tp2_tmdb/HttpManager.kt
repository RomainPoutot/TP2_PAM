package com.example.tp2_tmdb
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class HttpManager : ViewModel() {
    val liveData: MutableLiveData<Response> = MutableLiveData(null)
    val filmDetailData: MutableLiveData<FilmDetail> = MutableLiveData(null)
    private val apiKey = "a66d566f5e158fd47d2876326b8754a2"

    suspend fun requestApiGlobal(query: String, pageNumber: Int) {
        val client = HttpClient()
        val response: HttpResponse = client.request("https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$query&page=$pageNumber") {
            // Configure request parameters exposed by HttpRequestBuilder
        }
        // TODO : reprendre la doc, il y a moyen de faire que la requete renvoie direct un objet parsed
        val stringBody: String = response.receive()
        Log.i("coucou", stringBody)
        val obj = Json.decodeFromString<Response>(stringBody)
        Log.i("Parsed", obj.results[0].toString())

        liveData.value = obj
    }
    suspend fun requestApiDetails(filmId: String) {
        val client = HttpClient()
        val response: HttpResponse = client.request("https://api.themoviedb.org/3/movie/$filmId?api_key=$apiKey") {
            // Configure request parameters exposed by HttpRequestBuilder
        }
        val stringBody: String = response.receive()
        val obj = Json.decodeFromString<FilmDetail>(stringBody)

        filmDetailData.value = obj
    }
}