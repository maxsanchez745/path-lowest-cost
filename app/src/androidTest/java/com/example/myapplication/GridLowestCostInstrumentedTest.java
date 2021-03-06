package com.example.myapplication;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

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
        checkHasPathTextView(true);
        checkWeightTextView(0);
        checkPathTakenTextView(Arrays.toString(new int[]{2, 1, 2}));
    }

    @Test
    public void whenSimple3x3NonZeroWeight_shouldCalculateTotalWeight() throws Exception {
        putMatrixEditText("{1, 0, 1}, {0, 1, 1}, {1, 1, 1}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(1);
    }

    @Test
    public void when6x5NormalFlow_shouldFindPath() throws Exception {
        putMatrixEditText("{3, 4, 1, 2, 8, 6}, {6, 1, 8, 2, 7, 4}, {5, 9, 3, 9, 9, 5}, {8, 4, 1, 3, 2, 6}, {3, 7, 2, 8, 6, 4}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(16);
        checkPathTakenTextView(Arrays.toString(new int[]{1, 2, 3, 4, 4, 5}));
    }

    @Test
    public void when6x5NormalFlowSecondTestCase_shouldFindPath() throws Exception {
        putMatrixEditText("{3, 4, 1, 2, 8, 6}, {6, 1, 8, 2, 7, 4}, {5, 9, 3, 9, 9, 5}, {8, 4, 1, 3, 2, 6}, {3, 7, 2, 1, 2, 3}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(11);
        checkPathTakenTextView(Arrays.toString(new int[]{1, 2, 1, 5, 4, 5}));
    }

    @Test
    public void when5x3NoPathMatrix_shouldNotFindPath() throws Exception {
        putMatrixEditText("{19, 10, 19, 10, 19}, {21, 23, 20, 19, 12}, {20, 12, 20, 11, 10}");
        clickButton();
        checkHasPathTextView(false);
        checkWeightTextView(49);
        checkPathTakenTextView(Arrays.toString(new int[]{3, 1, 1}));
    }

    @Test
    public void when1x5Matrix_shouldFindPath() throws Exception {
        putMatrixEditText("{5, 8, 5, 3, 5}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(26);
        checkPathTakenTextView(Arrays.toString(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void when5x1Matrix_shouldFindPath() throws Exception {
        putMatrixEditText("{5},{8},{5},{3},{5}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(3);
        checkPathTakenTextView(Arrays.toString(new int[]{4}));
    }

    @Test
    public void whenNonNumericInput_shouldThrowInvalidMatrixException() throws Exception {
        putMatrixEditText("{5, 4, H}, {8, M, 7},{5, 7, 5}");
        clickButton();
        onView(withId(R.id.a_main_has_path))
                .check(matches(withText(R.string.invalid_matrix)));
    }

    @Test
    public void whenEmptyMatrix_shouldThrowInvalidMatrixException() {
        putMatrixEditText("");
        clickButton();
        onView(withId(R.id.a_main_has_path))
                .check(matches(withText(R.string.invalid_matrix)));
    }

    @Test
    public void whenStartingWithNumbersLargerThanFifty_shouldNotFindPath() throws Exception {
        putMatrixEditText("{69, 10, 19, 10, 19}, {51, 23, 20, 19, 12}, {60, 12, 20, 11, 10}");
        clickButton();
        checkHasPathTextView(false);
        checkWeightTextView(0);
        checkPathTakenTextView(Arrays.toString(new int[]{}));
    }

    @Test
    public void whenOneValueLargerThanFifty_shouldFindPath() throws Exception {
        putMatrixEditText("{60, 3, 3, 6}, {6, 3, 7, 9}, {5, 6, 8, 3}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(14);
        checkPathTakenTextView(Arrays.toString(new int[]{3, 2, 1, 3}));
    }

    @Test
    public void whenMatrixWithNegativeNumbers_shouldFindPath() throws Exception {
        putMatrixEditText("{6, 3, -5, 9}, {-5, 2, 4, 10}, {3, -2, 6, 10}, {6, -1, -2, 10}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(0);
        checkPathTakenTextView(Arrays.toString(new int[]{2, 3, 4, 1}));
    }

    @Test
    public void whenMatrixWithCompletePathAndLowerIncompletePath_shouldFindPath() throws Exception {
        putMatrixEditText("{51, 51}, {0, 51}, {51, 51}, {5, 5}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(10);
        checkPathTakenTextView(Arrays.toString(new int[]{4, 4}));
    }

    @Test
    public void whenMatrixWithLongerIncompletePathAndShorterLowerCostIncompletePath_shouldNotFindPath() throws Exception {
        putMatrixEditText("{51, 51, 51}, {0, 51, 51}, {51, 51, 51}, {5, 5, 51}");
        clickButton();
        checkHasPathTextView(false);
        checkWeightTextView(10);
        checkPathTakenTextView(Arrays.toString(new int[]{4, 4}));
    }

    @Test
    public void whenMatrixWithLargeNumberColumns_shouldFindPath() throws Exception {
        putMatrixEditText("{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(20);
        checkPathTakenTextView(Arrays.toString(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void whenMatrixSingleNonValidLine_shouldNotFindPath() throws Exception {
        putMatrixEditText("{51, -7, 3}");
        clickButton();
        checkHasPathTextView(false);
        checkWeightTextView(0);
        checkPathTakenTextView(Arrays.toString(new int[]{}));
    }

    @Test
    public void whenSimpleMatrixWithLargeNumberColumns_shouldFindPath() throws Exception {
        putMatrixEditText("{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}");
        clickButton();
        checkHasPathTextView(true);
        checkWeightTextView(24);
        checkPathTakenTextView(Arrays.toString(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    private void checkHasPathTextView(boolean shouldHavePath) {
        onView(withId(R.id.a_main_has_path))
                .check(matches(withText(shouldHavePath ? "Yes" : "No")));
    }

    private void checkWeightTextView(int weight) {
        onView(withId(R.id.a_main_total_weight))
                .check(matches(withText(weight + "")));
    }

    private void checkPathTakenTextView(String pathTaken) {
        onView(withId(R.id.a_main_path_taken))
                .check(matches(withText(pathTaken)));
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
