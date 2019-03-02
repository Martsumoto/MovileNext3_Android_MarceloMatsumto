package com.marcelokmats.movilenext3_android_marcelomatsumto.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

open class BaseTestRobot {
    fun fillEditText(resId: Int, text: String) =
        onView(withId(resId)).perform(replaceText(text), closeSoftKeyboard())

    fun doneEditText(resId: Int) = onView((withId(resId))).perform(pressImeActionButton())
}
