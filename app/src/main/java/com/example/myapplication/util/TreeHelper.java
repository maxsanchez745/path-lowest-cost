package com.example.myapplication.util;

import android.support.annotation.NonNull;

import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;

/**
 * Created by user on 8/10/17.
 */

public class TreeHelper {
    @NonNull
    public static Tree initTree(int[][] largeGrid) {
        Tree tree = new Tree(largeGrid);
        tree.generateTree();
        return tree;
    }

    public static int getDepth(TreeNode treeNode) {
        TreeNode rootNodesLeft = treeNode;
        int levels = 1;
        while (rootNodesLeft.getChildren() != null) {
            rootNodesLeft = rootNodesLeft.getChildren()[0];
            levels++;
        }
        return levels;
    }

    public static TreeNode getMostLeftNode(Tree tree) {
        TreeNode treeNode = tree.getRootNodes()[0];
        while (treeNode.getChildren() != null) {
            treeNode = treeNode.getChildren()[0];
        }
        return treeNode;
    }
}
