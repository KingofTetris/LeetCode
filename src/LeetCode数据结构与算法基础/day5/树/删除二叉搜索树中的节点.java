package LeetCode数据结构与算法基础.day5.树;

/**
 * @author KingofTetris
 * @File BST的删除与重建
 * @Time 2021/10/26  14:57
 */

import org.junit.Test;

/**从bst中删除指定的值，然后重建，仍然保证其为一颗bst
 * 分三种情况
 * 删除叶子节点 直接删就行
 * 删除分支节点只有一个儿子节点，把儿子拿上来就行
 * 分支节点如果左右儿子都有，那就要把分支节点的左子树拿过来，接在分支节点的右子树的最左节点上。
 *
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/miao-dong-jiu-wan-shi-liao-by-terry2020-tc0o/
 * */
public class 删除二叉搜索树中的节点 {
    @Test
    public void test(){
//        Integer[] nums = {5,3,6,2,4,null,7};
        Integer[] nums = {5,3,6,2,null,null,7};
        TreeNode root = TreeUtils.constructTree(nums);
        TreeUtils.show(root);
        deleteNode(root,3);
        TreeUtils.show(root);
    }

    /**
     * BST中删除key 有5种情况
     * 1.没找到key 那不用动
     * 2.找到key 是叶子节点 左右孩子都为空，直接删除
     * 3.找到key 左孩子为空，右孩子不为空，把父节点指向其右孩子即可
     * 4.找到key 左孩子不为空，右孩子为空，把父节点指向其左孩子即可
     * 5.找到key 左孩子不为空，右孩子不为空。
     *   这种情况就复杂一点
     *   我们选择用右孩子顶替删除节点的位置，这就需要我们去找到右孩子里面最小的元素
     *   其实就是右孩子里面最左边的节点，用他去接收删除节点的左子树，然后接到删除子节点的右孩子上即可。
     *   这样既保证左子树比他小，右子树都比他大，才能符合BST的性质。
     *
     *   这里的情况比较复杂，用文字比较难说清楚，你最好画个图。
     *
     *             7
     *           /   \
     *          5     9
     *         / \   / \
     *        4  6  8   10
     *      比如删掉这个7，我们要先找到8。用9去顶替掉7，5接到8后面
     *        也就变成了这样。
     *                    9
     *                  /  \
     *                 8   10
     *                /
     *               5
     *              / \
     *             4  6
     *
     *  而且本题非常好地体现了Java自动内存管理的特性，不需要像C和C++那样手动去回收不可达的对象
     *  这题目里面的删除，本质上都不是真的释放，而是变成不可达对象，GC会自动去回收他们。
     *
     *  而且这题有个比较让人恶心的地方是这种做法会导致树越来越高，
     *  如果在满足BST的前提下尽量减少树的高度？留给聪明的你思考。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key)
    {
        //1.没找到对应的key
       if (root == null) return null;
       //找到了对应的key
        if (root.val == key){
            //2.左右孩子都为空，直接删除
            if (root.left == null && root.right == null){
                return null;
            }
            //3.左孩子为空，右孩子不为空
            else if (root.left == null && root.right != null){
                return root.right;
            }
            //4.左孩子不为空，右孩子为空
            else if (root.left != null && root.right == null){
                return root.left;
            }
            //5.左右孩子都不为空
            else if (root.left != null && root.right != null){
                TreeNode cur = root.right;
                //去找最左边孩子
                while (cur.left != null){
                    cur = cur.left;
                }
                //找到以后
                cur.left = root.left;
                //用root.right充当父节点。
                return root.right;
            }
        }
        //不要看上面那么长，其实全是终止条件。
        //下面开始单层递归逻辑
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        //最后返回处理完的root
        return root;
    }

}
