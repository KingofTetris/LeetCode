package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */

//对比1，区别就是直接给你排好序
//请你设计并实现对数时间复杂度的算法解决此问题。那么显然就需要二分
public class H指数II {
    public int hIndex(int[] citations) {
        int low = 0, high = citations.length - 1;
        int ans = 0;
        while (low <= high) { // <=是为了处理citations.length=1的情况
            int mid = low + ((high - low) >> 1);
            if (mid + citations[mid] >= citations.length) {
                high = mid - 1;
                ans = citations.length - mid;
            } else low = mid + 1;
        }
        return ans;
    }
}
