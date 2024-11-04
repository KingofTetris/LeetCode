package LeetCode数据结构与算法基础.day7.堆或者叫优先队列;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author by KingOfTetris
 * @date 2024/10/23
 */


//朴素一点的解法 就是每次插入维持一个有序数组，根据奇偶返回中位数
    //那么这样的复杂度是O(N)

/**
 * 下面用堆的方法来做。
 */
public class 数据流的中位数 {

    @Test
    public void test(){
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        mf.addNum(4);
        mf.addNum(5);
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(7);
        System.out.println(mf.findMedian());
        mf.addNum(8);
        System.out.println(mf.findMedian());
    }
}

class MedianFinder {

    //使用大小根堆去实时 维护数组的一半
    //小根堆 根节点小于等于左右节点，大根堆 反过来 根节点大于等于左右节点。
    //那么根据上面性质，显然我们需要让小根堆去保存较大的一半，堆顶才会是靠近中间的数
    //反过来让大根堆去保存较小的一半，堆顶才会是靠近中间的数字

    //再根据大小根堆的长度去取中位数即可。
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>((o1,o2) -> o1 - o2);
        maxHeap = new PriorityQueue<>((o1,o2) -> o2 - o1);
    }

    //插入操作是有讲究的。
    public void addNum(int num) {
        int A = minHeap.size();
        int B = maxHeap.size();

        //如果数组长度是奇数，也就是A != B的情况
        //这个时候插入
        if (A != B){
            //先插入到小根堆，较大的一半，进行调整。
            //再把最小的元素放入到大根堆进行调整。
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
        //如果数组长度是偶数,也就是 A = B的情况
        //这个时候插入
        else {
            //先插入到大根堆，较小的一半，进行调整
            //再把最大的元素放入到小根堆中。
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        //查看两个堆的元素之和长度是多少
        //如果是奇数
        int len = minHeap.size() + maxHeap.size();
        if (len % 2 == 1){
            return (double) minHeap.peek();
        }
        //如果是偶数
        else {
            return (double) (minHeap.peek() + maxHeap.peek())/ 2;
        }
    }
}

