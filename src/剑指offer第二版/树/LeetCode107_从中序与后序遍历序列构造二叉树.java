package 剑指offer第二版.树;

import LeetCode数据结构入门.day5.树.TreeNode;
import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/11 17:05
 */
public class LeetCode107_从中序与后序遍历序列构造二叉树 {
    @Test
    public void test(){

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        剑指Offer07_重建二叉树 binaryTree = new 剑指Offer07_重建二叉树();
        return binaryTree.buildTreeUsePostAndIn(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

}
