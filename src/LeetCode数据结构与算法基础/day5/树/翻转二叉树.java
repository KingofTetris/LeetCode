package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File 翻转二叉树
 * @Time 2021/10/7  15:16
 */


public class 翻转二叉树 {


    @Test
    public void test(){
        TreeNode tree = TreeUtils.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        TreeUtils.show(tree);
        TreeNode invertTree = invertTree(tree);
        TreeUtils.show(invertTree);
    }
    //和对称二叉树其实是一个套路。但是这个得重建，对称二叉直接判断是否相等和可以了。
    //递归重建容易一点
    public TreeNode invertTree(TreeNode root) {
        //递归里面的return只是结束了本次方法，会自动回溯到上次调用的下一步
        if (root == null)
            return root;
        //直接左右交换，注意树这个结构交换的是**整颗子树** 而不是一个值 一个节点。
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //左右孩子是互换了，但是孩子还是指向原来的左右节点，不要搞错。
        //相当于是互换了左右子树！！
        //    4         互换一次会变成    4
        // 2    7                    7     2
        //1 3  6 9                 6  9   1  3
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //当然迭代实现 用层序遍历，DFS，BFS反转
    public TreeNode invertTree2(TreeNode root){
        if (root == null)
            return root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode head = queue.poll();
            TreeNode temp = head.left;
            head.left = head.right;
            head.right = temp;
            //谁不为空 谁入队
            //为空的话，会出现null = null这种情况出现 报出空指针异常
            if(head.left!= null)
                queue.add(head.left);
            if(head.right!= null)
                queue.add(head.right);
        }
        return root;
    }
}
