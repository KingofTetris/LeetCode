package LeetCode数据结构基础.day5.树;

import LeetCode数据结构入门.day5.树.TreeNode;
import LeetCode数据结构入门.day5.树.TreeUtils;
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
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        //为什么每次new一个新节点，为什么最后能连接到一起？？
        TreeNode merge = new TreeNode(root1.val + root2.val);
        //哦！！TMD，连接逻辑在这里!!!
        //merge.left = xx(left,left)
        //merge.right = xx(right,right)。。。= =|||
        merge.left = mergeTrees(root1.left,root2.left);
        merge.right = mergeTrees(root1.right,root2.right);
        return merge;
    }
}
