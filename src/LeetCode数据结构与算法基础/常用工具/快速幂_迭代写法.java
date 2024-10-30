package LeetCode数据结构与算法基础.常用工具;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 快速幂_迭代写法 {

    @Test
    public void test(){
        double res = myPow(2, -3);
        System.out.println(res);
    }

    /**
     * 看不懂就先看递归写法
     *
     * 由于递归需要使用额外的栈空间，我们试着将递归转写为迭代。
     *
     * 在方法一中，我们也提到过，从左到右进行推导是不容易的，因为我们不知道是否需要额外乘 x
     *
     * 但我们不妨找一找规律，看看哪些地方额外乘了 x，并且它们对答案产生了什么影响。
     *
     * 我们还是以 x ^ 77 作为例子：
     *
     *  x -> x^2 -> x^4 ->+ x^9 ->+ x^19 -> x^38 ->+ x^77
     *
     *  并且把需要额外乘 x 的步骤打上了 + 标记。可以发现
     *
     *  x^38 ->+ x^77 中额外乘的 x 在  x^77 中贡献了 x；
     *
     *  x^9 ->+ x^19 中额外乘的 x 在之后被平方了 2 次，因此在 x^77 中贡献了 x ^ 4
     *
     *  x^4 ->+ x^9 中额外乘的 x 在之后被平方了 3 次，因此在 x^77 中贡献了 x ^ 8
     *
     * 最初的 x 在之后被平方了 6 次 因此在x^77 中贡献了 x ^ 64
     *
     *  我们把这些贡献相乘 会发现
     *  x * x^4 * x^8 * x^64 = x^77
     *
     *
     *  而这些贡献的指数部分又是什么呢？它们都是 2 的幂次，
     *
     *  这是因为每个额外乘的 x 在之后都会被平方若干次。而这些指数 1，4，8 和 64，
     *
     *  恰好就对应了 77 的二进制表示 (1001101) 中的每个 1！
     *
     * 因此我们借助整数的二进制拆分，就可以得到迭代计算的方法，一般地，如果整数 n 的二进制拆分为
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/powx-n/solutions/238559/powx-n-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

/*    作者：力扣官方题解
    链接：https://leetcode.cn/problems/powx-n/solutions/238559/powx-n-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}
