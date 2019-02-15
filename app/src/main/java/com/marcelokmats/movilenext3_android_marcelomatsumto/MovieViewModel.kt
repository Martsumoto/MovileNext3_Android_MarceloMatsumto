package com.marcelokmats.movilenext3_android_marcelomatsumto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieSearchResult

class MovieViewModel() : ViewModel() {

    private val movieRetriever = MovieRetriever()

    private val movieSearchResultLive : LiveData<MovieSearchResult>
    private val searchTextList : MutableLiveData<String> = MutableLiveData()

    init {
        movieSearchResultLive = Transformations.switchMap(searchTextList) {
            searchText -> movieRetriever.getMoviesSearchResult(searchText)
        }
    }

    fun setSearchTextView(query: String) {
        searchTextList.value = query
    }

    fun getRepositoriesLiveData() : LiveData<MovieSearchResult> {
        return movieSearchResultLive
    }
}