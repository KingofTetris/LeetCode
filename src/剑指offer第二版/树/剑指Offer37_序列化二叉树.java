package 剑指offer第二版.树;

import LeetCode数据结构入门.day5.树.TreeUtils;
import LeetCode数据结构入门.day5.树.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/8/30 10:34
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 /反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，
 * 详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 示例：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer37_序列化二叉树 {

    @Test
    public void test(){
        Integer[] integers = new Integer[]{1,2,3,null,null,4,5};
        TreeNode tree = TreeUtils.createTree(integers);
        serialize(tree);
    }
    // Encodes a tree to a single string.

    /**
     * 用一种遍历确定树的结构，然后根据这种遍历返回树
     * 这里选择先序遍历 根->左->右
     * @param root
     * @return
     */

    public String serialize(TreeNode root) {
        return rserialize(root,"");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        List<String> dataList = new LinkedList<>();
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
            dataList.add(split[i]);
        }
        return rdeserialize(dataList);
    }
    /**
     * 用逗号分隔
     * @param root
     * @param str
     * @return
     */
    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {

            /**
             * 先序遍历，根左右
             */
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")) { //每次把列表头部的None给删除，返回null
            dataList.remove(0);
            return null;
        }
        /**
         * 同样根据先序遍历的顺序来还原
         */
        //根
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));//非空就构造root
        dataList.remove(0);//还是同样每次构造完，删除掉列表头部
        //左子树
        root.left = rdeserialize(dataList);
        //右子树
        root.right = rdeserialize(dataList);
        return root;
    }


}
