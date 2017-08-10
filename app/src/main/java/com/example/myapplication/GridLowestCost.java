package com.example.myapplication;

import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.myapplication.util.TreeHelper.getDepth;
import static com.example.myapplication.util.TreeHelper.getLeafs;

/**
 * Created by user on 8/9/17.
 */

public class GridLowestCost {
    private static final int MAXIMUM_WEIGHT = 50;

    private int[][] grid;

    private int calculatedWeight;
    private int[] pathTaken;

    public GridLowestCost(int[][] grid) {
        this.grid = grid;

        calculatedWeight = 0;
        pathTaken = null;
    }

    public boolean calculatePath() {
        Tree tree = new Tree(grid);
        int depth = getDepth(tree.getRootNodes()[0]);
        ArrayList<TreeNode> leafs = getLeafs(tree);

        Collections.sort(leafs, this::compareFinalWeights);

        TreeNode lowestCostNode = leafs.get(0);
        calculatedWeight = lowestCostNode.getAccumulatedWeight() + lowestCostNode.getWeight();
        pathTaken = traversePath(lowestCostNode, depth);
        return calculatedWeight < 50;
    }

    private int[] traversePath(TreeNode node, int depth) {
        int[] path = new int[depth];
        int i = 0;
        while (node != null) {
            path[i++] = node.getRow();
            node = node.getParent();
        }
        return path;
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