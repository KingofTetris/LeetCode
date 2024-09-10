package 校招笔试真题.网易.网易雷火Web后端20230422;

/**
 * @author by KingOfTetris
 * @date 2024/9/10
 */


import java.util.Scanner;

/**
 * 某游戏中，玩家可以携带K个行囊。在一场副本结束后，会奖励你一些宝石，每个宝石都有自己的价格。给你一个正整数数组price，
 * 其中price[i]代表第i+1个宝石的价格(下标从0 开始)。宝石需要按照以下规则放到行囊里:
 * 1.每个行囊都必须要装宝石
 * 2.如果第 i 个宝石和第 j 个宝石在同一个行囊里，那么下标在 i 到 j之间的所有宝石都必须在这同一个行囊中 i != j
 * <p>
 * 如果一个行囊有下标从i到j的所有宝石，那么这个行囊的价值是price[i] + price[j]。
 * 一个分配方案的分数是所有k个行囊的价值之和。
 * 请你返回所有分配方案中，最大分数与最小分数的差值是多少。
 * <p>
 * 样例
 * 样例一
 * 输入
 * <p>
 * [2,3,5,4],2
 * 输出
 * <p>
 * 4
 */
public class P1204 {

    //要找到最大最小方案的得分 才能求出差值

    /**
     * 问题就在于K个背包，要怎么装？每个包至少要放2个宝石。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        //不能排序，因为要求[i,j]之间的所有珠宝都要放到同一个袋子
        //排序以后顺序就乱了
        int diff = putGems(prices, k);
        System.out.println(diff);
    }


    public static int putGems(int[] price, int k) {
        //如果price长度 < 2 * k
        //是不可能保证每个袋子都有宝石的
        if (price.length < 2 * k) return -1;
        // write code here
        return 0;
    }
}
