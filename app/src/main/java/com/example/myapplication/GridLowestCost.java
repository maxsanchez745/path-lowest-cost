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

    // Constructor that receives a simple bi-dimensional array of integers
    public GridLowestCost(int[][] grid) {
        if (grid.length < 1) {
            throw new InvalidMatrixException();
        }
        this.grid = grid;

        calculatedWeight = 0;
        pathTaken = null;
    }

    // Constructor that receives a simple bi-dimensional array of Objects,
    // each value need to be parsed to an Integer
    public GridLowestCost(Object[][] objectGrid) {
        if (objectGrid == null || objectGrid.length < 1) {
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

    // Tree gets created by looking at the possible values in the grid, when tree is ready
    // move to test the leafs, and generate an ordered array to get the leaf with the lowest value
    // which will be "Lowest possible path"
    public boolean calculatePath() {
        Tree tree = new Tree(grid);

        if (!hasRootsLowerMaxValue(tree)) {
            pathTaken = new int[]{};
            return false;
        }

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
        if (calculatedWeight > 50) {
            pathTaken = new int[]{};
            calculatedWeight = 0;
            return false;
        }
        pathTaken = traversePath(lowestBeforeMax, TreeHelper.calculateDistanceFromRoot(lowestBeforeMax));
        return false;
    }

    // Early out check that validates that the Tree has at least 1 valid root node
    private boolean hasRootsLowerMaxValue(Tree tree) {
        for (TreeNode treeNode : tree.getRootNodes()) {
            if (treeNode.getWeight() < 50) {
                return true;
            }
        }
        return false;
    }

    // Get the path that the leaf had to go through in the tree
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

    // Reversing the array to instead of going from right to left, we go from left to right
    private void reverseArray(int[] path) {
        for (int i = 0; i < path.length / 2; i++) {
            swap(path, i, path.length - 1 - i);
        }
    }

    // Simple swap function
    private void swap(int[] array, int a, int b) {
        int aux = array[a];
        array[a] = array[b];
        array[b] = aux;
    }

    // Used in the comparator to check total weight (current node weight plus accumulated
    // weight), used to get the lowest possible path (leaf with lowest total weight)
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