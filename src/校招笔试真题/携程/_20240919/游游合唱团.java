package 校招笔试真题.携程._20240919;

import java.util.Arrays;
import java.util.HashMap;
import java.util.OptionalInt;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/19
 */
public class 游游合唱团 {

    static int[] a;
    static int[] b;
    static int n, k, l;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxn = 100000 + 100;
        a = new int[maxn];
        b = new int[maxn];

        n = scanner.nextInt();
        k = scanner.nextInt();
        l = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            b[i] = a[i];
        }

        Arrays.sort(b, 1, n + 1);

        int lef = 1, rig = n;
        int result = -1;
        while (lef <= rig) {
            int mid = lef + (rig - lef) / 2;
            if (check(b[mid])) {
                result = mid;
                lef = mid + 1;
            } else {
                rig = mid - 1;
            }
        }

        System.out.println(b[result]);
        scanner.close();
    }

    static boolean check(int val) {
        int last = 0;
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (a[i] < val) {
                if (last == 0) {
                    last = i;
                    cnt++;
                } else if (i - last + 1 > l) {
                    last = i;
                    cnt++;
                }
            }
        }
        return cnt <= k;
    }
}
