package LeetCode数据结构入门.day5.树;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File 验证二叉搜索树
 * @Time 2021/10/9  9:46
 */
/*98. 验证二叉搜索树
        给你一个二叉树的根节点 root，判断其是否是一个有效的二叉搜索树。

        有效 二叉搜索树定义如下：

        节点的左子树只包含 小于 当前节点的数。
        节点的右子树只包含 大于 当前节点的数。
        所有左子树和右子树自身必须也是二叉搜索树。

        提示：
        树中节点数目范围在[1, 10^4] 内
        -2^31 <= Node.val <= 2^31 - 1 显然这是int的范围，如果超过int会报错，所以要定义为long类型
*/

public class 验证二叉搜索树 {


    @Test
    public void test(){
        TreeNode tree = TreeUtils.createTree(new Integer[]{4,2,7,1,3,5,8});
        System.out.println(isValidBST(tree));
    }

    //BST<-->中序遍历是一个递增序列。

    //递归写法（还没理解为什么这样写）
    // 递归
    //写成判断每个节点满不满足left<root<right
    //变成非递归了
    //如果你只是递归获得了BST的中序遍历数组，然后通过数组来判断，那不是最好的解法
    //没有必要再弄一个数组出来。

    /**
     * 最优解，遍历的同时判断。
     */
    long maxVal = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        //空节点当然是bst
        if (root == null) {
            return true;
        }
        // 左
        boolean left = isValidBST(root.left);
        // 中
        // 按照bst的顺序，后一个节点肯定比前一个节点要大
        //否则就不是bst
        if (root.val > maxVal){
            maxVal = root.val;
        }else {
            return false;
        }
        // 右
        boolean right = isValidBST(root.right);
        return left && right;
    }

    //迭代写法 还是左根右 属于是时间换空间。
    //通过栈的先进后出特点存放
   /* public boolean isValidBST(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double pre = -Double.MAX_VALUE;
        //结束条件是栈为空 或者 遍历完所有节点
        while (!stack.isEmpty() || root != null) {
            //节点未遍历完 注空节点不压栈
            //当遍历到叶子节点时 再去找root.right为空，但是栈里还有父节点 栈不为空
            //直接弹出父节点，再找root.right就能实现遍历左右子树了。
            while (root != null) {
                //把根和左结点全部压入栈中
                stack.push(root);
                root = root.left;
            }
            //取出最左节点
            root = stack.pop();
            //pre初始化取了一个最小值 比这个还小就超出范围 return false
            //大于的话就把pre设为当前值和下一个值比较就行了
            // 如果中序遍历得到的节点的值小于等于前一个 pre，说明不是二叉搜索树
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;

            //遍历右节点
            root = root.right;
        }
        return true;
    }*/

}
