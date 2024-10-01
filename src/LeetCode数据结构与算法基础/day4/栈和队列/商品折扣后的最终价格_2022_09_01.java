package LeetCode数据结构与算法基础.day4.栈和队列;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author KingofTetris
 * @Date 2022/9/1 9:18
 * 给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
 * 商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，
 * 其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。
 * 请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
 * 示例 1：
 * 输入：prices = [8,4,6,2,3]
 * 输出：[4,2,4,2,3]
 * 解释：
 * 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
 * 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
 * 商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
 * 商品 3 和 4 都没有折扣。
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：[1,2,3,4,5]
 * 解释：在这个例子中，所有商品都没有折扣。
 * 示例 3：
 * 输入：prices = [10,1,1,6]
 * 输出：[9,0,1,6]
 * 提示：
 * 1 <= prices.length <= 500
 * 1 <= prices[i] <= 10^3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 商品折扣后的最终价格_2022_09_01 {

    @Test
    public void test(){
     int[] prices = new int[]{10,1,1,6};
        int[] ints = finalPrices2(prices);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + "\t");
        }
    }

    /**
     * 暴力模拟 O(n^2) 空间O(1)
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
//        int[] finalPrices = new int[prices.length];
        int[] finalPrices = prices; //原地改造不需要额外空间。
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]){
                    finalPrices[i] = prices[i] - prices[j];
                    break;//找到最小的就更新，然后跳出
                }
            }
        }
        return finalPrices;
    }

    /**
     * 单调栈 O(n) 空间O(n)
     * @param prices
     * @return
     */
    public int[] finalPrices2(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();//栈一般用ArrayDeque实现
        for (int i = n - 1; i >= 0; i--) { //从后面开始遍历
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return ans;
    }
}
