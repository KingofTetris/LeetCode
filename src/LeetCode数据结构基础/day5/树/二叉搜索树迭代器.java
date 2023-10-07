package LeetCode数据结构基础.day5.树;

import LeetCode数据结构基础.day5.树.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File 二叉搜索树迭代器
 * @Time 2021/10/27  10:23
 */

//满足迭代器输出顺序和中序遍历顺序一直就可以了。
public class 二叉搜索树迭代器 {

    public Deque<TreeNode> stack = new LinkedList<>();
    public 二叉搜索树迭代器(TreeNode root) {
        addTreeNode(root);
    }

    public void addTreeNode(TreeNode root) {
        while (root != null){
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode node = stack.poll();
        addTreeNode(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
