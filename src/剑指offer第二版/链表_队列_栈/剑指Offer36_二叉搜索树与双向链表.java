package 剑指offer第二版.链表_队列_栈;


import LeetCode数据结构与算法基础.DFS与BFS.Node;

/**
 * @Author KingofTetris
 * @Date 2022/8/29 16:18
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer36_二叉搜索树与双向链表 {

    /**
     * 从BST转换到双向循环链表，实际上是求BST的中序遍历
     * 1、我们定义两个指针 pre 和 head，pre指针用于保存中序遍历的前一个节点，head指针用于记录排序链表的头节点。
     * 2、中序遍历二叉树，因为是中序遍历，所以遍历顺序就是双线链表的建立顺序。
     * 我们只需要在中序遍历的过程中，修改每个节点的左右指针，将零散的节点连接成双向循环链表。
     * @param root
     * @return
     */
    Node pre = null, head = null; //初始化为null
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        dfs(root);//建立完了链表
        /**
         *  head 在头，pre在尾。首尾相接即可
         */
        head.left = pre;
        pre.right = head;
        return head;
    }

    /**
     * 中序遍历
     * @param root
     */
    void dfs(Node root){
        if (root == null) return; // 递归边界: 叶子结点返回
        dfs(root.left);

        if (pre != null) pre.right = root; //如果前去不为空,则将前驱节点的right指向当前节点。
        else head = root; // 如果前驱为空，则是链表头结点。保存头节点为head

        root.left = pre; //每一个root节点访问时它的左子树肯定被访问过了，因此放心修改它的left指针，
        // 将root的left指针指向它的前驱节点


        pre = root; //然后pre后移。

        dfs(root.right);
    }

}
