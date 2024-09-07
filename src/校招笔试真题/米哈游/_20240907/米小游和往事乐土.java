package 校招笔试真题.米哈游._20240907;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 米小游和往事乐土 {


    /**
     * 规模很小，考虑回溯
     * @param args
     */

    static int n, m;
    static int[] c, cnt;
    static int[][] a, b;


    // 主函数
    public static void main(String[] args) {
        input();
        System.out.println(dfs(0));
    }


    public static void input() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        c = new int[m + 1];
        for (int i = 1; i <= m; ++i) {
            c[i] = sc.nextInt();
        }
        a = new int[n][3];
        b = new int[n][3];
        int t = n;
        int p = 0;
        while (t-- > 0){
            for (int i = 0; i < 3; ++i) {
                a[p][i] = sc.nextInt();
            }
            for (int i = 0; i < 3; i++) {
                b[p][i] = sc.nextInt();
            }
            p++;
        }
        cnt = new int[m + 1];
    }


    /**
     * 这么简单，居然是回溯，你在贪心模拟牛魔。唉。
     * TODO 主要还是你没总结，根本不知道这个题目该用DFS去回溯。
     * 只想着求组合求方案求排列才用回溯
     *
     * 其实这种规模比较小的，一关一关，一步一步的都可以用DFS
     * 探索所有可能。
     *
     * @param u
     * @return
     */
    // 深度优先搜索函数
    public static long dfs(int u) {
        //如果闯关完毕，return
        if (u >= n) {
            return 0;
        }
        long ans = 0;
        for (int i = 0; i < 3; ++i) {
            int index = b[u][i]; //遍历英雄
            cnt[index]++; //套装+1
            long t = a[u][i]; //遍历物品
            if (cnt[index] == 3) {
                t += c[index];
            }
            //每次记录最大的ans,关卡+1，加上当前能力值
            ans = Math.max(ans, dfs(u + 1) + t);
            //每次把index 回溯
            cnt[index]--; // 回溯
        }
        return ans;
    }


}
