package LeetCode数据结构与算法基础.哈希表;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author KingofTetris
 * @File 三数之和
 * @Time 2021/10/10  23:26
 */
/*给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。
示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：
输入：nums = []
输出：[]
示例 3：
输入：nums = [0]
输出：[]
提示：
0 <= nums.length <= 3000
-105 <= nums[i] <= 105*/
    /* 题目中要求找到所有「不重复」且和为 0 的三元组，
    这个「不重复」的要求使得我们无法简单地使用三重循环枚举所有的三元组。
    这是因为在最坏的情况下，数组中的元素全部为 0，即
    0, 0, 0, 0, 0, ..., 0, 0, 0

     任意一个三元组的和都为 0。如果我们直接使用三重循环枚举三元组，会得到 O(N^3)
  个满足题目要求的三元组（其中 NN 是数组的长度）时间复杂度至少为 O(N^3)
  。在这之后，我们还需要使用哈希表进行去重操作，得到不包含重复三元组的最终答案，
  又消耗了大量的空间。这个做法的时间复杂度和空间复杂度都很高

    */
public class 三数之和 {

    @Test
    public void test(){
//        int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {-2, -1, -1, -1, 3, 3, 3};
        List<List<Integer>> list = threeSum(nums);
        for (List<Integer> temp : list) {
            System.out.println(temp);
        }
    }

    //    正确的暴力法和双指针
//可以先固定一个值，然后寻找后两个值时可采取双指针的方法，将总的时间复杂度优化到 O(n^2)
    //首先我们先对原数组进行排序，这样可以把重复的值集中到一起，便于去重。
    //确定第一个元素时，如果它已经比 0 大了，那么可以直接跳出循环，因为后面的数字都比它大。
    // 如 [1, 2, 3, 4], i = 0, nums[i] > 0, 这样是不可能产生合法的情况的，直接 break。
//    确定第一个元素时，如果发现它与它前面的值一样，那么跳过本轮。如 [-1, -1, 0, 1],
//    在第一轮后，已经选出了 {-1, 0, 1}, 现在 i = 1，nums[i] == nums[i - 1],
//    为了避免重复，直接 continue。
    //接下来利用双指针，left 指向 i + 1, right 指向 nums.length - 1
    // 逐个进行判断，并注意去重。
    public List<List<Integer>> threeSum(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        //小于3个数直接返回列表就行了
        if (nums.length <= 2) return ans;
        Arrays.sort(nums); // O(nlogn)
        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去掉重复情况
            //相加为0 就是找它的相反数
            int target = -nums[i];
            //left为当前固定nums[i]的下一个数的下标，right为末尾下标
            int left = i + 1, right = nums.length - 1;
//            终止条件left >= right
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    //ArrayList<>(List) 构造方法
                    //Arrays.asList(n1,n2,n3....)把这些数直接当成列表
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    //现在是找到了三数之和等于=0的情况
                    //那么必然要增加 left，减小 right，找下一组,但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3],
                    // nums[i] = -2, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++; right--; // 首先无论如何先要进行加减操作
                    //左边相等lef++ 右边相等right-- 去重
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
                //两数相加小于target 就让left++ 找大一点的数和最大数的相加 left++
                else if (nums[left] + nums[right] < target) {
                    left++;
                }
                //反过来大于target 就拿小一点的数和最小数相加 right--
                else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ans;
    }
}
