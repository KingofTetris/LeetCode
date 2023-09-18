package 十大排序.十大排序Java手写版;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/15
 */
public class QuickSort {
    @Test
    public void test(){
        int[] nums = {3,3,2,89,12,33,-123,64,8,92};
        quickSort(nums,0,nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
    public void quickSort(int[] nums,int low,int high){
        if (low >= high) return;
        int position = partition(nums,low,high);
        quickSort(nums,low, position - 1);
        quickSort(nums,position + 1,high);
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high){
            //找到第一个比pivot小的
            while (nums[high] >= pivot && high > low){
                high--;
            }
            //然后交换位置
            swap(nums,low,high);
            //现在high保持不动，low移动
            //找到第一个比pivot大的
            while (nums[low] <= pivot && low < high){
                low++;
            }
            swap(nums,low,high);
        }
        //最后返回pivot的最终地址，low high都一样。
        nums[low] = pivot;
        return low;
    }

    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
