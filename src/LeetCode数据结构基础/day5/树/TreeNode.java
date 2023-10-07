package LeetCode数据结构基础.day5.树;

/**
 * @author KingofTetris
 * @File TreeNode
 * @Time 2021/10/5  13:02
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(){
    }
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val,TreeNode left,TreeNode right){
        this.val = val;
        this.right = right;
        this.left = left;
    }
}
