package 校招笔试真题.携程._20240919;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/19
 */
public class 删除数字 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxn = 1000000 + 100;
        int[] a = new int[maxn];

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }

        int total = n;
        Arrays.sort(a, 1, n + 1);

        for (int i = 1; i <= n; i++) {
            int nxt = i + m - 1;
            if (nxt > n) {
                break;
            }
            if (a[nxt] - a[i] <= k) {
                total--;
            }
        }
        System.out.println(total);
        scanner.close();
    }
}
