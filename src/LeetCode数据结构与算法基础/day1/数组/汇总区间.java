package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 汇总区间 {
    //意思就是找出nums里面的连续区间。
    //并且是 无重复元素 的 有序 整数数组 nums 。

    @Test
    public void test() {
//        int[] nums = {0, 1, 2, 4, 5, 7};
//        int[] nums = {0, 2, 3, 4, 6, 8, 9};
        int[] nums = {-1};
        List<String> list = summaryRanges(nums);
        System.out.println(list);
    }

    //下面的summaryRanges2 双指针写得太丑陋了。
    //看这个

    /**
     * 方法一：一次遍历
     * 我们从数组的位置 0 出发，向右遍历。每次遇到相邻元素之间的差值大于 1 时，
     * 我们就找到了一个区间。遍历完数组之后，就能得到一系列的区间的列表。
     *
     * 在遍历过程中，维护下标 low 和 high 分别记录区间的起点和终点，对于任何区间都有 low≤high。
     * 当得到一个区间时，根据 low 和 high 的值生成区间的字符串表示。
     *
     * 当 low<high 时，区间的字符串表示为 ‘‘low→high"；
     *
     * 当 low=high 时，区间的字符串表示为 ‘‘low"。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/summary-ranges/solutions/553645/hui-zong-qu-jian-by-leetcode-solution-6zrs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums){
        List<String> ret = new ArrayList<>();
        int i = 0;//当前指针
        int n = nums.length;

        while (i < n) {
            int low = i; //left
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1; //right
            StringBuilder temp = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(nums[high]);
            }
            ret.add(temp.toString());
        }
        return ret;
    }


    //双指针就完了。
    public List<String> summaryRanges2(int[] nums) {
        if (nums == null) return new ArrayList<>();
        if (nums.length == 1){
            List<String> list = new ArrayList<>();
            list.add(nums[0] + "");
            return list;
        }

        int left = 0, right = 1;
        int n = nums.length;
        List<String> res = new ArrayList<>();
        while (right < n) {
            int pre = nums[left];
            int next = nums[right];
            while (next - pre == 1) {
                pre = next;
                right++;
                if (right < n)
                next = nums[right];
            }
            //如果next-pre不等于1了，说明不连续。
            String temp;
            if (right - left == 1) {
                temp = nums[left] + "";
            } else {
                //连续区间是[left,right - 1]
                temp = nums[left] + "->" + nums[right - 1];
            }
            res.add(temp);
            left = right;
            //如果left刚好是最后一个数字的下标。下轮循环就会直接跳出了，别把他漏了。
            if (left == n - 1) {
                res.add(nums[left] + "");
            }
            right++;
        }
        return res;
    }

}
