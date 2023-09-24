package 校招笔试真题.腾讯._0230910;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/10
 */
public class 牛牛的K次操作 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = solution(nums, k);
        System.out.println(res);
    }

    private static int solution(int[] nums, int k) {
        //可以对nums进行k次操作
        //让nums[i]中的最低位的1变成0,可以对同一个数反复操作
        //如何才能让nums.sum最小?
        int minSum = 0;
        //这个GPT的思路就是把所有位数加起来，然后每次把前面的1改为0
        //int类型最大32位
        int[] counts = new int[32];
        for (int num : nums) {
            int j = 0;
            //只要num>0
            while (num > 0) {
                if ((num & 1) == 1) {
                    counts[j]++;
                }
                //右移1位
                num >>= 1;
                j++;
            }
        }
        //对count数组进行操作
        for (int i = 0; i < k; i++) {
            int minCount = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = counts.length - 1; j >= 0; j--) {
                if (counts[j] < minCount && counts[j] > 0) {
                    minCount = counts[j];
                    minIndex = j;
                }
            }
            if (minIndex != -1) {
                counts[minIndex]--;
            }
        }
        //计算大小
        for (int i = 0; i < counts.length; i++) {
            //位数组计算大小的公式就是下面这个。
            //1 << i这个就相当于Math.pow(2,i)
            //但是效率要比pow运算快得多。
            minSum += (counts[i] * (1 << i));
        }
        return minSum;
    }
}
