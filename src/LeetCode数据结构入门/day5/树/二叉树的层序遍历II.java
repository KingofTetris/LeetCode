package LeetCode数据结构入门.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author KingofTetris
 * @File 二叉树的层序遍历
 * @Time 2021/10/6  9:10
 */

//自底向上层序遍历
public class 二叉树的层序遍历II {

    @Test
    public void test(){
        TreeNode root = TreeUtils.createTree(new Integer[]{1,2,3,4,5,6,7});
        List<List<Integer>> lists = levelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        //根节点为空直接返回空列表，而不是返回null
        if (root == null)
            return list;
        Queue<TreeNode> queue = new LinkedList();
        //根节点不为空入队。
        queue.offer(root);
        while (!queue.isEmpty()) {
            //每次建一个新列表 最后list.add就行了 不用耗费心机去设置layer
           List<Integer> layer_list = new LinkedList<>();
            //重要的就是这里，每次记录一层里面的节点数
            //queue.size() 然后每次出队这么多节点！
           int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                //poll取得队头 顺便queue--
                TreeNode peek = queue.poll();
                layer_list.add(peek.val);
                if(peek.left != null)
                    queue.offer(peek.left);
                if(peek.right != null)
                    queue.offer(peek.right);
            }
            list.add(layer_list);
        }
        List<List<Integer>> resList = new LinkedList<>();
        for (int i = list.size() - 1; i >= 0;i--) {
            resList.add(list.get(i));
        }
        return resList;
    }
}
