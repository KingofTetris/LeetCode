package 剑指offer第二版.动态规划;

/**
 * @Author KingofTetris
 * @Date 2022/9/16 15:07
 */
public class 剑指Offer63_买卖股票的最佳时机II {

    /**
     * 差别在于能多次买卖
     * 最好的办法是贪心。
     * 每天都计算买卖的差价，有赚就卖
     *
     * 假设每天都是上涨那么
     * pn - p1 = (p2 - p1) + (p3 -p2) + (p4- p3) +...+ (pn - pn-1)
     * 这个就是贪心正确的数学基础
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1 ; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }

        return profit;
    }
}
