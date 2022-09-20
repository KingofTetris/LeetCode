package 剑指offer第二版.树;

import LeetCode数据结构入门.day5.树.TreeNode;

/**
 * @Author KingofTetris
 * @Date 2022/9/9 11:26
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 *  
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 *  
 *
 * 限制：
 *
 * 0 <= 树的结点个数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer55_II平衡二叉树 {


    /**
     * 自顶向下判断，复杂度 O(nlogn) 最差情况下满二叉树。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root ==null) return true;
        return  Math.abs(depth(root.left) - depth(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);//要去遍历整棵树所有节点是否都是平衡二叉树
    }


    /**
     * 这个方法就是求一棵树的树高
     * 但是现在我们得把每个节点都看成是一棵树
     * 这样左子树 - 右子树的绝对值 <= 1 那么父节点就是平衡的。
     * 然后按这个思路递归整棵树
     * @param root
     * @return
     */
    public int depth(TreeNode root){
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.max(left,right) + 1;
    }
}
