package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class 二叉树的右视图 {

    @Test
    public void test(){

    }

    //层序遍历返回每层最后的节点即可。
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        //层序遍历其实就只需要一个队列就够了，其他的都是用于接受中间结果的变量
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size(); //每层获取当前队列的大小，这个变量非常关键，别忘了。
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (i == size - 1){ //如果是最后一个节点，则加入右视图。
                    res.add(cur.val);
                }
                //还是入这个队列，因为循环条件里面限制了每层只遍历size，然后开始下一层
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
        }
        return res;
    }
}
