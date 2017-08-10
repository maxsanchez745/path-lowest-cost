package com.example.myapplication;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by user on 8/10/17.
 */

public class GridLowestCostInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void whenSimple3x3_shouldCalculatePath() throws Exception {
        putMatrixEditText("{1, 0, 1}, {0, 1, 0}, {1, 1, 1}");
        clickButton();
        checkPathTextView(true);
    }

    private void checkPathTextView(boolean shouldHavePath) {
        onView(withId(R.id.a_main_has_path))
                .check(matches(withText(shouldHavePath ? "Yes" : "No")));
    }

    private void clickButton() {
        onView(withId(R.id.a_main_btn))
                .perform(click());
    }

    private void putMatrixEditText(String matrix) {
        onView(withId(R.id.a_main_et))
                .perform(clearText());

        onView(withId(R.id.a_main_et))
                .perform(typeText(matrix));
    }
}
