package LeetCode数据结构基础.day5.树;

import LeetCode数据结构基础.day5.树.TreeUtils;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File 对称二叉树
 * @Time 2021/10/6  10:19
 */

//给定一个二叉树，检查它是否是镜像对称的。
public class 对称二叉树 {

    @Test
    public void test() {
        TreeNode tree = TreeUtils.createTree(new Integer[]{1, 2, 2, null, 3, null, 3});
        boolean symmetric = isSymmetric(tree);
        System.out.println(symmetric);
    }


    //难以理解递归。只好写迭代
    //迭代比较容易理解
    //比较左右孩子
    //对称就是以根为轴：
    //左孩子的左孩子<-->右孩子的右孩子
    //左孩子的右孩子<-->右孩子的左孩子
    public boolean isSymmetric2(TreeNode root) {
        //空节点或只有一个节点
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        //用队列保存节点 实际上队列的实现就是LinkedList
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            //从队列中取出两个节点，再比较这两个节点
            //LinkedList的removeFirst方法可以将队头元素出队 返回值是List中的类型
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环
            // 两者有一个为空或者两者值不相当就返回false
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helperSymmetric(root.left, root.right);
    }

    private boolean helperSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 != null && r2 != null) {
            if (r1.val == r2.val) {
                boolean b1 = helperSymmetric(r1.left, r2.right);
                boolean b2 = helperSymmetric(r1.right, r2.left);
                if (!b1 || !b2) return false;
            } else {
                return false;
            }
        } else if (r1 == null && r2 != null) {
            return false;
        } else if (r1 != null && r2 == null) {
            return false;
        }
        return true;
    }
}
