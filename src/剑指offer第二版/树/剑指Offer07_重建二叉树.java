package 剑指offer第二版.树;

import LeetCode数据结构入门.day5.树.CreateTree;
import LeetCode数据结构入门.day5.树.TreeNode;
import org.junit.Test;

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
       /* int[] preOder = {3,9,5,6,20,15,7};
        int[] inOrder = {5,9,6,3,15,20,7};
        int[] postOrder = {5,6,9,15,7,20,3};
        CreateTree.show(buildTreeByPreAndIn(preOder,inOrder));
        */

//        int[] inOrder = {1,2,3,4,5};
//        int[] postOrder = {1,4,5,3,2};
        int[] inOrder = {5,9,6,3,15,20,7};
        int[] postOrder = {5,6,9,15,7,20,3};
        TreeNode treeNode = buildTreeByInAndPost(inOrder, postOrder);
        CreateTree.show(treeNode);

    }

    /**
     * 根据前序和中序重建二叉树
     * 二叉树的算法一般都是递归想起来简单 但确实很慢，而且时间复杂度和空间复杂度比较难分析
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeByPreAndIn(int[] preorder, int[] inorder) {
        return buildTreeUsePreAndIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        //-1是因为数组下标就到len-1
    }

    /**
     * 根据后序和中序重建二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeByInAndPost(int[] inorder, int[] postorder) {
        return buildTreeUsePostAndIn(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        //-1是因为数组下标就到len-1
    }


    /**
     * preStart到preEnd是左子树的部分
     * 而inStart到inEnd就是右子树的部分
     * 所以需要我们去找到在前序和中序中不同的位置。
     * 建议拿张纸写个前中序列找一下会清晰。
     * @param preorder
     * @param inorder
     * @param preStart
     * @param preEnd
     * @param inStart
     * @param inEnd
     * @return
     */
    public TreeNode buildTreeUsePreAndIn(int[] preorder, int[] inorder,int preStart,int preEnd,int inStart,int inEnd){
        if (preStart > preEnd || inStart > inEnd) return null;

        //先找到根节点
        int val = preorder[preStart];
        TreeNode root = new TreeNode(val);
        int index = 0;//去找根节点在中序序列中的索引
        for (int i = inStart; i <= inEnd; i++) { //注意是int i = inStart; i <= inEnd <= <= <=!!! 不然会StackOverflow
            if (val == inorder[i]){
                index = i;
                break;//直接跳出
            }
        }
        //计算左子树的长度
        int leftSize = index - inStart;

        /**
         * 开始递归建立左子树
         * eg: 前序：3 9 5 6 20 15 7
         *     中序：5 9 6 3 15 20 7
         *     index就是中序的3所在的位置
         *     leftSize = index - inStart
         *     前序的左子树部分就是preStart + 1 到 preStart + leftSize的部分
         *     中序的左子树就是 inStart到 Index - 1的部分 当然这个Index - 1和 inStart + leftSize是一样的。
         *     然后开始递归即可
         */
        root.left = buildTreeUsePreAndIn(preorder,inorder,
                preStart + 1,preStart + leftSize,  //左子树preoder从preStart+1开始
                inStart, index - 1);


        /**
         * 开始递归建立右子树
         * eg: 前序：3 9 5 6 20 15 7
         *     中序：5 9 6 3 15 20 7
         *     index就是中序的3所在的位置
         *     leftSize = index - inStart
         *     同理对右子树
         *     前序的右子树部分就是preStart+leftSize+1 到 preEnd的部分
         *     中序的右子树就是 index+1到inEnd的部分。
         */
        root.right = buildTreeUsePreAndIn(preorder,inorder,
                preStart + leftSize + 1,preEnd,//右子树preoder从preStart+leftSize+1开始
                index + 1 , inEnd); //右子树从inorder从index + 1开始
        return root;
    }

    public TreeNode buildTreeUsePostAndIn(int[] inorder, int[] postorder,int inStart,int inEnd,int postStart,int postEnd){
       if (postStart > postEnd || inStart > inEnd) return null;
       int val = postorder[postEnd]; //后序的最后一个数是根节点
       TreeNode root = new TreeNode(val);
        int index = 0; //找到根在中序的下标
        for (int i = inStart; i <= inEnd; i++) {
            if (val == inorder[i]){
                index = i;
                break;
            }
        }
        int leftSize = index - inStart;


        //TODO 为什么postStart要加进去，postStart不是一直都是0吗？
        /**
         * 中后序的左子树部分 为什么postEnd是postStart + leftSize - 1
         * postStart并不一直都是0，因为到right的部分，posStart会变成postStart + leftSize。
         */
        root.left = buildTreeUsePostAndIn(inorder,postorder,
                inStart,index - 1,
                postStart ,postStart + leftSize - 1);
        /**
         * 中后序的右子树部分 为什么postStart是postStart+leftSize
         */
        root.right = buildTreeUsePostAndIn(inorder,postorder,
                index + 1,inEnd,
                postStart + leftSize,postEnd - 1);
        return root;
    }

}
