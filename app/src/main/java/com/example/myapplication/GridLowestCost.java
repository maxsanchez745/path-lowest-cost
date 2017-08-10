package com.example.myapplication;

import com.example.myapplication.tree.Tree;

/**
 * Created by evin on 8/9/17.
 */

public class GridLowestCost {
    private static final int MAXIMUM_WEIGHT = 50;

    private int[][] grid;

    private boolean pathExists;
    private int calculatedWeight;
    private int[] pathTaken;

    public GridLowestCost(int[][] grid) {
        this.grid = grid;

        pathExists = false;
        calculatedWeight = 0;
        pathTaken = null;
    }

    public boolean calculatePath() {
        Tree tree = new Tree(grid);
        tree.generateTree();
        return false;
    }

    public int getCalculatedWeight() {
        return calculatedWeight;
    }

    public int[] getPathTaken() {
        return pathTaken;
    }
}