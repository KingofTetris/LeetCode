package 校招笔试真题.华为OD._2024_E卷;

import java.util.Scanner;

public class 分批萨 {
    static int[] pizza;
    // 缓存
    static long[][] cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = sc.nextInt();
        }

        // 缓存
        cache = new long[n][n];

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, recursive(check(i - 1), check(i + 1)) + pizza[i]);
        }

        System.out.println(ans);
    }

    public static long recursive(int l, int r) {
        if (pizza[l] > pizza[r]) {
            l = check(l - 1);
        } else {
            r = check(r + 1);
        }

        // 缓存优化，如果对应缺口状态的披萨铁盘结果已经算过了，则无需再次重复递归
        if (cache[l][r] > 0) {
            return cache[l][r];
        }

        if (l == r) {
            // 缓存对应缺口状态下，吃货可得的最大披萨大小
            cache[l][r] = pizza[l];
        } else {
            // 缓存对应缺口状态下，吃货可得的最大披萨大小
            cache[l][r] =
                    Math.max(recursive(check(l - 1), r) + pizza[l], recursive(l, check(r + 1)) + pizza[r]);
        }

        return cache[l][r];
    }

    public static int check(int idx) {
        if (idx < 0) {
            idx = pizza.length - 1;
        } else if (idx >= pizza.length) {
            idx = 0;
        }

        return idx;
    }
}
