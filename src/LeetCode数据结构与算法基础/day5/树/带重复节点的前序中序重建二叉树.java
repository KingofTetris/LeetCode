package LeetCode数据结构与算法基础.day5.树;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author KingofTetris
 * @Date 2023/3/30 11:37
 *
 * 2.
 * 带重复节点的前序中序二叉树
 * 已知一个二叉树的先序遍历序列和中序遍历序列，但其中一些节点的值可能相同。
 * 请你返回所有满足条件的二叉树。二叉树在数组中的顺序是任意的。
 *
 * 另外看这题之前，先复习一下通过前中，前后重建二叉树。
 */
public class 带重复节点的前序中序重建二叉树 {

    public static void main(String[] args) {
        带重复节点的前序中序重建二叉树 s = new 带重复节点的前序中序重建二叉树();
        Integer[] nums1 = {1,1,2};
        Integer[] nums2 = {1,2,1};
        ArrayList<Integer> preOrder = new ArrayList<>(Arrays.asList(nums1));
        ArrayList<Integer> inOrder = new ArrayList<>(Arrays.asList(nums2));

        ArrayList<TreeNode> binaryTrees = s.getBinaryTrees(preOrder, inOrder);

        for (TreeNode binaryTree : binaryTrees) {
            TreeUtils.show(binaryTree);
            System.out.println();
        }
    }
    /**
     * 经典递归。只需要枚举在中序遍历序列中每个可能成为下一个根节点的节点即可。
     * 因为是枚举的中序序列，那么假设某一个节点为根节点，这个节点左侧所有节点均为它的左子树，递归处理即可。
     * @param preOrder
     * @param inOrder
     * @return
     */
    public  ArrayList<TreeNode> getBinaryTrees(ArrayList<Integer> preOrder, ArrayList<Integer> inOrder) {
        // write code here
        //因为都是闭区间，所以从 0 到 size - 1
        return buildTree(preOrder, 0, preOrder.size() - 1,
                inOrder, 0, inOrder.size() - 1);
    }

    ArrayList<TreeNode> buildTree(ArrayList<Integer> preOrder, int preStart, int preEnd,
                                  ArrayList<Integer> inOrder, int inStart, int inEnd) {
        ArrayList<TreeNode> res = new ArrayList<>();
        if (preStart > preEnd || inStart > inEnd) {
            res.add(null);
            return res;
        }
        int rootVal = preOrder.get(preStart);//前序的节点当作根节点

        //包含重复的节点 你就得把所有重复的节点都放到indexies里面，就不能用map了
        //map不能一个key对应多个位置，所以多用了个indexies来存放所有的可能的根位置
        ArrayList<Integer> indexs = new ArrayList<>();
        for (int i = inStart; i <= inEnd; i++) { //遍历中序序列
            if (inOrder.get(i) == rootVal) { //去找这个根节点
                indexs.add(i); //保存下标。
            }
        }
        //遍历这些有可能当根节点的中序节点
        for (Integer index : indexs) {
            //递归建立左右子树
            //对应的下标要写正确
            //前序:根左右 中序：左根右
            //index对于中序来说就是根
            //所以中序的左子树[inStart,index - 1],右子树就是[index + 1,inEnd]
            //preStart对于前序来说就是根
            //所以前序的左子树就是[preStart + 1,preStart + index - inStart] index - inStart是左子树的节点个数
            //右子树就是[preStart + index - inStart + 1,preEnd]

            //反过来如果是后序+中序就要小心了。
            //因为根节点是从后面取的，有的起止位置就要多-1

            //总之不用死记，遇到了拿张纸出来写出序列，分成左右子树去递归就行。
            ArrayList<TreeNode> lefts = buildTree(preOrder, preStart + 1, preStart + index - inStart,
                    inOrder, inStart, index - 1);
            ArrayList<TreeNode> rights = buildTree(preOrder, preStart + index - inStart + 1, preEnd,
                    inOrder, index + 1, inEnd);



            //最后的形成的树也不能像唯一确定的那样
            //直接root.left=lefts,root.right=rights.

            //要遍历所有可能性的组合。
            //遍历的顺序无所谓，左子树先还是右子树先对结果没有影响。
            //因为2*3 和 3*2 没有区别。
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(rootVal);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
