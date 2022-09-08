package 剑指offer第二版.树;

import LeetCode数据结构入门.day5.树.CreateTree;
import LeetCode数据结构入门.day5.树.TreeNode;
import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/28 16:15
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 */
public class 剑指Offer27_二叉树的镜像 {

    @Test
    public void test(){
        Integer[] nodes = new Integer[]{4,2,7,1,3,6,9};
        TreeNode root = CreateTree.createTree(nodes);
        TreeNode mirrorTree = mirrorTree(root);
        CreateTree.show(mirrorTree);
    }
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;//终极条件 root为空
        /**
         * 左右子树互换
         */
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
