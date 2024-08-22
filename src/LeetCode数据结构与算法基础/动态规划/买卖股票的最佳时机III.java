package LeetCode数据结构与算法基础.动态规划;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/19
 */
public class 买卖股票的最佳时机III {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = solution(nums);
        System.out.println(res);
    }

    private static int solution(int[] prices) {
        //对一个股票可以有两次操作，买入卖出。
        //同一天，你只能买一只股票，或者卖一只股票。
        //也就是说，比如 1 2 8 这种股票
        //你不能第一天买入1，第二天买入2，然后第三天一起卖掉赚13
        //只能第一天买，第三天卖。赚7
        //或者你第2天卖，第三天卖，赚6

        /**
         * 代码随想录DP方式，两次买卖
         * 1.首先 我们定义dp数组
         *
         * 确定dp数组以及下标的含义
         * 一天一共就有五个状态，
         *
         * dp[i][0]没有操作 （其实我们也可以不设置这个状态）
         * dp[i][1]第一次持有股票
         * dp[i][2]第一次不持有股票
         * dp[i][3]第二次持有股票
         * dp[i][4]第二次不持有股票
         * dp[i][j]中 i表示第i天，j为 [0 - 4] 五个状态，dp[i][j]表示第i天状态j所剩最大现金。
         *
         *
         */

        /**
         * 2.确定递推公式
         * 达到dp[i][1]状态，有两个具体操作：
         *
         * 昨天没买入股票，那么今天去买： dp[i-1][0](当前手里有的钱) - prices[i]
         * 昨天已经持有，延续昨天的持有状态：  dp[i-1][1]
         *
         * 那么去选最大的 dp[i][1] = max(dp[i-1][0] - prices[i], dp[i - 1][1]);
         *
         *  dp[i][2] 表示第一次不持有
         *
         * 昨天持有，但是我今天卖掉，保存不持有状态
         * dp[i][2] = dp[i - 1][1] + prices[i]  注意卖掉是 + 当前price
         *
         * 延续昨天不持有的状态  dp[i][2] = dp[i - 1][2]
         * 还是取最大 dp[i][2] = max(dp[i - 1][1] + prices[i], dp[i - 1][2])
         *
         * 同理可推出剩下状态部分：
         *
         * dp[i][3] = max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
         *
         * dp[i][4] = max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
         */

        /**
         * dp数组如何初始化
         * 第0天没有操作，这个最容易想到，就是0，即：dp[0][0] = 0;
         *
         * 第0天做第一次买入的操作，dp[0][1] = -prices[0];
         *
         * 第0天做第一次卖出的操作，这个初始值应该是多少呢？
         *
         * 此时还没有买入，怎么就卖出呢？ 其实大家可以理解当天买入，当天卖出，所以dp[0][2] = 0;
         *
         * 第0天第二次买入操作，初始值应该是多少呢？应该不少同学疑惑，第一次还没买入呢，怎么初始化第二次买入呢？
         *
         * 第二次买入依赖于第一次卖出的状态，其实相当于第0天第一次买入了，第一次卖出了，然后再买入一次（第二次买入），那么现在手头上没有现金，只要买入，现金就做相应的减少。
         *
         * 所以第二次买入操作，初始化为：dp[0][3] = -prices[0];
         *
         * 同理第二次卖出初始化dp[0][4] = 0;
         */

        /**
         * 确定遍历顺序
         * 从递归公式其实已经可以看出，一定是从前向后遍历，因为dp[i]，依靠dp[i - 1]的数值。
         *
         * 举例推导dp数组
         * 以输入[1,2,3,4,5]为例
         */

        int len = prices.length;
        // 边界判断, 题目中 length >= 1, 所以可省去
        if (prices.length == 0) return 0;

        /*
         * 定义 5 种状态:
         * 0: 没有操作, 1: 第一次买入, 2: 第一次卖出, 3: 第二次买入, 4: 第二次卖出
         */
        int[][] dp = new int[len][5];
        dp[0][1] = -prices[0];
        // 初始化第二次买入的状态是确保 最后结果是最多两次买卖的最大利润
        //当天买当天卖，那么他的买入价格还是 -price[0]
        dp[0][3] = -prices[0];


        for (int i = 1; i < len; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[len - 1][4];
    }
}
