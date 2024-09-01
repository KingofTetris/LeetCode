package LeetCode数据结构与算法基础.day5.树;

/**
 * @author by KingOfTetris
 * @date 2024/9/1
 */
public class 修剪二叉搜索树 {

    /**
     * 删除BST所有节点中val不在[low,high]范围内的节点
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        //如果root.val比low还小，他的左子树就不要了，因为不可能比low大
        //但是右子树中还是有可能存在比low大的节点，要去遍历root.right进行修剪
        if (root.val < low){
            TreeNode right = trimBST(root.right,low,high);
            return right;
        }
        /**
         * 反过来如果root.val比high还大，他的右子树就不要了，因为不可能比high小
         * 但是左子树中还是有可能存在比high小的节点，要去遍历root.left进行修剪
         */
        if (root.val > high){
            TreeNode left = trimBST(root.left,low,high);
            return left;
        }

        //单层递归逻辑
        //其实相当于上一层的逻辑，因为上面都是终止条件，进行了返回。
        root.left = trimBST(root.left,low,high);
        root.right = trimBST(root.right,low,high);

        return root;
    }
}
