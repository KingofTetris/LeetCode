package 校招笔试真题.科大讯飞;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/15
 */


public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        int[] result = findLIS(nums, k);
        if (result != null) {
            System.out.println("重新排序后的排列为：" + Arrays.toString(result));
        } else {
            System.out.println("无法重新排序使得最长递增子序列的长度为" + k);
        }
    }

    private static int[] findLIS(int[] nums, int k) {
        int n = nums.length;
        if (k > n) {
            return null; // 无法重新排序成长度为k的递增子序列
        }

        int[] dp = new int[n]; // dp数组存储以每个元素为结尾的最长递增子序列的长度
        Arrays.fill(dp, 1); // 初始化每个元素自成一个子序列

        int maxLength = 1; // 最长递增子序列的初始长度
        int endIndex = 0; // 最长递增子序列的结束下标
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                endIndex = i;
            }
        }

        if (maxLength < k) {
            return null; // 无法重新排序成长度为k的递增子序列
        }

        List<Integer> lis = new ArrayList<>();
        lis.add(nums[endIndex]);

        // 从后往前寻找最长递增子序列的元素
        for (int i = endIndex - 1; i >= 0; i--) {
            if (nums[i] < nums[endIndex] && dp[i] == dp[endIndex] - 1) {
                lis.add(nums[i]);
                endIndex = i;
            }
        }

        // 将递增子序列逆序输出
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = lis.get(k - i - 1);
        }

        return result;
    }
}
