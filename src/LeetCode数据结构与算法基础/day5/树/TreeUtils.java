package LeetCode数据结构与算法基础.day5.树;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File CreateTree
 * @Time 2021/10/7  16:24
 */
public class TreeUtils {
    public static void main(String[] args) {
        Integer[] nums = {3, 9, 20, null,null,29, 30, 15, 7};
//        TreeNode root = new CreateTree().createTree(nums);
//        new CreateTree().printTree(root,"");
        TreeNode root2 = TreeUtils.constructTree(nums);
        TreeUtils.show(root2);
    }


    //用数组null表示空节点建立二叉树 借助一个队列还实现
    public static TreeNode constructTree(Integer[] nums) {
        if (nums.length == 0) return new TreeNode(0);
        //Deque 双端队列
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        // 创建一个根节点
        TreeNode root = new TreeNode(nums[0]);
        nodeQueue.offer(root);
        TreeNode cur;
        // 记录下一行节点的数量（注意不一定是2的幂，而是上一行中非空节点的数量乘2）
        // 因为空节点是没有孩子的
        int lineNodeNum = 2;
        // 记录当前行中数字在数组中的开始位置
        int startIndex = 1;
        // 记录数组中剩余的元素的数量
        int restLength = nums.length - 1;
        while (restLength > 0) {
            // 只有最后一行可以不满，其余行必须是满的
//            // 若输入的数组的数量是错误的，直接跳出程序
//            if (restLength < lineNodeNum) {
//                System.out.println("Wrong Input!");
//                return new TreeNode(0);
//            }
            for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
                // 说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i == nums.length) return root;
                cur = nodeQueue.poll();
                if (nums[i] != null && cur != null) {
                    cur.left = new TreeNode(nums[i]);
                    nodeQueue.offer(cur.left);
                }
                // 同上，说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i + 1 == nums.length) return root;
                if (nums[i + 1] != null && cur != null) {
                    cur.right = new TreeNode(nums[i + 1]);
                    nodeQueue.offer(cur.right);
                }
            }
            startIndex += lineNodeNum;
            restLength -= lineNodeNum;
            lineNodeNum = nodeQueue.size() * 2;
        }
        return root;
    }


    /**
     * java Queue中 add/offer，element/peek，remove/poll中的三个方法均为重复的方法，在选择使用时不免有所疑惑，这里简单区别一下：
     *
     * 1、add()和offer()区别:
     *
     * add()和offer()都是向队列中添加一个元素。一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，调用 add() 方法就会抛出一个 unchecked 异常，
     * 而调用 offer() 方法会返回 false。因此就可以在程序中进行有效的判断！
     *
     *  2、poll()和remove()区别：
     *
     * remove() 和 poll() 方法都是从队列中删除第一个元素。如果队列元素为空，调用remove() 的行为与 Collection 接口的版本相似会抛出异常，
     * 但是新的 poll() 方法在用空集合调用时只是返回 null。因此新的方法更适合容易出现异常条件的情况。
     *
     * 3、element() 和 peek() 区别：
     * element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，
     * 在队列为空时， element() 抛出一个异常，而 peek() 返回 null。
     *
     *
     * 总结而言就是offer,poll,peek() 这三个新接口会在一些本来会报错的情况下返回null或者false.
     * ————————————————
     * 版权声明：本文为CSDN博主「xiaozhegaa」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/xiaozhegaa/article/details/106136105
     * @param nums
     * @return
     */
    //层序遍历还原二叉树 还是层序遍历实现一颗二叉树简单点。
    public static TreeNode createTree(Integer[] nums) {
        TreeNode root = new TreeNode();
        if (nums.length == 0) return root; //如果是空数组直接返回空树

        //其他情况开始建树
        TreeNode temp;
//        Queue<TreeNode> linkedListQueue = new LinkedList<>();

        //LinkedList这个类非常神奇啊，他既可以当普通的列表add/remove,也可以当队列(offer,poll)，
        // 双端队列(offerFirst/Last,pollFirst/Last)，栈push/pop/top来使用
        LinkedList<TreeNode> linkedListQueue = new LinkedList<>();
        root = new TreeNode(nums[0]);

        //开始层序遍历，入队
        linkedListQueue.add(root);
        int i = 1;
        while (i < nums.length) {

            //每次弹出出当前节点。
            temp = linkedListQueue.poll();

            //左孩子入队
            if (null != nums[i] && temp != null) {
                temp.left = new TreeNode(nums[i]);
                linkedListQueue.add(temp.left);
            }
            i++;

            //如果大于数组长度了直接break;
            if (i >= nums.length) {
                break;
            }

            //右孩子入队
            if (null != nums[i] && temp != null) {
                temp.right = new TreeNode(nums[i]);
                linkedListQueue.add(temp.right);
            }
            i++;
        }

        //遍历数组完毕就形成树了。
        return root;
    }

    //获得树深
    public static int getTreeDepth(TreeNode root) {
        //递归左右子树，每调用一次深度 + 1
        return root == null ? 0 : 1 + Math.max(getTreeDepth(root.left) ,getTreeDepth(root.right));
//        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }


    /**
     * 绘二叉树
     *
     * @param currNode
     * @param rowIndex
     * @param columnIndex
     * @param res
     * @param treeDepth
     */
    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);
        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public static void show(TreeNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);
        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }
        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);
        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }


    /**
     * 寻找root中值为value的第一个左子节点。
     * 一般树中的数字不会相同。
     * @param root
     * @param value
     * @return
     */
    public static TreeNode findTreeNode(TreeNode root,int value){
        //递归结束的条件 root.val == value
        if (root != null && root.val == value){
            return root;
        }

        //res节点
        TreeNode res = null;


        //注意不能像前中后序遍历那样无脑直接递归，因为他们只是输出或者添加
        //你是要拿出这个节点，如果是空是不行的。

        //向左遍历，如果左子节点不为空
        if (root.left != null){
            res = findTreeNode(root.left,value);
            if (res != null){
                return res;
            }
        }
        //向右遍历，如果右子节点不为空
        if (root.right != null){
            res = findTreeNode(root.right,value);
            if (res != null){
                return res;
            }
        }
        //说明树中根本不存在value
        return null;
    }
}
