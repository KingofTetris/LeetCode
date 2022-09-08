package 剑指offer第二版.链表_队列_栈;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/7/12 14:49
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer09_用两个栈实现队列 {
    @Test
    public void test(){
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
    }
}


/**
 * 执行用时：236 ms, 在所有 Java 提交中击败了13.41%的用户
 * 内存消耗：49.4 mb, 在所有 Java 提交中击败了10.39%的用户
 */
class CQueue {
    Deque<Integer> deque1;
    Deque<Integer> deque2;
    public CQueue() {
        deque1 = new LinkedList<>();
        deque2 = new LinkedList<>();
    }

    /**
     * 入队就压入栈1
     * @param value
     */
    public void appendTail(int value) {
        deque1.push(value);
    }

    /**
     * 删除队头元素就需要把栈1的水倒入栈2 删除以后倒回栈1
     * @return
     */
    public int deleteHead() {
        if (deque1.isEmpty()) return -1; //栈1为空，队列中就没有任何元素
        while (!deque1.isEmpty()){
            deque2.push(deque1.pop());
        }
        int temp = deque2.pop(); //取出队头元素
        //重新倒回去给栈1
        while (!deque2.isEmpty()){
            deque1.push(deque2.pop());
        }
        return temp;
    }
}
