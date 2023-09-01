package LeetCode数据结构入门.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * @author KingofTetris
 * @File 二叉树的前中后序遍历_迭代实现
 * @Time 2021/10/5  13:41
 */
public class 二叉树的前中后序遍历_迭代实现 {

    List<Integer> list = new LinkedList<>();

    @Test
    public void test() {
        Random rand = new Random();
        Integer[] nodes = new Integer[5];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = rand.nextInt(100); //[0,99)
        }
        TreeNode root = TreeUtils.createTree(nodes);
        TreeUtils.show(root);
        List<Integer> preorder = preorderTraversal(root);
        for (Integer i : preorder) {
            if (i != null)
                System.out.print(i + "\t");
        }

        System.out.println();
        //清空list 中序遍历
        list.clear();
        List<Integer> inorder = inorderTraversal(root);
        for (Integer i : inorder) {
            if (i != null)
                System.out.print(i + "\t");
        }
        System.out.println();
        //后序遍历
        list.clear();
        List<Integer> postorder = postorderTraversal(root);
        for (Integer i : postorder) {
            if (i != null)
                System.out.print(i + "\t");
        }
    }


    public List preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                list.add(root.val);
                if (root.right != null)
                    stack.push(root.right);
                root = root.left;
            } else {
                root = stack.pop();
            }
        }
        return list;
    }

    public List inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }


    public List postorderTraversal(TreeNode root) {
        TreeNode node = new TreeNode();
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                //头插法 可以 add(int index, E element);实现
                list.add(0, root.val);
                if (root.left != null)
                    stack.push(root.left);
                //优先访问右子树
                root = root.right;
            } else {
                root = stack.pop();
            }
        }
        return list;
    }
}
