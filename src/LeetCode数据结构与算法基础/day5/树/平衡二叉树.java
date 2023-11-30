package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class 平衡二叉树 {

    @Test
    public void test() {

    }

    //这里的平衡二叉树不是AVL，只是专门指任意节点的左右子树高的差的绝对值<=1
    //那么其实只要递归判定每个节点是不是都平衡就可以了。
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        //先判断自己是不是平衡
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1)
            return false;
        //然后递归判断左右子树。 有一个不为true 就不平衡。
        return isBalanced(root.left) && isBalanced(root.right);
    }

    int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }
}
