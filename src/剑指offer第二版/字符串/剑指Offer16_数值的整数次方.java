package 剑指offer第二版.字符串;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2022/7/18
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^-2 = (1/2)^2 = 1/4 = 0.25
 *  
 * <p>
 * 提示：
 * <p>
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer16_数值的整数次方 {

    @Test
    public void test() {
        System.out.println(myPow(2, 12));
    }

    /**
     * 普通的循环
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double product = 1;
        if (n == 0) return 1;
        if (x == 1) return 1; //1也比较特殊，直接return 1 不用算了。
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                product = product * x;
            }
        }
        if (n < 0) {
            x = 1 / x;//小于0 先变成倒数
            for (int i = 0; i < (-n); i++) { //n反过来-n
                product = product * x;
            }
        }
        return product;
    }

    /**
     * 快速幂
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickPow(x, N) : 1 / quickPow(x, -N);//直接最后求倒数就行了，不用带到快速幂里面，变成1/x
    }

    public double quickPow(double x, long N) {
        double ans = 1.0;

        double base = x;

        while (N > 0) {
            if ((N & 1) == 1) { //如果N是奇数
                ans = base * ans;//就要乘一次上次的base
            }

            //其他情况每次 N 右移1位，然后反复让a平方就行了
            N = N >> 1;
            //base每次乘以base
            base = base * base;
        }

        return ans;
    }
}
