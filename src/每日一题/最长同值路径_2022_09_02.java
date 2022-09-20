package 每日一题;

import LeetCode数据结构入门.day5.树.TreeNode;



/**
 * @Author KingofTetris
 * @Date 2022/9/2 8:56
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。
 * 这条路径可以经过也可以不经过根节点。
 * 两个节点之间的路径长度 由它们之间的边数表示。
 * 示例 1:
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 * 示例 2:
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 * 提示:
 * 树的节点数的范围是 [0, 104] 
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长同值路径_2022_09_02 {

    /**
     * 要求的是路径的长度 不是相同值的数量。
     * 那么某个节点出发的路径长度必然等于 左路径长度 + 右路径长度
     * 从根节点出发DFS，左孩子相同则 left1 = left + 1；不同则置为0
     * 右孩子相同则 right1 = right + 1; 不同则置为0
     * 最长路径res  res = max(res,right1 + left1)
     * @param root
     * @return
     */
    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root){
        if(root == null) return 0;//DFS至叶子节点结束,叶子节点无左右孩子，不会有更长的路径，必然为0

        int left = dfs(root.left),right = dfs(root.right);//left,right记录当前节点的下层相同值路径长度

        int left1 = 0,right1 = 0;//left1,rigth1记录从当前节点出发的值相同路径长度

        //条件，左孩子不为空且值相同
        if (root.left != null && root.left.val == root.val){
            left1 = left + 1; //有一个相同就 + 1 层
        }

        //条件，右孩子不为空且值相同
        if (root.right != null && root.right.val == root.val){
            right1 = right + 1;
        }

        res = Math.max(res,left1 + right1);//每次更新res为最大值

        return Math.max(left1,right1);//返回当前节点的左右长度中最长的那条，因为路径不能分叉，所以只返回一条。
    }
}
