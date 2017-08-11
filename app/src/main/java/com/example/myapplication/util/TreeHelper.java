package com.example.myapplication.util;

import com.example.myapplication.tree.Tree;
import com.example.myapplication.tree.TreeNode;

import java.util.ArrayList;

/**
 * Created by user on 8/10/17.
 */

public class TreeHelper {
    // Get the depth of the node looping through its children until a leaf is found
    public static int getDepth(TreeNode treeNode) {
        int levels = 1;
        while (treeNode.getChildren() != null) {
            treeNode = treeNode.getChildren()[0];
            levels++;
        }
        return levels;
    }

    // Overloading method to get the depth of the tree (which stores the root node)
    public static int getDepth(Tree tree) {
        return getDepth(tree.getRootNodes()[0]);
    }

    // Get the most left leaf. Used for testing purposes
    public static TreeNode getMostLeftNode(Tree tree) {
        TreeNode treeNode = tree.getRootNodes()[0];
        while (treeNode.getChildren() != null) {
            treeNode = treeNode.getChildren()[0];
        }
        return treeNode;
    }

    // Find all the leafs in the tree which will subsequently be possible candidates to be the
    // lowest path.
    public static ArrayList<TreeNode> getLeafs(Tree tree) {
        ArrayList<TreeNode> leafs = new ArrayList<>();
        findLeafs(tree.getRootNodes(), leafs);
        return leafs;
    }
    // Find all the leafs in the tree (elements with no children), looping through the tree until
    // a children is not found.
    private static void findLeafs(TreeNode[] treeNodes, ArrayList<TreeNode> leafs) {
        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getChildren() == null) {
                leafs.add(treeNode);
            } else {
                findLeafs(treeNode.getChildren(), leafs);
            }
        }
    }

    // Find a possible sub-path (a path that doesn't go all the way to the right)
    public static TreeNode findLowestNodeBeforeMax(Tree tree, int maximumWeight) {
        TreeTraverse treeTraverse = new TreeTraverse(maximumWeight);
        return treeTraverse.traverse(tree);
    }

    // Keep going through the path until you find a parent
    public static int calculateDistanceFromRoot(TreeNode lowestBeforeMax) {
        int aux = 0;
        while (lowestBeforeMax != null) {
            aux++;
            lowestBeforeMax = lowestBeforeMax.getParent();
        }
        return aux;
    }

    // Helper class that helps to traverse the whole tree recursively.
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

        // Find a the max node (but still lower than the maxValue (50)) recursively
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
