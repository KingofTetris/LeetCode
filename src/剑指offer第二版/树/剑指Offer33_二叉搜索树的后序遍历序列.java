package 剑指offer第二版.树;

/**
 * @Author KingofTetris
 * @Date 2022/8/29 14:38
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，
 * 否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * 参考以下这颗二叉搜索树：
 * <p>
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * 示例 1：
 * <p>
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 * <p>
 * 输入: [1,3,2,6,5]
 * 输出: true
 *  
 * <p>
 * 提示：
 * <p>
 * 数组长度 <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer33_二叉搜索树的后序遍历序列 {

    /**
     * 判断输入的数组是否是某棵二叉树搜索树(BST)的后序遍历 最后一个节点是根节点
     * BST就要求左孩子小于根，右孩子大于根。
     * 假设数字不重复
     * 所以有 左孩子 < 根 < 右孩子
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }


    //[1,3,2,6,5]
    private boolean recur(int[] postorder, int left, int right) {
        //（结束条件最后看！）左右指针重合的时候，即left~right区间只有一个数
        if (left >= right) {
            return true;
        }
        //在后序遍历中根节点一定是最后一个点
        int root = postorder[right];
        int index = left;
        /**
         * 找到根节点左边第一个比root大的结点。这个结点 左边就是root的左子树，右边和他本身都是root的右子树
         * 这一步也验证了左子树 < 根这个条件
         */
        while (postorder[index] < root) {
            index++;
        }

        /**
         * 下面开始验证 根 < 右孩子
         */
        int m = index;//找到根的右孩子
        //如果m~right区间（root的右子树）出现了比root小的节点，则不可能是BST
        while (index < right) {
            if (postorder[index] < root) {
                return false;
            }
            index++;
        }
        //上面的循环都没有返回false则，这个根节点的左右子树里面 左边的值 < 根 < 右边的值
        //也就是有BST成立的条件，继续往下递归。直到叶子节点也就是left >= right

        //此时能保证left ~ m-1都比root小，m ~ right-1都比root大，但这两个子区间内部的情况需要继续递归判断
        /**
         * recur(postorder, left, m - 1) 递归判断左子树为BST
         * recur(postorder, m, right - 1) 递归判断右子树为BST
         */
        return recur(postorder, left, m - 1) && recur(postorder, m, right - 1);
    }
}
