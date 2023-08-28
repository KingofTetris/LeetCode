package 校招笔试真题.华为.华为春招0419;

import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2023/4/23 14:31
 *
 * [1,1] [2,2] [6,6]..[n,n]都算一分钟
 */
public class 塔子哥监考 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 可以考虑用数组存储每个时间点的任务数，使用差分数组实现对某个区间的快速增加1（任务数）.
        // 差分数组的任务就是对数组的某一个区间元素进行频繁的修改（同时增加，减少）
        // 比如为了让原区间[1,4]都+3 那你只需要让diff[1] += 3 diff[5] -= 3.
        // 然后根据diff还原数组即可
        // 所以差分数组一般应用在你需要知道每个下标/时刻/站点有多少任务量的背景下使用。

        // 前缀和是为了快速求某个区间之和 比如求[1,4]用前缀和数组就是preSum[5] - preSum[1],preSum[n + 1]从1开始记
        int[] time = new int[(int) (1e6 + 6)]; //统计每个时间点的任务数量，时间片最长为1e6

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (n-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();

            min = Math.min(min, l);//每次取最小
            max = Math.max(max, r);//每次取最大


            //然后对区间[l,r]区间中的任务量都+1
            //diff[l]++
            // diff[r + 1]--
            time[l]++;
            time[r + 1]--;
        }


        int ans = 0;

        //从min到max开始计算前缀和
        //每个cur就对应每个时刻的任务量
        int pre = 0;
        for (int j = min; j <= max; j++) {
            int cur = pre + time[j];
            pre = cur;
            if (cur == 0) {
                ans += 1;
            } else if (cur == 1) {
                ans += 3;
            } else {
                ans += 4;
            }
        }
        System.out.println(ans);
    }
}
