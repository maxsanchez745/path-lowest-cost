package com.example.myapplication.tree;

/**
 * Created by user on 8/9/17.
 */

public class TreeNode {
    private int row;
    private int weight;
    private Integer accumulatedWeight;
    private TreeNode[] children;
    private TreeNode parent;

    // TreeNode which will allow us to store different children (B-Tree) and the accumulated weight
    // in each path, as well as its parent, so the path can be retrived later.
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

    public Integer getAccumulatedWeight() {
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

    @Override
    public String toString() {
        return "TreeNode{" +
                "row=" + row +
                ", weight=" + weight +
                ", accumulatedWeight=" + accumulatedWeight +
                '}';
    }
}
