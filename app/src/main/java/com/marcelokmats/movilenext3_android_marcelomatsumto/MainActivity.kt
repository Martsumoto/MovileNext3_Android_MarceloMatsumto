package com.marcelokmats.movilenext3_android_marcelomatsumto

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.marcelokmats.movilenext3_android_marcelomatsumto.favoriteList.FavoriteListFragment
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var mMovieViewModel: MovieViewModel
    private val movieListFragment by lazy { MovieListFragment.newInstance() }
    private val favoriteListFragment by lazy { FavoriteListFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        replaceFragment(MovieListFragment.newInstance())
        this.setupBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
