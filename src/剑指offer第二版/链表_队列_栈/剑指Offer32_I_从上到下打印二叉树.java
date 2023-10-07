package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;
import LeetCode数据结构基础.day5.树.TreeUtils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author KingofTetris
 * @Date 2022/7/30 17:00
 * 其实就是层序遍历
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 *  
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer32_I_从上到下打印二叉树 {

    @Test
    public void test(){
        Integer[] nums = new Integer[]{3,9,20,null,null,15,7};
        TreeNode tree = TreeUtils.createTree(nums);
        int[] ints = levelOrder(tree);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];//返回数组不能返回null，而是新建个长度为0的给他
        LinkedList<TreeNode> level = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>(); //需要一个队列来模拟

        queue.offer(root); //先把root入队
        while (!queue.isEmpty()){
            TreeNode peek = queue.poll();//每次取队头
            level.add(peek); //加入序列
            /**
             * 左右子树不为空就加入队列
             */
            if (peek.left != null)
            queue.add(peek.left);
            if (peek.right != null)
            queue.add(peek.right);
        }
        int[] nums = new int[level.size()];
        int i = 0;
        for (TreeNode treeNode : level) {
            nums[i] = treeNode.val;
            i++;
        }
        return nums;
    }
}
