package 校招笔试真题.腾讯.腾讯音乐娱乐集团2023技术类;

import LeetCode数据结构入门.day5.树.TreeNode;

/**
 * @Author KingofTetris
 * @Date 2023/3/30 17:00
 * 给定一棵二叉树，二叉树的每个结点只有0或2个孩子。(说明他是一个完全二叉树，但未必是满二叉树(只有最后一层存在叶子节点))
 * 你需要对每个结点赋值一个正整数，使得每个结点的左右子树权值和相等。
 * 你需要返回所有结点的最小权值和对 1e9 + 7取模的结果 e1 = 10 ^ 1
 * 二叉树的节点个数不会超过 1e5
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 示例1
 * 输入例子：
 * {0,0,0}
 * 输出例子：
 * 3
 */
public class 嘤嘤的新平衡树 {

    int MOD = (int) (1e9 + 7);


    /**
     * 为什么题目要求的左右子树权值和相等也可以理解为“把两个子树”变成一样的树结构？
     * 问题实际上是求一颗满二叉树的结点总数（最小权值是正整数1）。
     *
     * 懂了，其实是说，把这棵树想象一下，补全为一棵满二叉树，然后把所有的节点都赋值为1，
     * 这样一定满足左右子树的权值相等而且每棵子树的权值一定是最小的。
     * 那么问题就变成了这颗二叉树有多少个节点。
     * 求二叉树的节点就变成了求树深，n = 2^h - 1
     * @param tree
     * @return
     */
    public int getTreeSum(TreeNode tree) {
        // write code here
        int depth = getDepth(tree);

        //有了树深
        //求满二叉树的节点数 = 2^h - 1
        int s = 1;

        for (int i = 1; i <= depth; i++) {
            s = s*2 % MOD; //这里面也取MOD也是同样的道理。
        }
        return (s - 1 + MOD) % MOD; //有了总节点数再加上Mod是为了让结果一定要比MOD大
        //比如10和5，虽然10比5大，但是%7 以后，10%7=3,5%7=5，反而取了小的5就不合理了。
    }

    public int getDepth(TreeNode treeNode){
        return treeNode == null ? 0 : Math.max(getDepth(treeNode.left),getDepth(treeNode.right)) + 1;
    }
}
