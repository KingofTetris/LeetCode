package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构与算法基础.day5.树.TreeNode;
import LeetCode数据结构与算法基础.day5.树.TreeUtils;
import org.junit.Test;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2022/8/1 16:09
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
 *    15   7W
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer32_III_从上到下打印二叉树 {

    @Test
    public void test(){
        Integer[] nodes = new Integer[]{3,9,20,null,null,15,7};
        TreeNode tree = TreeUtils.createTree(nodes);
        List<List<Integer>> lists = levelOrder2(tree);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
    /**
     * 锯齿状打印二叉树
     * 栈和队列配合使用，每次要从队列取数的时候，把队列先颠倒一下
     * 击败96.62  26.38。用到的空间太多了。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new LinkedList<>();
        List<List<Integer>> levels = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        queue.offer(root);
        int h = 1;
        while (!queue.isEmpty() || !stack.isEmpty()){ //栈或队列不为空
            int currentQueueSize = queue.size();//记录每一层的数量
            int currentStackSize = stack.size();//记录每一层的数量
            List<Integer> level = new LinkedList<>();//存放每层的非空节点值
            if ((h &1) == 1){ //奇数层从队列取数，放入栈
                reverseQueue(queue); //因为栈取出的元素刚好是相反的，所以每次要先把队列颠倒一下
                for (int i = 1; i <= currentQueueSize; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if (node.left != null){
                        stack.push(node.left);
                    }
                    if (node.right != null){
                        stack.push(node.right);
                    }
                }
                h++;//每层h++
                levels.add(level);
            }
            else if ((h & 1) != 1){//偶数层从栈取数，放入队列
                for (int i = 1; i <= currentStackSize; i++) {
                    TreeNode node = stack.pop();
                    level.add(node.val);
                    /**
                     * 这里要反着放入队列
                     */
                    if (node.right != null){
                        queue.offer(node.right);
                    }
                    if (node.left != null){
                        queue.offer(node.left);
                    }
                }
                h++;//每层h++
                levels.add(level);
            }
        }
        return levels;
    }

    /**
     * 直接双端队列 主义这个双端队列指的是存放每层数据的结构是双端队列。层序遍历还是普通的队列就行了
     * 奇数层 则添加至 tmp 尾部 ，
     * 偶数层 则添加至 tmp 头部 。
     * 时间是都是O(n) 但节省了很多空间
     */
    public List<List<Integer>> levelOrder2(TreeNode root){
        if (root == null) return new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            LinkedList<Integer> tmp = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                // res大小就是层数,但是res的大小是从0开始，加个1从1开始比较清晰
                if( ((res.size() + 1) & 1 ) == 1) tmp.addLast(node.val); // 奇数层反过来放，addLast
                else tmp.addFirst(node.val); // 偶数层，正常放addFirst
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
    /**
     * 颠倒队列的顺序
     * @param queue
     */
    private void reverseQueue(Queue<TreeNode> queue) {
        Stack<TreeNode> tempStack = new Stack<>();
        while (!queue.isEmpty()){
            tempStack.push(queue.poll());
        }
        while (!tempStack.isEmpty()){
            queue.offer(tempStack.pop());
        }
    }
}
