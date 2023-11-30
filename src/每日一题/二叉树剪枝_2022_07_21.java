package 每日一题;


import LeetCode数据结构与算法基础.day5.树.TreeNode;
import LeetCode数据结构与算法基础.day5.树.TreeUtils;
import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/21 13:21
 * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,0,0,1]
 * 输出：[1,null,0,null,1]
 * 解释：
 * 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 * 示例 2：
 *
 *
 * 输入：root = [1,0,1,0,0,0,1]
 * 输出：[1,null,1,null,1]
 * 示例 3：
 *
 *
 * 输入：root = [1,1,0,1,1,0,1,0]
 * 输出：[1,1,0,1,1,null,1]
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 200] 内
 * Node.val 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-pruning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树剪枝_2022_07_21 {

    @Test
    public void test(){
        Integer[] integers = {1,0,1,0,0,0,1};
        TreeNode tree = TreeUtils.createTree(integers);
        TreeUtils.show(pruneTree(tree));
    }
    /**
     * 删除不包含1的所有子树
     * 思路就是从最底层开始每层删去为0的叶子节点。
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null){ //空节点返回空
            return null;
        }
        /**
         * 遍历
         */
        root.left = pruneTree(root.left);  //这两个赋值只有两种可能就是赋值为空，删除为0的孩子
        root.right = pruneTree(root.right);//或者保留为1的孩子节点。也就实现了自底向上删除为0的孩子节点。

        //叶子节点就删去
        if (root.left == null && root.right == null && root.val == 0){
            return null;
        }
        return root; //二叉树的递归 不管你里面玩得多花，最后还是返回第一次的根节点
    }
}
