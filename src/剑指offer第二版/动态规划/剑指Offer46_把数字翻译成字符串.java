package 剑指offer第二版.动态规划;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/2 9:34
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0
 * 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 提示：
 * 0 <= num < 2^31
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 * 他只是问你有多少种方案，没问你具体的方案就没必要回溯。
 */
public class 剑指Offer46_把数字翻译成字符串 {

    /**
     * 26个字母顺序对应0-25.返回把数字翻译成字符串有多少种歧义
     * 其实本质也是一种青蛙跳台阶问题。
     * 青蛙从字符串外面出发，每次只能跳一步或者两步。并且跳两步后，组成的数字不能大于25,也不能包含前导0
     * @param num
     * @return
     */

    /**
     * 最后两位如果可以被合起来翻译，也就是落在 10-25之间。
     * 那么有两种选择，要那么合起来翻译，要么不能合起来翻译，也就是分开翻译
     * 也就是 f(i-2) 和 f(i-1)之和
     * 如果后两位直接就不能合起来，那就只有 f(i-1)一种
     * https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/solution/mian-shi-ti-46-ba-shu-zi-fan-yi-cheng-zi-fu-chua-6/
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        if (len <= 1) return 1; //小于1的直接返回1 也预防长度为0，下面的DP就没有DP[1]。

        //大于1的才去DP
        int[] dp = new int[len + 1];
        //初始状态，无数字和一个数字都是1种翻译
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < len + 1; i++) {
            String tmp = s.substring(i - 2,i);//substring左闭右开 每次取下后两位判断是否落在10-25内
            //字符串比较方法，compareTo，学到一招。
            //递推公式:dp[i] = dp[i - 1] + dp[i - 2] 如果这两位数字 >= 10 ,<= 25
            //如果在这个范围外面，也就是01,02..26,27..这种情况，就不符合
            //那么就只能一个一个翻译，那么dp[i] = dp[i - 1]
            dp[i] = tmp.compareTo("10") >= 0
                    && tmp.compareTo("25") <= 0 ? dp[i - 1] + dp[i - 2] : dp[i - 1];
        }

        return dp[len];//最后返回dp[end]即可
    }

    @Test
    public void test(){
        int num = 2311236;
        System.out.println(translateNum(num));
    }
}
