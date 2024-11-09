package LeetCode数据结构与算法基础.排序;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 找到和最大的长度为K的子序列 {

    public int[] maxSubsequence(int[] nums, int k) {
        int len = nums.length;
        // idxMap：辅助数组，用来存储数值和索引
        int idxMap[][] = new int[len][2];
        for (int idx = 0; idx < len; idx++) {
            idxMap[idx][1] = nums[idx];
            idxMap[idx][0] = idx;
        }
        // 按照数值nums[idx]从大到小排序
        Arrays.sort(idxMap, (a, b) -> b[1] - a[1]);
        // 按照索引idx从小到大进行排列
        Arrays.sort(idxMap, 0, k, (a, b) -> a[0] - b[0]);

        // 复制结果
        int[] res = new int[k];
        for (int idx = 0; idx < k; idx++) {
            res[idx] = idxMap[idx][1];
        }

        return res;
    }
}
