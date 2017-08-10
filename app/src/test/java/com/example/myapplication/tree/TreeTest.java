package com.example.myapplication.tree;

import android.support.annotation.NonNull;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by evin on 8/9/17.
 */
public class TreeTest {
    // TODO: 8/9/17

    @Test
    public void onSimpleTreeCreation_shouldInitializeNodes() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        Tree tree = new Tree(simpleGrid);
        TreeNode[] rootNodes = tree.getRootNodes();

        for (int i = 0; i < rootNodes.length; i++) {
            assertEquals("The accumulated weight of the root nodes should be 0!", 0, rootNodes[i].getAccumulatedWeight());
        }
        for (int i = 0; i < rootNodes.length; i++) {
            assertEquals("The initial weight should match the first value of each row!" + i, simpleGrid[i][0], rootNodes[i].getWeight());
        }
        for (int i = 0; i < rootNodes.length; i++) {
            assertEquals("The initial row of the rootNodes should be " + i, i, rootNodes[i].getRow());
        }
    }

    @Test
    public void onSimpleTreeCreation_shouldGenerateLevels() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        Tree tree = initTree(simpleGrid);
        int levels = getLevels(tree.getRootNodes()[0]);

        assertEquals("The 3x3 simpleGrid generated-tree should have 3 levels", 3, levels);
    }

    @Test
    public void onLargeTreeCreation_shouldGenerateDepthLevels() throws Exception {
        int[][] largeGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        Tree tree = initTree(largeGrid);
        int levels = getLevels(tree.getRootNodes()[0]);

        assertEquals("The 3x8 largeGrid generated-tree should have 3 levels", 3, levels);
    }

    @Test
    public void onWideTreeCreation_shouldGenerateDepthLevels() throws Exception {
        int[][] wideGrid = {
                {1, 0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 0, 1, 0}
        };

        Tree tree = initTree(wideGrid);
        int levels = getLevels(tree.getRootNodes()[0]);

        assertEquals("The 8x3 wideGrid generated-tree should have 8 levels", 8, levels);
    }

    @Test
    public void onSimpleTreeCreation_middleElementsShouldHaveAccumulatedWeight() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        Tree tree = initTree(simpleGrid);
        TreeNode rootNodesLeft = tree.getRootNodes()[0];
        TreeNode middleNode = rootNodesLeft.getChildren()[0];
        final int accumulatedWeight = middleNode.getAccumulatedWeight();

        assertEquals("The middle node " + middleNode + "should have accumulatedDepth 1", 1, accumulatedWeight);
    }

    @Test
    public void onSimpleTreeCreation_leafElementsShouldHaveAccumulatedWeight() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        Tree tree = initTree(simpleGrid);
        TreeNode rootNodesLeft = tree.getRootNodes()[0];
        TreeNode middleNode = rootNodesLeft.getChildren()[0];
        TreeNode leafNode = middleNode.getChildren()[0];
        final int accumulatedWeight = leafNode.getAccumulatedWeight();

        assertEquals("The leaf node " + leafNode + "should have accumulatedDepth 2", 2, accumulatedWeight);
    }

    @Test
    public void onTreeTestCaseCreation_shouldGenerateAccumulatedWeightOnLeafs() throws Exception {
        int[][] testGrid = {
                {3, 4, 1, 2, 8, 6},
                {6, 1, 8, 2, 7, 4},
                {5, 9, 3, 9, 9, 5},
                {8, 4, 1, 3, 2, 6},
                {3, 7, 2, 8, 6, 4}
        };

        Tree tree = initTree(testGrid);

        int accumulatedWeightMostLeftLeafNode = tree.getRootNodes()[0]
                .getChildren()[0]
                .getChildren()[0]
                .getChildren()[0]
                .getChildren()[0]
                .getChildren()[0].getAccumulatedWeight();

        assertEquals("The most left leaf node should have an accumulated weight of 27", 27, accumulatedWeightMostLeftLeafNode);
    }

    @NonNull
    private Tree initTree(int[][] largeGrid) {
        Tree tree = new Tree(largeGrid);
        tree.generateTree();
        return tree;
    }

    private int getLevels(TreeNode treeNode) {
        TreeNode rootNodesLeft = treeNode;
        int levels = 1;
        while (rootNodesLeft.getChildren() != null) {
            rootNodesLeft = rootNodesLeft.getChildren()[0];
            levels++;
        }
        return levels;
    }
}