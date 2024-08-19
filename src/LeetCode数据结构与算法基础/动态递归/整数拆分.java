package LeetCode数据结构与算法基础.动态递归;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */
public class 整数拆分 {
    //其实这题也是在求方案数，把一个正整数数字转化为多个数字正整数之和有多少种方案。
    //返回这些方案中乘积最大的。
    public static void main(String[] args) {
//        int solution = integerBreak(59);
        int solution = integerBreak(60);//60就溢出INT了。
        System.out.println(solution);
    }

    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];//把一个数n拆分成n-x 和 x
        //那么他的乘积就是 x(n-x)  或者  你继续拆分 (n-x) 那么他的乘积就是 x * 拆分(n-x)
        //所以这题的DP公式就出来了
        /**
         * DP[i] = max{j*(i-j),j*dp[i-j]}
         */
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    /*  作者：力扣官方题解
      链接：https://leetcode.cn/problems/integer-break/solutions/352875/zheng-shu-chai-fen-by-leetcode-solution/
      来源：力扣（LeetCode）
      著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public static int solution(int n) {
//        dp[i] 代表 拆分数字能得到的最大乘积
        //乘积 有两种计算公式
        //j * (i-j) j从1-n-1 就是拆成两种数的情况
        // dp[i-j] * j ，相当于把i-j继续拆分，拆成多个数的情况
        //那么我们为什么不继续拆分j呢？？

        //这题动归其实不太好理解。其实是道数学题
        //定义函数 f(x)表示将给定的正整数 n 拆分成尽可能多的正数 x 的情况下的最大乘积
        //那么n就可以分成 n /x 项，此时f(x) = x ^ (n/x) = e ^ (n * ln x / x)
        //我们的目标是求f(x)的最大值
        //那么令g(t) = e^t h(x) = (ln x / x)
        //f(x) = g(n * h(x))
        //因为g(t)是单调递增的，n > 0,所以h(x)和f(x)的单调性相同。

        //那么我们计算h(x)的单调性，可以得到
        //h(x)的极大值点是 x = e .
        //那么fx的极大值点也就是 x= e
        //因为e不是整数，使用与e最接近的2或者3作为代替，
        //就变成了f(2)和f(3)谁比较大。
        //带进去计算可得 f(3) > f(2)
        //所以结论就是我们要得到最大的乘积，就要尽可能地把数字拆出更多的 3

        //那么这题就很简单了。
        if (n <= 3) { //2,3只有一种拆分 1*1 1*2
            return n - 1;
        }
        //其他情况
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) { //如果余数是1,说明剩下4,4不要再拆分成3*1。拆成2*2更大，或者就直接*4
            return (int) Math.pow(3, quotient - 1) * 4;
        } else { //如果余数是2 那么直接乘以2
            return (int) Math.pow(3, quotient) * 2;
        }

      /*  作者：力扣官方题解
        链接：https://leetcode.cn/problems/integer-break/solutions/352875/zheng-shu-chai-fen-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
