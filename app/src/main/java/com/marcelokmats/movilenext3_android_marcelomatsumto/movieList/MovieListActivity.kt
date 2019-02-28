package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.fragment.FavoriteMovieListFragment
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.fragment.MovieTicketsListFragment
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.fragment.SearchMovieListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity() {


    private lateinit var mMovieViewModel: MovieListViewModel
    private val movieListFragment by lazy { SearchMovieListFragment.newInstance() }
    private val favoriteListFragment by lazy { FavoriteMovieListFragment.newInstance() }
    private val ticketsListFragment by lazy { MovieTicketsListFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMovieViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        replaceFragment(SearchMovieListFragment.newInstance())
        this.setupBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_movies -> {
                    replaceFragment(movieListFragment)
                }
                R.id.action_favorites -> {
                    replaceFragment(favoriteListFragment)
                }
                R.id.action_tickets -> {
                    replaceFragment(ticketsListFragment)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
