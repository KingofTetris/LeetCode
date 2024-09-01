package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author KingofTetris
 * @File 路径总和II
 * @Time 2021/10/26  13:42
 */

/**输入二叉树和 target 找到所有 从根节点到叶子节点之和等于target的路径
 *
 * 路径总和I就是判断有没有这条路径可以了。一样的做法。
 * */
public class 路径总和II {

    @Test
    public void test(){
        TreeNode root = TreeUtils.createTree(new Integer[]{1, 2, 3, 4, 4, 6, 7});
        TreeUtils.show(root);
        List<List<Integer>> res = pathSum(root,10);
        for (List<Integer> item:res) {
            for (Integer i:item) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }



    //还是这种找出所有方案的还是回溯
    List<List<Integer>> ret = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    //回溯
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        backtracking(root,targetSum);
        return ret;
    }

    public void backtracking(TreeNode root, int targetSum) {
        //找到叶子节点并且targetSum = 0;
        path.add(root.val);
        targetSum -= root.val;
        //终止条件。
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
            return;
        }
        else if (root.left == null && root.right == null && targetSum != 0){
            return;
        }

        /**
         * 看到没，这里回溯了两次。
         */
        backtracking(root.left, targetSum);
        //回溯重置条件。
        path.remove(path.size() - 1);
        backtracking(root.right, targetSum);
        //回溯重置条件。
        path.remove(path.size() - 1);
    }

}
