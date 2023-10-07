package LeetCode数据结构基础.day5.树;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class 二叉树的节点个数 {


    @Test
    public void test(){
        TreeNode tree = TreeUtils.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(treenodeNum(tree));
    }


    public int treenodeNum(TreeNode root){
        return root == null ? 0 : treenodeNum(root.left) + treenodeNum(root.right) + 1;
    }
}
