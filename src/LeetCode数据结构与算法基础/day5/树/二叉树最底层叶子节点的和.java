package LeetCode数据结构与算法基础.day5.树;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 二叉树最底层叶子节点的和 {

   /* public int treeDepth(TreeNode root){
        return root == null ? 0 : Math.max(treeDepth(root.left) + 1,treeDepth(root.right) + 1);
    }*/


    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            sum = 0; // 重置为0，因为我们只关心最后一层的叶子节点和，当queue为空的时候，当前的sum就是最后一层叶子之和
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }
}
