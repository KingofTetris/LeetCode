package 剑指offer第二版.树;

import LeetCode数据结构入门.day5.树.CreateTree;
import LeetCode数据结构入门.day5.树.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/8/29 15:21
 * <p>
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
 * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 */
public class 剑指Offer34_二叉树中和为某一值的路径 {

    @Test
    public void test(){
        Integer[] nums = new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1};
        TreeNode tree = CreateTree.createTree(nums);
        List<List<Integer>> lists = pathSum(tree, 22);
        for (List<Integer> path : lists) {
            for (Integer node : path) {
                System.out.print(node + "\t");
            }
            System.out.println();
        }
    }

    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        recur(root, target);
        return res;
    }
    public void recur(TreeNode root, int tar) {
        if (root == null) return; //遇到空节点返回

        path.add(root.val);

        tar = tar - root.val;//每次计算tar - root.val的值是否为0

        if (tar == 0 && root.left == null && root.right == null)
            res.add(new LinkedList(path)); //如果为0，而且是叶子节点则该路径成立添加到结果集合res里面
        //需要注意的是如果直接添加path，则添加的是path的地址，那么以后path改变,res里也会变。所以要新new 一个

        /**
         * 递归去找左右孩子是否满足条件
         */
        recur(root.left, tar);

        recur(root.right, tar);

        path.remove(path.size() - 1); //不满足的节点从path里面删掉。
    }
}
