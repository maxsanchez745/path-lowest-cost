package com.example.myapplication;

import org.junit.Test;

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
}