package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/18
 */
public class 下一个更大元素III {

    /**
     * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，
     * 并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
     * <p>
     * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 12
     * 输出：21
     * 示例 2：
     * <p>
     * 输入：n = 21
     * 输出：-1
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 2^31 - 1
     *
     * @param n
     * @return
     */

    @Test
    public void test() {
        int n = Integer.MAX_VALUE;
        int element = nextGreaterElement(n);
        System.out.println(element);
    }


    /**
     * 其实就是下一个排列，套了个壳子，从直接给你int[]数组，换成给你一个数。
     * 可以先把这个数换成int[]数组，再去求下一个排列。
     */
    int flag = 0;

    public int nextGreaterElement(int n) {
        int[] nums = numToArray(n);
        permute(nums);
        if (flag == 1) return -1;
        long res = 0;
        int base = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res += (long) nums[i] * base;
            //如果下一个排列大于MAX，也返回-1
            if (res > Integer.MAX_VALUE ) return -1;
            base = base * 10;
        }
        return (int) res;
    }

    public void permute(int[] nums) {
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    Arrays.sort(nums, i + 1, n);
                    return;
                }
            }
        }
        flag = 1;
        Arrays.sort(nums);
    }

    private void swap(int[] nums, int i, int j) {
        //交换两个元素
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] numToArray(int n) {
        Deque<Integer> stack = new LinkedList<>();
        while (n != 0) {
            stack.push(n % 10);
            n = n / 10;
        }
        int len = stack.size();
        int[] nums = new int[len];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = stack.pop();
        }
        return nums;
    }
}
