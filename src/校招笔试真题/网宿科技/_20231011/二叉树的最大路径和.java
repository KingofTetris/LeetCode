package 校招笔试真题.网宿科技._20231011;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/10/11
 */
public class 二叉树的最大路径和 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }
        TreeNode[] nodes = new TreeNode[n + 1];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode(val[i]);//初始化n个节点.
        }
        int[] parent  = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = sc.nextInt();
        }
        for (int i = 1; i < parent.length; i++) {
            if (nodes[parent[i] - 1].left == null){
                nodes[parent[i] - 1].left = nodes[i];
                continue;
            }
            if (nodes[parent[i] - 1].right == null){
                nodes[parent[i] - 1].right = nodes[i];
            }
        }
//        TreeUtils.show(nodes[0]);
        int sumMax = getSumMax(nodes[0]);
        System.out.println(sumMax);
    }

    static int ans = Integer.MIN_VALUE;

    public static int getSumMax(TreeNode root){
        backTracking(root);
        return ans;
    }

    private static int backTracking(TreeNode root) {
        //终止条件
        if (root == null) return 0;
        int left = backTracking(root.left);
        int right = backTracking(root.right);
        int temp = root.val;
        //如果大于0就相加
        if (left > 0) temp += left;
        if (right > 0) temp += right;
        ans = Math.max(temp,ans);
        return Math.max(root.val,Math.max(left,right) + root.val);
    }
}
