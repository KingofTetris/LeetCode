package LeetCode数据结构与算法基础.双指针与滑动窗口;

/**
 * @author by KingOfTetris
 * @date 2023/10/14
 */


/**
 * 给你两个升序数组nums1,nums2，数组中的元素各不相同。
 * 1.首先从左到右遍历数组1
 * 2.当遇到数组1元素等于数组2元素时，你可以选择走另外一条路径，也就是跳到另一个数组。
 * 3.重复这个过程直到某个数组被遍历完
 * 4.路径和就是你选择的路径上所有元素之和。注意相同元素只能算一次。
 * 现在要求所有路径和中的最大值。
 * 比如数组1 [1,2,3,4,5] 数组2[3,5,7]
 * 则最大路径是1,2,3,5,7。最大和为17。
 * 数组1 [-3,-2,-1,0,1] 数组2[1,2,3]
 * 则最大路径为-3,-2,-1,0,1,2,3，最大和为0
 */
public class 两数组的最大路径和 {
    public static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {3, 5, 7};
        int maxPathSum = solution(nums1, nums2);
        System.out.println("最大路径和：" + maxPathSum);
    }

    public static int solution(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        long sum1 = 0, sum2 = 0, maxSum = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i < nums1.length && (j == nums2.length || nums1[i] < nums2[j])) {
                sum1 += nums1[i++];
            } else if (j < nums2.length && (i == nums1.length || nums1[i] > nums2[j])) {
                sum2 += nums2[j++];
            } else {
                maxSum += Math.max(sum1, sum2);
                sum1 = sum2 = 0;
                i++;
                j++;
            }
        }
        maxSum += Math.max(sum1, sum2);
        return (int) (maxSum % MOD);
    }
}
