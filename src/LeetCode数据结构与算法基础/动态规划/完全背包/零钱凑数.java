package LeetCode数据结构与算法基础.动态规划.完全背包;

/**
 * @author by KingOfTetris
 * @date 2024/9/9
 */

import java.util.*;

/**
 * 现在有 M 种不同面值的硬币（硬币个数数目无限），如何用这些硬币组合为总数额为 N 的钱，
 * 同时满足使用硬币的个数最少？
 * <p>
 * ## 输入格式
 * <p>
 * 第一行为硬币面值正整数数组 （无序）
 * <p>
 * 第二行为目标总数额，正整数
 * <p>
 * ## 输出格式
 * <p>
 * 总面值等于目标总数额的，且长度最小的硬币组合数组，结果不要求排序。如果不存在解则返回空数组
 * <p>
 * ## 输入样例
 * <p>
 * ```
 * [1,2,5]
 * 18
 * ```
 * <p>
 * ## 输出样例
 * <p>
 * ```
 * [5,5,5,2,1]
 * ```
 */
public class 零钱凑数 {


    public static void main(String[] args) {
        //才3821就OOM了。这就是暴力回溯。
        //Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
        int[] coins = {4, 8, 3, 5};
        //搜个44都要半天，
        int total = 3;
        List<Integer> solution = solution(coins, total);
//        System.out.println(solution);
//        List<Integer> res1 = List.of(5, 5, 5, 2, 1);
//        System.out.println(solution.equals(res1));
    }

    static List<List<Integer>> res = new LinkedList<>();
    static List<Integer> path = new LinkedList<>();

    //可以无限使用，那么其实就不需要used数组。
    public static List<Integer> solution(int[] coins, int total) {
        //因为要记录具体的方案，DP就不太管用了， 需要回溯
        //主要先排序，保证从小到大，才能找到所有的组合
        Arrays.sort(coins);
        backTracking(coins, total, 0, 0);
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).size() < min) {
                System.out.println(res.get(i));//看一下有多少种方案
                min = res.get(i).size();
                index = i;
            }
        }
        //如果凑不成返回空列表
        if (index == -1) return new LinkedList<>();
        //非要排序恶心人
        res.get(index).sort(((o1, o2) -> o2 - o1));
        return res.get(index);
    }

    private static void backTracking(int[] coins, int total, int sum, int startIndex) {
        if (sum == total) {
            res.add(new LinkedList<>(path));
        }
        //已经大于就没必要再加了。
        if (sum > total) {
            return;
        }
        for (int i = startIndex; i < coins.length; i++) {
            sum += coins[i];
            path.add(coins[i]);
            backTracking(coins, total, sum, startIndex);
            path.remove(path.size() - 1);
            sum -= coins[i];
        }
    }


}
