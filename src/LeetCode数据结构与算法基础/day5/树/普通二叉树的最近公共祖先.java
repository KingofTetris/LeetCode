package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/8/31
 */
public class 普通二叉树的最近公共祖先 {
    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTree(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeUtils.show(tree);
        TreeNode p = TreeUtils.findTreeNode(tree, 7);
        TreeNode q = TreeUtils.findTreeNode(tree, 1);
        TreeNode lca = lowestCommonAncestor(tree, p, q);
        System.out.println("LCA:");
        TreeUtils.show(lca);
    }

    @Test
    public void test(){
        double x = 3.0;
        int y = 5;
        //double / int
        //精度高的和精度低的运算会把结果转化成结果精度高的。
        x /= --y;
        System.out.println(x);
    }

    /**
     * 最简单的朴素算法，
     * 1.向上标记法
     * u一直向上到root节点，把沿途走过的节点都标记一遍，如果标记的途中刚好发现了节点v，那么LCA(P,Q)=v
     * 如果没发现节点v,那么v再向上走，他也把走过的节点都标记一遍，如果标记途中刚好发现u,那么LCA(P,Q)=u
     * 如果没有上面那两种那么巧,那么v发现有节点已经被标记过的时候，这个节点就是LCA(p,q)
     * 或者是
     * 2.同步前进法
     * 让u,v中深度较深的先走到同一深度，然后一起向上走。如果他们碰头了，那么这个节点就是LCA(p,q)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */

    /**
     * 求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从底向上的遍历方式。
     *
     * 在回溯的过程中，必然要遍历整棵二叉树，即使已经找到结果了，依然要把其他节点遍历完，
     * 因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
     *
     * 要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //https://www.bilibili.com/video/BV1jd4y1B7E2/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click&vd_source=299caa32bd4dc5f5ad17129611289250
        //终止条件，如果遇到了空节点，返回null
        if(root == null) return null;
        //如果遇到了p,q，就把p,q返回到上一层
        if(root == p || root == q) return root;

        //然后后序遍历先找左右孩子，再返回到根节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //两边都不为空的话，那么root其实就是LCA
        if (left != null && right != null) return root;
        //或者哪边不为空就说明在哪个子树里面
        if (left == null && right != null) return right;
        else if (left != null && right == null) return left;
        //如果两边都为null 那就不在这棵子树里面。
        else if (left == null && right == null) return null;

        //这个条件其实只是作为返回结果为了编译通过，其实永远也不可能到达。
        return null;
    }
}
