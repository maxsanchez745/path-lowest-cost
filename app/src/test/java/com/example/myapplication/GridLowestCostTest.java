package com.example.myapplication;

import com.example.myapplication.exception.InvalidMatrixException;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evin on 8/9/17.
 */
public class GridLowestCostTest {
    @Test
    public void whenSimple3x3_shouldCalculatePath() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };
        GridLowestCost gridLowestCost = new GridLowestCost(simpleGrid);

        assertTrue("Simple 3x3 grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 0!", 0, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {2, 1, 2}!", new int[]{2, 1, 2}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenSimple3x3NonZeroWeight_shouldCalculateTotalWeight() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 1},
                {1, 1, 1}
        };
        GridLowestCost gridLowestCost = new GridLowestCost(simpleGrid);

        assertTrue("Simple 3x3 grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 1!", 1, gridLowestCost.getCalculatedWeight());
    }

    @Test
    public void when6x5NormalFlow_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {3, 4, 1, 2, 8, 6},
                {6, 1, 8, 2, 7, 4},
                {5, 9, 3, 9, 9, 5},
                {8, 4, 1, 3, 2, 6},
                {3, 7, 2, 8, 6, 4}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Simple 6x5 grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 16!", 16, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {1, 2, 3, 4, 4, 5}!", new int[]{1, 2, 3, 4, 4, 5}, gridLowestCost.getPathTaken());
    }

    @Test
    public void when6x5NormalFlowSecondTestCase_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {3, 4, 1, 2, 8, 6},
                {6, 1, 8, 2, 7, 4},
                {5, 9, 3, 9, 9, 5},
                {8, 4, 1, 3, 2, 6},
                {3, 7, 2, 1, 2, 3}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Second simple 6x5 grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 11!", 11, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {1, 2, 1, 5, 4, 5}!", new int[]{1, 2, 1, 5, 4, 5}, gridLowestCost.getPathTaken());
    }

    @Test
    public void when5x3NoPathMatrix_shouldNotFindPath() throws Exception {
        int[][] testGrid = {
                {19, 10, 19, 10, 19},
                {21, 23, 20, 19, 12},
                {20, 12, 20, 11, 10}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertFalse("Third simple 5x3 grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 49 even when path it's not found!", 49, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {3, 1, 1}, this is the lowest path!", new int[]{3, 1, 1}, gridLowestCost.getPathTaken());
    }

    @Test
    public void when1x5Matrix_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {5, 8, 5, 3, 5}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Fourth sample 1x5 grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 26!", 26, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {1, 1, 1, 1, 1}!", new int[]{1, 1, 1, 1, 1}, gridLowestCost.getPathTaken());
    }

    @Test
    public void when5x1Matrix_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {5},
                {8},
                {5},
                {3},
                {5}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Fifth sample 5x1 grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 3!", 3, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {4}!", new int[]{4}, gridLowestCost.getPathTaken());
    }

    @Test(expected = InvalidMatrixException.class)
    public void whenNonNumericInput_shouldThrowInvalidMatrixException() {
        Object[][] invalidGrid = {
                {5, 4, "H"},
                {8, "M", 7},
                {5, 7, 5}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(invalidGrid);
    }

    @Test(expected = InvalidMatrixException.class)
    public void whenEmptyMatrix_shouldThrowInvalidMatrixException() {
        Object[][] emptyGrid = {};

        GridLowestCost gridLowestCost = new GridLowestCost(emptyGrid);
    }

    @Test
    public void whenStartingWithNumbersLargerThanFifty_shouldNotFindPath() throws Exception {
        int[][] testGrid = {
                {69, 10, 19, 10, 19},
                {51, 23, 20, 19, 12},
                {60, 12, 20, 11, 10}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertFalse("Eight sample grid should not have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 0 since there is no possible path!", 0, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {} since there is not even one possible path!", new int[]{}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenOneValueLargerThanFifty_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {60, 3, 3, 6},
                {6, 3, 7, 9},
                {5, 6, 8, 3}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Ninth sample grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 14!", 14, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {3, 2, 1, 3}!", new int[]{3, 2, 1, 3}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenMatrixWithNegativeNumbers_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {6, 3, -5, 9},
                {-5, 2, 4, 10},
                {3, -2, 6, 10},
                {6, -1, -2, 10}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Tenth sample grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 0!", 0, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {2, 3, 4, 1}!", new int[]{2, 3, 4, 1}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenMatrixWithCompletePathAndLowerIncompletePath_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {51, 51},
                {0, 51},
                {51, 51},
                {5, 5}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Eleventh sample grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 10!", 10, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {4, 4}!", new int[]{4, 4}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenMatrixWithLongerIncompletePathAndShorterLowerCostIncompletePath_shouldNotFindPath() throws Exception {
        int[][] testGrid = {
                {51, 51, 51},
                {0, 51, 51},
                {51, 51, 51},
                {5, 5, 51}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertFalse("Twelfth sample grid should not have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 10, even when it's not a full path!", 10, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {4, 4}, even when it's not a full path!", new int[]{4, 4}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenMatrixWithLargeNumberColumns_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Thirteenth grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 20!", 20, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}!", new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenMatrixSingleNonValidLine_shouldNotFindPath() throws Exception {
        int[][] testGrid = {
                {51, -7, 3}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertFalse("Fourteenth grid should NOT have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should be 0!", 0, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {}!", new int[]{}, gridLowestCost.getPathTaken());
    }

    @Test
    public void whenSimpleMatrixWithLargeNumberColumns_shouldFindPath() throws Exception {
        int[][] testGrid = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };

        GridLowestCost gridLowestCost = new GridLowestCost(testGrid);

        assertTrue("Thirteenth grid should have a path!", gridLowestCost.calculatePath());
        assertEquals("Lowest path should have weight 24!", 24, gridLowestCost.getCalculatedWeight());
        assertArrayEquals("The path should be {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}!", new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, gridLowestCost.getPathTaken());
    }
}