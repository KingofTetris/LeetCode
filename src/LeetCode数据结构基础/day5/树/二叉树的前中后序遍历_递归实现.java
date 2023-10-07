package LeetCode数据结构基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author KingofTetris
 * @File 二叉树的前中后序遍历
 * @Time 2021/10/5  13:01
 */

 /*     频繁的插入、删除操作：LinkedList
        频繁的随机访问操作：ArrayDeque
        未知的初始数据量：LinkedList*/

public class 二叉树的前中后序遍历_递归实现 {
    List<Integer> list = new LinkedList<>();
    @Test
    public void test(){
        Random rand = new Random();
        Integer[] nodes = new Integer[5];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = rand.nextInt(100); //[0,99)
        }
        TreeNode root = TreeUtils.createTree(nodes);
        TreeUtils.show(root);
        System.out.print("前序遍历：");
        List<Integer> preorder = preorderTraversal(root);
        for(Integer i:preorder){
            if(i != null)
            System.out.print(i+"\t");
        }
        System.out.println();

        //清空list
        list.clear();
        System.out.print("中序遍历：");
        List<Integer> inorder = inorderTraversal(root);
        for(Integer i:inorder){
            if(i != null)
            System.out.print(i+"\t");
        }
        System.out.println();

        list.clear();
        System.out.print("后序遍历：");
        List<Integer> postorder = postorderTraversal(root);
        for(Integer i:postorder){
            if(i != null)
            System.out.print(i+"\t");
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return null;
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return list;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return null;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return null;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);
        return list;
    }
}
