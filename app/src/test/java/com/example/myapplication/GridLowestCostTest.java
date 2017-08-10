package com.example.myapplication;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

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
        assertArrayEquals("The path should be {1, 0, 1}!", new int[]{1, 0, 1}, gridLowestCost.getPathTaken());
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
}