package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import android.app.Application
import androidx.lifecycle.*
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieSearchResult
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.repository.MovieRepository
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.observeOnce

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRetriever = MovieRetriever()

    val movieSearchResultLive : LiveData<MovieSearchResult>
    val searchTextList : MutableLiveData<String> = MutableLiveData()
    val favoriteListLive : MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val favoriteMap : MutableMap<String, Movie> = HashMap()
    val movieTicketListLive : MutableLiveData<MutableList<MovieTicket>> = MutableLiveData()

    //val moviesFavoritesPairLive : LiveData<Pair<MovieSearchResult, MutableList<Movie>>>

    private val repository = MovieRepository(application)

    init {
        repository.favoritedMovies.observeOnce(Observer { loadFavorites(it) })
        repository.movieTickets.observeOnce(Observer { movieTicketListLive.value = it.toMutableList() })

        movieSearchResultLive = Transformations.switchMap(searchTextList)
        { searchText -> movieRetriever.getMoviesSearchResult(searchText) }
        movieSearchResultLive.observeForever {
            setFavoritesInMovies(it.movies)
        }
    }

    fun setSearchTextView(query: String) {
        searchTextList.value = query
    }

    fun insertFavorite(movie: Movie) {
        repository.insertFavorite(movie)

        addFavoriteFromMemory(movie)
    }

    fun removeFavorite(movie: Movie) {
        repository.removeFavorite(movie)

        removeFavoriteFromMemory(movie)
    }

    fun createMovieFromTicket(movieTicket: MovieTicket) : Movie =
            Movie(movieTicket.imdbID, movieTicket.Title, movieTicket.Year, movieTicket.Poster)

    fun addFavoriteFromMemory(movie: Movie) {
        if (!favoriteMap.containsKey(movie.imdbID)) {
            val movieList: MutableList<Movie> = if (favoriteListLive.value == null) {
                favoriteListLive.value = mutableListOf()
                favoriteListLive.value!!
            } else {
                favoriteListLive.value!!
            }
            movie.isFavorite = true
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
            movie.isFavorite = false
            movieList.remove(movie)
            favoriteListLive.value = movieList
        }
    }

    fun updateMovieTicket(newMovieTicket: MovieTicket) {
        val movieTicketList = movieTicketListLive.value ?: emptyList<MovieTicket>().toMutableList()
        var hasMatch : Boolean = false

        for (ticket in movieTicketList) {
            if (newMovieTicket.imdbID == ticket.imdbID) {
                hasMatch = true

                if (newMovieTicket.amount > 0) {
                    // Amount updated
                    ticket.amount = newMovieTicket.amount
                } else {
                    // Ticket removed
                    movieTicketList.remove(ticket)
                }
            }
        }

        if (!hasMatch) {
            movieTicketList.add(newMovieTicket)
        }

        if (newMovieTicket.amount > 0) {
            movieTicketListLive.value = movieTicketList
        }
    }

    fun isMovieFavorite(imdbID: String) : Boolean = favoriteMap.containsKey(imdbID)

    private fun loadFavorites(favorites: List<Movie>) {
        favoriteListLive.value = favorites.toMutableList()

        for (movie in favorites) {
            movie.isFavorite = true
            favoriteMap[movie.imdbID] = movie
        }

        if (favoriteMap.isNotEmpty()) {
            for (movie in favorites) {
                movie.isFavorite = true
            }
        }
    }

    private fun setFavoritesInMovies(movies: List<Movie>) {
        if (favoriteMap.isNotEmpty()) {
            for (movie in movies) {
                movie.isFavorite = favoriteMap.containsKey(movie.imdbID)
            }
        }
    }

    private fun loadMovieTckets(movieTickets: List<MovieTicket>) {

    }
}