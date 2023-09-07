package 剑指offer第二版.动态规划;

/**
 * @Author KingofTetris
 * @Date 2022/7/13 19:33
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import org.junit.Test;

/**
 * 其实就是找规律。
 * n=1时 ：1
 * n=2 时：(1,1) (2)
 * n=3时：(1,2) (1,1,1) (2,1)
 *  n=4时：(1,1,2) (2,2) (1,2,1) (1,1,1,1) (2,1,1)
 * 发现规律了： n=4中的(1,1,2) (2,2)是从n=2中跳2台阶得到的，(1,2,1) (1,1,1,1) (2,1,1)是从n=3中跳1台阶得到的
 * 所以 n的跳法 = n-1 的跳法都跳一个台阶 + n-2的跳法都跳两个台阶
 * 所以f(n) =f(n-1)+f(n-2) 就是这个DP的状态转移方程
 */
public class 剑指Offer10_II_青蛙跳台阶问题 {
    final int MOD = (int) (1e9 + 7); //1000000007 是最小的十位质数。模1000000007，可以保证值永远在int的范围内。
    @Test
    public void test(){
        System.out.println(numWays(44));
        System.out.println(numWays2(44));
    }
    /**
     * 迭代版动态规划
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n < 2){
            return 1;
        }
        int p = 0,q = 1,r=1; //初始的滚动数组 q 和 r是跳0阶和1阶的跳法数量
        // p是用来给第三个数垫位置的，q,r初始是f(0)和f(1)
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r  = (p + q) % MOD;
        }
        return r;
    }

    /**
     * 带备忘录的，普通的动态规划
     */
    int[] memo = new int[101];
    public int numWays2(int n) {
        if (n < 2){
            return 1;
        }
        memo[0] = 1;
        memo[1] = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (memo[i - 1] + memo[i - 2]) % MOD; //直接去取memo里的数 memo[i]相加也可能溢出，所以也要取余
            memo[i] = res % MOD; //把res存入memo
        }
        return res;
    }
}
