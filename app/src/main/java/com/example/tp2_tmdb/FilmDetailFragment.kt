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
            Log.i("OnViewCreated FilmDetailFragment", httpManager.filmDetailData.value.toString())
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


            var genreListTemp = ""
            filmDetail.genres?.forEach { e ->
                genreListTemp = if (genreListTemp.isEmpty()) "${e.name}";
                else "$genreListTemp, ${e.name}"
            }

            mTitleView.text = filmDetail.title
            mOverviewView.text = filmDetail.overview
            mGenresView.text = genreListTemp
            if (filmDetail.backdrop_path != null) {
                Log.i("bindData FilmDetailFragment", "banner exists")
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
                if (e.origin_country != null) productionDetails += "(${e.origin_country})"
            }
            mReleaseProductionView.text = if (productionDetails.isEmpty()) "Unknown" else productionDetails

            var economy = ""
            if (filmDetail.budget != null && filmDetail.budget != 0) economy += "Budget : ${filmDetail.revenue}$\n"
            if (filmDetail.revenue != null && filmDetail.revenue != 0) economy += "Revenue : ${filmDetail.revenue}$"
            mBudgetView.text = economy






            var infosView = ""
            infosView += "Original title: " + filmDetail.original_title + "\n"
            infosView += "Original language: " + filmDetail.original_language + "\n"
            infosView += "Release date: "+ filmDetail.release_date + "\n"
            //if (filmDetail.budget != null) infosView += "Budget: " + filmDetail.budget + " $\n"


            mInfosView.text = infosView


        }
    }

}