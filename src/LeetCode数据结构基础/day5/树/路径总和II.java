package LeetCode数据结构基础.day5.树;

import LeetCode数据结构入门.day5.树.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author KingofTetris
 * @File 路径总和II
 * @Time 2021/10/26  13:42
 */

/**输入二叉树和 target 找到所有 从根节点到叶子节点之和等于target的路径*/
public class 路径总和II {

    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    //看不懂递归就自己debug看过程 递归实际上就自带回溯
    @Test
    public void test(){
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
        List<List<Integer>> res = pathSum(root,30);

        for (List<Integer> item:res) {
            for (Integer i:item) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
    //DFS
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum);
        return ret;
    }

    public void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);

        //遍历到叶子节点不是目标值就把它弹出，回溯到父节点。
        path.pollLast();
    }

}
