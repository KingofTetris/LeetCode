package 校招笔试真题.阿里云;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/30
 */

/**
 * 小红有一个长度为 n 的排列 p，其中 1 到 n 的每个数都恰好出现一次，pos[i] 表示排列中元素 i 的下标。
 * 例如 p = [3,2,4,5,1]，则 pos = [5,2,1,3,4]。还有一个长度为 m 的数组 a，
 * 数组的元素互不相同，即 ai != aj，如果满足 pos[a[i]] < pos[a[i+1]] <= pos[a[i]] + d，则认为 a 是一个优秀的数组。
 * <p>
 * <p>
 * 现在给定长度为 n 的排列 p，以及长度为 m 的数组a1, a2, ... am，
 * 小红想知道最少需要多少次操作可以将数组变的不优秀，每次操作可以交换排列 p 的相邻元素，对应的 pos 数组也会发生变化。
 */
public class 小红的不优秀数组 {


    /**
     * 题意：给你一个p数组，然后说pos数组是 数组p的元素在数组p里的下标值，然后下标值要从1开始。
     * 然后再给你一个a数组，定义是满足 pos[a[i]] < pos[a[i+1]] <= pos[a[i]] + d，
     * 则认为 a 是一个优秀的数组。现在要让a数组不优秀，问最少次数。
     * <p>
     * 题解：理解一下那个公式，就是a数组中相邻的两个元素在p数组中对应的位置的关系满足x<y<=x+d,
     * 那么要让a数组不优秀就有两种解法：
     * <p>
     * 让x和y交换，次数为y-x
     * 让y到x+d+1，次数为x+d+1-y
     * 取最小即可。
     * <p>
     * 最后注意如果一开始就不满足，直接返回即可。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int d = scanner.nextInt();

        int[] p = new int[n + 1];
        int[] pos = new int[n + 1];
        int[] a = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            int num = scanner.nextInt();
            p[i] = num;
            pos[num] = i;//在num的位置放上i
        }

        for (int i = 1; i <= m; i++) {
            a[i] = scanner.nextInt();
        }

        int minn = 10005;// m<=n<=10005

        for (int i = 1; i < m; i++) {
            int x = pos[a[i]];
            int y = pos[a[i + 1]];

            if (x < y && y <= x + d) {
                minn = Math.min(y - x, x + d + 1 - y);
            } else {
                System.out.println(0);
                return;
            }
        }

        System.out.println(minn);
    }
}
