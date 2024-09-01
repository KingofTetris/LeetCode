package LeetCode数据结构与算法基础.day5.树;

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

    //递归，这个递归没用到BST的性质啊。
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        //左右子树里面找到一个就行了。
        TreeNode root_left = searchBST(root.left, val);
        TreeNode root_right = searchBST(root.right, val);
        return root_left == null ? root_right : root_left;
    }

    //利用BST的性质，非递归
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            //找到就返回
            if (root.val == val) return root;
            //如果当前root.val小于val，说明要去右子树找
            else if (val > root.val) root = root.right;
            //反过来就去左子树找
            else if (val < root.val) root = root.left;
        }
        return null;
    }
}
