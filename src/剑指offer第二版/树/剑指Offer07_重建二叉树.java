package 剑指offer第二版.树;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;
import LeetCode数据结构基础.day5.树.TreeUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/7/11 15:12
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer07_重建二叉树 {
    @Test
    public void test(){

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.remove(0);
        list.size();
        list.contains(2);
        if (list.size() > 2){
            list.get(1);
        }
        list.isEmpty();

        //队列
        list.offer(1);
        list.poll();

        //双端队列
        //两边出入队
        list.offerFirst(1);
        list.offerLast(2);
        list.pollLast();
        list.pollFirst();
        //两边取元素
        list.peekFirst();
        list.peekLast();
        //栈
        list.push(1);
        list.pop();
        list.peek();//栈顶

        int[] preOder = {3,9,5,6,20,15,7};
        int[] inOrder = {5,9,6,3,15,20,7};
        int[] postOrder = {5,6,9,15,7,20,3};
        TreeUtils.show(buildTreeByPreAndIn(preOder,inOrder));
        System.out.println();
        TreeUtils.show(buildTreeByPostAndIn(postOrder,inOrder));

//        int[] inOrder = {1,2,3,4,5};
//        int[] postOrder = {1,4,5,3,2};
     /*   int[] inOrder = {5,9,6,3,15,20,7};
        int[] postOrder = {5,6,9,15,7,20,3};
        TreeNode treeNode = buildTreeByInAndPost(inOrder, postOrder);
        CreateTree.show(treeNode);*/

    }


    //本来不想写这个map的，但是后面发现确实要写，不然递归起来很麻烦
    //用来保存中序遍历数值与下标的关系
    HashMap<Integer,Integer> inOrderMap = new HashMap<>();

    /**
     * 根据前序和中序重建二叉树
     * 二叉树的算法一般都是递归想起来简单 但确实很慢，而且时间复杂度和空间复杂度比较难分析
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeByPreAndIn(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i],i);
        }

        return buildTreeUsePreAndIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        //-1是因为数组下标就到len-1
    }

    /**
     * 根据后序和中序建立二叉树
     * @param postorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeByPostAndIn(int[] postorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i],i);
        }
        return buildTreeUsePostAndIn(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
    }


    private TreeNode buildTreeUsePreAndIn(int[] preorder, int[] inorder, int l1, int r1, int l2, int r2) {
        //递归结束的条件，当左右子树都是空的时候就可以停止了
        if (l1 > r1 && l2 > r2){  //其实这里 &&和或者都一样，因为是同一棵树。
            return null; //返回空节点给叶子
        }
        int rootVal = preorder[l1];
        TreeNode root = new TreeNode(rootVal);
        int index = inOrderMap.get(rootVal);
        //l1,r1. l2,r2 自己找张纸写一下顺序。
        root.left = buildTreeUsePreAndIn(preorder,inorder,l1 + 1,l1 + (index - l2),l2,index-1);
        root.right = buildTreeUsePreAndIn(preorder,inorder,l1 + (index - l2)  + 1,r1,index + 1,r2);

        return root;
    }

    public TreeNode buildTreeUsePostAndIn(int[] postorder, int[] inorder, int l1, int r1, int l2, int r2) {

        if (l1 > r1 && l2 > r2){
            return null;
        }
        //取根节点
        int rootVal = postorder[r1]; //注意这里有区别，后序是从尾巴取根节点
        TreeNode root = new TreeNode(rootVal);

        int index = inOrderMap.get(rootVal);

        //根据左子树的后中 递归建立左子树
        //因为树根在后面！
        //l1 + index - l2 - 1，注意这里要多减去1!!
        root.left = buildTreeUsePostAndIn(postorder,inorder,l1,l1 + index - l2 - 1,l2,index - 1);
        //根据右子树的后中 递归建立右子树
        root.right = buildTreeUsePostAndIn(postorder,inorder,l1 + index - l2,r1 - 1,index + 1,r2);

        return root;
    }
}
