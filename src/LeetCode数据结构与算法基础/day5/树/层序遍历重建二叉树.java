package LeetCode数据结构与算法基础.day5.树;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 层序遍历重建二叉树 {

    //输入数组，-1代表空节点，层序遍历构建二叉树
    public static TreeNode constructTree(int[] arr) {
        if (arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            //插入左孩子
            if (index < arr.length && arr[index] != -1) {
                cur.left = new TreeNode(arr[index]);
                queue.add(cur.left);
                index++;
            }
            else if (index < arr.length && arr[index] == -1) {
                index++;
            }

            //插入右孩子
            if (index < arr.length && arr[index] != -1) {
                cur.right = new TreeNode(arr[index]);
                queue.add(cur.right);
                index++;
            }
            else if (index < arr.length && arr[index] == -1) {
                index++;
            }
        }
        return root;
    }
}
