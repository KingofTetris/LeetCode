package 剑指offer第二版.树;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;

/**
 * @Author KingofTetris
 * @Date 2022/7/28 16:06
 */
public class test {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) &&
                (recur(A, B) || isSubStructure(A.left,B) || isSubStructure(A.right,B));//先去找A中是否存在B的根节点
    }

    public boolean recur(TreeNode A,TreeNode B){ //找到B节点以后
        if (B == null) return true;//B探索完了，说明成功
        if (A == null || A.val != B.val) return false;//A探完了，或者A,B的值不一样则探索失败
        //递归探索左右子树
        return recur(A.left,B.left) && recur(A.right,B.right);
    }

}
