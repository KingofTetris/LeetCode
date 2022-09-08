package LeetCode数据结构基础.day5.树;

import LeetCode数据结构入门.day5.树.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author KingofTetris
 * @File 二叉树的右视图
 * @Time 2021/10/26  13:18
 */
/*给定一个二叉树的 根节点 root，
想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
        示例 1:
        输入: [1,2,3,null,5,null,4]
        输出: [1,3,4]
        示例 2:
        输入: [1,null,3]
        输出: [1,3]
        示例 3:
        输入: []
        输出: []
        提示:
        二叉树的节点个数的范围是 [0,100]
        -100 <= Node.val <= 100 */

public class 二叉树的右视图 {

    //思路其实很简单 就是把每层的最后一个节点加入到res里面就行了 为空是不会加进去的。
    //反过来 左视图就是第一个节点
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()){
            int currentSize = deque.size();
            //用i限制只加入该层的最后一个元素 实际上就是currentSize-1
            // 循环仍然是为了加入各层节点
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = deque.pollFirst();
                if (i == currentSize - 1) res.add(temp.val);
                if (temp.left != null) deque.add(temp.left);
                if (temp.right != null) deque.add(temp.right);
            }
        }
        return res;
    }
}
