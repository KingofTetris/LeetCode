package LeetCode数据结构与算法基础.day5.树;

/**
 * @Author KingofTetris
 * @Date 2022/8/30 9:31
 *
 * 本题就是最大二叉树的插入。给你一个最大二叉树和一个新节点。
 * 插入后让他保持仍是一棵最大二叉树。
 *
 * 树中节点数目在范围 [1, 100] 内
 * 1 <= Node.val <= 100
 * 树中的所有值 互不相同
 * 1 <= val <= 100
 *  
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-binary-tree-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最大二叉树II_2022_08_30 {

    /**
     * 给出一颗最大树，现在在这棵最大树的副本里面插入val。返回插入后新的最大二叉树。
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
                //父节点不为空，那么就构建val节点，它的左孩子设为cur
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
