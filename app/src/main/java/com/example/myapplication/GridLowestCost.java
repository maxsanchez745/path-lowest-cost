package com.example.myapplication;

import com.example.myapplication.exception.InvalidMatrixException;
import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;
import com.example.myapplication.util.TreeHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 8/9/17.
 */

public class GridLowestCost {
    private static final int MAXIMUM_WEIGHT = 50;

    private int[][] grid;

    private int calculatedWeight;
    private int[] pathTaken;

    public GridLowestCost(int[][] grid) {
        if (grid.length < 1) {
            throw new InvalidMatrixException();
        }
        this.grid = grid;

        calculatedWeight = 0;
        pathTaken = null;
    }

    public GridLowestCost(Object[][] objectGrid) {
        if (objectGrid.length < 1) {
            throw new InvalidMatrixException();
        }
        this.grid = new int[objectGrid.length][objectGrid[0].length];
        for (int i = 0; i < objectGrid.length; i++) {
            for (int j = 0; j < objectGrid[i].length; j++) {
                final int gridValue;
                try {
                    gridValue = Integer.parseInt(String.valueOf(objectGrid[i][j]));
                    this.grid[i][j] = gridValue;
                } catch (NumberFormatException e) {
                    throw new InvalidMatrixException();
                }
            }
        }
    }

    public boolean calculatePath() {
        Tree tree = new Tree(grid);
        int depth = TreeHelper.getDepth(tree);
        ArrayList<TreeNode> leafs = TreeHelper.getLeafs(tree);

        Collections.sort(leafs, this::compareFinalWeights);

        TreeNode lowestCostNode = leafs.get(0);

        calculatedWeight = lowestCostNode.getAccumulatedWeight() + lowestCostNode.getWeight();
        if (calculatedWeight < 50) {
            pathTaken = traversePath(lowestCostNode, depth);
            return true;
        }

        TreeNode lowestBeforeMax = TreeHelper.findLowestNodeBeforeMax(tree, MAXIMUM_WEIGHT);
        calculatedWeight = lowestBeforeMax.getAccumulatedWeight() + lowestBeforeMax.getWeight();
        pathTaken = traversePath(lowestBeforeMax, TreeHelper.calculateDistanceFromRoot(lowestBeforeMax));
        return false;
    }

    private int[] traversePath(TreeNode node, int depth) {
        int[] path = new int[depth];
        int i = 0;
        while (node != null) {
            path[i++] = node.getRow() + 1;
            node = node.getParent();
        }
        reverseArray(path);
        return path;
    }

    private void reverseArray(int[] path) {
        for (int i = 0; i < path.length / 2; i++) {
            swap(path, i, path.length - 1 - i);
        }
    }

    private void swap(int[] array, int a, int b) {
        int aux = array[a];
        array[a] = array[b];
        array[b] = aux;
    }

    private int compareFinalWeights(TreeNode t1, TreeNode t2) {
        Integer finalWeight1 = t1.getWeight() + t1.getAccumulatedWeight();
        Integer finalWeight2 = t2.getWeight() + t2.getAccumulatedWeight();
        return finalWeight1.compareTo(finalWeight2);
    }

    public int getCalculatedWeight() {
        return calculatedWeight;
    }

    public int[] getPathTaken() {
        return pathTaken;
    }
}