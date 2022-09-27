package 剑指offer第二版.面试题;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2022/9/20
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class 面试题59_II_队列的最大值 {
}

class MaxQueue {

    //因为要保证出对入队的同时能取到最大值
    //如果我们用一个max来存储，那你出队入队后需要遍历整个队列才能确定max 也就是O(n)的复杂度 不符合O(1)
    //所以我们采取空间换时间的做法，再来个双端队列同步在队头存储最大值
    //每次取得双端队列的最大值即可
    Queue<Integer> queue;
    Deque<Integer> deque;//一般用Deque代表双端队列
    public MaxQueue() {

        //LinkedList可以当链表，队列，双端队列来用
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }
    public int max_value() {
        //max 每次返回双端队列的头部
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }
    public void push_back(int value) {
        queue.offer(value);//入普通队列
        //如果双端队列不为空，且尾部小于value 那么就把全部尾部出队
        while(!deque.isEmpty() && deque.peekLast() < value)
            deque.pollLast();
        //value入队，才能保持队头是max
        deque.offerLast(value);
    }
    public int pop_front() {
        if(queue.isEmpty()) return -1;

        //如果队头和 max 相同，那么max就要出双端队列
        if(queue.peek().equals(deque.peekFirst()))
            deque.pollFirst();
        return queue.poll();
    }

 /*   作者：jyd
    链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/solution/jian-zhi-offer-59-ii-dui-lie-de-zui-da-z-0pap/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */