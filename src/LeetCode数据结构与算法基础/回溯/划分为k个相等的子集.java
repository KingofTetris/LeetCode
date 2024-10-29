package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/8/20
 */

//TODO 没用回溯写出来，很难顶
public class 划分为k个相等的子集 {

    @Test
    public void test(){
        int[] nums = {4,3,2,3,5,2,1};
        int k = 4;
//        int[] nums = {1,1,1,1,2,2,2,2};
//        int k = 2;
        boolean b = canPartitionKSubsets(nums, k);
        System.out.println(b);
    }


    /**
     * 按照代码随想录的回溯的模板这题没法做
     * 正确的回溯看下面。
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % k != 0) return false;
        int target = sum / k;
        //k个桶 每个桶的容量是target
        int arr[] = new int[k];
        Arrays.fill(arr, target);
        Arrays.sort(nums);
        //cur是尝试把当前数字放到桶里面
        //从大到小 放入
        return backtracking(nums, nums.length - 1, arr, k);
    }

    public boolean backtracking(int[] nums, int cur, int[] arr, int k){
        //已经遍历到了-1说明前面的所有数都正好可以放入桶里，
        // 那所有桶的值此时都为0，说明找到了结果，返回true
        if(cur < 0) return true;

        //遍历K个桶
        //从大到小放入桶中
        //如果当前数字能放入某个桶中,就先放进去
        for(int i = 0; i < k; i++){
            //两种可能，这个数正好是桶当前的容量，或者将这个数放进桶后这个桶还能再放别的数。
            //>=nums[0] 说明还可用往前面找。
            if(nums[cur] == arr[i] || arr[i] - nums[cur] >= nums[0]){
                arr[i] -= nums[cur];
                if(backtracking(nums, cur - 1, arr, k)) return true;
                arr[i] += nums[cur];
            }
        }
        return false;
    }
/*————————————————

    版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

    原文链接：https://blog.csdn.net/weixin_44806531/article/details/131947315*/


}
