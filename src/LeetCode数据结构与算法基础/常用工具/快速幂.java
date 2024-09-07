package LeetCode数据结构与算法基础.常用工具;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 快速幂 {

    public static int MOD = (int) (1e9 + 7);

    private static long quickPow(long base, long n) {
        long res = 1;
        //如果幂次 > 00
        while (n > 0) {
            //如果是奇数 也可以写成 n & 1 == 1
            if ((n % 2) == 1) res = (res * base) % MOD;//幂次为奇数res就乘以一次base
            base = (base * base) % MOD;//基数平方 base^2可能溢出int，要强转成long
            n = n / 2; //幂次/2
        }
        return res;
    }
}
