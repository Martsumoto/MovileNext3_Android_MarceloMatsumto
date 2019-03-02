package com.marcelokmats.movilenext3_android_marcelomatsumto

import android.content.Context
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.marcelokmats.movilenext3_android_marcelomatsumto.robot.BaseTestRobot
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not




class MovieListRobot(private val context: Context) : BaseTestRobot() {
    fun setSearch(text: String) = apply { fillEditText(R.id.etSearch, text) }

    fun setDoneSearch() = apply { doneEditText(R.id.etSearch) }

    fun matchToast(messageId: Int, view: View) {
        onView(withText(messageId)).inRoot(withDecorView(not(`is`(view))))
            .check(matches(isDisplayed()))
    }

    fun progressBarShown() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    fun errorMessageShown() {
        onView(withId(R.id.tvErrorMessage)).check(matches(isDisplayed()))
    }
}
