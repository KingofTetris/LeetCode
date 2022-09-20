package LeetCode数据结构入门.day5.树;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 二叉树的深度
 * @Time 2021/10/6  9:54
 */
public class 二叉树的深度 {

    @Test
    public void test(){
        TreeNode root  = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        node2.left = node3;
        node2.right = node4;

        System.out.println(maxDepth(root));
    }


    //经典递归应用，实际上会先一直跑到左分支的末尾的空节点，返回1给父节点。
    //然后去找父节点的右节点，继续重复这个过程。当所有子节点都遍历完毕后，
    //会重新回到父节点再+1.以此类推到根节点。
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        else{
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            //这个+1是必不可少的 不然一直返回0;
            return Math.max(leftDepth,rightDepth) + 1;
        }
    }
}
