package 校招笔试真题.腾讯._0230910;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 腾讯笔试20230910_权值优势路径计数 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        TreeNode root = constructTree(arr);
        int res = traversal(root,0,0);
        System.out.println(res);

    }

    private static int traversal(TreeNode root, int zeronum, int onenum) {
        //根节点为空返回0
        if(root == null)return 0;
        //找到叶子节点就可以统计了
        if(root.left == null && root.right ==null){
            if(root.val == 0)zeronum++;
            else onenum++;
            //如果1的数量刚好比0多1，结果+1
            if(onenum - zeronum == 1)return 1;
            else return 0;
        }
        //根据节点的值统计1和0的个数
        if(root.val == 0)zeronum++;
        else onenum++;

        int left = traversal(root.left, zeronum, onenum);
        int right = traversal(root.right, zeronum, onenum);
        return left + right;
    }


    //层序遍历将数组构建为二叉树
    private static TreeNode constructTree(int[] arr) {
        if (arr[0] == -1)return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            //如果节点是-1，代表空节点，就不用添加了。
            if(index < arr.length && arr[index] != -1){
                cur.left = new TreeNode(arr[index]);
                queue.add(cur.left);
                index++;
            }
            else if(index < arr.length && arr[index] == -1){
                index++;
            }
            if(index < arr.length && arr[index] != -1){
                cur.right = new TreeNode(arr[index]);
                queue.add(cur.right);
                index++;
            }
            else if(index < arr.length && arr[index] == -1){
                index++;
            }
        }
        return root;
    }

}
