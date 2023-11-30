package 剑指offer第二版.树;

import LeetCode数据结构与算法基础.day5.树.TreeNode;
import LeetCode数据结构与算法基础.day5.树.TreeUtils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author KingofTetris
 * @Date 2022/7/29 14:39
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  限制：
 *
 * 0 <= 节点个数 <= 1000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer28_对称的二叉树 {

    @Test
    public void test(){
        Integer[] nodes = new Integer[]{1,2,2,null,3,3};
        TreeNode tree = TreeUtils.createTree(nodes);
        System.out.println(isSymmetric2(tree));
    }


    /**
     * 递归
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        //判断root为空？为空直接true，不然就循环左右阶段去判断
        return root == null ? true : recur(root.left, root.right);
    }

    /**
     * 对称二叉树定义： 对于树中 任意两个对称节点 L 和 R ，一定有：
     * L.val=R.val ：即此两对称节点值相等。
     * L.left.val = R.right.val ：即 L 的 左子节点 和 R 的 右子节点 对称；
     * L.right.val = R.left.val ：即 L 的 右子节点 和 R 的 左子节点 对称。
     *根据以上规律，考虑从顶至底递归，判断每对节点是否对称，从而判断树是否为对称二叉树。
     * @param L
     * @param R
     * @return
     * recur(L, R) ：
     *
     * 终止条件：
     * 当 L 和 R 同时越过叶节点： 此树从顶至底的节点都对称，因此返回 true ；
     * 当 L 或 R 中只有一个越过叶节点： 此树不对称，因此返回 false ；
     * 当节点 L值不等于节点R值： 此树不对称，因此返回 false ；
     * 递推工作：
     * 判断两节点 L.left 和 R.right 是否对称，即 recur(L.left, R.right) ；
     * 判断两节点 L.right 和 R.left 是否对称，即 recur(L.right, R.left) ；
     *
     * 返回值： 两对节点都对称时，才是对称树，因此用与逻辑符 && 连接。
     */
    boolean recur(TreeNode L, TreeNode R) {
        if(L == null && R == null) return true;  //当 L 和 R 同时越过叶节点： 此树从顶至底的节点都对称，因此返回 true ；
        if(L != null && R == null) return false;
        if(L == null && R != null) return false;
        if(L != null && R != null && L.val != R.val) return false;
       /* if(L == null || R == null || L.val != R.val) return false; */
        //这句话就等于上面三句话
        //某一个节点为空，另一个不为空返回false，都不为空但值不同false。有兴趣可以去慢慢推敲。
        // 但根本没必要这样写。可读性太差，写三个if就完事了
        /**
         * 如果上面的条件都没成立，那么说明梁节点不为空而且值相等，那么就继续下一层
         * 左右和右左比较
         */
        return recur(L.left, R.right) && recur(L.right, R.left);
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
       return isSymmeByLevel(root,root);
    }


    /**
     * 因为想不出递归怎么写，于是写出了层序遍历的迭代
     * @param L
     * @param R
     * @return
     */
    public boolean isSymmeByLevel(TreeNode L, TreeNode R) { //两颗相同的树，都从根节点出发，一棵往左走，一棵往右走。
        Queue<TreeNode> level = new LinkedList<>();
        level.offer(L);
        level.offer(R);
        while (!level.isEmpty()) { /** 直到遍历完所有的镜像位置 */
            /**
             * 每次拿出队头的两个镜像位置节点比较
             */
            L = level.poll();
            R = level.poll();

            /**
             * 都为空节点则直接下一轮，因为下面的入队操作也做不了了，直接跳过
             */
            if (L == null && R == null) {
                continue;
            }
            if(L != null && R == null) return false;
            if(L == null && R != null) return false;
            if(L != null && R != null && L.val != R.val) return false;

            /**
             * 两棵树
             * 入队L的left
             * 入队R的right
             */
            level.offer(L.left);
            level.offer(R.right);
            /**
             * 反过来
             * L的right
             * R的left
             */
            level.offer(L.right);
            level.offer(R.left);
        }
        return true;
    }
}
