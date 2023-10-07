package 剑指offer第二版.树;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/9/9 10:54
 */
public class 剑指Offer55_I二叉树的深度 {

    /**
     * 简单递归
     * 如果为空就+1，不为空就递归去判断左右子树哪个大。取最深的就行了
     *
     * 一般记住这个一行递归就行了，又快又省空间。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(maxDepth(root.left), maxDepth(root.right)));//每个不为空的节点都+1

        /**
         * 稍微麻烦一点就写成这样。
         if (root == null) {
         return 0;
         } else {
         int leftHeight = maxDepth(root.left);
         int rightHeight = maxDepth(root.right);
         return Math.max(leftHeight, rightHeight) + 1;
         }
         */
    }

    /**
     * 非递归版本就需要层次遍历了
     */
    public int maxDepth2(TreeNode root){
        if(root == null) return 0;
        List<TreeNode> queue = new LinkedList<>(), tmp;
        queue.add(root);
        int res = 0;
        while(!queue.isEmpty()) { //当queue为空时，res就到底了。
            tmp = new LinkedList<>();//用来存储下一层节点的临时队列
            for(TreeNode node : queue) {
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            queue = tmp;//每次直接指向下一层。
            res++;
        }
        return res;
    }

}
