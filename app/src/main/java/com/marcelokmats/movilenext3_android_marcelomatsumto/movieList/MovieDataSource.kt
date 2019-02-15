package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieSearchResult

class MoviePageKeyedDataSource : PageKeyedDataSource<Long, List<MovieSearchResult>>() {

    private val mNetworkState : MutableLiveData<Boolean> = MutableLiveData()

    init {

    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, List<MovieSearchResult>>
    ) {
        //mNetworkState.postValue(net)

        MovieRetriever().getMoviesSearchResult("lake")
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, List<MovieSearchResult>>) {

        MovieRetriever().getMoviesSearchResult("lake", params.key)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, List<MovieSearchResult>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}