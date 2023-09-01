package LeetCode数据结构入门.day5.树;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/8/31
 */
public class 普通二叉树的最近公共祖先 {
    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTree(new Integer[]{3,4,null,5,null,7,9,10,null,11});
        TreeUtils.show(tree);
        TreeNode p = TreeUtils.findTreeNode(tree, 4);
        TreeNode q = TreeUtils.findTreeNode(tree, 5);
        TreeNode lca = LCA(tree, p, q);
        System.out.println("LCA:");
        TreeUtils.show(lca);
    }

    @Test
    public void test(){
        double x = 3.0;
        int y = 5;

        //double / int
        //精度高的和精度低的运算会把结果转化成结果精度高的。
        x /= --y;
        System.out.println(x);
    }

    /**
     * 最简单的朴素算法，
     * 1.向上标记法
     * u一直向上到root节点，把沿途走过的节点都标记一遍，如果标记的途中刚好发现了节点v，那么LCA(P,Q)=v
     * 如果没发现节点v,那么v再向上走，他也把走过的节点都标记一遍，如果标记途中刚好发现u,那么LCA(P,Q)=u
     * 如果没有上面那两种那么巧,那么v发现有节点已经被标记过的时候，这个节点就是LCA(p,q)
     * 或者是
     * 2.同步前进法
     * 让u,v中深度较深的先走到同一深度，然后一起向上走。如果他们碰头了，那么这个节点就是LCA(p,q)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */

    //因为我们的Node是孩子双亲表示法，不记录parent，那么就不能直接从p出发向上走
    //需要从root反过来向下记录路径，那么为了达到向上的目的。
    //我们用栈来记录，然后用栈的先进先压栈特性实现 向上爬的效果
    public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pStack = new LinkedList<>();
        LinkedList<TreeNode> qStack = new LinkedList<>();
        //如果p或者q根本就不存在于root里面,那么都不用谈LCA了。
        boolean f1 = getPath(root, p, pStack);
        boolean f2 = getPath(root, q, qStack);
        if (!f1){
            System.out.println("树中不存在p节点");
            return null;//返回null
        }
        if (!f2){
            System.out.println("树中不存在q节点");
            return null;//返回null
        }
        //然后我们开始寻找p,q的LCA
        //pStack就是从p出发向上到root的路径

        //我们开始遍历qStack
        while (!qStack.isEmpty()){
            TreeNode pop = qStack.pop();
            if (pStack.contains(pop)){
                return pop;//pop就是lca
            }
        }
        //如果没有LCA，那么返回一个空节点
        return new TreeNode();
    }
    //root根结点，node:指定的节点，stack:存放根节点到指定节点的路径
    //寻找从node -> root的路径
    public static boolean getPath(TreeNode root, TreeNode node, LinkedList<TreeNode> stack) {
        if (root == null || node == null) return false;
        stack.push(root);
        //如果root就是node，就不用再找了。
        if (node == root) return true;
        //否则继续left,right
//        getPath(root.left, node, stack);
//        getPath(root.right, node, stack);
        boolean flg1 = getPath(root.left, node, stack);
        if (flg1) return true;//找到啦
        boolean flg2 = getPath(root.right, node, stack);
        if (flg2) return true;//找到啦
        //如果root里面根本就不存在node节点。那么
        //因为是右节点，他之前会把左节点也push到栈中，要先把左节点给pop出去。
        stack.pop();
        return false;
    }
/*————————————————
    版权声明：本文为CSDN博主「sqyaa.」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/m0_74106420/article/details/129491164*/
}
