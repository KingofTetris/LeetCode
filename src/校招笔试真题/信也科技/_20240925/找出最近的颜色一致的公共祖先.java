package 校招笔试真题.信也科技._20240925;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/25
 */

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    String color;

    public TreeNode() {
    }

    public TreeNode(int val, String color) {
        this.val = val;
        this.color = color;
    }
}

// 60%
public class 找出最近的颜色一致的公共祖先 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        TreeNode root = buildTree(s);
        int x = sc.nextInt();
        int y = sc.nextInt();
        TreeNode p = search(root, x);
        TreeNode q = search(root, y);
        String color1 = p.color;
        String color2 = q.color;
        //两个颜色不一致的节点不可能有公共祖先
        if (!color1.equals(color2)){
            System.out.println("未查询到公共祖先节点");
            return;
        }
        TreeNode res = colorLCA(root, p, q, color1);
        if (res != null)
            System.out.println(res.val + "-" + res.color);
        else
            System.out.println("未查询到公共祖先节点");
    }

    private static TreeNode colorLCA(TreeNode root, TreeNode p, TreeNode q, String color) {
        //这里是思考难点。定义规则：比如在某一棵子树上先找到了p，
        // 则无需继续遍历这棵子树，因为即使这棵子树有q，p也一定是q的祖先，
        // 也就是它们两个的最近公共祖先。

        if (root == null)
            return null;

        if ((root == q || root == p) && root.color.equals(color))
            return root;

        //在左子树找p,q 实际上找到的是较上层的那个结点，下层的结点不会递归到
        //找到上层结点后，return它 下面这个递归就结束了，然后去右子树里找。
        TreeNode left = colorLCA(root.left, p, q, color);

        //在右子树找p,q
        TreeNode right = colorLCA(root.right, p, q, color);

        //如果在左右子树里找到了p,q 那root就是LCA
        if (left != null && right != null && root.color.equals(color))
            return root;
            //p,q在左子树中
        else if (left != null && right == null && left.color.equals(color)) {
            return left;
        }

        //p,q在右子树中
        else if (right != null && left == null && right.color.equals(color)) {
            return right;
        }
        //左右子树都找不到p,q 即left right都为空 或者 root节点颜色不对。
        else {
            return null;
        }
    }

    public static TreeNode buildTree(String str) {
        String[] nodes = str.split(",");
        List<TreeNode> levelNodes = new ArrayList<>();
        int index = 0;
        for (String node : nodes) {
            String[] parts = node.split("-");
            int value = Integer.parseInt(parts[0]);
            String color = parts[1];
            levelNodes.add(new TreeNode(value, color));
        }

        return buildTreeHelper(levelNodes, 0);
    }

    private static TreeNode buildTreeHelper(List<TreeNode> nodes, int index) {
        if (index >= nodes.size()) {
            return null;
        }
        TreeNode node = nodes.get(index);
        node.left = buildTreeHelper(nodes, 2 * index + 1);
        node.right = buildTreeHelper(nodes, 2 * index + 2);
        return node;
    }

    public static TreeNode search(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.val == value) {
            return root;
        }

        TreeNode leftResult = search(root.left, value);
        if (leftResult != null) {
            return leftResult;
        }

        return search(root.right, value);
    }
}



