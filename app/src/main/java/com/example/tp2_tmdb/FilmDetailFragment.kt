package com.example.tp2_tmdb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class FilmDetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmId: String? = arguments?.getString("filmId")

        var httpManager = HttpManager()
        httpManager.filmDetailData.observe(viewLifecycleOwner) {
            bindData(view, httpManager.filmDetailData.value);
        }

        viewLifecycleOwner.lifecycleScope.launch {
            if (filmId != null) {
                httpManager.requestApiDetails(filmId)
            }
        }
    }

    fun bindData(view: View, filmDetail: FilmDetail?) {
        if (filmDetail != null) {
            val mTitleView: TextView = view.findViewById(R.id.filmDetailTitle)
            val mOverviewView: TextView = view.findViewById(R.id.filmDetailOverview)
            val mImageView: ImageView = view.findViewById(R.id.filmDetailPoster)
            val mGenresView: TextView = view.findViewById(R.id.filmDetailGenres)
            val mInfosView: TextView = view.findViewById(R.id.filmDetailAdditionalInfos)
            val mReleaseDateView: TextView = view.findViewById(R.id.filmDetailsDate)
            val mReleaseProductionView: TextView = view.findViewById(R.id.filmDetailsProduction)
            val mBudgetView: TextView = view.findViewById(R.id.filmBudget)
            val mNoteView: TextView = view.findViewById(R.id.filmDetailNote)
            val mTaglineView: TextView = view.findViewById(R.id.filmDetailTagline)


            var genreListTemp = ""
            filmDetail.genres?.forEach { e ->
                genreListTemp = if (genreListTemp.isEmpty()) "${e.name}";
                else "$genreListTemp, ${e.name}"
            }

            mTitleView.text = filmDetail.title


            mTaglineView.text = filmDetail.tagline ?: "Once upon a time ..."


            mOverviewView.text = filmDetail.overview
            mGenresView.text = genreListTemp
            if (filmDetail.backdrop_path != null) {
                mImageView.load("https://image.tmdb.org/t/p/w1280${filmDetail.backdrop_path}");
            } else {
                mImageView.load("https://image.tmdb.org/t/p/w300${filmDetail.poster_path}");
            }

            if (filmDetail.release_date != null) mReleaseDateView.text= filmDetail.release_date
            else mReleaseDateView.text= "Unknown"


            var productionDetails = ""
            filmDetail.production_companies?.forEach { e ->
                if (productionDetails != "") productionDetails += "\n"
                productionDetails += "- ${e.name}"
                if (e.origin_country != null && e.origin_country != ("")) productionDetails += " (${e.origin_country})"
            }
            mReleaseProductionView.text = productionDetails.ifEmpty { "Unknown" }

            mBudgetView.text = if (filmDetail.budget != null && filmDetail.budget != 0) filmDetail.budget.toString() + "$" else "Unknown"


            var noteDetails = ""
            if (filmDetail.vote_average != null) {
                noteDetails += "${filmDetail.vote_average}/10"
                if (filmDetail.vote_count != null) {
                    noteDetails += "(${filmDetail.vote_count} voters)"
                }
            }
            mNoteView.text = if (noteDetails != "") noteDetails else "-"



            var infosView = ""
            infosView += "Original title: " + filmDetail.original_title + "\n"
            infosView += "Original language: " + filmDetail.original_language + "\n"
            if (filmDetail.runtime != null && filmDetail.runtime != 0) {
                val hours = filmDetail.runtime / 60
                infosView += "Runtime : $hours hours ${filmDetail.runtime - hours * 60} minutes\n"
            }
            if (filmDetail.status != null){
                infosView += "Status : ${filmDetail.status}\n"
            }

            mInfosView.text = infosView


        }
    }

}