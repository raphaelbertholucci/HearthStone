package com.bertholucci.home.extensions

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.bertholucci.home.helpers.RecyclerViewMatcher.Companion.withRecyclerView
import kotlin.test.assertTrue

inline fun <reified T : View> Int.checkRecyclerViewItem(
    position: Int,
    idItem: Int,
    crossinline assert: (T) -> Boolean
) {
    onView(withRecyclerView(this).atPositionOnView(position, idItem))
        .check { view, _ ->
            if (view is T) {
                assertTrue {
                    assert.invoke(view)
                }
            }
        }
}

fun Int.hasText(textId: Int) {
    onView(withId(this)).check(matches(withText(textId)))
}

fun Int.hasText(text: String) {
    onView(withId(this)).check(matches(withText(text)))
}