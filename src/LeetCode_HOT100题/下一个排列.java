package LeetCode_HOT100题;

import java.util.Arrays;

/**
 * @Author KingofTetris
 * @Date 2022/10/12 18:58
 */
//我TM淦啊 我燥！这个就是c++的std:next_permutation
public class 下一个排列 {
    /**
     * 比如 原始数组是 123
     * 那么下一个排列最小的就是 132
     * 全排列有
     * 123 132 213 231 312 321
     * 上面6种情况，形成一个闭环。
     * 找到下一个最小的排列输出
     * @param nums
     */
    /**
     * 要比nums大，而且要大得最少，增幅最小
     * 那么需要尽可能靠右的低位换到前面，从后向前找
     * eg:123465 把5和4交换，不是4和6交换，越前面的数字尽可能越小
     * 交换后形成123564
     * 我们还需要把后半部份改成升序也就是64换成46
     * 变成123546
     * 123546也就是123465的下一个排列
     * @param nums
     */


    /**
     * 下面的复杂度O(mn) 是不符合O(n) 的，像看O(n)的看更下面的官解
     * @param nums
     */
    // 输入： 1 2 3 8 5 7 6 4
    // 结果： 1 2 3 8 6 4 5 7
    public void nextPermutation(int[] nums) {
        // 从后往前，找到前一位比当前位置小的(i-1 < i)
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            // 人话： (nums[4] = 5) < (nums[5] = 7)
            if (nums[i] > nums[i - 1]) {
                // 将 [i] -> [length] 排序,这里是 7 6 4
                // 这个排序的意义就是为了让后半部分保持升序。这样下面交互一次就行了。
                Arrays.sort(nums, i, len);
                // 排序完成之后的结果
                // 1 2 3 8 5     4 6 7
                //只遍历 4 6 7
                for (int j = i; j < len; j++) {
                    // 从左往右，在 4 6 7 中找到第一个 > nums[4] = 5
                    // 注意是 nums[i - 1] 是 i-1 ，不是 j !!!
                    if (nums[j] > nums[i - 1]) {
                        //也就是把5和6交换 于是就变成了 1 2 3 8 6 4 5 7
                        //交换位置，然后return
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
            //如果走一遍空循环，看下一行注释
        }
        // 上面循环完之后，程序还在运行说明 此排列已经最大值了
        // 返回最小排列，也就是做一个升序排列就完事
        Arrays.sort(nums);
    }


    //原地反转找到下一个更大元素，也就是下一个排列。

    /**
     * 标准的 “下一个排列” 算法可以描述为：
     *
     * 从后向前 查找第一个 相邻升序 的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
     * 在 [j,end) 从后向前 查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
     * 将 A[i] 与 A[k] 交换
     * 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
     * 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
     *
     * 作者：Imageslr
     * 链接：https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

   /* 作者：LeetCode-Solution
    链接：https://leetcode.cn/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
