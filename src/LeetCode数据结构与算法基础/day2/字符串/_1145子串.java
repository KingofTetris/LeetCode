package LeetCode数据结构与算法基础.day2.字符串;

import java.util.Scanner;

public class _1145子串 {

    static int MOD = (int) (1e9 + 7);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String line = sc.next();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = line.charAt(i) - '0';
        }

        /**
         * 将连续的1数量做前缀和，5的数量做后缀和，遍历字符串，遇到4的时候，
         * 将4前面的11和后面的5排列组合即为答案
         *
         * 但是你得保证11和5分别在这个4的前面和后面
         */
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        long count = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] == 1 && arr[i - 1] == 1) {
                count += 1;
            }
            prefix[i] = count; //记录以index = i为结尾的字符串有多少个连续11
        }
        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 5) {
                count += 1;
            }
            suffix[i] = count;//记录以index = i为开头到结尾的字符串有多少个5
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 4) {
                //当碰到一个4的时候，就可以计算这个四的前面有多少个11，后面有多少个5
                res = (res + prefix[i] * suffix[i]) % MOD;
            }
        }
        System.out.println(res);
        sc.close();
    }
}
