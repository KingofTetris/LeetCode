package LeetCode数据结构基础.day5.树;

/**
 * @author KingofTetris
 * @File BST中第K小的元素
 * @Time 2021/10/27  9:41
 */



import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/** 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
        示例 1：
        输入：root = [3,1,4,null,2], k = 1
        输出：1
        示例 2：
        输入：root = [5,3,6,2,4,null,null,1], k = 3
        输出：3
        提示：
        树中的节点数为 n 。
        1 <= k <= n <= 104
        0 <= Node.val <= 104
        进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？*/

public class BST中第K小的元素 {
    List<Integer> list = new LinkedList<>();
    @Test
    public void test(){
        Integer[] nums = {5,3,6,2,4,null,null,1};
        TreeNode node = TreeUtils.constructTree(nums);
        TreeUtils.show(node);
        System.out.println(kthSmallest(node,3));
    }

    //BST的中序遍历实际上是个升序数组，找第K小就直接变成了找数组中的第K个元素
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root);
        return list.get(k - 1);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return null;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }
    //用栈对上面中序遍历进行改写，即从递归改为迭代。
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            //一直去找左孩子，BST的性质就是左边小。
            //用栈存储则越小就在越上面
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            //把root的右子树入栈
            root = root.right;
        }
        return root.val;
    }
}
