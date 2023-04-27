package 校招真题.腾讯.腾讯音乐2023暑期实习;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/18
 * 曾经有一个数学热爱者，名叫塔子哥，他发现了一个特殊的数组，只有三种元素：0,1,2
 * 相邻的元素都不相等。他把这种数组叫做“好数组”，这些特点启发了他思考如何描述一个好数组的特征和性质。
 * 他注意到，对于一个好数组，相邻元素的差的绝对值之和非常有趣，他把这个值叫做“平滑值”。
 * 于是他开始研究所有长度为 n 的好数组的平滑值之和，并想知道这个值是多少。
 * 由于答案过大，请对 10^9+7 取模。
 * 输入为一个整数 n （2 ≤ n ≤ 10^9）。
 * 输出为一个整数，表示长度为 n 的所有好数组的平滑值之和。
 * 样例：
 * 输入：
 * 2
 * 输出：
 * 8
 * 样例解释：
 * 共有 [0,1]，[1,0]，[0,2]，[2,0]，[1,2]，[2,1] 这六个好数组。平滑值之和为 1+1+2+2+1+1=8。
 */
public class 平滑值 {

    public static int MOD = (int) (1e9 + 7);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long res = (n - 1) * quickPow(2, n + 1) % MOD;//TODO 为什么所有数组之和是(n-1)*2^(n+1)，这是怎么算出来的？
        System.out.println(res);
    }

    //n位的好数组总数是 3*2^(n-1)个
    //3是因为第一位固定是0,1,2。剩下的数字以好数组的规矩去组合
    //每位都有2种选择,然后选择(n-1)次
    //但是要怎么把所有的好数组的平滑值都求出来相加?
    //穷举可能是不可能的。
    // 最大n都到 1e9了
    //这道题必然是有规律的
    //动态规划

    //dp[i] 表示长度为n的所有好数组的平滑值之和
    //dp[2] = 8 就是题目的解释
    //那么递归公式是？
    //dp[i] = ?


    //快速幂，我建议你看到题目数据非常大，或者要对1e9 + 7 取模的时候，数据类型全部用long
    //就不用考虑数据类型转来转去了
    private static long quickPow(long base, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = (res * base) % MOD;//幂次为基数res就乘以一次base
            base =  (base * base) % MOD;//基数平方 base^2可能溢出int，要强转成long
            n = n / 2; //幂次/2
        }
        return res;
    }
}