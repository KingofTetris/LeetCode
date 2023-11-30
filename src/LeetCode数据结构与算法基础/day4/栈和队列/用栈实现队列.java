package LeetCode数据结构与算法基础.day4.栈和队列;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File 用栈实现队列
 * @Time 2021/10/4  17:40
 */
public class 用栈实现队列{
    @Test
    public void test(){
        MyQueue mq = new MyQueue();
        mq.push(1);
        mq.push(2);
        mq.push(3);
        mq.pop();
        System.out.println(mq.pop());
    }
}
class MyQueue {
    //就像两个水杯互相倒水一样。
    //使用 a 的栈顶模拟队尾，使用 b 的栈顶模拟队头
    LinkedList<Integer> a, b;
//    为了保证结果的正确性，
//    我们需要在每一次操作后都将所有元素保存在其中一个栈内
//    （否则若分散在两个栈内，进行下一步操作后一定会改变元素顺序，
//    就无法模拟队列的 先进先出 特性了）。

//    我们不妨将所有元素都时刻保存在栈 b中，
//    也即任何操作过后，要保证全部元素保存在栈 b中。需要模拟的操作如下：

/*    1.模拟元素“入队”时，要从队尾入队，也即要压入栈 a，
    但在此之前要先把栈 b 中的所有元素放入 a 中，压栈后再将 a 中元素再放回 b 中；

    2.模拟元素“出队”时，要从队头出队，也即从 b 的栈顶弹出元素。

    3.模拟 top/pop 操作时，类似元素“出队”操作，由于全部元素时刻保存在栈 b 中，
    所以只需对栈 b 进行取栈顶操作或弹出栈顶元素操作即可。*/


        public MyQueue() {
            a = new LinkedList<>();
            b = new LinkedList<>();
        }

        public void push(int x) {

            //一开始先把水往a里面倒
            //保证B是队列正序
            //b要是不空就先把水都倒进a里面
            while (!b.isEmpty()) {
                a.push(b.poll());
            }
            //a再加入新的水
            a.push(x);
            //再重新倒回b里面，
            while (!a.isEmpty()) {
                b.push(a.poll());
            }
            //这样下面的代码就好写多了，B就相当于正序队列。正常POP,PEEK,ISEMPTY就行了。
        }

        public int pop() {
            return b.pop();
        }

        public int peek() {
            return b.peek();
        }

        public boolean empty() {
            return b.isEmpty();
        }
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
