package LeetCode数据结构与算法基础.哈希表;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author KingofTetris
 * @File 两数之和
 * @Time 2021/9/28  9:22
 */
/*1. 两数之和
        给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
        你可以按任意顺序返回答案。
        示例 1：
        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
        示例 2：

        输入：nums = [3,2,4], target = 6
        输出：[1,2]
        示例 3：

        输入：nums = [3,3], target = 6
        输出：[0,1]
        提示：
        2 <= nums.length <= 104
        -109 <= nums[i] <= 109
        -109 <= target <= 109
        只会存在一个有效答案
        进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？*/
public class 两数之和 {
    @Test
    public void test() {
        int[] numbers = {2, 7, 11, 15, 22, 311};
        int target = 13;
        int[] result = twoSum(numbers, target);
        for (int i : result) {
            System.out.println(i);
        }
    }

    //没有排序的数组 暴力O(n2)或者哈希表法O(n)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //注意不能先把数组元素和下标放进map里面，否则如果target刚好是n[0]的两倍
            //那下面的containsKey会直接返回1 1
//            map.put(nums[i],i + 1);
            //map.containsKey可不是去遍历，他是拿着key去hash，复杂度是O(1)。
            //containsValue才需要遍历每个Node的每条链表，这个可就麻烦了
            if (map.containsKey(target - nums[i])) {
                //注意返回的是get(key)就是对应的另一个加数的下标 和当前元素下标，两个下标
                return new int[]{map.get(target - nums[i]), i};
            }
            //处理这个bug的方法就是先判断,再Put 可以避开上面的 1 1bug
            map.put(nums[i], i);
        }
        return new int[0]; //没有就返回空数组
    }
}
