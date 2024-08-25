package LeetCode数据结构与算法基础.day5.树;

/**
 * @author by KingOfTetris
 * @date 2024/8/17
 */
public class 路径总和III {


    /**
     * 实际上是前缀和的应用题
     * 和560 和为K的子数组是一个类型
     * @param root
     * @param targetSum
     * @return
     */

    //这道题DFS也能解，等把DFS,BFS和回溯搞完再回头来看这个吧
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = rootSum(root, targetSum); //判断自己等于不等于targetSum
        ret += pathSum(root.left, targetSum);//判断左子树
        ret += pathSum(root.right, targetSum);//判断右子树
        return ret;
    }

    private int rootSum(TreeNode root, int targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }
        /**
         * targetSum - 当前值 向下遍历
         * 相当于把前面走过的路都加起来。
         */
        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;

    }
}
