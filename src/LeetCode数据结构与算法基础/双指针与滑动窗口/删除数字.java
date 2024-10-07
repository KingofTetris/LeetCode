package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/19
 *
 * 小塔在黑板上写下了n个数字，构成了一个可重集合。
 *
 * 小塔请你参与一个游戏:每轮操作你可以任选集合中最大值和最小值的差不超过k的 m 个数字，
 * 然后删去这 m个数字中的最小值（删除一个），并把其他的数字放回集合中。
 *
 * 若无法选出符合条件的
 * m个数，则无法继续操作。
 *
 * 你可以无限次进行这个操作，直到没法操作为止。
 *
 * 要使得最后留下的数最少，请你求出操作后留下的最少的数字数量。
 */
public class 删除数字 {
    //排序从小到大遍历
    //维护一个大小为m的窗口即可
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        Arrays.sort(a);

        int ans = n;
        for (int l = 0, r = 0; r < n; r++) {
            //如果窗口大小为m,则尝试删去最小值。
            if (r - l + 1 == m) {
                if (a[r] - a[l] <= k) {
                    ans--;
                }
                //每到一个窗口就把l右移。
                l++;
            }
        }
        //最后输出ans即可。
        System.out.println(ans);
    }
}
