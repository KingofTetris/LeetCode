package 剑指offer第二版.动态规划;

/**
 * @Author KingofTetris
 * @Date 2022/9/16 14:33
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer63_股票的最大利润 {

    /**
     * 暴力法 O(n^2)
     * 本题刚好过。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                profit = Math.max(profit,prices[j] - prices[i]);
            }
        }
        return profit;
    }

    /**
     * 动态规划
     * dp[i]代表 前i天的最大利润
     * 因为只能买卖一次，所以
     * 前i日最大利润=max(前(i−1)日最大利润,第i日价格−前i日最低价格) //把前i日最低价格记为cost
     * dp[i] = max(dp[i-1],prices[i] - cost))
     * dp[1] = 0 //第一天利润为0
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices.length < 2) return 0; // 没有卖出的可能性
        // 定义状态，第i天卖出的最大收益
        int[] dp = new int[prices.length];
        dp[0] = 0;  // 初始第一天收益为0
        int cost = prices[0]; // 第一天的成本就是当天的股价
        for (int i = 1; i < prices.length; i++) { //从第二天开始算dp
            //计算最低cost
            cost = Math.min(cost, prices[i]);

            //然后计算dp
            dp[i] = Math.max(dp[i - 1], prices[i] - cost);

        }
        //最后返回dp[n-1]就是最大利润。
        return dp[prices.length - 1];
    }

    public int maxProfit3(int[] prices) {

        //当然这题没必要用数组存储，可以从一维化为常数空间
        int cost = Integer.MAX_VALUE,profit = 0;
        for (int price : prices) {
            cost = Math.min(cost,price);

            profit = Math.max(profit,price - cost);
        }

        return profit;
    }
}
