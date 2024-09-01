package LeetCode数据结构与算法基础.day5.树;

/**
 * @author KingofTetris
 * @File 二叉树的锯齿形层序遍历
 * @Time 2021/10/25  20:57
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

        例如：
        给定二叉树 [3,9,20,null,null,15,7],

         3
        / \
        9  20
        /  \
        15   7
        返回锯齿形层序遍历如下：

        [
        [3],
        [20,9],
        [15,7]
        ]*/
public class 二叉树的锯齿形层序遍历 {

    @Test
    public void tese(){

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(18);
        TreeNode node5 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);

        root.right = node2;
        root.left = node1;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        List<List<Integer>> res = zigzagLevelOrder(root);

        for (List<Integer> item:res) {
            for (Integer i:item) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        //层序遍历固定的四行
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);

        int count = 0;

        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> path = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left!=null) queue.add(temp.left);
                if (temp.right!=null) queue.add(temp.right);
                //奇数
                if (count%2 == 1){
                    path.addFirst(temp.val);
                }//偶数
                else {
                    path.addLast(temp.val);
                }
            }
            count++;
            res.add(new ArrayList<>(path));
        }
        return res;
    }

}
