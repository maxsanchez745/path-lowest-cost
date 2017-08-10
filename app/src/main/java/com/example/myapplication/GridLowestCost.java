package com.example.myapplication;

import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        ArrayList<TreeNode> leafs = getLeafs(tree);

        Collections.sort(leafs, this::compareFinalWeights);

        TreeNode lowestCostNode = leafs.get(0);
        calculatedWeight = lowestCostNode.getAccumulatedWeight() + lowestCostNode.getWeight();
        return calculatedWeight < 50;
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