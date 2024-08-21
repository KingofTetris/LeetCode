package LeetCode数据结构与算法基础.动态递归._01背包;

class 目标和_DP版本 {


    //求组合数，方案数
    //回溯DP都可以。你能用哪种就哪种
    //像这题的DP可不是那么好想


    /**
     *
     * 假设加法的总和为x，那么减法对应的总和就是sum - x。
     *
     * 所以我们要求的是 x - (sum - x) = target
     *
     * x = (target + sum) / 2
     *
     * 此时问题就转化为，装满容量为x的背包，有几种方法。
     *
     * 这里的x，就是bagSize，也就是我们后面要求的背包容量。
     *
     * 大家看到(target + sum) / 2 应该担心计算的过程中向下取整有没有影响。
     *
     * 这么担心就对了，例如sum是5，target是2 的话
     *
     * 那么其实 x 应该 = 3.5，但是 /2 直接向下取整了
     *
     * 实际上，都是整数是没有 target = 3.5 这种方案的，因此如果 % 2 != 0 可以直接返回false
     *
     * @param nums
     * @param target
     * @return
     */

    /**
     * 这次和之前遇到的01背包问题不一样了，之前都是求容量为j的背包，最多能装多少。
     *
     * 本题则是装满有几种方法。其实这就是一个组合问题了。
     *
     * dp的含义，递推公式和初始值就不一样了
     *
     * dp[j] 表示：填满j（包括j）这么大容积的包，有dp[j]种方法
     *
     * 然后是递推公式
     *
     * 有哪些来源可以推出dp[j]呢？
     *
     * 只要搞到nums[i]，凑成dp[j]就有dp[j - nums[i]] 种方法。
     *
     * 例如：dp[j]，j 为5，
     *
     * 已经有一个1（nums[i]） 的话，有 dp[4]种方法 凑成 容量为5的背包。
     * 已经有一个2（nums[i]） 的话，有 dp[3]种方法 凑成 容量为5的背包。
     * 已经有一个3（nums[i]） 的话，有 dp[2]种方法 凑成 容量为5的背包
     * 已经有一个4（nums[i]） 的话，有 dp[1]种方法 凑成 容量为5的背包
     * 已经有一个5 （nums[i]）的话，有 dp[0]种方法 凑成 容量为5的背包
     * 那么凑整dp[5]有多少方法呢，也就是把 所有的 dp[j - nums[i]] 累加起来。
     *
     * 所以求组合类问题的公式，都是类似这种：
     *
     * 本题还是有点难度，大家也可以记住，在求装满背包有几种方法的情况下，递推公式一般为：
     *
     *
     * 下面这个公式真的非常他妈的重要，一定要记住
     *
     * ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐
     * ⭐⭐⭐⭐  dp[j] += dp[j - nums[i]] ⭐⭐⭐⭐⭐
     * ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐ ⭐⭐⭐⭐⭐
     *
     * 上面这个公式非常重要，基本上DP求组合数排列数都是他。
     *
     *
     * 从递推公式可以看出，在初始化的时候dp[0] 一定要初始化为1，
     * 因为dp[0]是在公式中一切递推结果的起源，如果dp[0]是0的话，递推结果将都是0。
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) sum += nums[i];

            //如果target的绝对值大于sum，那么是没有方案的
            if (Math.abs(target) > sum) return 0;
            //如果(target+sum)除以2的余数不为0，也是没有方案的
            if ((target + sum) % 2 == 1) return 0;

            //很关键的一步是先求出背包大小是多少
            int bagSize = (target + sum) / 2;
            int[] dp = new int[bagSize + 1];
            dp[0] = 1;

            //遍历物品
            for (int i = 0; i < nums.length; i++) {
                //遍历背包，倒序遍历 保证不重复拿物品
                for (int j = bagSize; j >= nums[i]; j--) {
                    dp[j] += dp[j - nums[i]];
                }
            }

            return dp[bagSize];
    }

}
