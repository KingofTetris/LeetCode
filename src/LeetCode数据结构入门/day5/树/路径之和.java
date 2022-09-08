package LeetCode数据结构入门.day5.树;

/**
 * @author KingofTetris
 * @File 路径之和
 * @Time 2021/10/7  16:14
 */

/*112. 路径总和
        给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，
        判断该树中是否存在 根节点到叶子节点 的路径，
        这条路径上所有节点值相加等于目标和 targetSum 。

        叶子节点 是指没有子节点的节点。*/

/*    提示：

            树中节点的数目在范围 [0, 5000] 内
            -1000 <= Node.val <= 1000
            -1000 <= targetSum <= 1000
            通过次数271,655提交次数516,360

            来源：力扣（LeetCode）
            链接：https://leetcode-cn.com/problems/path-sum
            著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/


    //注意已经指定了一定是从根到叶子的路径。
public class 路径之和 {

    //BFS 非常耗时。而且也不算好想
/*    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

         //两个队列一个维护路径， 一个维护targetSum
        LinkedList<TreeNode> queueNode = new LinkedList<>();
        LinkedList<Integer> queueVal = new LinkedList<>();

        queueNode.offer(root);
        queueVal.offer(root.val);


        //只要点没探完就一直探到结束 或者 遇到目标值
        while (!queueNode.isEmpty()){

             //每次出一个节点
            TreeNode now = queueNode.poll();

            //每次更新一个路径上的sum 方法是在while末尾用temp加上节点的val
            //然后取出来和target对比
            int temp = queueVal.poll();


            if (now.right == null && now.left == null){
                if (temp == targetSum){
                    return true;
                }
                //如果不等于目标值就 结束这次while 因为下面肯定空的。没必要再去判断
                continue;
            }
            if (now.left != null){
                queueNode.offer(now.left);
                queueVal.offer(now.left.val + temp);
            }
            if (now.right != null){
                queueNode.offer(now.right);
                queueVal.offer(now.right.val + temp);
            }
        }
        return false;
    }*/

    //DFS 也就是递归 是反过来用Sum减去节点值
    //又快又省，就是难想。
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);

    }
}
