package 剑指offer第二版.树;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/9/9 10:16
 * 给定一棵二叉搜索树(BST)!，请找出其中第 k 大的节点的值。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer54_二叉搜索树的第k大节点 {

    LinkedList<Integer> nums = new LinkedList<>();
    /**
     * 遍历加排序适用于所有二叉树
     * @param root
     * @param k
     * @return
     */
    public int kthLargest1(TreeNode root, int k) {
        dfs(root);
//        Collections.sort(nums, (o1, o2) -> -(o1 - o2) );//从大到小排序 lamda写法 省去Comparator那一大段
        return nums.get(nums.size() - k); //返回第k个大的数字
    }

    /**
     * 省去排序，因为这是一棵BST
     * 本题的最优解法
     * @param root
     * @param k
     * @return
     */
    public int kthLargest2(TreeNode root, int k) {
//        dfs(root);
//        Collections.sort(nums, (o1, o2) -> -(o1 - o2) );//从大到小排序 lamda写法 省去Comparator那一大段
//        return nums.get(k - 1); //返回第k个大的数字
        dfs2(root,k);
        return res;
    }

    /**
     * 普通树用什么遍历都行，先把所有数字拿出来
     * 如果是BST，中序遍历是一个升序数组 就不必排序了
     * @param root
     */
    public void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.left);
        nums.add(root.val);
        dfs(root.right);
    }


    /**
     * 其实BST的中序遍历倒过来
     * 也就是用: 右 根 左的顺序遍历的话，就是从大到小排序
     * 就不用额外的空间存储数组。也不必遍历完整棵树。
     * @param root
     */

    int count = 0;//第k次就是第k大的数 当然要追求极致的话，这个count也可以去掉
    //直接用 --k 判断等不等于 0 就行了。
    int res;//结果
    public void dfs2(TreeNode root,int k){
        if (root == null) return;
        dfs2(root.right,k);
        count++;
        if (count == k){
            res = root.val;
            return;
        }
        dfs2(root.left,k);
    }
}
