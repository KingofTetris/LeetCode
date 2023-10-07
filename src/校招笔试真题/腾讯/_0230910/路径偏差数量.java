package 校招笔试真题.腾讯._0230910;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;
import LeetCode数据结构基础.day5.树.TreeUtils;

import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2023/9/10
 */
public class 路径偏差数量 {
    LinkedList<Integer> list = new LinkedList<>();
    int res = 0;
    public static void main(String[] args) {
        TreeNode root = TreeUtils.createTree(new Integer[]{1, 0, 0, 1, 0, null, 1});
        路径偏差数量 m1 = new 路径偏差数量();
        System.out.println(m1.pathNumber(root));
    }
    public int pathNumber (TreeNode root) {
        if (root == null) return 0;
        backtracking(root);
        return res;
    }

    private void backtracking(TreeNode root) {
        list.add(root.val);
        if (root.left == null && root.right == null){
            //找到一条路径。
            int c0 = 0,c1 = 0;
            for (Integer integer : list) {
                if (integer == 0) c0++;
                if (integer == 1) c1++;
            }
            if (c1 - c0 == 1) res++;
            return;
        }
        if (root.left != null){
            backtracking(root.left);
            list.remove(list.size() - 1);
        }
        if (root.right != null){
            backtracking(root.right);
            list.remove(list.size() - 1);
        }
    }
}
