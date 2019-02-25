package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.marcelokmats.movilenext3_android_marcelomatsumto.MovieViewModel
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity() {


    private lateinit var mMovieViewModel: MovieViewModel
    private val movieListFragment by lazy { SearchMovieListFragment.newInstance() }
    private val favoriteListFragment by lazy { FavoriteMovieListFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
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
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
