package LeetCode数据结构与算法基础.动态规划;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 打家劫舍III_简单递归 {

    /**
     * 难度再升级，从数组变成二叉树
     * 实际上这题比起树形DP
     *
     * 直接递归更容易点
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;

        // 偷父节点
        int val1 = root.val;

        // 跳过 root->left，相当于不考虑左孩子了
        if (root.left != null)
            val1 += rob(root.left.left) + rob(root.left.right);

        // 跳过 root->right，相当于不考虑右孩子了
        if (root.right != null)
            val1 += rob(root.right.left) + rob(root.right.right);

        // 不偷父节点
        // 考虑 root 的左右孩子
        int val2 = rob(root.left) + rob(root.right);

        return Math.max(val1, val2);
    }
}
