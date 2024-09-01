package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/8/17
 */
public class 将有序数组转换为二叉搜索树 {

    @Test
    public void test(){
        int[] nums = {-10,-3,0,5,9};
        TreeNode treeNode = sortedArrayToBST(nums);
        TreeUtils.show(treeNode);
    }
    public TreeNode sortedArrayToBST(int[] nums) {

        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        //如果left > right 说明已经找完了
        if (left > right)
            return null;
        //每次首选节点都是，这一段的中间节点。也就是二分查找
        int mid = (right - left) / 2 + left;
        TreeNode root = new TreeNode(nums[mid]);
        //递归去找left,mid - 1
        //mid + 1 right
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 判断是不是平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        //先判断自己是不是平衡
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1)
            return false;
        //然后递归判断左右子树。 有一个不为true 就不平衡。
        return isBalanced(root.left) && isBalanced(root.right);
    }

    int maxDepth(TreeNode root) {
        //下面这句话求树深非常重要，无论如何都要记住。
        return root == null ? 0 : Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }
}
