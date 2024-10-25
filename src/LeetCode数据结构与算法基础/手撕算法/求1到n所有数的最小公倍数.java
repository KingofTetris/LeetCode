package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */

import java.util.Scanner;

/**
 * 题意：
 * 求1-n所有数的最小公倍数。n的范围是【1-100】
 * 分析：
 * 仔细观察会发现，【1-n】的最小公倍数，是【1-n-1】的最小公倍数乘以n的所有素因子中没有被【1-n-1】包含的素因子。
 * 例如：【1-7】的最小公倍数是2*3*2*5*7，8=2*2*2,
 * (8中2出现3次，【1-7】的素因子中只出现2次)那么【1-8】就是2*3*2*5*7*2
 */
public class 求1到n所有数的最小公倍数 {
    static int[] sum = new int[100]; // 记录最小公倍数的所有素因子
    static int[] num = new int[101]; // 记录1-100的素数表
    static String[] N = new String[101]; // 记录最小公倍数

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 100; i++) {
            sum[i] = 0;
        }
        for (int i = 0; i < 101; i++) {
            num[i] = 0;
        }
        for (int i = 0; i < 101; i++) {
            N[i] = "";
        }
        calculateSmallestCommonMultiples();
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            String result = calculateResult(n);
            System.out.println(result);
        }
    }

    private static void calculateSmallestCommonMultiples() {
        for (int i = 2; i < 11; i++) {
            if (num[i] == 0) {
                for (int j = 2 * i; j < 101; j += i) {
                    if (j % i == 0) {
                        num[j] = 1;
                    }
                }
            }
        }
        N[1] = "1";
        for (int i = 2; i < 101; i++) {
            int W = 1;
            int w = i;
            for (int j = 2; j <= i && w > 1; j++) {
                if (num[j] == 0 && w % j == 0) {
                    int k = 0;
                    while (w % j == 0) {
                        w /= j;
                        k++;
                    }
                    if (sum[j] < k) {
                        W *= j;
                        sum[j]++;
                    }
                }
            }
            if (W > 1) {
                int flag = 0;
                int j = 0;
                while (N[i - 1].length() > j) {
                    int x = (N[i - 1].charAt(j) - '0') * W + flag;
                    N[i] += x % 10;
                    flag = x / 10;
                    j++;
                }
                while (flag > 0) {
                    N[i] += flag % 10;
                    flag /= 10;
                }
            } else {
                N[i] = N[i - 1];
            }
        }
    }

    private static String calculateResult(int n) {
        int i = 0;
        while (N[n].length() > i) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i - 1; j >= 0; j--) {
            sb.append(N[n].charAt(j));
        }
        return sb.toString();
    }
}
