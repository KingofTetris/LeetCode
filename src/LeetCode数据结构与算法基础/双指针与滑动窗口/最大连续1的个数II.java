package LeetCode数据结构与算法基础.双指针与滑动窗口;

/**
 * @author by KingOfTetris
 * @date 2024/11/8
 */

//给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数。
public class 最大连续1的个数II {

    /**
     * 使用滑动窗口的思路
     * 1、设定窗口左右指针l，r 最大长度len
     * 2、设定flag标记0出现的次数
     * 当前数字为0 且flag为true时代表 0第一次出现 将flag置为false
     * 当前数字为0 且flag为false时代表 0出现了2次 即记录该窗口长度len=(r-l)
     * 3、左指针移动至r+1的位置 重复上述步骤
     * 4、找到最大窗口len值
     */

    public int findMaxConsecutiveOnes(int[] nums) {
        int l = 0, r = 0;
        int len = 0;
        //用于标记0出现次数
        boolean flag = true;
        int t = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                if (flag)
                    flag = false;
                else {
                    len = Math.max(len, r - l);
                    l = t + 1;
                }
                t = r;
            }
            r++;
        }
        //兼容数组中0只有一次的情况
        len = Math.max(len, r - l);
        return len;
    }
}
