package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/26
 */


//这种根据中后序，中前序还原二叉树的题目
    //核心都是每次先取出根节点
    //然后循环遍历中序，再次分出左右子树
    //递归去遍历inorder,postorder
    //或者inorder,preorder.
    //两个左右子树的下标，自己拿笔在纸上算，光靠想是想不出来的
public class 根据中后遍历还原二叉树 {

    @Test
    public void test() {
        int[] inorder = {4,2,5,1,3};
        int[] postorder = {4,5,2,3,1};
        TreeNode root = buildTree(inorder, postorder);
        TreeUtils.show(root);
    }

    //本质还是回溯
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0)
            return null;
        //每次取出根节点
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        //遍历中序找到根节点然后开始递归
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) { //找到中序遍历中根在哪里，然后分左右子树
                //中序找到根分左右就很简单
                //自己写左闭右闭的复制
                //中序左子树[0,i-1] 右子树[i+1,len - 1]
                int[] in_left = copyRange(inorder,0,i - 1);
                int[] in_right = copyRange(inorder,i + 1, inorder.length - 1);
                int leftLen = i; //左子树长度
                int rightLen = inorder.length - (i + 1); //右子树长度
                //后序左子树[0,i-1] 右子树[i,i + rightLen - 1]
                int[] post_left = copyRange(postorder,0,i - 1);
                int[] post_right = copyRange(postorder,i,i + rightLen - 1);
                root.left = buildTree(in_left, post_left);
                root.right = buildTree(in_right, post_right);
                //这个break的作用是?
                //因为找到根节点以后就可以结束了，后面的inorder就没必要继续遍历了。
                //当然这种做法只适合用于不包含重复数字的二叉树
                //如果重复了就需要其他东西来辨别，到底谁才是根节点。那就非常麻烦了
                //所以实际上index B+树也是需要主键这个唯一字段来帮忙的
                break;
            }
        }
        return root;
    }


    public int[] copyRange(int[] nums,int i,int j){
        int[] res = new int[j - i + 1];
        int index = 0;
        for (int k = i; k <= j; k++) {
            res[index++] = nums[k];
        }
        return res;
    }

}
