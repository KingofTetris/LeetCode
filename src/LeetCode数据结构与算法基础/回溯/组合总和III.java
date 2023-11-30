package LeetCode数据结构与算法基础.回溯;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */

import java.util.LinkedList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。
 * 组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1: 输入: k = 3, n = 7 输出: [[1,2,4]]
 * 示例 2: 输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
//这题是组合总和里面最简单的，已经使用了k来限定深度
    //而且每个元素不能重复选取，也没有重复的数字。
public class 组合总和III {

    public static void main(String[] args) {
        组合总和III zh = new 组合总和III();
        zh.combineSum(9, 3);
    }

    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();


    public void combineSum(int n, int k) {
        if (n > 9 * k) return; //如果k个9都满足不了n 那么就没必要回溯了，不可能加得到。
        backtracking(n, k, 1);
        System.out.println(res);
    }

    private void backtracking(int n, int k, int startIndex) {
        //终止条件
        int sum = 0;
        for (Integer i : path) {
            sum += i;
        }
        if (sum > n){
            return; //剪枝1：如果sum已经大于n了，那就没必要继续往下DFS了。
        }
        if (sum == n && path.size() == k) {
            //每次添加都是新的path，不然你直接加path
            //相当于一直添加相同地址的数据，只添加了一个path.
            res.add(new LinkedList<>(path));
            return;//别忘了return;
        }

        //这个题如果要剪枝应该怎么做呢?
        //这题有两个地方可以剪枝，一个是sum,一个是集合个数k
        //集合个数k和前一题一样，为了保证集合一定要有K个元素
        //那么我们必须要让 i <= 9-(k-path.size()) + 1
        //再往后的i是凑不齐k个数的
//        for (int i = startIndex; i <= 9; i++) {
        //剪枝2：9 - (k - path.size()) + 1
        //对集合个数有限制的通用公式就是 n - (k-path.size()) + 1;
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }


}
