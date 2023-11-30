package LeetCode数据结构与算法基础.day5.树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class N叉树的层序遍历 {
    @Test
    public void test(){

    }

    public List<List<Integer>> levelNNode(N_Node root){
        List<List<N_Node>> res = new LinkedList<>();
        List<List<Integer>> resInteger = new LinkedList<>();
        Queue<N_Node> q = new LinkedList<>();
        if (root == null) return resInteger;
        q.offer(root);

        while (!q.isEmpty()){
            List<N_Node> level = new LinkedList<>();
            int curSize = q.size();
            for (int i = 0; i < curSize; i++) {
                N_Node node = q.poll();
                level.add(node);
                for (N_Node child : node.children) {
                    if (child != null){
                        q.add(child);
                    }
                }
            }
            res.add(level);
        }
        for(List<N_Node> list : res){
            List<Integer> Integers = new LinkedList<>();
            for(N_Node node : list){
                Integers.add(node.val);
            }
            resInteger.add(Integers);
        }
        return resInteger;
    }
}
class N_Node {
    public int val;
    public List<N_Node> children;
    public N_Node() {}
    public N_Node(int _val) {
        val = _val;
    }
    public N_Node(int _val, List<N_Node> _children) {
        val = _val;
        children = _children;
    }
};
