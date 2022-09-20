package 剑指offer第二版.链表_队列_栈;

import org.junit.Test;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2022/8/31 10:31
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 * 限制：
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer41_数据流中的中位数 {
    @Test
    public void test() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        medianFinder.addNum(10);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
    }
}

class MedianFinder {
    /**
     * initialize your data structure here.
     */
    LinkedList<Integer> nums;
    public MedianFinder() {
        nums = new LinkedList<>();
    }

    public void addNum(int num) {
        nums.add(num);
    }

    /**
     * 中位数要先排序 但本题直接对列表排序超时。
     * @return
     */
    public double findMedian() {
        int len = nums.size();
        if (len == 0) return 0;
        Collections.sort(nums);
        int mid = len / 2;
        if ((len & 1) == 1) { //如果是奇数，取中间就行了
            return (double)nums.get(mid);
        } else { //偶数
            return (double) ( nums.get(mid) + nums.get(mid - 1) ) / 2;
        }
    }
}

class MedianFinder2 {
    Queue<Integer> A, B;//小根堆和大根堆 但是A保存的大的一半，B保存的是小的一半。
    // 大小根堆是数据的保存方式，不是数据的大小。
    // 说白了就是用小根堆存大数，大根堆存小数。方便取大数的min,小数的max，从而得到中位数。

    /**
     * 并且规定 A 的大小为 m   B的大小为 n
     * 设序列长度为N，m = N / 2 (N为偶数） m = ( N + 1) / 2 (N为奇数)
     * 设序列长度为N，n = N / 2 (N为偶数） n = ( N - 1) / 2 (N为奇数)
     * 如果N为偶数，则两个堆顶元素相加除2即可，如果为奇数，取小根堆的堆顶即可
     */
    public MedianFinder2() {
        A = new PriorityQueue<>(); // 优先队列默认为小顶堆，保存较大的一半
        B = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1 - o2);
            }
        }); // 大顶堆，保存较小的一半
    }
    public void addNum(int num) {
        int m = A.size();
        int n = B.size();
        /**
         * 调整的目的在于让大的元素正序，小的元素倒叙。从而实现O(1)取出中位数。
         */
        if(m != n) { //序列长度为奇数
            A.offer(num); //先offer到A中，是为了调整小根堆。
            B.offer(A.poll());
        } else { //序列长度为偶数
            B.offer(num); //先offer到B中，是为了调整大根堆。
            A.offer(B.poll());
        }
    }
    public double findMedian() {
        //当 m=n（ N 为 偶数）：则中位数为 (( A 的堆顶元素 + B 的堆顶元素 )/2)。
        //当 m!=n（ N 为 奇数）：则中位数为 A 的堆顶元素。
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }
}