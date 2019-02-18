package com.marcelokmats.movilenext3_android_marcelomatsumto

import android.app.Application
import androidx.lifecycle.*
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieSearchResult
import com.marcelokmats.movilenext3_android_marcelomatsumto.repository.MovieRepository
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.observeOnce

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRetriever = MovieRetriever()

    val movieSearchResultLive : LiveData<MovieSearchResult>
    val searchTextList : MutableLiveData<String> = MutableLiveData()
    val favoriteListLive : MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val favoriteMap : MutableMap<String, Movie> = HashMap()

    //val moviesFavoritesPairLive : LiveData<Pair<MovieSearchResult, MutableList<Movie>>>

    private val repository = MovieRepository(application)

    init {
        repository.favoritedMovies.observeOnce(Observer { loadFavorites(it) })
        movieSearchResultLive = Transformations.switchMap(searchTextList) {
            searchText -> movieRetriever.getMoviesSearchResult(searchText)
        }

        //moviesFavoritesPairLive = zipLiveData(movieSearchResultLive, favoriteListLive)
    }

    fun setSearchTextView(query: String) {
        searchTextList.value = query
    }

    fun insertFavorite(movie: Movie) {
        repository.insert(movie)

        addFavoriteFromMemory(movie)
    }

    fun removeFavorite(movie: Movie) {
        repository.remove(movie)

        removeFavoriteFromMemory(movie)
    }

    fun addFavoriteFromMemory(movie: Movie) {
        if (!favoriteMap.containsKey(movie.imdbID)) {
            val movieList: MutableList<Movie> = if (favoriteListLive.value == null) {
                favoriteListLive.value = mutableListOf()
                favoriteListLive.value!!
            } else {
                favoriteListLive.value!!
            }
            movieList.add(movie)
            favoriteListLive.value = movieList
            favoriteMap[movie.imdbID] = movie
        }
    }

    fun removeFavoriteFromMemory(movie: Movie) {
        if (favoriteMap.containsKey(movie.imdbID)) {
            favoriteMap.remove(movie.imdbID)

            val movieList : MutableList<Movie> = if (favoriteListLive.value == null) {
                favoriteListLive.value = mutableListOf()
                favoriteListLive.value!!
            } else {
                favoriteListLive.value!!
            }
            movieList.remove(movie)
            favoriteListLive.value = movieList
        }
    }

    private fun loadFavorites(favorites: List<Movie>) {
        favoriteListLive.value = favorites.toMutableList()

        for (movie in favorites) {
            favoriteMap[movie.imdbID] = movie
        }
    }

}