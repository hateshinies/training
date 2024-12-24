package com.greatestsasha.training.leetcode.symmetrictree;
import com.greatestsasha.training.leetcode.treenode.TreeNode;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.right, root.left);
    }

    private boolean isMirror(TreeNode right, TreeNode left) {
        if (right == null && left == null) return true;
        if (right == null || left == null) return false;
        return (right.val == left.val) &&
                isMirror(right.left, left.right) &&
                isMirror(left.left, right.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        TreeNode sym = new TreeNode(1);
        sym.left = new TreeNode(2);
        sym.right = new TreeNode(2);
        sym.left.left = new TreeNode(3);
        sym.left.right = new TreeNode(4);
        sym.right.left = new TreeNode(4);
        sym.right.right = new TreeNode(3);

        System.out.println(new Solution().isSymmetric(sym));
        System.out.println(new Solution().isSymmetric(root));
    }
}