package LeetCode数据结构基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class 二叉树的所有路径 {

    @Test
    public void test(){
        TreeNode tree = TreeUtils.createTree(new Integer[]{1});
        TreeUtils.show(tree);
        List<String> stringList = binaryTreePaths(tree);
        for (String s : stringList) {
            System.out.println(s);
        }
    }
    List<String> res = new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return res;
        String s = "" + root.val;
        if (root.left != null)
        helperGenerate(root.left,s);
        if (root.right != null)
        helperGenerate(root.right,s);
        //如果是单节点，直接添加s
        if (res.size() == 0){
            res.add(s);
        }
        return res;
    }

    private void helperGenerate(TreeNode root, String s) {
        s += "->" + root.val;
        if (root.left == null && root.right == null){
            res.add(s);
            return;
        }
        if (root.left != null){
            helperGenerate(root.left,s);
        }
        if (root.right != null){
            helperGenerate(root.right,s);
        }
    }


}
