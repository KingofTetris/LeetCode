package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/21
 */
public class 合并二叉树 {

    @Test
    public void test(){
        TreeNode root1 = TreeUtils.createTree(new Integer[]{1, 3, 2, 5});
        TreeNode root2 = TreeUtils.createTree(new Integer[]{2, 1, 3, null, 4, null, 7});
        TreeNode res = mergeTrees(root1, root2);
        TreeUtils.show(res);
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //如果其中一个树为空，就直接返回另一个就行了。
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        //两个都不为空，返回两者之和。
        TreeNode merge = new TreeNode(root1.val + root2.val);
        //递归构建左右子树即可
        merge.left = mergeTrees(root1.left,root2.left);
        merge.right = mergeTrees(root1.right,root2.right);
        return merge;
    }
}
