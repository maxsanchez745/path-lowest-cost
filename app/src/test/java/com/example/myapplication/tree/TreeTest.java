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
        assertEquals("The accumulated weight of the root nodes should be 0!", 0, rootNodes[0].getAccumulatedWeight());
    }
}