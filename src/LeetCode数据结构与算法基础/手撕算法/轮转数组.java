package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 轮转数组 {

    //给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
    //这是右转k位，你要左转k位，实际上就相当于右转(n-k)位，效果是一样的。
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; //先对k取余数。
        //翻转0,k-1
        reverse(nums, 0, n - k - 1);
        //翻转k,n-1
        reverse(nums, n - k, n - 1);
        //翻转0,n-1
        reverse(nums, 0, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
