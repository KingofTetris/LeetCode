package 剑指offer第二版.排序;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author KingofTetris
 * @Date 2022/9/8 17:02
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
 * 并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *  
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer53_II_0到n减1中缺失的数字 {

    /**
     * 利用辅助的完整数组来进行对比。
     * O(n) O(n)
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        //求出数组的长度n
        int n = nums.length;
        //那么整个升序数组应该为0 - n这个区间里删掉一个数字。
        //现在要找出这个缺少的数字
        int[]  complete = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            complete[i] = i;
        }

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] != complete[i]) {
                return complete[i];//如果不相同 就缺少这个
            }
            if (i == n - 1) flag = true;//如果到第n位都相同 把flag标为true
        }

        if (flag == true)  return n;//就少了n

        return 0;//这一句是执行不到的。只是为了编译不出错
    }

    /**
     * 辅助集合和上面的辅助数组是一个思路，差别只有代码简单一点。
     * @param nums
     * @return
     */
    public  int missingNumber3(int[] nums){
        Set<Integer> set = new HashSet<Integer>();
        int n = nums.length + 1;
        for (int i = 0; i < n - 1; i++) {
            set.add(nums[i]);
        }
        int missing = -1;
        for (int i = 0; i <= n - 1; i++) {
            if (!set.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;

    }


    /**
     * 当然既然是排序数组，那么自然离不开二分查找。
     * 可以把时间复杂度降到 O(logN)
     */

    public int missingNumber2(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
/*
        作者：jyd
        链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/solution/mian-shi-ti-53-ii-0n-1zhong-que-shi-de-shu-zi-er-f/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    /**
     * 还有就是简单的数学
     * 用总和减去数组之和就是缺少的数字
     * @param nums
     * @return
     */
    public int missingNumber4(int[] nums) {
        int n = nums.length + 1;
        int total = n * (n - 1) / 2;
        int arrSum = 0;
        for (int i = 0; i < n - 1; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }

}
