package LeetCode数据结构与算法基础.day5.树;

/**
 * @author KingofTetris
 * @File BST中第K小的元素
 * @Time 2021/10/27  9:41
 */



import org.junit.Test;

import java.util.*;

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

public class 二叉搜索树中第K小的元素 {

    @Test
    public void test(){
        Integer[] nums = {5,3,6,2,4,null,null,1};
        TreeNode node = TreeUtils.constructTree(nums);
        TreeUtils.show(node);
        System.out.println(kthSmallest(node,3));
    }


    //BST的中序遍历实际上是个升序数组，找第K小就直接变成了找数组中的第K个元素
    List<Integer> list = new LinkedList<>();
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root);
        //最后返回下标k-1的元素就是第k小的元素
        return list.get(k - 1);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        /**
         * 递归三部曲
         * 1.递归参数和返回值
         * 2.递归终止条件
         * 3.递归单层逻辑
         */
        if(root == null) return null;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }


    /**
     * 再省一点就是用一个变量order来记录到哪了。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest3(TreeNode root, int k) {
        traverse(root,k);
        return val;
    }
    int order = 0;
    int val = -1;
    public void traverse(TreeNode root,int k){
        if(root == null) return;
        traverse(root.left,k);
        order++;
        if(order == k){ //找到order == k的时候就可以return了
            val = root.val;
            return;
        }
        traverse(root.right,k);
    }

    //用栈对上面中序遍历进行改写，即从递归改为迭代。
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
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
