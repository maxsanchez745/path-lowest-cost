package com.example.myapplication.util;

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

    public static int getDepth(Tree tree) {
        return getDepth(tree.getRootNodes()[0]);
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

    public static TreeNode findLowestNodeBeforeMax(Tree tree, int maximumWeight) {
        TreeTraverse treeTraverse = new TreeTraverse(maximumWeight);
        return treeTraverse.traverse(tree);
    }

    public static int calculateDistanceFromRoot(TreeNode lowestBeforeMax) {
        int aux = 0;
        while (lowestBeforeMax != null) {
            aux++;
            lowestBeforeMax = lowestBeforeMax.getParent();
        }
        return aux;
    }

    private static class TreeTraverse {
        private TreeNode treeNode;
        private int maxValue;

        private TreeTraverse(int maxValue) {
            this.maxValue = maxValue;
        }

        private TreeNode traverse(Tree tree) {
            for (TreeNode node : tree.getRootNodes()) {
                traverseTreeRecursive(node);
            }
            return treeNode;
        }

        private void traverseTreeRecursive(TreeNode currentNode) {
            if (treeNode == null || treeNode.getAccumulatedWeight() + treeNode.getWeight() > maxValue) {
                treeNode = currentNode;
                return;
            }
            final int totalWeightCurrentNode = currentNode.getAccumulatedWeight() + currentNode.getWeight();
            final int totalWeightMaxNode = treeNode.getAccumulatedWeight() + treeNode.getWeight();
            if (totalWeightCurrentNode > totalWeightMaxNode && totalWeightCurrentNode < maxValue) {
                treeNode = currentNode;
            }
            if (currentNode.getChildren() != null) {
                for (TreeNode node : currentNode.getChildren()) {
                    traverseTreeRecursive(node);
                }
            }
        }
    }
}
