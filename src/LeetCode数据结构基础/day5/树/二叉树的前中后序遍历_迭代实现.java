package LeetCode数据结构基础.day5.树;

import org.junit.Test;

import java.util.*;

/**
 * @author KingofTetris
 * @File 二叉树的前中后序遍历_迭代实现
 * @Time 2021/10/5  13:41
 */
public class 二叉树的前中后序遍历_迭代实现 {

    List<Integer> list = new LinkedList<>();

    @Test
    public void test() {
        Random rand = new Random();
        Integer[] nodes = new Integer[5];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = rand.nextInt(100); //[0,99)
        }
        TreeNode root = TreeUtils.createTree(nodes);
        TreeUtils.show(root);
        List<Integer> preorder = preorderTraversal(root);
        for (Integer i : preorder) {
            if (i != null)
                System.out.print(i + "\t");
        }

        System.out.println();
        //清空list 中序遍历
        list.clear();
        List<Integer> inorder = inorderTraversal(root);
        for (Integer i : inorder) {
            if (i != null)
                System.out.print(i + "\t");
        }
        System.out.println();
        //后序遍历
        list.clear();
        List<Integer> postorder = postorderTraversal(root);
        for (Integer i : postorder) {
            if (i != null)
                System.out.print(i + "\t");
        }
    }

    //前中后序的迭代实现，都需要借助stack来完成。
    // 前序遍历顺序：中-左-右，入栈顺序：中-右-左
    //要注意栈的出栈顺序是反过来的。
    //先让右孩子进栈，我才能保证左孩子后入栈，先出栈。
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            //先压右再压左。
            //来这里看动图，https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%BF%AD%E4%BB%A3%E9%81%8D%E5%8E%86.html#%E6%80%9D%E8%B7%AF
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return result;
    }


    //中序遍历
    //左中右
    //这个就麻烦了，不能像前序那样，遇到谁处理谁
    //这里需要先遍历到最左边，然后再处理。
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            //如果left为空了，
            else{
                //那么就可以pop出栈顶，然后加入cur的right节点。
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    // 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果

    /**
     * 再来看后序遍历，先序遍历是中左右，后续遍历是左右中，那么我们只需要调整一下先序遍历的代码顺序，
     * 就变成中右左的遍历顺序，然后在反转result数组，输出的结果顺序就是左右中了，如下图：
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        //root先入栈。相当于中在外面就处理了
        stack.push(root);
        while (!stack.isEmpty()){
            //入栈顺序左右，那么出栈就变成右左
            //整个顺序就是中右左。
            //那么最后把他反转胰一下就变成了左右中。就是后序了。
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null){
                stack.push(node.left);
            }
            if (node.right != null){
                stack.push(node.right);
            }
        }
        //上面的代码和迭代前序遍历完全一样，唯一的区别就是入栈顺序变成了left,right
        //最后再反转result.
        Collections.reverse(result);
        return result;
    }
}
