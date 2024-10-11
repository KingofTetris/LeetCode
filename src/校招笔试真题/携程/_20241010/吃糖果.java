package 校招笔试真题.携程._20241010;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/10
 */
public class 吃糖果 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long count = 0;
        while (n > 0){
            //如果是素数
            if (isPrime(n)){
                long t = n / 3;
                n -= t + 1;
                if (n >= 0)  count++;
            }
            else {
                long t = n / 2;
                n -= t + 1;
                if (n >= 0) count++;
            }

        }
        System.out.println(count);
    }

    /**
     * 判断一个数是否是素数
     * @param val
     * @return
     */
    public static boolean isPrime(long val) {
        if (val <= 1) {
            return false;
        }
        for (long i = 2; i * i <= val; i++) {
            if (val % i == 0)
                return false;
        }
        return true;
    }
}
