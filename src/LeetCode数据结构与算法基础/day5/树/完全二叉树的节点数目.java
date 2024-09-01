package LeetCode数据结构与算法基础.day5.树;

/**
 * @author by KingOfTetris
 * @date 2024/8/31
 */
public class 完全二叉树的节点数目 {

    //那一个遍历不就完了吗？这题啥意思。
    //普通做法就是遍历一遍，遍历到一个节点就+1
    //遍历所有节点的做法就是O(n) O(1)
    int n = 0;
    public int countNodes2(TreeNode root) {
        preOrder(root);
        return n;
    }

    private void preOrder(TreeNode root) {
        if (root == null) return;
        n++;
        preOrder(root.left);
        preOrder(root.right);
    }

    //但是这题的本意不是让你去一个一个遍历，他是完全二叉树
    /**
     * 在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2^(h-1)  个节点。
     *
     * 面试出这题的本意是让你通过完全二叉树的性质，求出节点数量
     */

    /**
     * 针对完全二叉树的解法
     *
     * 满二叉树的结点数为：2^depth - 1
     *
     * 这个复杂度是O(logN * logN)
     */
    public int countNodes(TreeNode root) {
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
            return (int) (Math.pow(2,leftDepth) - 1);
        }
        //如果不同，那么就要递归遍历左右子树 + 根节点。
        // 完全二叉树递归左右子树必然是一颗满二叉树
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


}
