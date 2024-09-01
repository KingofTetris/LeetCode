package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author by KingOfTetris
 * @date 2024/9/1
 */
public class 把二叉搜索树转换为累加树 {

    @Test
    public void test(){
        Integer[] nums = new Integer[]{4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
        TreeNode tree = TreeUtils.createTree(nums);
        convertBST(tree);
        TreeUtils.show(tree);
    }

    /**
     * 递归
     * 其实是二叉树里面的双指针
     */
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        traversal(root);
        return root;
    }

    private void traversal(TreeNode current) {
        if (current == null) return;
        //右
        traversal(current.right);
        //中
        current.val += pre;
        pre = current.val;//记录上一个节点的累加和。
        //左
        traversal(current.left);
    }

    /**
     * 迭代法
     */
    ArrayList<TreeNode> nodes = new ArrayList<>();
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) return null;
        traversalBST(root);
        int n = nodes.size();
        int[] sums = new int[n];
        for (int i = 0; i < n; i++) {
            //初始化sum[i] 为node.val
            sums[i] = nodes.get(i).val;
        }
        //开始计算后缀和W
        for (int i = n - 2; i >= 0; i--) {
            sums[i] += sums[i + 1];
        }
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).val = sums[i];
        }
        return root;
    }

    public void traversalBST(TreeNode root){
        if (root == null) return;
        traversalBST(root.left);
        nodes.add(root);
        traversalBST(root.right);
    }
}
