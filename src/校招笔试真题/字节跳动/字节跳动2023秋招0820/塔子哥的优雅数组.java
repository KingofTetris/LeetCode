package 校招笔试真题.字节跳动.字节跳动2023秋招0820;

import java.util.Arrays;
import java.util.Scanner;

public class 塔子哥的优雅数组{

    public static void main(String[] agrs){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];

        for(int i = 0;i<n;i++){
            array[i] = sc.nextInt();
        }
        Arrays.sort(array); // 将数组排序
        //Arrays.copyOf 复制数组三个参数，(源数组,起始下标，结束下标)，如果是2个参数，那么就是（原数组，新的长度）。新的长度从0下标开始。
        //nums1拿最大当作 剩下的那个数，nums2反过来拿最小当特例。
        int[] nums1 = Arrays.copyOf(array, n - 1);
        int[] nums2 = Arrays.copyOfRange(array, 1, n);

        //输出 nums1和nums2 哪种情况调整最少。
        System.out.println(Math.min(solve(nums1, array[n - 1]), solve(nums2, array[0])));

    }

    private static long solve(int[] array, int num) {
        int n = array.length;
        // 计算三个可能的中位数，这些中位数用于比较和计算操作次数

        //1.偶数有两种可能n/2 -1 和 n/2 + 1
        //奇数则刚好是n/2

        //为了避免n == 1 出现 n/2 - 1 == -1，所以要和0比较
        //为了避免同样 为了避免 n=2,n/2 + 1 = 2，数组越界， 让n/2 + 1和 n-1比较。
        /**
         * 本题如果你不考虑这两个特殊情况，那么会有 2个测试用例过不起，
         * 因为数组越界。RTE Runtime Error
         */
        long avg1 = array[Math.max(0, n / 2 - 1)],
            avg2 = array[n / 2],
            avg3 = array[Math.min(n / 2 + 1, n - 1)];

        // 计算将每个元素调整到三种中位数各自所需的操作次数
        //每次操作可以使得数组中一个数的值加1 或减1 。
        long ans1 = 0, ans2 = 0, ans3 = 0;
        for (int j : array) {
            ans1 += Math.abs(avg1 - j);
            ans2 += Math.abs(avg2 - j);
            ans3 += Math.abs(avg3 - j);
        }
        // 如果最大/最小元素和中位数相等，需要额外增加一次操作 让最大最小取不一样的值。
        if (avg1 == num) ans1++;
        if (avg2 == num) ans2++;
        if (avg3 == num) ans3++;
        // 返回三种情况中的最小操作次数
        return Math.min(ans1, Math.min(ans2, ans3));
    }
}
