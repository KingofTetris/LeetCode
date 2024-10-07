package LeetCode数据结构与算法基础.二分查找专题;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * 题目内容：
 * 小塔有一个 n 人组成的合唱团，第 i 个人的能力值为 a_i。
 *
 * 现在将 n 个人排成一排，小塔有 k 次训练的机会，让不超过 l 个连续的人的能力值变为任意值。（等同于一次训练不超过 (l-1)*k+1 个连续的人的能力值变为任意值）
 *
 * 合唱团的实力定义为所有人的能力值中的最小值。
 *
 * 你能帮助小塔求出合唱团的实力的最大值吗？
 *
 * 输入描述：
 * 第一行三个整数 n，k，l，表示人数，训练次数，每次训练的最大长度。 (2 <= n <= 10^5, 1 <= k*l < n)
 *
 * 第二行 n 个整数 a_i，表示第 i 个人的能力值为 a_i (1 <= a_i <= 10^9)
 *
 * 输出描述：
 * 输出答案
 *
 * 样例1：
 * 输入
 *
 * 8 2 3
 * 7 4 11 2 14 7 5
 * 选择第2个人到第6个人变成无穷大
 *
 * 输出
 *
 * 5
 *
 * 样例2：
 * 输入
 *
 * 8 2 2
 * 7 4 11 2 14 7 5
 * 输出
 *
 * 4
 *
 *
 * 文字很多，但其实就一句话
 *
 * 给定一个数组，可以进行k次操作，每次可以将最多连续l个元素值变为任意值，求操作结束数组最小值是多少。
 */
public class 游游合唱团 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int l = sc.nextInt();

        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }


        //数组的最大最小值
        int L = Collections.min(a);
        int R = Collections.max(a);

        //二分
        while (L < R) {
            int mid = (L + R + 1) / 2;
            //然后将所有小于mid的下标存在一个idx数组里
            //遍历并查看是否可以覆盖即可
            List<Integer> idx = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (a.get(i) < mid) {
                    idx.add(i);
                }
            }

            /**
             * 这段代码中的部分实现了一个逻辑用于检查是否存在足够的连续区间，
             * 使得区间内元素小于给定的阈值 mid
             */
            int limit = -1;//limit 变量用于记录当前连续区间的最右边界的位置，
            int cnt = 0;
            //idx 记录的是值小于mid的下标
            for (int id : idx) {
                if (id > limit) {
                    cnt++;
                    limit = id + l - 1;
                }
            }
            if (cnt > k) {
                R = mid - 1;
            } else {
                L = mid;
            }
        }

        System.out.println(L);
    }
}
