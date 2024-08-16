package 校招笔试真题.用友.用友普通批;

/**
 * @author by KingOfTetris
 * @date 2023/9/4
 */

import java.util.*;

//// 说明：这里使用两个堆来维护最大值和最小值
// 代码简单易懂，笔试时是一个速AC的好方法， 时间复杂度O(N*logK)，
// 比起单调队列的O(N)的复杂度，是差了一些, 另外这道题是力扣HOT100 原题
public class 滑动窗口的最大值 {
    public static void main(String[] args) {

        //1 3 -1 -3 5 3 6 7
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        //最大堆
//        PriorityQueue<Integer> 优先队列，其实就是堆，默认是最小堆，
        //((o1,o2)->(o2-o1))就是最大堆
        PriorityQueue<Integer> maxH = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        //最小堆
        PriorityQueue<Integer> minH = new PriorityQueue<>((o1, o2) -> (o1 - o2));
        for (int i = 0; i < k; i++) {
            maxH.add(arr[i]);
            minH.add(arr[i]);
        }
        int res = maxH.peek() - minH.peek();
        for (int i = k; i < n; i++) {
            int pollE = arr[i - k];
            maxH.remove(pollE);
            minH.remove(pollE);
            maxH.add(arr[i]);
            minH.add(arr[i]);
            res = Math.max(res, maxH.peek() - minH.peek());
        }
        System.out.println(res);
        sc.close();
    }
}

