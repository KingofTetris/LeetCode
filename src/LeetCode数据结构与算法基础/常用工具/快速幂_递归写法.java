package LeetCode数据结构与算法基础.常用工具;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 快速幂_递归写法 {


    @Test
    public void test(){
        double res = quickPow(2, -3);
        System.out.println(res);
    }

    /**
     * 其实快速幂也没什么特别复杂的，就是省略掉一次一次乘法，尽量每次平方。
     * 比如 计算x^64次方，你就不要傻乎乎真的x*64次了
     *
     * x -> x^2 -> x^4 -> x^8 -> x^16 -> x^32 -> x^64
     * 每次直接把上一次的结果进行平方，计算 6 次就可以得到结果
     *
     * 再举一个例子，如果我们要计算 x ^ 77
     *
     * 我们可以
     * x -> x^2 -> x^4 -> x^9 -> x^19 -> x^38 -> x^77
     *
     * 可以发现有的操作我们直接把上一次的结果进行平方
     * 而有的操作我们把上一次的结果进行平方后，还要额外乘一个 x。
     *
     * 直接从左到右进行推导看上去很困难，因为在每一步中，我们不知道在将上一次的结果平方之后，还需不需要额外乘 x
     *
     * 但是你一旦反过来操作，
     *
     * 1.当我们要计算x ^ n时，我们可以先递归地计算出 y ^ [n / 2],[]表示向下取整
     * 2.根据递归计算的结果，如果 n 为偶数，那么x ^ n = y ^ 2。就不需要乘以x
     * 如果n为奇数，那么x^n = y ^ 2 * x;
     * 3.递归的边界为 n=0，任意数的 0 次方均为 1。
     *
     * 由于每次递归都会使得指数减少一半，因此递归的层数为 O(logn)，算法可以在很快的时间内得到结果。
     *
     */
    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    private double quickPow(double x, long n) {
        long N = n;
        //N小于0的话，先把N转成整数，最后取倒数即可。
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }



   /* 作者：力扣官方题解
    链接：https://leetcode.cn/problems/powx-n/solutions/238559/powx-n-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
