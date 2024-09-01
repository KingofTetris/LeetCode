package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

class 每日温度 {

    /**
     * 暴力法超时 47/48
     *
     * @param nums
     * @return
     */
    public int[] dailyTemperatures(int[] nums) {
        int left = 0;
        int n = nums.length;
        int[] res = new int[n];
        while (left < n) {
            for (int i = left + 1; i <= n - 1; i++) {
                if (nums[i] > nums[left]) {
                    res[left] = i - left;
                    break;
                }
            }
            left++;
        }
        return res;
    }

    /**
     * 什么是单调栈，单调栈其实就是维护一个递增或递减的栈。
     * 之前在求最大滑动窗口的时候我们其实用过单调队列，这个也差不多。
     * 那么什么时候用单调栈？
     * 通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，此时我们就要想到可以用单调栈了。
     */
    public int[] dailyTemperatures2(int[] temperatures) {

        //这个版本稍微啰嗦一点，认真比较了3种情况
        //这个就是单调栈的模板了

        int lens = temperatures.length;
        int[] res = new int[lens];

        /**
         如果当前遍历的元素 大于栈顶元素，表示 栈顶元素的 右边的最大的元素就是 当前遍历的元素，
         所以弹出 栈顶元素，
         如果栈不空的话，还要考虑新的栈顶与当前元素的大小关系
         否则的话，可以直接入栈。
         注意，单调栈里 加入的元素是 下标。
         */
        //Java里面的栈尽量用Deque接口，Linked List来初始化
        //因为原本的Stack类是继承了Vector类，这个类虽然线程安全，但是效率低。
        //Vector分配内存的时候需要连续的存储空间，如果数据太多，容易分配内存失败
        Deque<Integer> stack = new LinkedList<>();

        //先将下标0入栈
        stack.push(0);
        for (int i = 1; i < lens; i++) {
            //从栈顶到栈底维护一个单调递增的栈
            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }

        return res;
    }
}
