package LeetCode数据结构入门.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 买卖股票的最佳时机
 * @Time 2021/9/28  13:11
 */

/*121. 买卖股票的最佳时机I 只能选一天买，选一天卖。
        给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

        返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。



        示例 1：

        输入：[7,1,5,3,6,4]
        输出：5
        解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
        注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
        示例 2：

        输入：prices = [7,6,4,3,1]
        输出：0
        解释：在这种情况下, 没有交易完成, 所以最大利润为 0。


        提示：

        1 <= prices.length <= 105
        0 <= prices[i] <= 104*/
public class 买卖股票的最佳时机 {
    int maxProfit = 0 ;

    @Test
    public void test(){
        int[] prices = {7,2,3,4,5};
        System.out.println(maxProfit(prices));
    }

    //暴力法 找出数组间的最大差值就是最大利润
    //但是O(n2) 会超时
    /*public int maxProfit(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                 maxProfit = Math.max(prices[j] - prices[i],maxProfit);
            }
        }
        return maxProfit;
    }*/

    //emmm...你在干嘛。一天买一天卖，你直接O(n) 选出min 和 max相减不就完了呗。。

    //其实我只关心最大值
    //在最低价时买入
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            //只要价格比min还小就买入。
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }
            //只要赚钱就卖掉，要注意的是，这个卖相当于只卖了最高的一次。
            else if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }

}
