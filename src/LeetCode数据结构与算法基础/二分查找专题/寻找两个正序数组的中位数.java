package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/4/19
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 1e6
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 寻找两个正序数组的中位数 {

    @Test
    public void test() {
        int[] nums1 = {3, -1, 2};
        int[] nums2 = {3, 5};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] nums = new int[n + m];

        int p = 0;
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                nums[p] = nums1[i];
                i++;
            } else {
                nums[p] = nums2[j];
                j++;
            }
            p++;
        }
        //如果数组1先拼接完了
        if (i >= n) {
            while (j < m) {
                nums[p] = nums2[j];
                p++;
                j++;
            }
        }
        //如果是数组2先拼接完了
        else {
            while (i < n) {
                nums[p] = nums1[i];
                p++;
                i++;
            }
        }

        //现在nums[i] 就是有序合并数组了

        //二分 左右两个下标
        int l = 0;
        int r = n + m;
        int mid = (l + r) / 2; //不会溢出。直接除2
        if (((r - l) & 1) == 1) return nums[mid]; //如果长度是奇数直接返回mid
        //如果是偶数
        return (double) (nums[mid] + nums[mid - 1]) / 2;
    }
}
