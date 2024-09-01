package LeetCode数据结构与算法基础.day5.树;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2024/8/31
 */
public class 二叉树的最小深度_层序遍历 {
    /**
     * 迭代法，层序遍历
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                // 是叶子结点，直接返回depth，因为从上往下遍历，所以该值就是最小值
                if (poll.left == null && poll.right == null) {
                    return depth;
                }
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return depth;
    }
}
