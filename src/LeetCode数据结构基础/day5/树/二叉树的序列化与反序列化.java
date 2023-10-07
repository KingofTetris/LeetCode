package LeetCode数据结构基础.day5.树;

import LeetCode数据结构基础.day5.树.TreeUtils;

import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File 二叉树的序列化与反序列化
 * @Time 2021/10/27  11:35
 */
public class 二叉树的序列化与反序列化 {

    String SEP = ",";     // 代表分隔符
    String NULL = "#";    // 代表 null 空指针

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    // 辅助函数，将二叉树存入 StringBuilder
    public void serialize(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        // 前序遍历位置
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 将字符串转化为列表
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    // 辅助函数，通过 nodes 列表构造二叉树
    public TreeNode deserialize(LinkedList<String> nodes) {
        // base case
        if (nodes.isEmpty()) {
            return null;
        }
        // 前序遍历位置
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        // 递归遍历
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));
}
