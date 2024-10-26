package LeetCode数据结构与算法基础.day1.数组;

import java.util.Arrays;

/**
 * @Author KingofTetris
 * @Date 2022/10/25 14:27
 */
public class 最接近的三数之和 {


    /**
     * 在数组中找到三个数之和最接近target
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {

        //res 默认为前三个数
        int res = nums[0] + nums[1] + nums[2];

        Arrays.sort(nums);//先升序

        for (int i = 0; i < nums.length; i++) {

            //固定第一个数去找后两个
            int left = i + 1,right = nums.length - 1;

            while (left < right){
                int temp = nums[i] + nums[left] + nums[right]; //三数之和
                //如果更接近target
                if (Math.abs(target - temp) < Math.abs(target - res)){
                    res = temp; //更新res
                }

                //如果temp比target大，那就right--
                if (temp > target){
                    right--;
                }
                //如果temp比target小，那就left++
                else if (temp < target){
                    left++;
                }
                else { //如果都完全就是target了，就不用再继续了
                    return res;
                }
            }
        }

        return res;
    }
}
