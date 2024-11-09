package LeetCode数据结构与算法基础.二分查找专题;

import java.util.Arrays;

class 数组中的最长方波 {

    //排序+二分解题

    /**
     * 提示一
     * 本题可以先排序简化解题。
     *
     * 提示二
     * 遍历时，nums[i] * nums[i] > nums[n - 1]时，可以直接跳出循环。
     *
     * 提示三
     * 在查找是否存在当前值的平方时，可以采用二分法查找，因为只需要查找是否存在，所以可以用库函数Arrays.binarySearch(int[] nums, int target)，返回值idx 小于0，表示不存在。
     *
     * 作者：道哥刷题
     * 链接：https://leetcode.cn/problems/longest-square-streak-in-an-array/solutions/2016147/dao-ge-shua-ti-by-lcfgrn-6qx7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int longestSquareStreak(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] * nums[i] > nums[n - 1]) break;
            int idx = i, count = 0;
            while (idx >= 0) {
                //可以直接用Arrays的二分去找
                idx = Arrays.binarySearch(nums, nums[idx] * nums[idx]);
                if (idx >= 0) ++count;
            }
            ans = Math.max(ans, count + 1);
        }
        return ans > 1 ? ans : -1;
    }
}

/*作者：道哥刷题
链接：https://leetcode.cn/problems/longest-square-streak-in-an-array/solutions/2016147/dao-ge-shua-ti-by-lcfgrn-6qx7/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
