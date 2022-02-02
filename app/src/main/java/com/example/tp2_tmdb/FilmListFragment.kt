package com.example.tp2_tmdb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.tp2_tmdb.FilmItem
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class FilmListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_film_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query : String? = arguments?.getString("query")

        var httpManager = HttpManager()
        httpManager.liveData.observe(viewLifecycleOwner) {
            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
            val adapter = httpManager.liveData.value?.let { it1 -> FilmListAdapter(it1) }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)

        }

        viewLifecycleOwner.lifecycleScope.launch {
            if (query != null) {
                httpManager.requestAPI(query)
            }
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_film_list, container, false)
//
//        return view
//    }

}