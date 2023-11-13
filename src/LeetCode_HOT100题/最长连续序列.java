package LeetCode_HOT100题;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2023/10/18
 */
public class 最长连续序列 {

    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     *示例 1：
     *
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     *
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     * @param nums
     * @return
     */
    @Test
    public void test(){
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        int i = longestConsecutive(nums);
        System.out.println(i);
    }
    //可以直接排序，然后遍历nums[i]
    //查看nums[i]+1,...,nums[i]+n是否存在于nums中，如果存在长度就是n+1
    //如果不存在，当前长度为1.每次更新最大长度即可
    //但是题目要求O(n) 用上面的朴素思路，是O(nlogn)


    //这就需要动下脑经，才能达到O(n)了
    public int longestConsecutive(int[] nums) {
        // 建立一个存储所有数的哈希表，同时起到去重功能
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        // 遍历去重后的所有数字
        for (int num : set) {
            int cur = num;
            // 只有当num-1不存在时，才开始向后遍历num+1，num+2，num+3......
            //因为这个if条件，虽然看起来是for+while
            //但是由于if (!set.contains(cur - 1))判断的存在，每个元素只会被遍历一次，因此时间复杂度也为O(n)。
            if (!set.contains(cur - 1)) {
                while (set.contains(cur + 1)) {
                    cur++;//当前数字自增。
                }
            }
            // [num, cur]之间是连续的，数字有cur - num + 1个
            ans = Math.max(ans, cur - num + 1);
        }
        return ans;

    /*    作者：超小白
        链接：https://leetcode.cn/problems/longest-consecutive-sequence/solutions/1176496/xiao-bai-lang-ha-xi-ji-he-ha-xi-biao-don-j5a2/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
