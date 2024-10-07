package 校招笔试真题.携程._20240919;

import java.util.Arrays;
import java.util.Scanner;


/**
 * 假设公司有n名员工，员工初始位置分别在a[i]，办公室的位置在p。
 *
 * 有k个通行证，通行证的位置分别在b[i]，每个通行证位置只有一个通行证。
 *
 * 员工上班前必须拿到通行证，每个通行证只能供一个人使用，一个位置上最多只有一个通行证。
 *
 * 员工的初始位置可能和通行证的初始位置相同。
 *
 * 每名员工必须有通行证才能进办公室，员工移动1单位距离需要花费1单位时间。
 *
 * 求这n个员工都达到办公室的最短时间。
 *
 * 输入描述：
 *
 * 第一行三个整数 n, k, p (1 ≤ n ≤ 1000, n ≤ k ≤ 2000, 1 ≤ p ≤ 10^9)
 * 第二行 n 个整数 a[i] (1 ≤ a[i] ≤ 10^9)
 * 第三行 k 个整数 b[i] (1 ≤ b[i] ≤ 10^9)
 * 输出描述：
 *
 * 输出一行，一个整数表示这n个员工都达到办公室的最短时间。
 * 样例输入：
 * 2 4 50
 * 20 100
 * 60 10 40 80
 * 样例输出：
 * 50
 */

/**
 * 可以看出这n个员工拿的n个通行证一定是连续的，否则答案会变差，因此，对员工和通行证排序，然后双重遍历即可。
 * 我不知道你怎么看出的，很无语。
 */
public class 求所有员工拿到通行证再到公司的最短时间 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //n个员工
        int k = sc.nextInt(); //k个通行证
        int p = sc.nextInt(); //办公室终点

        int[] a = new int[n]; //员工初始位置
        int[] b = new int[k];  // 通行证位置
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < k; i++) {
            b[i] = sc.nextInt();
        }
        //给a,b从大到小排序。
        Arrays.sort(a);
        Arrays.sort(b);

        long ans = Long.MAX_VALUE;
        /**
         * 外部循环确保考虑所有可能的n个通行证的连续集合，在k个可用通行证中。
         * i 从0到k-n循环，确保考虑所有可能的连续n个通行证的起始点。
         */
        for (int i = 0; i < k - n + 1; i++) {
            long cur = 0;
            // 遍历每个员工和对应的通行证
            for (int j = 0, r = i; j < n; j++, r++) {
                int person = a[j];//第j个人
                int card = b[r]; //第r张通行证
                //这个人和通行证的距离 加上 通行证到办公室的距离。就是他到办公室的时间
                //要让所有人的到达，那么就是求最晚到达的那个，也就是max
                cur = Math.max(cur, Math.abs(person - card) + Math.abs(card - p));
            }
            //通过将当前通行证集合的时间 (cur)
            //与所有员工到达办公室的最短时间 (ans) 进行比较来更新最终的最短时间。
            //也就是每连续n个比较一次
            ans = Math.min(ans, cur);
        }

        System.out.println(ans);
    }
}
