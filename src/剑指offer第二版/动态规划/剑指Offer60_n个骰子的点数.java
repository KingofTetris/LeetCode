package 剑指offer第二版.动态规划;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/15 14:54
 */


//把n个骰子仍在地上，所有骰子朝上一面的点数之和为s.输入n,打印出s的所有可能的值出现的概率。（骰子有六个面）
public class 剑指Offer60_n个骰子的点数 {

    @Test
    public void test() {

        //给你n个骰子，投出去以后这n个骰子和为
        int n = 6;
        double[] res = dicesProbability(n);
        for (int i = 0; i < res.length; i++) {
            System.out.println("res = " + (i + n) + "的概率是" + res[i]);
        }
    }


    /**
     * 暴力法就是列举出所有的可能，然后计算各种可能的概率。
     * 时间复杂度是O(6^n) 指数级复杂度，非常的可怕
     * 显然是不可能用暴力法的。
     * @param n
     * @return
     */

    /**
     * DP
     * n 个骰子「点数和」的范围为 [n, 6n] ，数量为 6n−n+1=5n+1 种。
     * 定义dp[i][j]为i个骰子，和为j的组合有多少种。
     * 那么概率就是dp[i][j] / 6^n
     * <p>
     * //i-1个骰子，和为j-1到j-6,那么再来一个骰子投出1-6凑出dp[i][j]
     * <p>
     * 递推关系 dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] +...+ dp[i-1][j-6]
     * 意思就是拿一个骰子出来，他的可能有1-6 6种情况。把这6种加起来
     * <p>
     * 有了递推关系，然后是初始值
     * dp[1][1] = dp[1][2] = ...= dp[1][6] = 1;都是一种
     *
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        //前面是n个骰子 后面是n个骰子之和的可能。 n - 6n //+1是为了舍去0不用 方便
        int[][] dp = new int[n + 1][6 * n + 1];

        //赋上初始值
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        //根据公式计算dp数组
        // dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] +...+ dp[i-1][j-6]
        for (int i = 2; i <= n; i++) { //从2个骰子到n个骰子
            for (int j = i; j <= 6 * i; j++) { //n个骰子之和 范围就是 n - 6n
                // 注意j是从i开始 不是1 所以终点也就是6i
                for (int k = 1; k <= 6; k++) {
                    if (j < k) break;
                    //要注意 j < k 会发生数组越界，而且 j < k的情况是不可能发生的 比如 2 - 3 = -1
                    // 所以我们把 j < k 直接break掉
                    //累加
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        double[] res = new double[5 * n + 1];//6n - n ,5n + 1种情况
        double sum = Math.pow(6, n); //总数6^n
        int index = 0;

        //最后一行 / 6^n 就是概率。
        for (int i = n; i <= 6 * n; i++) {
            res[index++] = dp[n][i] / sum;//把n个骰子，结果为n - 6n的情况的组合数 / sum 就是概率
        }

        return res;
    }
}
