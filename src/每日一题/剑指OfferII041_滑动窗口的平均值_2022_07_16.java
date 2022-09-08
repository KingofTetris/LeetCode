package 每日一题;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2022/7/16
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 *
 * 实现 MovingAverage 类：
 *
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，
 * 请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
 *  
 *
 * 示例：
 *
 * 输入：
 * inputs = ["MovingAverage", "next", "next", "next", "next"]
 * inputs = [[3], [1], [10], [3], [5]]
 * 输出：
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * 解释：
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // 返回 1.0 = 1 / 1
 * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
 *  
 *
 * 提示：
 *
 * 1 <= size <= 1000
 * -10^5 <= val <= 10^5
 * 最多调用 next 方法 10^4 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/qIsx9U
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指OfferII041_滑动窗口的平均值_2022_07_16 {
    @Test
    public void test(){
        MovingAverage movingAverage = new MovingAverage(3);
        movingAverage.next(1); // 返回 1.0 = 1 / 1
        movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
        movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
        movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
    }
}
class MovingAverage {
    Queue<Integer> queue;
    int size;
    double sum;

    public MovingAverage(int size) {
        queue = new ArrayDeque<Integer>();
        this.size = size;
        sum = 0;
    }

    public double next(int val) {
        if (queue.size() == size) { //当滑动窗口达到Size时，就移动向后移动一位。也就是减去队头元素。
            sum -= queue.poll();
        }
        queue.offer(val);//入队
        sum += val;//计算总和
        return sum / queue.size();//总和除以当前数量
    }
}
