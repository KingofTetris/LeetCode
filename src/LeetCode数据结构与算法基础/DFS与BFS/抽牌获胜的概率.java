package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.HashMap;
import java.util.Map;

public class 抽牌获胜的概率 {
    //牌堆有无限多张点数均匀分布在1-13间的牌，你可以抽取任意张。你的初始点数是0，
    // 当你手中牌点数之和位于[21,24)时获胜，否则失败。求你获胜的概率。

    //采用递归的方式计算在当前点数和的情况下获胜的概率。
    // 我们使用一个字典memo来记录已经计算过的点数和对应的概率，
    // 避免重复计算。最后，输出在点数和为0的情况下获胜的概率。

    static double winProbability(int sumPoint, Map<Integer, Double> memo) {
        if (sumPoint >= 21 && sumPoint < 24) {
            return 1;
        }
        if (sumPoint >= 24) {
            return 0;
        }
        if (memo.containsKey(sumPoint)) {
            return memo.get(sumPoint);
        }

        double winProb = 0;
        for (int i = 1; i <= 13; i++) {
            //每次牌都是1 / 13
            winProb += 1.0 / 13 * winProbability(sumPoint + i, memo);
        }

        memo.put(sumPoint, winProb);
        return winProb;
    }

    public static void main(String[] args) {
        Map<Integer, Double> memo = new HashMap<>();
        double result = winProbability(0, memo);
        System.out.println(result);
    }
}
