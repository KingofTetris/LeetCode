package LeetCode数据结构与算法基础.回溯;


/**
 * 某仓库有一排货箱，按从左致右的顺序把每个货箱的重量记录在数组 boxes中，工人们需要搬运所有货箱，要求如下:
 * 1.货箱堆从左到右分成三堆，每一堆至少有一个货箱
 * 2.三堆重量 左<=中<=右
 * 问，有几种可行的方法
 * 例:boxes =[1,1,2,1,4]有5种:(1,1,7)(1,3,5)(2,2,5)(1,4,4)(2,3,4)
 * boxes =[3,2,3]有0种
 * <p>
 * 思路:
 * 1、这个题就是压缩数组，不能改变原来的相对顺序，相邻元素相加合并为一个。
 * 2、使得最后数组元素个数压缩为3个，也就是3堆。
 */

/**
 * 思路:
 * 1、这个题就是压缩数组，不能改变原来的相对顺序，相邻元素相加合并为一个。
 * 2、使得最后数组元素个数压缩为3个，也就是3堆。
 */

import java.util.Scanner;

public class 搬运货箱的方案 {
    static int[] piles = new int[3]; // 代表三个堆
    static int sumWeight;
    static int firstLimit;
    static int secondLimit;
    static int res = 0;
    static int[] nums;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        sumWeight = 0;
        for (int num : nums) {
            sumWeight += num;
        }
        firstLimit = sumWeight / 3 + 1;
        secondLimit = firstLimit * 2;

        dfs(0, 0);
        System.out.println(res);
    }

    public static void dfs(int index, int pileId) {
        if (pileId == 2) {
            // 到了最后一堆，把剩余元素全加到最后一个堆
            piles[pileId] += sumWeight - sumArray(nums, index);
            // 检查是否满足条件
            if (piles[0] > 0 && piles[0] <= piles[1] && piles[1] <= piles[2]) {
                res++;
            }
            return;
        }

        for (int i = index; i < nums.length - (2 - pileId); i++) {
            piles[pileId] += nums[i];
            // 提前终止循环
            if (piles[0] >= firstLimit) {
                break;
            }
            if (piles[0] + piles[1] >= secondLimit) {
                break;
            }
            // 递归调用
            dfs(i + 1, pileId + 1);
            piles[pileId + 1] = 0; // 回溯
        }
    }

    // 计算数组从指定位置开始的元素之和
    public static int sumArray(int[] arr, int start) {
        int sum = 0;
        for (int i = start; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
