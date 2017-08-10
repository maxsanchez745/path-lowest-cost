package com.example.myapplication.tree;

/**
 * Created by user on 8/9/17.
 */

public class TreeNode {
    private int row;
    private int weight;
    private int accumulatedWeight;
    private TreeNode[] children;
    private TreeNode parent;

    public TreeNode(int row, int weight, int accumulatedWeight, TreeNode[] children, TreeNode parent) {
        this.row = row;
        this.weight = weight;
        this.accumulatedWeight = accumulatedWeight;
        this.children = children;
        this.parent = parent;
    }

    public int getRow() {
        return row;
    }

    public int getWeight() {
        return weight;
    }

    public int getAccumulatedWeight() {
        return accumulatedWeight;
    }

    public TreeNode[] getChildren() {
        return children;
    }

    public void setChildren(TreeNode[] children) {
        this.children = children;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
