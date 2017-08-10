package com.example.myapplication.tree;

/**
 * Created by evin on 8/9/17.
 */

public class TreeNode {
    private int row;
    private int weight;
    private int accumulatedWeight;
    private TreeNode[] children;

    public TreeNode(int row, int weight, int accumulatedWeight, TreeNode[] children) {
        this.row = row;
        this.weight = weight;
        this.accumulatedWeight = accumulatedWeight;
        this.children = children;
    }
}
