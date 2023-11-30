package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 二叉树的最近公共祖先
 * @Time 2021/10/27  10:29
 */

/** 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    百度百科中最近公共祖先的定义为：
 “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
        示例 1：
        输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        输出：3
        解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
        示例 2：
        输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        输出：5
        解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
        示例 3：
        输入：root = [1,2], p = 1, q = 2
        输出：1


    提示：这个提示还挺重要的
        树中节点数目在范围 [2, 105] 内。
        -109 <= Node.val <= 109
        所有 Node.val 互不相同 。
        p != q
        p 和 q 均存在于给定的二叉树中。*/

public class 二叉树的最近公共祖先 {

    @Test
    public void test(){
        Integer[] nums = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeUtils.constructTree(nums);
        TreeUtils.show(root);
        TreeNode p = root.left.right.left;//7
//        TreeNode q = root.right.left;//0
        TreeNode q = root.left;//5
        TreeNode ancestor = lowestCommonAncestor(root,p,q);
        System.out.println(ancestor.val);
    }



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //这里是思考难点。定义规则：比如在某一棵子树上先找到了p，
        // 则无需继续遍历这棵子树，因为即使这棵子树有q，p也一定是q的祖先，
        // 也就是它们两个的最近公共祖先。
        if(root == null || root == q || root == p)
            return root;

        //在左子树找p,q 实际上找到的是较上层的那个结点，下层的结点不会递归到
        //找到上层结点后，return它 下面这个递归就结束了，然后去右子树里找。
        TreeNode left = lowestCommonAncestor(root.left,p,q);

        //在右子树找p,q
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        //如果在左右子树里找到了p,q 那root就是LCA
        if (left != null && right != null)
            return root;

        //p,q在左子树中
        else if(left != null){
            return left;
        }

        //p,q在右子树中
        else if(right != null){
            return right;
        }

        //左右子树都找不到p,q 即left right都为空
        else {
            return null;
        }
    }
}
