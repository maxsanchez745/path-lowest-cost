package com.example.myapplication.util;

import android.support.annotation.NonNull;

import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;

import java.util.ArrayList;

/**
 * Created by user on 8/10/17.
 */

public class TreeHelper {
    public static int getDepth(TreeNode treeNode) {
        int levels = 1;
        while (treeNode.getChildren() != null) {
            treeNode = treeNode.getChildren()[0];
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

    public static ArrayList<TreeNode> getLeafs(Tree tree) {
        ArrayList<TreeNode> leafs = new ArrayList<>();
        findLeafs(tree.getRootNodes(), leafs);
        return leafs;
    }

    private static void findLeafs(TreeNode[] treeNodes, ArrayList<TreeNode> leafs) {
        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getChildren() == null) {
                leafs.add(treeNode);
            } else {
                findLeafs(treeNode.getChildren(), leafs);
            }
        }
    }
}
