package 剑指offer第二版.数组_矩阵;

import java.util.HashSet;

/**
 * @Author KingofTetris
 * @Date 2022/9/13 14:12
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer57_和为s的两个数字 {


    /**
     * 暴力超时
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length ; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[]{nums[i],nums[j]};
            }
        }
        return new int[]{};
    }


    /**
     * 双指针
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right){

            /**
             * 严格来讲不应该用a + b作为判断条件，可能溢出
             * 应该用 target - nums[left] < nums[rigth]
             * target - nums[left] > nums[rigth]
             * 作为判断条件
             */
            if  ( (nums[left] + nums[right]) > target){
               right--;
            }
            else if ((nums[left] + nums[right]) < target){
                left++;
            }
            else { //如果等于
                return new int[]{nums[left],nums[right]};
            }
        }
        return new int[]{}; //如果left >= rigth 则没有。
    }

    /**
     * HashSet的查询，理论是极大概率是O(1)时间内。
     * 因为链表长度最长也就是8，是常数时间
     * 哈希桶超过64个，链表长度超过8个才会转化成红黑树存储
     * 这种概率非常的小，所以我们理论上是认为HashMap的查询是O(1)复杂度
     */

    public int[] twoSum3(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (set.contains(target - num)){
                return new int[]{num,target - num};
            }
        }
        return new int[]{};

    }

}
