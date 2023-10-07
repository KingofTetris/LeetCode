package LeetCode数据结构基础.day5.树;

/**
 * @author KingofTetris
 * @File BST的删除与重建
 * @Time 2021/10/26  14:57
 */

import LeetCode数据结构基础.day5.树.TreeUtils;
import LeetCode数据结构基础.day5.树.TreeUtils;
import org.junit.Test;

/**从bst中删除指定的值，然后重建，仍然保证其为一颗bst
 * 分三种情况
 * 删除叶子节点 直接删就行
 * 删除分支节点只有一个儿子节点，把儿子拿上来就行
 * 分支节点如果左右儿子都有，那就要把分支节点的左子树拿过来，接在分支节点的右子树的最左节点上。
 *
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/miao-dong-jiu-wan-shi-liao-by-terry2020-tc0o/
 * */
public class BST的删除与重建 {
    @Test
    public void test(){
//        Integer[] nums = {5,3,6,2,4,null,7};
        Integer[] nums = {5,3,6,2,null,null,7};
        TreeNode root = TreeUtils.constructTree(nums);
        TreeUtils.show(root);

        deleteNode(root,3);
        TreeUtils.show(root);
    }
    //这种方法不用考虑左旋右旋，但是改变了树高
    //所有 有左右孩子的都是这种处理，先找到删除减点右子树的最左节点，然后把待删除节点的左子树赋给最左节点的左孩子
    //最后这个待删除节点直接用它的右孩子覆盖掉，即root = root.right
    //例子中实际上是把2放到了4的左孩子，然后拿4替换掉3
    public TreeNode deleteNode(TreeNode root, int key)
    {
        if (root == null)    return null;
        if (key > root.val)    root.right = deleteNode(root.right, key);     // 去右子树删除
        else if (key < root.val)    root.left = deleteNode(root.left, key);  // 去左子树删除
        else    // 当前节点就是要删除的节点
        {
            if (root.left == null)   return root.right; // 情况1，欲删除节点无左子
            if (root.right == null)  return root.left;  // 情况2，欲删除节点无右子
            TreeNode node = root.right;           // 情况3，欲删除节点左右子都有
            while (node.left != null)          // 寻找欲删除节点右子树的最左节点
                node = node.left;
            node.left = root.left;    // 将欲删除节点的左子树成为其右子树的最左节点的左子树
            root = root.right;         // 欲删除节点的右子顶替其位置，节点被删除
        }
        return root;
    }

}
