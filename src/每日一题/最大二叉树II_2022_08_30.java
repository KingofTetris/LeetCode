package 每日一题;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;

/**
 * @Author KingofTetris
 * @Date 2022/8/30 9:31
 * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。
 *
 * 给你最大树的根节点 root 和一个整数 val 。
 *
 * 就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
 *
 * 如果 a 为空，返回 null 。
 * 否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
 * root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
 * root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
 * 返回 root 。
 * 请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
 *
 * 假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
 *
 * 返回 Construct(b) 。
 * 示例 1：
 *
 * 输入：root = [4,1,3,null,null,2], val = 5
 * 输出：[5,4,null,1,3,null,null,2]
 * 解释：a = [1,4,2,3], b = [1,4,2,3,5]
 * 示例 2：
 *
 *
 * 输入：root = [5,2,4,null,1], val = 3
 * 输出：[5,2,4,null,1,null,3]
 * 解释：a = [2,1,5,4], b = [2,1,5,4,3]
 * 示例 3：
 *
 *
 * 输入：root = [5,2,3,null,1], val = 4
 * 输出：[5,2,4,null,1,3]
 * 解释：a = [2,1,5,3], b = [2,1,5,3,4]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 100] 内
 * 1 <= Node.val <= 100
 * 树中的所有值 互不相同
 * 1 <= val <= 100
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-binary-tree-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最大二叉树II_2022_08_30 {

    /**
     * 给出一颗最大树，现在在这棵最大树的副本里面插入val。返回插入后的最大树副本。
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode cur = root;
        while (cur != null) {
            if (val > cur.val) {//val比cur大
                if (parent == null) { //且父节点为空，就是val比root.val更大的情况
                    return new TreeNode(val, root, null);
                }
                //父节点不为空，那么就让构建val节点，它的左孩子设为cur
                TreeNode node = new TreeNode(val, cur, null);
                parent.right = node;//cur的父节点的右孩子设为node。插入完成。
                return root;
            } else { //如果val比cur小，就一层一层往下找
                parent = cur; //记录父节点
                cur = cur.right; //当前节点下移到右孩子。因为插入的元素是数组的末尾，按照构建的方法，则一定在右子树。
                //所以只需要遍历右子树
            }
        }
        //如果遍历完右子树都比cur小，那么就插入到叶子节点。
        parent.right = new TreeNode(val); //就叶子而言，左右其实无所谓。
        return root;
    }
}
