package LeetCode_HOT100题;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/6/13
 */
public class _41不在数组中的最小正整数 {

    @Test
    public void test(){
        int[] nums = {3,4,-1,1};
        int res = solution2(nums);
        System.out.println(res);
    }

    //需要O(n)的额外空间。
    private static int solution1(int[] nums) {
        int N = nums.length;
        int[] flag = new int[N + 1];//记录从1到N的数是否出现。N+1避免N越界
        //记录1到n是否在标记中出现
        for (int num : nums) {
            if (num >= 1 && num <= N) flag[num] = 1;
        }
        //从左往右遍历这个标记数组 谁没出现 谁就是最小的正整数
        for (int i = 1; i < flag.length; i++) {
            if (flag[i] == 0)
                return i;
        }
        //都出现了就返回N+1
        return N + 1;
    }

    //优化为O(N) 不用额外空间
    //让数组成为自己的Hash表，我们把数字i放在下标为i-1的位置
    //比如 1 2 3 4
    //放在 0 1 2 3
    //当然还有可能存在不合适的情况比如
    //1 -1 4 3
    //我们就需要调整为
    //1 -1 3 4
    //就会发现下标为1的位置上的数字是-1，但我们预期是i+1也就是2，那么他就是第一个不在数组中的最小正整数。
    private static int solution2(int[] nums){
        int N = nums.length;
        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            //交换的条件是
            //nums[i] >= 1,<= N
            //把数字 nums[i] 放到 nums[i] - 1的位置上
           /* if (nums[i] >= 1 && nums[i] <= N){
                swap(nums,i,nums[i] - 1);
            }*/
            //那么会有个问题 如果 nums[ nums[i] - 1] 上本来就是nums[i]
            //那就没必要交换了。比如1 -1 4 3上的1，0的位置上本来就应该是1。
            while (nums[i] >= 1 && nums[i] <= N && nums[ nums[i] -1 ] != nums[i]){
                //把nums[i]上的元素换到nums[i] - 1去。
                swap(nums,i,nums[i] - 1);
            }
        }
        System.out.println(Arrays.toString(nums));
        //交换完毕以后再从左往右遍历
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return N + 1;
    }

    //交换下标为i 和下标为j的两个元素
    private static void swap(int[] nums,int i ,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
