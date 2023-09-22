package 校招面试真题;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/22
 */
public class MergeSort {


    @Test
    public void test(){
        int[] nums = {5, 2, 9, 1, 3, 6};
        mergeSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        mergeSort(nums, 0, nums.length - 1);
    }

    //mergeSort本质就是递归。
    private static void mergeSort(int[] nums, int left, int right) {
        //递归结束的条件left == right 就不用再排序了
        if (left == right ) return;
        //分两半去合并。
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid); // 左半边归并排序
        mergeSort(nums, mid + 1, right); // 右半边归并排序
        //最后再左右两边合并起来。
        merge(nums, left, mid, right);
    }

    //这个函数就很简单了，就是合并两个数组。
    //不过你得有个mid来区分，哪边是前一个数组，哪边是后一个数组。
    private static void merge(int[] nums, int left, int mid, int right) {
        //right - left + 1 就是合并的长度。
        int[] temp = new int[right - left + 1]; // 创建一个临时数组，长度为要合并的部分的长度
        int i = left; // 左半边数组的起始位置
        int j = mid + 1; // 右半边数组的起始位置
        int k = 0; // 临时数组的起始位置
        // 合并两个有序数组到临时数组中
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        // 将剩余的元素拷贝到临时数组中
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // 别忘了将临时数组的元素拷贝回原数组中
        for (int m = 0; m < temp.length; m++) {
            nums[left + m] = temp[m];
        }
    }
}
