package LeetCode数据结构与算法基础.day5.树;


import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 根据前中序遍历还原二叉树
 * @Time 2021/10/25  20:13
 */

/**
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素，若包含重复元素就无法保证唯一了，包含重复可以在项目里面查 ”带重复节点的前序中序二叉树“
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 */
public class 根据前中序遍历还原二叉树 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0)
            return null;
        //每次取出根节点
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) { //找到中序遍历里面 根在哪里，然后分左右子树
                //copyOfRange[a,b)
                //pre的左子树就是 [1,1 + i] i就是左子树的节点个数
                int[] pre_left = Arrays.copyOfRange(preorder, 1, i + 1);
                //pre的右子树就是 [i + 1 + 1,preorder.length - 1]
                int[] pre_right = Arrays.copyOfRange(preorder, i + 1, preorder.length);
                //中序左子树就是 [0,i - 1]
                int[] in_left = Arrays.copyOfRange(inorder, 0, i + 1);
                //右子树就是 [i + 1,inorder.length - 1]
                int[] in_right = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                root.left = buildTree(pre_left, in_left);//左子树去构造左子树
                root.right = buildTree(pre_right, in_right);//右子树去构造右子树
                //这个break的作用是?
                //因为找到根节点以后就可以结束了，后面的inorder就没必要继续遍历了。
                //当然这种做法只适合用于不包含重复数字的二叉树
                //如果重复了就需要其他东西来辨别，到底谁才是根节点。那就非常麻烦了
                //所以实际上index B+树也是需要主键这个唯一字段来帮忙的
                break;
            }
        }
        return root;
    }
}
