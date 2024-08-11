package 校招笔试真题.美团._20230916;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 */

//从数组中选出1个或者多个数字，设他们相与的和为res。
    //假设这个res能被2^m整除,求m的最大值。
    //比如，数组是[21,1,20,28]
    //可以取20，一个数不用和其他数字相与，res就=20，那么m的最大值就是2，以为4能整除20
    //也可以取，20和28，相与结果是28&20=20，res=20，这个结果也一样。m=2.
    //现在给你一个数组，请你返回最大的m.

    //下面这个程序应该不对。为什么这个B能AC？
    //https://www.nowcoder.com/discuss/532661796222562304?sourceSSR=search
public class 小美的数组相与 {
    /**
     * 题解
     * 2^m == （1 << m） 从这里可以看出，如果 k % (2^m) == 0 这里的 m 就是k二进制后从低位到高位第一个1出现的位置。
     *
     * 然后两种情况进行枚举找到最佳答案：
     *
     * 只选中一个元素；
     * 选择两个元素（元素没有相同的1位就没有必要进行选择，因为这样的选择不会优于只选择一个元素的情况）；
     *
     * 作者：code5bug
     * 链接：https://www.nowcoder.com/discuss/534003042790780928?urlSource=sitemapApi
     * 来源：牛客网
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        Solution2 solution = new Solution2();
        System.out.println(solution.solve(a));
    }
}

class Solution2 {
    int max = 0;
    public int solve(int[] a) {
        // 对元素进行分组 groupMap.get(i) 表示二进制第i位值为1的数字
        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        for (int num : a) {
            int bit = 0;
            boolean foundOne = false;

            int k = num;
            while (k != 0) {
                if ((k & 1) == 1) {
                    //这里更新结果，是判断只选当前元素是的结果值（即第一个１所在的位置）
                    if (!foundOne) max = Math.max(bit, max);

                    // Map<bit, num>
                    groupMap.computeIfAbsent(bit, p -> new ArrayList<>()).add(num);
                    foundOne = true;
                }

                k >>= 1;
                bit++;
            }
        }

        // 这里是尝试选取两个元素，进行求最佳的解
        // 元素没有相同的1位就没有必要进行选择，因为这样的选择不会优于只选择一个元素的情况
        for (List list : groupMap.values()) {
            int n = list.size();
            for (int i = 0; i < n; i++) {
                int ai = (int) list.get(i);
                for (int j = i + 1; j < n; j++) {
                    int aj = (int) list.get(j);

                    this.max = Math.max(max, f(ai & aj));
                }
            }
        }


        return max;
    }

    // 求 k 二进制，从低位到高位第一个1出现的位置,  这个位置即为 k % (2^m) == 0 的最大的 m
    public int f(int k) {
        int bit = 0;
        while (k != 0) {
            if ((k & 1) == 1) break;

            k >>= 1;
            bit++;
        }

        return bit;
    }
}
/*
作者：code5bug
        链接：https://www.nowcoder.com/discuss/534003042790780928?urlSource=sitemapApi
        来源：牛客网*/
