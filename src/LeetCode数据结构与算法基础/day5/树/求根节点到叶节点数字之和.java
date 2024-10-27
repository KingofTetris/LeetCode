package LeetCode数据结构与算法基础.day5.树;

/**
 * @author by KingOfTetris
 * @date 2024/10/27
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 */
public class 求根节点到叶节点数字之和 {

    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int i){
        if (root == null) return 0;
        int temp = i * 10 + root.val;
        //走到叶子节点，返回当前值
        if (root.left == null && root.right == null)
            return temp;
        //dfs左右节点。
        return helper(root.left, temp) + helper(root.right, temp);
    }

  /*  作者：Jacky
    链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers/solutions/131428/0-ms-jiao-ke-shu-ji-jie-da-by-liuzhaoce/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/


}
