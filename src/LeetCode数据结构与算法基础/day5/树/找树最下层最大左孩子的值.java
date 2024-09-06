package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/8/31
 */
public class 找树最下层最大左孩子的值 {

    @Test
    public void test(){
        Integer[] nums = new Integer[]{1,null,1};
        TreeNode tree = TreeUtils.createTree(nums);
        int value = findBottomLeftValue(tree);
        System.out.println(value);
    }
    /**
     * 理解错了，最左边 != 左孩子。那其实这个应该用层序遍历的，因为最左边就是队头元素。
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     *
     * 假设二叉树中至少有一个节点。
     *
     * 和左叶子之和一样，你还是不能直接层序找第一个，这样没法保证他是左叶子
     *

     * @param root
     * @return
     */

    int res = 0;
    int maxH = 0;
    public int findBottomLeftValue(TreeNode root) {
        if (root.left == null && root.right == null) return root.val;
        //这回不仅要加上左孩子标记，还要加上深度标记
        find(root.left,1,1);
        find(root.right,0,1);
        //把深度放到x[1]中
        return res;
    }

    private void find(TreeNode root, int flag,int h) {
        if (root == null) return;
        //如果是左叶子
        if (root.left == null && root.right == null && flag == 1){
            //与当前最深左节点比较深度
            if (h > maxH){
                maxH = h;
                res = root.val;
            }
            if (h == maxH){
                res = Math.max(res,root.val);
            }
            //已经是叶子了还往下走什么
            return;
        }
        //去找左孩子
        find(root.left,1,h + 1);
        find(root.right,0,h + 1);
    }
}
