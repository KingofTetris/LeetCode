package 剑指offer第二版.树;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;

/**
 * @Author KingofTetris
 * @Date 2022/7/26 16:33
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer26_树的子结构 {

    /**
     * isSubStructure(A, B) 函数：在A里面先找到B
     *
     * 特例处理： 当 树 A 为空 或 树 B 为空 时，直接返回 false ；
     * 返回值： 若树 B 是树 A 的子结构，则必满足以下三种情况之一，因此用或 || 连接；
     * 以 节点 A 为根节点的子树 包含树 B ，对应 recur(A, B)；
     * 树 B 是 树 A 左子树 的子结构，对应 isSubStructure(A.left, B)；
     * 树 B 是 树 A 右子树 的子结构，对应 isSubStructure(A.right, B)；
     *
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        /**
         * A和B都不能为空
         * 先在A里面找到B的根节点，如果没有，那也不用玩了
         * 找到B的根节点后，判断是否包含这颗子树
         * 判断A,B是否是相同子树
         * A.left里面有B吗？A.right里面有B吗？
         * 这里实际上是一个前序遍历
         * 三个条件成立一个就行
         *  recur成立那就直接true了
         *  isSubStructure先在左右子树里面找到B这个点。然后递归调用recur(A.B,B)
         */
        return (A != null && B != null) &&
                (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }


    /**
     * recur(A, B) 函数：//判断A,B两棵树是否匹配
     *
     * 终止条件：
     * 当节点 B 为空：说明树 B 已匹配完成（越过叶子节点），因此返回 true ；
     * 当节点 A 为空：说明已经越过树 AA 叶子节点，即匹配失败，返回false ；
     * 当节点 A 和 B 的值不同：说明匹配失败，返回 false ；
     * 返回值：
     * 判断 A 和 B 的左子节点是否相等，即 recur(A.left, B.left) ；
     * 判断 A 和 B 的右子节点是否相等，即 recur(A.right, B.right) ；
     *
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param B
     * @return
     */
    boolean recur(TreeNode A, TreeNode B) { //下面是一个先序遍历

        /**
         * 这两个判断不能对调。如果先判断A 会漏掉A为空，B也为空返回true的情况
         */
        if(B == null) return true;//B遍历过了叶子节点 说明是true
        if(A == null || A.val != B.val) return false; //A为空或者A,B的值不等返回false
        return recur(A.left, B.left) && recur(A.right, B.right); //递归判断左子树和右子树
    }
}

