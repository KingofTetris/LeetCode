package 剑指offer第二版.字符串;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author KingofTetris
 * @Date 2022/8/30 14:51
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *  
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 *
 */
public class 剑指Offer39_数组中出现次数超过一半的数字 {
    /**
     * HashMap。O(n) O(n)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1); //每put一次+1
            //其实还可以在Put后面加一步。因为众数一定大于等于nums.length / 2 + 1  +1是因为取整是向下取。所以+1.比如7/2=3。实际应该取4
            //另外在put后面判断是因为 没put前，可能get到空值，导致空指针异常
            //所以判断一下如果map.get大于nums.length / 2 + 1 。那直接返回key就行了。
            if (map.get(nums[i]) >= nums.length / 2 + 1) return nums[i];

            /**
             * 但整体看下来这个判断最好也就是众数全在前面一半的情况，那也就从O(n) 到 O(1/2n)。实际上影响不大。
             */
        }

        /*加了判断就不用再遍历哈希表了。
        int max = Integer.MIN_VALUE;
        int key = Integer.MIN_VALUE;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (entry.getValue() > max){
                max = entry.getValue();
                key = entry.getKey();
            }
        }
        return key;*/
        return Integer.MIN_VALUE;//这一句实际上是不可达的，为了编译还是写上return.
    }

    /**
     * 排序
     * 时间复杂度：O(nlogn)。将数组排序的时间复杂度为 O(nlogn)。
     * 空间复杂度：O(logn)。如果使用语言自带的排序算法，需要使用 O(logn) 的栈空间。
     * 如果自己编写堆排序，则只需要使用 O(1) 的额外空间。
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法，众数记为 1，其他数记为0。然后打擂台，活下来的就是众数。
     * 所以也叫打擂台法，一次遍历，常数空间消耗。
     */
    public int majorityElement3(int[] nums) {
        int candidate = -1;//初始值无所谓，反正看到会被nums[0]取代。
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0){ //每次count清零才更换擂主
                candidate = nums[i];
            }
            count = count + ((nums[i] == candidate) ? 1 : -1);//如果重复就+1，不重复就-1;
        }
        return candidate;
    }

    /**
     * 这题的变种，不使用额外空间，统计众数出现的次数。
     * 那么就需要先用摩尔投票法找出众数，
     * 然后遍历数组统计次数。
     *
     * @param nums
     * @return
     */
    public static int findMostRepeatedNumberCount(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        // 找到可能的重复次数最多的数字
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // 统计候选数字出现的次数
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return count;
    }
}
