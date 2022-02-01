package com.example.tp2_tmdb
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
    val liveData: MutableLiveData<List<FilmItem>> = MutableLiveData(null)
    private val apiKey = "a66d566f5e158fd47d2876326b8754a2"

    // fun getStringsFromNetwork() = viewModelScope.launch {
    // private val MutableLiveData = androidx.lifecycle.MutableLiveData<>
    // viewModel.liveData.observe(this) { if not empty afficher else lsite vide jsp }

    suspend fun requestAPI(query: String) {
        val client = HttpClient()
        val response: HttpResponse = client.request("https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$query") {
            // Configure request parameters exposed by HttpRequestBuilder
        }
        // TODO : reprendre la doc, il y a moyen de faire que la requete rrenvoie direct un objet parsed
        val stringBody: String = response.receive()
        Log.i("coucou", stringBody)
        val obj = Json.decodeFromString<Response>(stringBody)
        Log.i("Parsed", obj.results[0].toString())

        liveData.value = obj.results
    }

//    suspend fun getImage(poster_path: String): Drawable {
//        val client = HttpClient()
//        val response: HttpResponse = client.request("https://image.tmdb.org/t/p/w300$poster_path") {
//            // Configure request parameters exposed by HttpRequestBuilder
//        }
//        val body: String = response.receive()
//        val d: Drawable = Drawable.createFromStream(body)
//    }
}