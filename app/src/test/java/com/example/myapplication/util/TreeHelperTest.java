package com.example.myapplication.util;

import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;

import org.junit.Test;

import java.util.ArrayList;

import static com.example.myapplication.util.TreeHelper.getLeafs;
import static com.example.myapplication.util.TreeHelper.initTree;
import static org.junit.Assert.*;

/**
 * Created by user on 8/10/17.
 */
public class TreeHelperTest {
    @Test
    public void onSimpleGrid_shouldReturnNineLeafs() throws Exception {
        int[][] simpleGrid = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        Tree tree = initTree(simpleGrid);
        final ArrayList<TreeNode> leafs = getLeafs(tree);

        assertEquals("There should be 9 leafs on this tree!", 9, leafs.size());
    }
}