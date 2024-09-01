package LeetCode数据结构与算法基础.day5.树;

/**
 * @author by KingOfTetris
 * @date 2024/8/31
 */
public class 二叉树的最小深度_DFS {



    /**
     * 递归法（思路来自二叉树最大深度的递归法）
     * 该题求最小深度，最小深度为根节点到叶子节点的深度，所以在迭代到每个叶子节点时更新最小值。
     * 这个栈空间太深了，不如直接层序遍历
     */

    int min = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        //如果root不为null，那么高度至少是1
        helpMinDepth(root.left, root.right, 1);
        return min;
    }

    private void helpMinDepth(TreeNode left, TreeNode right, int height) {
        //每找到一个深度就和min比较一下。
        if (left == null && right == null){
            min = Math.min(height,min);
        }
        //如果left为空，但right不为空，不能直接返回height
        //因为root到left，不是根节点到叶子节点，而是到空节点。这个不算深度

        //哪边不等于null 搜哪边。
        if (left == null && right != null) {
            helpMinDepth(right.left, right.right, height + 1);
        }
        //反过来也一样
        if (left != null && right == null) {
            helpMinDepth(left.left, left.right, height + 1);
        }

        //如果两边都不等于0,那就两边都继续搜
        if (left != null && right != null){
            helpMinDepth(left.left,left.right,height + 1);

            helpMinDepth(right.left,right.right,height + 1);
        }
    }


}
