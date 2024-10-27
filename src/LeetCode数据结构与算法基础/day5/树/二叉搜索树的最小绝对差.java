package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2023/9/26
 */
public class 二叉搜索树的最小绝对差 {

    @Test
    public void test(){
        TreeNode tree = TreeUtils.createTree(new Integer[]{1, 0, 48, null, null, 12, 49});
        int minimumDifference = getMinimumDifference(tree);
        System.out.println("minimumDifference = " + minimumDifference);
    }

    int min = Integer.MAX_VALUE;
    LinkedList<Integer> nums = new LinkedList<>();
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    //中序遍历，同时记录最小min
    public void inorder(TreeNode root){
        //左中右
        if (root == null){
            return;
        }
        inorder(root.left);
        nums.add(root.val);
        //每次计算最新两个数的差值，因为是bst，中序遍历cur一定是大于pre的。
        if (nums.size() >= 2){
            int cur = nums.get(nums.size() - 1);
            int pre = nums.get(nums.size() - 1 - 1);
            min = Math.min(min,cur - pre);
        }
        inorder(root.right);
    }
}
