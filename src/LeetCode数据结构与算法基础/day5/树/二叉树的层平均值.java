package LeetCode数据结构与算法基础.day5.树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2024/10/27
 */
public class 二叉树的层平均值 {

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            long sum = 0;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                sum += node.val;
            }
            double avg = (double) sum / n;
            res.add(avg);
        }
        return res;
    }
}
