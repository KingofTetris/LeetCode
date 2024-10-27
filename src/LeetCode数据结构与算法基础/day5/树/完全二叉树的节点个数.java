package LeetCode数据结构与算法基础.day5.树;

/**
 * @author by KingOfTetris
 * @date 2024/10/27
 */
public class 完全二叉树的节点个数 {

    //通用，而且只需要O(N)的方法
    int count = 0;

    public int countNodes(TreeNode root) {
        helper(root);
        return count;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        count++;
        countNodes(root.left);
        countNodes(root.right);
    }

    //装逼的写法，非要利用完全二叉树。也不见得更快。
    public int countNodes2(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 1, rightDepth = 1; // 这里初始为0是有目的的，为了下面求指数方便
        while (left != null) {  // 求左子树深度
            left = left.left;
            leftDepth++;
        }
        while (right != null) { // 求右子树深度
            right = right.right;
            rightDepth++;
        }
        //如果左边和右边的延展深度相同，那么说明这颗完全二叉树就是满二叉树。
        if (leftDepth == rightDepth) {
            //满二叉树的节点数量=1+2+4+8+16+....
            // = 2^h - 1
            return (int) (Math.pow(2, leftDepth) - 1);
        }
        //如果不同，那么就要递归遍历左右子树 + 根节点。
        // 完全二叉树递归左右子树必然是一颗满二叉树
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
