package 校招笔试真题.虾皮._20240924;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/9/24
 */
public class 最大奇约数 {

    @Test
    public void test(){
        long l = SumOfGreatestOddDivisor(10);
        System.out.println(l);
    }
    public long SumOfGreatestOddDivisor(long n) {
        //去求最大奇约数肯定超时，奇数的最大奇约数就是他自己
        //对于偶数则一直/2，得到的第一个奇数就是他的最大奇约数
        //g(n) = f(1) + f(2) + ... + f(n)
        //肯定是数字肯定是一奇一偶的出现。
        //eg.n = 10,那么1 2 ... 9 10
        //奇数之和就是1 3 5 7 9相加就是等差数列求和 (1+9) * 5 / 2 = 20
        //偶数就需要f(2) + f(4) + f(6) + f(8) + f(10)
        // 1 + 1 + 3 + 1 + 5 = 11;
        //实际上是去找求和规律。
       /* long sum = 0;
        for (long i = n; i > 0; i = i / 2) {
            long t = (i + 1) / 2;
            sum += t * t;
        }
        return sum;*/
        if (n == 1) return 1;
        if (n == 0) return 0;
        if (n % 2 == 0){
            return SumOfGreatestOddDivisor(n / 2) + n * n / 4;
        }
        else{
            return SumOfGreatestOddDivisor(n - 1) + n;
        }
    }
}
