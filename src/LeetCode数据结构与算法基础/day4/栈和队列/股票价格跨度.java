package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.ArrayDeque;
import java.util.Deque;

class 股票价格跨度 {
    Deque<int[]> stack;
    int idx;

    public 股票价格跨度() {
        stack = new ArrayDeque<>();
        stack.push(new int[]{-1, Integer.MAX_VALUE});
        idx = -1;
    }

    public int next(int price) {
        idx++;
        while (price >= stack.peek()[1]) {
            stack.pop();
        }
        int ret = idx - stack.peek()[0];
        stack.push(new int[]{idx, price});
        return ret;
    }
}

