package LeetCode数据结构与算法基础.day5.树;

/**
 * @author by KingOfTetris
 * @date 2024/11/10
 */
public class 二叉树中的最长交错路径 {

    //就是从根节点出发，分两个方向向下DFS
    // 1.左->右->左->右->....
    // 2.右->左->右->左....

    //最长的交错路径长度是多少

    /**
     * 对于任意一个节点node，可以通过一个标志flag来记录该节点的父节点本身是左子节点还是右子节点还是根节点
     * 1.父节点本身为根节点：flsg = 0：
     *                node不管是左子节点还是右子节点，记录路径累加1
     * 2.父节点本身为左子节点：flsg = 1：
     *             （1）node为父节点的左子节点，路径重置为1
     *             （2）node为父节点的右子节点，记录路径累加1
     * 3.父节点本身为右子节点：flsg = 2：
     *             （1）node为父节点的左子节点，记录路径累加1
     *             （2）node为父节点的右子节点，路径重置为1
     *
     * 如果累加路径大于当前最大值，更新最大值。
     *
     * 作者：ChenXW
     * 链接：https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree/solutions/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    int re = Integer.MIN_VALUE;;
    public int longestZigZag(TreeNode root) {
        traverse(root, 0, 0);
        return re;
    }
    public void traverse(TreeNode node, int sum, int flag){
        if(node == null){
            return ;
        }
        if(sum>re){
            re = sum;
        }
        if(flag == 0){
            traverse(node.left, sum+1, 1);
            traverse(node.right, sum+1, 2);
        }
        if(flag==1){
            traverse(node.left, 1, 1);
            traverse(node.right, sum+1, 2);
        }
        if(flag==2){
            traverse(node.left, sum+1, 1);
            traverse(node.right, 1,2);
        }
    }
}
