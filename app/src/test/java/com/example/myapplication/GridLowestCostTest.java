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
    }
}