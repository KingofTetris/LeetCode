package LeetCode数据结构与算法基础.排序;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 划分数组使最大差为K {

    public int partitionArray(int[] nums, int k) {
        int count = 1;
        Arrays.sort(nums);
        int start = nums[0];
        for (int num : nums) {
            if (num - start > k) {
                count++;
                start = num;
            }
        }
        return count;
    }

  /*  作者：Storm
    链接：https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k/solutions/2630626/2294-hua-fen-shu-zu-shi-zui-da-chai-wei-okktb/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
