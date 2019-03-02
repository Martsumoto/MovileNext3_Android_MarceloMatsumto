package com.marcelokmats.movilenext3_android_marcelomatsumto

import android.app.Application
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever
import com.marcelokmats.movilenext3_android_marcelomatsumto.repository.MovieRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class MovieRetrieverModule {
    @Provides @Singleton
    fun privideMovieRetriever() = MovieRetriever()
}

@Module
class MovieRepositoryModule(application: Application) {

    val mRepository : MovieRepository

    init {
        mRepository = MovieRepository(application)
    }

    @Provides @Singleton
    fun provideMovieRepository() = mRepository
}

@Module
class AppModule(application: Application) {

    val mApplication: Application = application

    @Provides @Singleton
    fun provideApplication() = mApplication
}

class MovieApplication @Inject constructor(private val movieRetriever: MovieRetriever,
                                           private val movieRepository: MovieRepository)
@Singleton
@Component(
    modules = [
        MovieRetrieverModule::class,
        MovieRepositoryModule::class
    ]
)
interface MovieComponent {
    fun movieRetriever(): MovieRetriever
    fun movieRepository(): MovieRepository
}