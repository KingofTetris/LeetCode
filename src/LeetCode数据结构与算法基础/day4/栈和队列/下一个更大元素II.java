package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2024/8/18
 */
public class 下一个更大元素II {

    //从定长数组 变成了 循环数组，让你去找下一个更大元素
    //找更大那么还是维护一个从栈顶到栈底单增的序列
    //找更小，那么就维护从栈顶到栈底单减
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res,-1);
        stack.push(0);
        //循环遍历？实际上用 i % size 就可以实现循环了。
        for (int i = 1; i < 2 * n; i++) {
            if (nums[i % n] <= nums[stack.peek()]) {
                stack.push(i % n);
            } else {
                while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                    //弹出栈顶，更新记录
                     int index = stack.pop();
                     res[index] = nums[i % n];
                }
                stack.push(i % n);
            }
        }
        return res;
    }
}
