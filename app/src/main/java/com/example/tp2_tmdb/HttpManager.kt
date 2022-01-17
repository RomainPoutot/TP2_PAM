package com.example.tp2_tmdb
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

    // fun getStringsFromNetwork() = viewModelScope.launch {
    // private val MutableLiveData = androidx.lifecycle.MutableLiveData<>
    // viewModel.liveData.observe(this) { if not empty afficher else lsite vide jsp }

    suspend fun requestAPI(query: String) {
        val apiKey = "a66d566f5e158fd47d2876326b8754a2"
        val client = HttpClient()
        val response: HttpResponse = client.request("https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$query") {
            // Configure request parameters exposed by HttpRequestBuilder
        }
        val stringBody: String = response.receive()
        //TODO : traiter body, le parser tout ca tout ca la famille
        Log.i("coucou", stringBody)
        val obj = Json.decodeFromString<Response>(stringBody)
        Log.i("Parsed", obj.results[0].toString())

        liveData.setValue(obj.results)
    }


    companion object {
        fun getFilmList() {
            // TODO : faire des trucs ici encore
            // Appel HTTP + parsing  et renvoi de la liste de film item
        }
    }

}