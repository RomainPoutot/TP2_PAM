package com.example.tp2_tmdb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                var httpManager = HttpManager()
                val query : String = view.findViewById<EditText>(R.id.editText).text.toString()
                httpManager.requestAPI(query)
                // ChangeActivity(view)
            }
        }
    }


    fun ChangeActivity(view: View) {
        findNavController().navigate(R.id.action_searchFragment_to_itemFragment)
    }

    fun CallTmdbAPI(view: View, toSearch: String) {

    }
}