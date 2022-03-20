package com.example.tp2_tmdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class FilmListFragment : Fragment() {
    var query : String? = ""
    var pageNumber : Int? = 1
    var maxPageNumber : Int? = 1

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_film_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        query = arguments?.getString("query")
        pageNumber = arguments?.getInt("pageNumber")

        var httpManager = HttpManager()
        httpManager.liveData.observe(viewLifecycleOwner) {
            if (httpManager.liveData.value?.results?.size != 0) {
                val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
                val adapter =
                    httpManager.liveData.value?.let { it1 -> FilmListAdapter(it1.results, this) }
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(activity)
                maxPageNumber = httpManager.liveData.value?.total_pages
            } else {
                findNavController().navigate(R.id.action_filmListFragment_to_noResultPage)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            if (query != null) {
                if (pageNumber != null) {
                    httpManager.requestApiGlobal(query!!, pageNumber!!)
                }
            }
        }
    }

    fun onFilmItemClicked( filmId: String) {
        val bundle = bundleOf("filmId" to filmId)
        findNavController().navigate(R.id.action_filmListFragment_to_filmDetailFragment, bundle)
    }

    fun goToNextPage(view: View) {
        if (pageNumber != null) {
            if (pageNumber!! < maxPageNumber!!) {
                val bundle = bundleOf("query" to query, "pageNumber" to pageNumber!! + 1)
                findNavController().navigate(R.id.action_filmListFragment_self, bundle)
            } else {
                Toast.makeText(context, "Vous avez atteint la dernière page !", Toast.LENGTH_LONG).show();
            }
        }
    }
    fun goToPreviousPage(view: View) {
        if (pageNumber != null) {
            if (pageNumber!! > 1) {
                val bundle = bundleOf("query" to query, "pageNumber" to pageNumber!! - 1)
                findNavController().navigate(R.id.action_filmListFragment_self, bundle)
            } else {
                Toast.makeText(context, "Vous êtes sur la première page !", Toast.LENGTH_LONG).show();
            }
        }
    }

}