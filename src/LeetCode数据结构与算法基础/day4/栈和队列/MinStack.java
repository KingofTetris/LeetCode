package LeetCode数据结构与算法基础.day4.栈和队列;

/**
 * @author KingofTetris
 * @File MinStack
 * @Time 2021/10/24  14:05
 */


import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {


    //整体思路就是一个普通stack正常出入栈。然后min_stack只保存最小值。
    Deque<Integer> stack;
    Deque<Integer> min_stack;

    @Test
    public void test(){
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        System.out.println(ms.getMin());
//        System.out.println(ms.getMin());
    }
    public MinStack() {
        stack = new LinkedList<>();
        min_stack = new LinkedList<>();
        //初值值设为最大值，保证所有val都比它小。
        min_stack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        //为什么offer就是MAX_VALUE push才是最小值
        stack.push(val);

        /**
         * 关键就在这里 min_stack 无论怎么样都会保持和stack一样的大小
         * 只是peek只维护最小的值。
         * 这样stack pop的时候，大的出去了，小的还是留在min_stack里面
         * 才能保证min_stack.peek始终最小。
         */
        //这里push 不管哪个小都会pop进去一个数，保持和stack一样的个数
        //这样下面才敢一起pop
        min_stack.push(Math.min(min_stack.peek(),val));
    }

    public void pop() {
        stack.pop();
        min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}
