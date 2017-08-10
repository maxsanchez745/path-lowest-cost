package com.example.myapplication.tree;

import android.support.annotation.NonNull;

/**
 * Created by evin on 8/9/17.
 */

public class Tree {
    private int[][] grid;

    private TreeNode[] rootNodes;

    public Tree(int[][] grid) {
        this.grid = grid;
        this.rootNodes = new TreeNode[grid.length];

        initializeRootNodes();
    }

    private void initializeRootNodes() {
        for (int i = 0; i < rootNodes.length; i++) {
            rootNodes[i] = new TreeNode(i, grid[i][0], 0, null);
        }
    }

    public void generateTree() {
        createChildren(rootNodes, 0);
    }

    private void createChildren(TreeNode[] treeNodes, int column) {
        if (column + 1 >= grid[0].length) {
            return;
        }
        for (TreeNode currentNode : treeNodes) {
            TreeNode[] childrenNode = new TreeNode[3]; // Up, middle, down nodes
            for (int j = 0; j < childrenNode.length; j++) {
                childrenNode[j] = generateChildNode(column, currentNode, j);
            }
            currentNode.setChildren(childrenNode);
            createChildren(childrenNode, column + 1);
        }
    }

    @NonNull
    private TreeNode generateChildNode(int column, TreeNode currentNode, int j) {
        final int row = currentNode.getRow();
        final int weight = currentNode.getWeight();
        final int accumulatedWeight = currentNode.getAccumulatedWeight();
        final int newRow = calculateNewRow(row, j);
        final int newWeight = getWeight(newRow, column + 1);
        return new TreeNode(newRow, newWeight, weight + accumulatedWeight, null);
    }

    private int calculateNewRow(int row, int j) {
        switch (j) {
            case 0: // Up - right cell
                if (row == 0) {
                    return grid.length - 1; // Overflow up
                } else {
                    return row - 1;
                }
            case 2: // Bottom - right cell
                if (row == grid.length - 1) {
                    return 0;
                } else {
                    return row + 1;
                }
        }
        return row;
    }

    private int getWeight(int row, int column) {
        return grid[row][column];
    }

    public TreeNode[] getRootNodes() {
        return rootNodes;
    }
}
