package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author KingofTetris
 * @Date 2022/9/9 15:39
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer56_II数组中数字出现的次数 {

    @Test
    public void test(){
        int[] nums = {5,2,2,2,5,5,4};
        int i = singleNumber3(nums);
        System.out.println(i);
    }

    /**
     * 通用方法。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res = entry.getKey();
            }
        }
        return res;
    }

    /**
     * 当然还有简单的排序 只不过排序就已经是 O(nlogn)了
     * 当nums[n]!=nums[n+1]时一定是那个唯一的值，此时输出nums[n]即可
     */
    public int singleNumber3(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length;) {//记得把后面的i++给去掉
            if (i < nums.length - 1 && nums[i] == nums[i + 1])
                i = i + 3;//如果相同就别遍历了，下一个数吧
            else {
                return nums[i];
            }
        }
        return 0;//这句不会执行到，只是为了编译通过。
    }
}
