package com.example.myapplication.tree;

/**
 * Created by evin on 8/9/17.
 */

public class Tree {
    private int[][] grid;

    private TreeNode[] rootNodes;

    public Tree(int[][] grid) {
        this.grid = grid;
        this.rootNodes = new TreeNode[grid[0].length];

        initializeRootNodes();
    }

    private void initializeRootNodes() {

    }

    public void generateTree() {

    }
}
