package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构入门.day5.树.CreateTree;
import LeetCode数据结构入门.day5.树.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author KingofTetris
 * @Date 2022/8/1 15:00
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
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
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer32_II_从上到下打印二叉树 {

    @Test
    public void test(){
        Integer[] nodes = new Integer[]{3,9,20,null,null,15,7};
        TreeNode tree = CreateTree.createTree(nodes);
        List<List<Integer>> lists = levelOrder(tree);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }


    /**
     * 因为每次queue就自带计数的功能，入队就++，出队就--；
     * 只需要记录下当前queue的数量就是这一层的数量
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new LinkedList<>();//为了过测试用例，要返回空的对应对象，不能直接返回null
        List<List<Integer>> levels =  new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int currentSize = queue.size();//记录每一层的数量
            List<Integer> level = new LinkedList<>();//存放每层的非空节点值
            for (int i = 1; i <= currentSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                /**
                 * 没入队一个不为null的节点,count++
                 * 对应的下一轮就要count个节点出队
                 */
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            levels.add(level);
        }
        return levels;
    }
}
