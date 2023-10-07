package LeetCode数据结构基础.day5.树;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 二叉搜索树BST中的搜索
 * @Time 2021/10/8  10:36
 */

/*700. 二叉搜索树中的搜索
        给定二叉搜索树（BST）的根节点和一个值。
        你需要在BST中找到节点值等于给定值的节点。
        返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
        BST 根结点>左孩子 根节点<右孩子 必不会有重复的元素*/
public class 二叉搜索树BST中的搜索 {

    @Test
    public void test() {
//        CreateTree ct = new CreateTree();
        Integer[] num = new Integer[]{4, 2, 7, 1, 3, 5, 8};
        TreeNode tree = TreeUtils.createTree(num);
        TreeNode children = searchBST(tree, 2);
        TreeUtils.show(children);
    }

    //递归
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        TreeNode root_left = searchBST(root.left, val);
        TreeNode root_right = searchBST(root.right, val);
        return root_left == null ? root_right : root_left;
    }

    //非递归
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) return root;
            else if (val > root.val) root = root.right;
            else if (val < root.val) root = root.left;
        }
        return null;
    }
}
