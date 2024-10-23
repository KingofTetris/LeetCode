package 校招面试真题;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

/**
 * @author by KingOfTetris
 * @date 2024/10/18
 */
public class LCA {

    public TreeNode findLCA(TreeNode root,TreeNode p,TreeNode q){
        if (root == null || p == null || q == null){
            return null;
        }

        //去左右子树找p,q
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        if (left != null && right != null){
            return root;
        }

        else if (left != null){
            return left;
        }

        else if (right != null){
            return right;
        }
        //否则p,q没有LCA
        else {
            return null;
        }
    }
}
