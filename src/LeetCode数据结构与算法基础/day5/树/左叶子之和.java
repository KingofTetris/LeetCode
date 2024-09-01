package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/8/31
 */
public class 左叶子之和 {


    @Test
    public void test(){
        Integer[] nums = {1};
        TreeNode tree = TreeUtils.createTree(nums);
        int sumOfLeftLeaves = sumOfLeftLeaves(tree);
        System.out.println(sumOfLeftLeaves);
    }
    /**
     * 首先要注意是判断左叶子，不是二叉树左侧节点，所以不要上来想着层序遍历。
     * 要和二叉树的右视图作出区分
     *     1
     *   2    3
     *     4    5
     *       6
     * 比如这个图，2，4确实是左叶子，但是6是右叶子。
     * 6也是队列中的第一个叶子节点，但他不是左叶子，所以这题不能用层序
     * @param root
     * @return
     */
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return sum;
        //如果只有根节点，按照题意他也不算叶子节点，那就还是返回0
        if (root.left == null && root.right == null) return sum;
        helpSum(root.left,1);
        helpSum(root.right,0);
        return sum;
    }

    //如果flag为1，说明他是父节点的左孩子
    private void helpSum(TreeNode root,int flag) {
        if (root == null ) return;
        if (root.left == null && root.right == null && flag == 1){
            sum += root.val;
        }
        helpSum(root.left,1);
        helpSum(root.right,0);
    }
}
