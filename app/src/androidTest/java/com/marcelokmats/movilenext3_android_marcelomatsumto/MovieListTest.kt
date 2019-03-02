package com.marcelokmats.movilenext3_android_marcelomatsumto

import androidx.test.rule.ActivityTestRule
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieListActivity
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.fragment.SearchMovieListFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieListTest {
    @get:Rule
    val rule = ActivityTestRule(MovieListActivity::class.java)

    private lateinit var robot: MovieListRobot

    val activity: MovieListActivity by lazy {
        rule.activity
    }

    @Before
    fun setup() {
        activity.replaceFragment(SearchMovieListFragment.newInstance())
        robot = MovieListRobot(activity)
    }

    @Test
    fun shortSearchLenght() {
        robot.setSearch("ab")
            .setDoneSearch()
            .matchToast(R.string.search_text_too_short, rule.activity.window.decorView)
    }

    @Test
    fun progressBarDisplayed() {
        robot.setSearch("abc")
            .setDoneSearch()
            .progressBarShown()
    }

    @Test
    fun searchMovieError() {
        robot.setSearch("djkfgnasfdobnefalkdvb") // when searching an invalid movie name
            .setDoneSearch()
            .progressBarShown()
    }
}