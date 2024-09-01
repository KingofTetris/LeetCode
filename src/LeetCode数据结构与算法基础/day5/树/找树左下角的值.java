package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2024/8/31
 */
public class 找树左下角的值 {


    @Test
    public void test(){
        Integer[] nums = new Integer[]{1,null,1};
        TreeNode tree = TreeUtils.createTree(nums);
        int value = findBottomLeftValue(tree);
        System.out.println(value);
    }

    /**
     * 层序找最左边也就是第一个数
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int h = 1;
        int res = 0;
        int maxH = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                if (i == 0 && h > maxH){
                    maxH = h;
                    res = temp.val;
                }
                if (temp.left != null){
                    q.add(temp.left);
                }
                if (temp.right != null){
                    q.add(temp.right);
                }
            }
            h++;//走完一层h++
        }
        return res;
    }
}
