package 校招笔试真题.携程._20240919;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/19
 */
public class 游游寻宝 {


    //其实没必要四个方向
    //只有往下，往右才会变大。
    //但是每次往下能变得更大。首先往下，然后往右。


    /**
     * 1
     * 2 2 5
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        while (q-- > 0) {
            long n, m, k, res;
            n = scanner.nextLong();
            m = scanner.nextLong();
            k = scanner.nextLong();


            //如果小于n-1 就一直往下走
            if (k <= n - 1) {
                res = (1 + k) * k * m / 2;
            }
            //如果小于n-1 + m-1 那就一直往下走再一直往右走。
            else if (k <= n + m - 2) {
                long a = n * (n - 1) * m / 2;
                k -= (n - 1);
                res = a + k * (n - 1) * m + (k + 1) * k / 2;
            }

            //如果大于，那么就在最后两个数来回循环即可
            else {
                res = n * (n - 1) * m / 2 + (m - 1) * (n - 1) * m + m * (m - 1) / 2;
                k -= (n + m - 2);
                res += ((k / 2) * (2 * (n - 1) * m + 2 * m - 3));
                if (k % 2 == 1) {
                    res += ((n - 1) * m + m - 2);
                }
            }
            System.out.println(res);
        }
        scanner.close();
    }


}
