package 每日一题;

import org.junit.Test;

import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2023/7/12
 */
public class 最大子序列交替和_20230711 {

    //一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
    //找出一个数组最大的子序列交替和


    /**
     * 这题和122买卖股票的最佳时机II 相比其实是一样的，偶数之和 - 奇数之和 其实就是相邻元素相减。
     * <p>
     * 区别在于，股票那里我们手上已经有一支股票了，而这里还没有，我们可以人为在数组前面加个0，持有0
     *
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int[] prices = new int[n + 1];
        for (int i = 0; i < n; ++i)
            prices[i + 1] = nums[i];

        long maxProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            int profit = prices[i] - prices[i - 1];
            if (profit > 0) maxProfit += profit;
        }
        return maxProfit;
    }

    /**
     * 普通思路，最大/最长/最小...一般都是动态规划DP
     * 换个问法，求偶数子序列和奇数子序列之和中的最大值。
     */
    public long maxAlternatingSum2(int[] nums) {
        //要注意Long.MIN_VALUE 表示的是 0x8000000000000000L
        //计算机存储负数使用二进制补码，他实际上的大小是符号位取反 + 1
        long max_even = Integer.MIN_VALUE;
        long max_odd = Integer.MIN_VALUE;
        int length = nums.length;
        long preMaxEven = Integer.MIN_VALUE;
        long preMaxOdd = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            if ((i & 1) != 1) {
                preMaxEven = Math.max(preMaxEven + nums[i], nums[i]);
                max_even = Math.max(preMaxEven, max_even);
            }
            if ((i & 1) == 1) {
                preMaxOdd = Math.max(preMaxOdd + nums[i], nums[i]);
                max_odd = Math.max(preMaxOdd, max_odd);
            }
        }
        System.out.println(max_even + "\t" + max_odd);
        return Math.max(max_even, max_odd);
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            extracted();
            System.out.println();
        }
    }

    private void extracted() {
        int[] nums = new int[new Random().nextInt(1, 10)];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = new Random().nextInt(-10, 10);
            System.out.print(nums[i] + "\t");
        }
        System.out.println();
        long res = maxAlternatingSum2(nums);
        System.out.println(res);
    }
}
