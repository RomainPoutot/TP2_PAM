package com.example.tp2_tmdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


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
        val editText = view.findViewById<EditText>(R.id.editText)

        button.setOnClickListener {
            val query : String = editText.text.toString()
            if (query == "") {
                Toast.makeText(context, "You must enter something first", Toast.LENGTH_LONG).show()
            } else {
                changeActivity(view, query)
            }
        }

        editText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    val query : String = editText.text.toString()
                    if (query == "") {
                        Toast.makeText(context, "You must enter something first", Toast.LENGTH_LONG).show()
                    }
                    else {
                        changeActivity(view, query)
                    }
                    true
                }
                else -> false
            }
        }
    }


    fun changeActivity(view: View, query: String) {
        val bundle = bundleOf("query" to query, "pageNumber" to 1)
        findNavController().navigate(R.id.action_searchFragment_to_filmListFragment, bundle)
    }
}