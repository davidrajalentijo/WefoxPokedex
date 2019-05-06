package com.david.wefoxpokedex

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun espressoTest() {
        val appCompatImageView = onView(
                allOf(withId(R.id.backpack_pokemon),
                        childAtPosition(
                                allOf(withId(R.id.initial_fragment),
                                        childAtPosition(
                                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                                1)),
                                0)))
        appCompatImageView.perform(scrollTo(), click())

        val tabView = onView(
                allOf(withContentDescription("Search Pokemons"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.sliding_tabs),
                                        0),
                                1),
                        isDisplayed()))
        tabView.perform(click())

        val viewPager = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                        0),
                                1),
                        isDisplayed()))
        viewPager.perform(swipeLeft())

        val appCompatButton = onView(
                allOf(withId(R.id.catch_pokemon), withText("Throw a pokeball"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                6)))
        appCompatButton.perform(scrollTo(), click())

        val appCompatButton2 = onView(
                allOf(withId(R.id.run), withText("You have this Pokemon"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                7)))
        appCompatButton2.perform(scrollTo(), click())

        val appCompatButton3 = onView(
                allOf(withId(R.id.run), withText("Find another Pokemon"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                7)))
        appCompatButton3.perform(scrollTo(), click())

        val tabView2 = onView(
                allOf(withContentDescription("Pokedex"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.sliding_tabs),
                                        0),
                                0),
                        isDisplayed()))
        tabView2.perform(click())

        val viewPager2 = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                        0),
                                1),
                        isDisplayed()))
        viewPager2.perform(swipeRight())

        val recyclerView = onView(
                allOf(withId(R.id.recyclerview_main_data_pokemons),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        pressBack()
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
