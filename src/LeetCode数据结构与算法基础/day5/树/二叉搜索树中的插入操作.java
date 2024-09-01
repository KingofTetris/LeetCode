package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 二叉搜索树中的插入操作
 * @Time 2021/10/8  11:24
 */

/*701. 二叉搜索树中的插入操作
        给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
        返回插入后二叉搜索树的根节点。 输入数据 保证 ，
        新值和原始二叉搜索树中的任意节点值都不同。

        注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
        你可以返回 任意有效的结果 。*/
public class 二叉搜索树中的插入操作 {

    @Test
    public void test() {
//        CreateTree ct = new CreateTree();
        Integer[] num = {4, 2, 7, 1, 3};
        TreeNode tree = TreeUtils.createTree(num);
        TreeNode new_node1 = insertIntoBST(tree, 5);
        TreeUtils.show(new_node1);
        TreeNode new_node2 = insertIntoBST(tree, 6);
        TreeUtils.show(new_node2);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        //如果是空树 直接新建一个就行了。
        //或者说遍历到叶子节点了，创建一个新的节点，就可以了。
        if (root == null) {
            return new TreeNode(val);
        }
        //比root小就往左边放，比root大就往右边放。
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

}
