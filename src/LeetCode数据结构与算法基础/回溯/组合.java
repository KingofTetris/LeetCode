package LeetCode数据结构与算法基础.回溯;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */


import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例: 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 */
public class 组合 {

    //回溯一般需要一个路径集合和结果集合
    static List<List<Integer>> result = new LinkedList<>();
    static List<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        //10个里面取5个组合数，要注意组合数中，他们的顺序是无所谓的
        //所以其他就是从1-6开头就结束了，从7开头[7,8,9,10]
        // 已经凑不齐5个数了 就结束了
        List<List<Integer>> combine = combine(10, 5);
        for (List<Integer> list : combine) {
            System.out.println(list);
        }
    }

    //回溯模板
    public static List<List<Integer>> combine(int n ,int k){
        //写好backtracking(确定参数,起始位置)
        backtracking(n,k,1);
        return result;
    }

    private static void backtracking(int n, int k, int startIndex) {
        /**
         *  if (终止条件) {
         *         存放结果;
         *         return;
         *     }
         */
        if (path.size() == k){
            //要注意这里要new一个List保存，不然path到最后全部弹空了，都是[]
            result.add(new LinkedList<>(path));
            return;//结束 这个非常重要不要忘记return
        }
        //这个path是反复使用的。
        //这个代码还能继续优化，比如上面说到的第一位是7的时候就没必要回溯了。
        //回溯其实就是 树的DFS过程。
        //我们可以根据条件来剪枝
        /**
         * for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
         *         处理节点;
         *         backtracking(路径，选择列表); // 递归
         *         回溯，撤销处理结果
         *     }
         */
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backtracking(n,k,i+1);
            path.remove(path.size() - 1);//回溯就去掉刚刚添加的。
        }
    }

    //剪枝优化
    private static void backtrackingCut(int n, int k, int startIndex) {
        //满足k层的条件，加入result
        if (path.size() == k){
            result.add(new LinkedList<>(path));
            return;//结束
        }
        //https://www.bilibili.com/video/BV1wi4y157er/?spm_id_from=333.788&vd_source=299caa32bd4dc5f5ad17129611289250
        /**
         * 1.已经选择的元素个数：path.size();
         *
         * 2.所需需要的元素个数为: k - path.size();
         *
         * 3.列表中剩余元素（n-i） >= 所需需要的元素个数(k - path.size())
         *
         * 4.在集合n中至多要从该起始位置 : i <= n - (k - path.size()) + 1，开始遍历
         *
         * 为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。
         *
         * 举个例子，n = 4，k = 3， 目前已经选取的元素为0（path.size为0），
         *
         * n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2。
         *
         * 从2开始搜索都是合理的，可以是组合[2, 3, 4]。
         *
         * 这里大家想不通的话，建议也举一个例子，就知道是不是要+1了。
         */
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtrackingCut(n,k,i+1);
            path.remove(path.size() - 1);//回溯就去掉刚刚添加的。
        }
    }

    //普通暴力法，当k越来越大的时候，你要嵌套的层数就越来越多。
    //那就根本写出来了，所以我们需要回溯法。
    private static void extracted() {
        int n = 100;
        int k = 3;
        //如果是暴力写100，3的组合，那么需要嵌套3层循环如下
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int u = j + 1; u <= n; u++) {
                   System.out.print(i);
                   System.out.print(j);
                   System.out.print(u);
                   System.out.println();
                }
            }
        }
    }
}
