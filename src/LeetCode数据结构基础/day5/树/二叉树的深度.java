package LeetCode数据结构基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author KingofTetris
 * @File 二叉树的深度
 * @Time 2021/10/6  9:54
 */
public class 二叉树的深度 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        node2.left = node3;
        node2.right = node4;

        System.out.println(maxDepth(root));
    }


    //经典递归应用，实际上会先一直跑到左分支的末尾的空节点，返回1给父节点。
    //然后去找父节点的右节点，继续重复这个过程。当所有子节点都遍历完毕后，
    //会重新回到父节点再+1.以此类推到根节点。
    public int maxDepth(TreeNode root) {
    /*    if (root == null)
            return 0;
        else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            //这个+1是必不可少的 不然一直返回0;
            return Math.max(leftDepth, rightDepth) + 1;
        }*/
        return root == null ? 0 : Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    /**
     * 迭代就要用到层序遍历了。每下一层就+1;
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        List<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }}, tmp;//匿名内部类直接添加root的话，后面的TreeNode不能省略
        int res = 0;
        while (!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for (TreeNode node : queue) {
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            queue = tmp;//每次要把上一层情空，所以才弄tmp这个临时变量
            res++;
        }
        return res;
    }

//    作者：jyd
//    链接：https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/solution/mian-shi-ti-55-i-er-cha-shu-de-shen-du-xian-xu-bia/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
