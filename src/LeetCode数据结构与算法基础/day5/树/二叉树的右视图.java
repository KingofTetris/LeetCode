package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class 二叉树的右视图 {

    @Test
    public void test(){

    }

    //层序遍历返回每层最后的节点即可。
    public List<Integer> rightSideView(TreeNode root) {
        List<List<TreeNode>> levels = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            LinkedList<TreeNode> level = new LinkedList<>();
            int curSize = queue.size();
            //把当前队列中的节点都添加道队列中
            for (int i = 0; i < curSize; i++) {
                TreeNode temp = queue.poll();
                level.add(temp);
                if (temp.left != null){
                    queue.add(temp.left);
                }
                if (temp.right != null){
                    queue.add(temp.right);
                }
            }
            levels.add(level);
        }

        //然后取每层最后的节点添加到res中即可
        for (List<TreeNode> level : levels) {
            res.add(level.get(level.size() - 1).val);
        }

        return res;
    }
}
