package LeetCode数据结构与算法基础.day5.树;

import LeetCode数据结构与算法基础.day5.树.TreeNode;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2022/9/5 9:28
 */
public class 寻找重复子树_2022_09_05 {


    /**
     * 既然两个树的结构完全一致（包含其数据），
     * 那么我们按照某种统一的规则将一棵树的数据取出来然后形成一个序列之类的东西，
     * 这个东西也应该一样。这时候我们很容易想到dfs+map的思想。
     *
     * 作者：phc_seu
     * 链接：https://leetcode.cn/problems/find-duplicate-subtrees/solution/er-cha-shu-de-xu-lie-hua-by-phc_seu-0g94/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    Map<String, TreeNode> seen = new HashMap<>();

    /**
     * 我们需要使用一个哈希映射 seen 存储序列到子树的映射。
     * 如果在计算序列时，通过 seen 查找到了已经出现过的序列，
     * 那么就可以把对应的子树放到哈希集合 repeat 中，这样就可以保证同一类的重复子树只会被存储在答案中一次。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/find-duplicate-subtrees/solution/xun-zhao-zhong-fu-de-zi-shu-by-leetcode-zoncw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    Set<TreeNode> repeat = new HashSet<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            dfs(root);
            return new ArrayList<>(repeat);//最后返回重复树结构即可
        }

    /**
     * 先序遍历 序列化
     * 使用递归的方法进行序列化。我们可以将一棵以 x 为根节点值的子树序列化为：
     * x(左子树的序列化结果)(右子树的序列化结果)
     * 左右子树分别递归地进行序列化。如果子树为空，那么序列化结果为空串。
     * 每下一层就嵌套一个(。然后回溯加一个)，用()表示空节点
     * 如例1的序列化 1(2(4()())())(3(2(4()())())(4()()))  例1：https://leetcode.cn/problems/find-duplicate-subtrees/
     * @param node
     * @return
     */
    public String dfs(TreeNode node) {
            if (node == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(node.val);
            sb.append("(");
            sb.append(dfs(node.left));
            sb.append(")(");
            sb.append(dfs(node.right));
            sb.append(")");
            String serial = sb.toString();

        /**
         * 如果Map里面已经存在该序列，就把value也就是根节点拿出来
         */
        if (seen.containsKey(serial)) {
                repeat.add(seen.get(serial));
            } else {
                //不存在就放入序列-根节点
                seen.put(serial, node);
            }
            return serial;
        }
}
