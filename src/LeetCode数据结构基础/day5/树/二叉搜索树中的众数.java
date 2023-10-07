package LeetCode数据结构基础.day5.树;

import LeetCode数据结构基础.day5.树.TreeNode;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/26
 */
public class 二叉搜索树中的众数 {


    /**
     * 普通中序遍历完，加入数组以后查询众数，超时。
     * 因为你遍历了一次二叉树，又遍历了一次数组。
     * 本题的要求是只遍历一次BST完成对众数的搜索。
     * @param root
     * @return
     */
    //下面给出正确写法。完成只遍历一次BST就找出所有众数。
    //当然这种写法也只能用于BST，普通的二叉树是没有中序遍历是一个升序序列这种特性的。
    ArrayList<Integer> resList; //结果集
    int maxCount;//记录最大的数字出现次数
    int count;//当前出现次数
    TreeNode pre;//前一个节点。
    public int[] findMode(TreeNode root){
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        findMode1(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
    public void findMode1(TreeNode root) {
        if (root == null) {
            return;
        }
        findMode1(root.left);
        int rootValue = root.val;
        // 计数
        //pre的null说明是根节点
        //当前值不等于preVal说明不是同一个值
        //重新计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }
        // 更新结果以及maxCount
        //如果count已经大于maxCount了，
        //就要把结果清空重新添加值
        if (count > maxCount) {
            resList.clear();
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }
        //记得把pre = root.这一步很关键。
        pre = root;
        findMode1(root.right);
    }
}
