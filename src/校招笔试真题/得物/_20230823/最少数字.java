package 校招笔试真题.得物._20230823;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */
public class 最少数字 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int target = in.nextInt();
        int[] nums = new int[count];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = in.nextInt();
        }
        int[] dp = new int[target + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 10000; //初始都设置为no solution
        }

        //dp[j] = min(dp[j],dp[j - nums[i]) + 1)
        // j = target -> nums[i] ?
        for (int i = 0; i < count; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.min(dp[j], dp[j - nums[i]] + 1);
            }
        }
        if (dp[target] == 10000) {
            System.out.println("No solution");
        } else {
            System.out.println(dp[target]);
        }
    }

    private static void solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
//        int[] nums = new int[n];
        //nums中找出组合能使得这组组合之和为m,每个数字只能用一次
        //然后我们要取组合数字最少的方案
        int sum = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            set.add(temp);
            sum += temp;
        }
        //如果所有不重复数字之和都小于m 那么根本就不可能有solution
        if (sum < m) {
            System.out.println("No solution");
        } else { //其他情况
            //使用set中的数字，每个选一次，找出能够组合成m的方案。
            //其实就变成了凑硬币。但是这题只需要硬币最少的方案。
            //那么其实只要记录当前方案的硬币数量，如果硬币数量已经大于等于上一个策略就可以直接break了
            //如果小于那么就更新为这个最小的。
            int ss = set.size();
            int[] bcf = new int[ss];
            int index = 0;
            for (Integer t : set) {
                bcf[index++] = t;
            }
            Arrays.sort(bcf);//这一步很关键，还是得sort
            int res = Integer.MAX_VALUE;
            boolean flag = false;
            for (int i = 0; i < ss; i++) {
                sum = bcf[i];
                int temp = 1;
                if (sum == m) {
                    res = 1;
                    break; //如果刚好就是m 那就不用再走了，这就是最少的方案。
                }
                for (int j = i + 1; j < ss; ) {
                    while (sum < m && j < ss) {
                        sum += bcf[j];
                        temp++; //数量+1
                        j++;
                    }
                    if (sum == m) {
                        res = Math.min(res, temp);
                    } else if (sum > m) { //如果>m还有救
                        break;
                    } else { //如果小于m 那么没救了。直接跳出两层循环吧
                        flag = true;
                        break;
                    }
                }
                if (flag) break; //因为我们已经排序了，如果前面全部加起来都还小于m，那么后面也不可能了。
            }
            if (res == Integer.MAX_VALUE)
                System.out.println("No solution");
            else {
                System.out.println(res);
            }
        }
    }
}
