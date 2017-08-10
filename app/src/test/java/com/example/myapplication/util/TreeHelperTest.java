package com.example.myapplication.util;

import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;

import org.junit.Test;

import java.util.ArrayList;

import static com.example.myapplication.util.TreeHelper.getLeafs;
import static org.junit.Assert.assertEquals;

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

        Tree tree = new Tree(simpleGrid);
        final ArrayList<TreeNode> leafs = getLeafs(tree);

        assertEquals("There should be 27 leafs on this tree!", 27, leafs.size());
    }
}