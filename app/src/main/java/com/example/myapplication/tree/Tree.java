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

    }

    public TreeNode[] getRootNodes() {
        return rootNodes;
    }
}
