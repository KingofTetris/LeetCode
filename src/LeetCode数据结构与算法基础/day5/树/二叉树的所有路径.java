package LeetCode数据结构与算法基础.day5.树;

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
        TreeNode tree = TreeUtils.createTree(new Integer[]{1,2,3,null,5});
        TreeUtils.show(tree);
        List<String> stringList = binaryTreePaths(tree);
        for (String s : stringList) {
            System.out.println(s);
        }
    }


    //经典回溯。
    List<String> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return res;
         backTracking(root);
         return res;
    }

    private void backTracking(TreeNode root) {
        //停止条件，遇到叶子就停下记录路径,如果直接null，不用管他。
        if (root == null) return;
        if (root.left == null && root.right == null){
            path.add(root.val);
            StringBuilder sb = new StringBuilder();
            int n = path.size();
            for (int i = 0; i < n; i++) {
                if (i != n - 1){
                    sb.append(path.get(i)).append("->");
                }
                else{
                    sb.append(path.get(i));
                }
            }
            res.add(sb.toString());
            path.remove(path.size() - 1);
            //记得return
            return;
        }

        //如果不是叶子，就把root.val拉进来
        path.add(root.val);
        backTracking(root.left);
        backTracking(root.right);
        path.remove(path.size() - 1);
    }


}
