package com.example.myapplication.tree;

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
            rootNodes[i] = new TreeNode(1, grid[i][0], 0, null);
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
                childrenNode[j] = new TreeNode(currentNode.getRow() + 1, getWeight(currentNode.getRow(), column, j), currentNode.getWeight() + currentNode.getAccumulatedWeight(), null);
            }
            currentNode.setChildren(childrenNode);
            createChildren(childrenNode, column + 1);
        }
    }

    private int getWeight(int row, int column, int j) {
        return 0;
    }

    public TreeNode[] getRootNodes() {
        return rootNodes;
    }
}
