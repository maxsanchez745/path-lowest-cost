package com.example.myapplication.tree;

import android.support.annotation.NonNull;

/**
 * Created by user on 8/9/17.
 */

public class Tree {
    private static final int UP = 0;
    private static final int DOWN = 2;

    private int[][] grid;

    private TreeNode[] rootNodes;

    // Tree constructor, creating the children depending on the length of the grid
    public Tree(int[][] grid) {
        this.grid = grid;
        this.rootNodes = new TreeNode[grid.length];

        initializeRootNodes();
        createChildren(rootNodes, 0);
    }

    // Initializing the toot Nodes of the tree depending on the length of the grid
    private void initializeRootNodes() {
        for (int i = 0; i < rootNodes.length; i++) {
            rootNodes[i] = new TreeNode(i, grid[i][0], 0, null, null);
        }
    }

    // Creates the children of an array of TreeNodes depending on the column, which will keep
    // increasing per each iteration
    private void createChildren(TreeNode[] treeNodes, int column) {
        if (column + 1 >= grid[0].length) {
            return;
        }
        for (TreeNode currentNode : treeNodes) {
            TreeNode[] childrenNode = new TreeNode[Math.min(3, grid.length)]; // Up, middle, down nodes
            for (int j = 0; j < childrenNode.length; j++) {
                childrenNode[j] = generateChildNode(column, currentNode, j);
            }
            currentNode.setChildren(childrenNode);
            createChildren(childrenNode, column + 1);
        }
    }

    // Creating a new childNode based on the current column, the currentNode (which will be the
    // parent of the node created) and the
    @NonNull
    private TreeNode generateChildNode(int column, TreeNode currentNode, int verticalPositionNextNode) {
        final int row = currentNode.getRow();
        final int weight = currentNode.getWeight();
        final int accumulatedWeight = currentNode.getAccumulatedWeight();
        final int newRow = calculateNewRow(row, verticalPositionNextNode);
        final int newWeight = getWeight(newRow, column + 1);
        return new TreeNode(newRow, newWeight, weight + accumulatedWeight, null, currentNode);
    }

    // Calculate the new row depending on the value of adjacent row, if the value is 0 means
    // that you will try to get the left-up cell, if the value is 1 means you will get the cell
    // right next to the current cell, if the value is 2 it means you are trying to get the right-down
    // cell. This method basically checks if there will be an overflown (to make the matrix wrap if
    // the current value is the bottom one and you want to go down)
    private int calculateNewRow(int row, int verticalPositionNextNode) {
        int newRow = row;
        if (verticalPositionNextNode == UP) {
            if (row == 0) {
                newRow = grid.length - 1; // Overflow up, go to the most bottom row
            } else {
                newRow = row - 1;
            }
        } else if (verticalPositionNextNode == DOWN) {
            if (row == grid.length - 1) {
                newRow = 0; // Overflow down, go to the most top row
            } else {
                newRow = row + 1;
            }
        }
        return newRow;
    }

    private int getWeight(int row, int column) {
        return grid[row][column];
    }

    public TreeNode[] getRootNodes() {
        return rootNodes;
    }
}
