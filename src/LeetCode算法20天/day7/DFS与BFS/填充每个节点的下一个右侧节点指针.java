package LeetCode算法20天.day7.DFS与BFS;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author KingofTetris
 * @File 填充每个节点的下一个右侧节点指针
 * @Time 2021/9/25  16:16
 */

/*116. 填充每个节点的下一个右侧节点指针
        给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

        struct Node {
        int val;
        Node *left;
        Node *right;
        Node *next;
        }
        填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，
        则将 next 指针设置为 NULL。

        初始状态下，所有 next 指针都被设置为 NULL。



        进阶：

        你只能使用常量级额外空间。
        使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。


        示例：



        输入：root = [1,2,3,4,5,6,7]
        输出：[1,#,2,3,#,4,5,6,7,#]
        解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。


        提示：

        树中节点的数量少于 4096
        -1000 <= node.val <= 1000*/



//下面就是层序遍历的模板
//while(!queue.isEmpty())
//        {
//        int size = queue.size();
////            System.out.println(size);//1 2 4 8 ........
//        // 遍历这一层的所有节点
//        for (int i = 0; i < size; i++) {
//             操作ABCD....
//              左右子树入队
//                queue.offer(left);
//                queue.offer(right);
//        }
public class 填充每个节点的下一个右侧节点指针 {

    @Test
    public void test(){

        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        connect(root);
    }
    public Node connect(Node root) {
        if(root == null)
            return root;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);


        // 外层的 while 循环迭代的是层数
        //这题的关键就是队列的操作
        //每次先取得队列的长度，把队头出队，再把左右孩子进队。层序遍历
        while(!queue.isEmpty())
        {
            int size = queue.size();
//            System.out.println(size);//1 2 4 8 ........
            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                //连接
                //右边还有节点就指过去queue.peek() 没用就不赋值 刚好是null
                if(i < size - 1)
                    node.next  = queue.peek();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return  root;
    }
}


