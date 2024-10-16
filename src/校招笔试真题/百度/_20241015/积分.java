package 校招笔试真题.百度._20241015;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/15
 */

/**
 * 从整数1-n，中选择k个数，计算最多能获得多少积分
 * 计分规则：初始0分，对于被选取的整数i，如果i + 1没选，则积分加1。
 * 例如n=1,k=1，则选择1，积分为1
 * n=4,k=2，则可以选择1，3。积分为2
 * java实现
 */
public class 积分 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while (q-- > 0) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            System.out.println(f(n, k));
        }
    }

    private static long f(long n, long k) {
        //偶数
        if (n % 2 == 0) {
            //
            if (n / 2 >= k) {
                return k;
            } else {
                return n / 2 - (k - n / 2) + 1;
            }
        }
        //奇数
        else {
            if (n / 2 + 1 >= k) {
                return k;
            } else {
                return n / 2 + 1 - (k - n / 2 - 1);
            }
        }
    }
}
