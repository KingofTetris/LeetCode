package 剑指offer第二版.动态规划;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/12 15:13
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *  
 *
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer10_i_斐波那契数列 {
    final int MOD = 1000000007;
    int[] memo = new int[10001];//递归动态规划需要的数组

    /**
     * 最简单的递归分段函数。什么技巧都没有，必定超时。
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-2) + fib(n-1);
    }

    /**
     * 滚动数组 其实就是迭代版的动态规划 时间复杂度O(n)
     */
    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0,q = 0,r = 1;//因为是前两项相加，所以用了三个数pqr,当然你要用数组也行，只是空间更多
        for (int i = 2; i <= n; i++) {
            /**
             * 其实就是后两项往前面移1位，然后算F(N)补充到第三位
             */
            p  = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r ;
    }

    /**
     * 普通动态规划 用memo存储中间结果
     * @param n
     * @return
     */
    public int fib3(int n) {
        if (n < 2) {
            return n;
        }
        if (n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = ( fib3(n-1) + fib3(n-2) )% MOD;//其实就是加了个备忘录
        return memo[n] ;
    }

    /**
     * 首先矩阵快速幂适用于 f(n) = a_1*f(n-1) + a_2*f(n-2) + .... + a_n-1 * f(1) 这样的递推式。
     * 关键是找到这个递推关系从而推导出M矩阵
     * 矩阵快速幂 O(logn) 也就是快速求 M矩阵的 n次幂。
     * 它的基本原理是把n拆成二进制。比如 23 = 1 + 2 + 4 + 16 = (10111) 二进制
     * 核心：反复平方法，即 不断把底数平方
     * 如普通的求数的n次幂，3^23 = 3^1 + 3^2 + 3^4 + 3^8 + 3^16 //当然这里的3^8实际上是没有的但不影响反复平方a
     *         =  a + a=a^2  a=a^2 a=a^2 a=a^2  每次把 a=上一个a的平方  就是反复平方法。
     * 再详细一点
     * 3^10=3*3*3*3*3*3*3*3*3*3
     *     =9^5 = 9^4*9
     *     =81^2*9
     *     =6561*9
     * 基于以上原理，我们在计算一个数的多次幂时，可以先判断其幂次的奇偶性，然后：
     * 如果幂次为偶直接 base(底数) 作平方，power(幂次) 除以2 也就转成了 这里就是 3^10 ->(3^2)^5
     * 如果幂次为奇则底数平方，幂次整除于2然后再多乘一次底数 这里就是 9^5-> 81^2 * 9
     *
     * 对于以上涉及到 [判断奇偶性] 和 [除以2] 这样的操作。使用系统的位运算比普通运算的效率是高的，因此可以进一步优化：
     *
     * 把 power % 2 == 1 变为 (power & 1) == 1
     * 把 power = power / 2 变为 power = power >> 1
     *
     * O(logn)
     *      * @param n
     *      * @return
     */

    @Test
    public void test(){
        System.out.println(fib4(100)); //为什么矩阵快速幂算出来是32448255？？
    }

    public int fib4(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}}; //M矩阵
        int[][] p = {{1},{0}};
        int[][] res = pow(q, n - 1); //用矩阵快速幂求出M的n - 1次幂，这样最后的结果里面res[0][0]就是F(n)而不是F(n+1)
        res = multiply(res,p);        //再去乘以{ F(1)
                                      //         F(0)}
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + "\t");
            }
            System.out.println();
        }
        return res[0][0];
    }

    /**
     * 定义函数,求底数为 base 幂次为 power 的结果
     * @param base
     * @param power
     * @return
     */
    public int[][] pow(int[][] base, int power) { //计算 base的power次幂
        //定义变量，存储计算结果，此次定义为单位阵  如果是普通求幂就设为1就行了
        int row = base.length;
        int col = base[0].length;
        int[][] ret = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                 if (i == j) {
                     ret[i][j] = 1;//初始化为单位矩阵
                 }
            }
        }
//        int[] shape = shape(ret);
//        System.out.println(shape[0] + "\t" + shape[1]);
        //一直对幂次进行整除
        while (power > 0) {
            /**
             * 矩阵快速幂的核心就是下面这三段话
             */
            //1.若为奇数，需多乘一次 base
            //2.若power除到1，乘积后得到res
            //此处使用位运算在于效率高
            if ((power & 1) == 1) { //如果n是奇数，就多乘一次 base 然后n/2
                ret = multiply(ret, base);
            }

            //不管幂次是奇还是偶，整除的结果是一样的如 5/2 和 4/2
            //此处使用位运算在于效率高
            power >>= 1; //右移比除法速度更快
            base = multiply(base, base); //反复平方 base = base^2 就在这里
        }
        return ret;
    }

    /**
     * 普通的矩阵乘法，如果只是用这个循环n次，那么时间复杂度根本没变还是O(M)。重点在上面的pow 矩阵的幂次函数
     * @param A
     * @param B
     * @return
     */
    //TODO 为什么改了矩阵乘法LeetCode那边出错了？
    public int[][] multiply(int[][] A, int[][] B) {
        if (A[0].length != B.length) {
            System.out.println("矩阵不像容");
            return null;
        } else {
            /**
             * 普通的矩阵乘法 O(n^3) 有人研究出了 O(n^2.7)
             */
            int[][] C = new int[A.length][B[0].length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int t = 0; t < A[0].length; t++) {
                        C[i][j] = (int) ((long) C[i][j] + (long)(A[i][t] * B[t][j])) % MOD;
                        //是不是取余这里出了错,如果不取余？不能不取余，不取余就变负数了。
                    }
                }
            }
            return C;
        }
    }

    /**
     * 以数组形式返回二维数组的形状
     * @param matrix
     * @return
     */
    public int[] shape(int[][] matrix){
        int[] shape = new int[2];
        shape[0] = matrix.length;
        shape[1] = matrix[0].length;
        return shape;
    }
    /**
     * 测试矩阵相乘，确实没问题。
     */
    @Test
    public void testMultiply(){
      int[][] A = {{1,3,5},{2,4,6} };
      int[][] B = { {1,1} ,{2,2} ,{3,3}};
      int[][] multiply = multiply(A, B);
        for (int i = 0; i < multiply.length; i++) {
            for (int j = 0; j < multiply[0].length; j++) {
                System.out.print(multiply[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
