package 剑指offer第二版.排序;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/6 18:48
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 * 限制：
 * 0 <= 数组长度 <= 50000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer51_数组中的逆序对 {

    @Test
    public void test(){
        int[] nums = {7,5,6,4,2};
        System.out.println(reversePairs(nums));
    }

    /**
     * 最简单的暴力会超时
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j] ) count++;
            }
        }
        return count;
    }

    /**
     * 先分治成单个数字肯定是有序的 再用归并排序 合并两个有序数组
     * 在归并过程中，就可以判断逆序对的数量
     * @param nums
     * @return
     */
    int count;
    public int reversePairs2(int[] nums) {
        count = 0;
        merge(nums, 0, nums.length - 1);
        return count;
    }


    /**
     * 归并
     * @param nums
     * @param left
     * @param right
     */
    public void merge(int[] nums, int left, int right) {
        int mid = left + ((right - left) >> 1); //取中点
        if (left < right) { //left > right中止。
            merge(nums, left, mid);
            merge(nums, mid + 1, right);
            mergeSort(nums, left, mid, right);
        }
    }

    public void mergeSort(int[] nums, int left, int mid, int right) {
        int[] temparr = new int[right - left + 1];//辅助数组
        int index = 0;
        int temp1 = left, temp2 = mid + 1;

        while (temp1 <= mid && temp2 <= right) {

            //如果前面小于等于后面就是正序
            if (nums[temp1] <= nums[temp2]) {
                temparr[index++] = nums[temp1++];
            }
            //反过来就是逆序
            else {
                //用来统计逆序对的个数
                count += (mid - temp1 + 1);
                temparr[index++] = nums[temp2++];
            }
        }
        //把左边剩余的数移入数组
        while (temp1 <= mid) {
            temparr[index++] = nums[temp1++];
        }
        //把右边剩余的数移入数组
        while (temp2 <= right) {
            temparr[index++] = nums[temp2++];
        }
        //把新数组中的数覆盖nums数组
        for (int k = 0; k < temparr.length; k++) {
            nums[k + left] = temparr[k];
        }
    }
}
