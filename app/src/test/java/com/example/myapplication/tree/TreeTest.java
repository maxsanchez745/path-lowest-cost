package com.example.myapplication.tree;

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
        Tree tree = new Tree(simpleGrid);
        tree.generateTree();
        TreeNode rootNodesLeft = tree.getRootNodes()[0];
        int levels = 1;
        while (rootNodesLeft.getChildren() != null) {
            rootNodesLeft = rootNodesLeft.getChildren()[0];
            levels++;
        }

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
        Tree tree = new Tree(largeGrid);
        tree.generateTree();
        TreeNode rootNodesLeft = tree.getRootNodes()[0];
        int levels = 1;
        while (rootNodesLeft.getChildren() != null) {
            rootNodesLeft = rootNodesLeft.getChildren()[0];
            levels++;
        }

        assertEquals("The 3x8 largeGrid generated-tree should have 3 levels", 3, levels);
    }

    @Test
    public void onWideTreeCreation_shouldGenerateDepthLevels() throws Exception {
        int[][] wideGrid = {
                {1, 0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 0, 1, 0}
        };
        Tree tree = new Tree(wideGrid);
        tree.generateTree();
        TreeNode rootNodesLeft = tree.getRootNodes()[0];
        int levels = 1;
        while (rootNodesLeft.getChildren() != null) {
            rootNodesLeft = rootNodesLeft.getChildren()[0];
            levels++;
        }

        assertEquals("The 8x3 wideGrid generated-tree should have 8 levels", 8, levels);
    }

    @Test
    public void onSimpleTreeCreation_middleElementsShouldHaveAccumulatedWeight() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };
        Tree tree = new Tree(simpleGrid);
        tree.generateTree();
        TreeNode rootNodesLeft = tree.getRootNodes()[0];
        TreeNode middleNode = rootNodesLeft.getChildren()[0];
        assertEquals("The middle node " + middleNode + "should have accumulatedDepth 1", 1, middleNode.getAccumulatedWeight());
    }

    @Test
    public void onSimpleTreeCreation_leafElementsShouldHaveAccumulatedWeight() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };
        Tree tree = new Tree(simpleGrid);
        tree.generateTree();
        TreeNode rootNodesLeft = tree.getRootNodes()[0];
        TreeNode middleNode = rootNodesLeft.getChildren()[0];
        TreeNode leafNode = middleNode.getChildren()[0];
        assertEquals("The leaf node " + leafNode + "should have accumulatedDepth 2", 2, leafNode.getAccumulatedWeight());
    }
}