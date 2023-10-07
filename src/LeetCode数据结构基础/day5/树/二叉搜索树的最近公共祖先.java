package LeetCode数据结构基础.day5.树;

/**
 * @author KingofTetris
 * @File 二叉搜索树的公共祖先
 * @Time 2021/10/9  13:01
 */

/*235. 二叉搜索树的最近公共祖先
        给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
        百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
        最近公共祖先表示为一个结点 x，
        满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
        eg:节点 2 和节点 4 的最近公共祖先是 2
        例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]


            所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。

        */
public class 二叉搜索树的最近公共祖先 {

    //因为这是棵BST可以直接利用BST的性质来定位p,q的最近公共祖先
    // 直接递归

    /**
     * 如果只是普通的二叉树就没有这种性质了，那么对于普通二叉树P,Q的LCA看下一题
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //如果在同一边 那么继续下一层
        if(root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        if(root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);

        //如果和ROOT比 一大一小 root就是P,Q的最近公共祖先LCA
        return root;
    }
}
