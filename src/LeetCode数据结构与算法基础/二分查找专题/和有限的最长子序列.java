package LeetCode数据结构与算法基础.二分查找专题;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 和有限的最长子序列 {

    /**
     * 千万别被题目带偏啦！ 思考一：如何达到最大长度，不难想到每次取最小的，贪心思想。
     * 思考二：能否对数组进行排序? 由于选取的元素没有连续的要求，因此可以对数组进行排序。
     * @param nums
     * @param queries
     * @return
     */
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i] + nums[i];
        }
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            answer[i] = binarySearch(f, queries[i]) - 1;
        }
        return answer;
    }

    public int binarySearch(int[] f, int target) {
        int low = 1, high = f.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (f[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

   /* 作者：力扣官方题解
    链接：https://leetcode.cn/problems/longest-subsequence-with-limited-sum/solutions/2172081/he-you-xian-de-zui-chang-zi-xu-lie-by-le-xqox/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
