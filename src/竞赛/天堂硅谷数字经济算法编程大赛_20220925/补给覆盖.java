package 竞赛.天堂硅谷数字经济算法编程大赛_20220925;

import LeetCode数据结构入门.day5.树.TreeNode;

/**
 * @author by KingOfTetris
 * @date 2022/9/26
 */
public class 补给覆盖 {
    private int count = 0;
    public int minSupplyStationNumber(TreeNode root) {
        if (dfs(root) == 0) count++;
        return count;
    }

    private int dfs(TreeNode root){
        if (root == null) return -1;

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (left == 0 || right == 0) {
            count++;
            return 2;
        }

        if (left == 2 || right == 2) {
            return 1;
        }

        //其他情况 返回0
        return 0;
    }
}
