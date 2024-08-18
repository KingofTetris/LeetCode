package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/8/17
 */
public class 二叉树展开为链表 {


    @Test
    public void test() {
        TreeNode root = TreeUtils.createTree(new Integer[]{1, 2, 5, 3, 4, null, 6});

    }

    /**
     * 把原本的二叉树改为一条前序遍历顺序的链表，不是让你前序遍历。
     *
     * 没思路的时候就纸上画一画，看一看
     *
     * 整体思路就是不断把root的左子树 去充当root的右子树，就能保证left == null
     * right是一个单链表。
     */
    public void flatten(TreeNode root) {
        while (root != null){
            //如果root有左子树
            if (root.left != null){
                TreeNode tempLeft = root.left;
                //然后去找这个左子树最右边的节点
                while (tempLeft.right != null){
                    tempLeft = tempLeft.right;
                }
                //找到以后把最右边节点插入到root的右子树
                tempLeft.right = root.right;
                root.right = root.left;
                //然后root.left记得置为null
                root.left = null;
            }
            //root变到右子树下一层
            root = root.right;
        }
    }
}
