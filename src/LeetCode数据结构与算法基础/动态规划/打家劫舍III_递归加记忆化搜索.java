package LeetCode数据结构与算法基础.动态规划;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

import java.util.HashMap;
import java.util.Map;

class 打家劫舍III_递归加记忆化搜索 {
    Map<TreeNode, Integer> umap = new HashMap<>(); // 记录计算过的结果
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        if (umap.containsKey(root)) return umap.get(root); // 如果 umap 里已经有记录则直接返回

        // 偷父节点 不考虑root的左右孩子
        int val1 = root.val;
        if (root.left != null)
            //因为相邻的不能偷，那么只能隔层去偷
            val1 += rob(root.left.left) + rob(root.left.right); // 跳过 root->left
        if (root.right != null)
            val1 += rob(root.right.left) + rob(root.right.right); // 跳过 root->right

        // 不偷父节点
        int val2 = rob(root.left) + rob(root.right); // 考虑 root 的左右孩子

        umap.put(root, Math.max(val1, val2)); // umap 记录一下结果
        return Math.max(val1, val2);
    }
}
