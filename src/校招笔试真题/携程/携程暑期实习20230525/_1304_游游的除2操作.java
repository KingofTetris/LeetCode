package 校招笔试真题.携程.携程暑期实习20230525;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/6/26
 */

/**
 * 塔子哥和朋友到郊外玩耍，偶然间，他获得
 * n棵长度随机的竹子，他每次都会进行如下操作:·选择一棵正数高度的竹子，
 * 砍去二分之一的长度（结果向下取整）。请你帮塔子哥计算一下，他最少多少次操作可以使得所有竹子长度相等?
 */



public class _1304_游游的除2操作 {
    public static void main(String[] args) {

        /**
         * 要让所有数字相等，而且只有/2这个操作，那么当然只能让所有数字都变成数组中最小的那个
         *
         * 但是也有个问题，比如 2，3
         * 2确实是当前最小的，但是 3/2 == 1 导致最小变成了1
         * 那么2 就要继续 2 / 2 == 1
         *
         * 所以在操作过程中要及时更新最小值
         */
        int minX = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 堆在java里面有现成的数据结构,就是优先队列,但是默认为小根堆
        //直接使用lambda表达式建造大根堆
        //大根堆是指 根 >= 左右孩子
        //小根堆指的是 根 <= 左右孩子
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1,o2) -> (o2 - o1));
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            heap.offer(x);//添加到大根堆中
            minX = Math.min(minX,x); //取最小的数字
        }
//        System.out.println(heap);
        long res = 0;
        //如果heap.peek()和记录的最小元素不一致就要继续/2
        //一致操作到最大值等于最小值那就全部一样了。
        while (heap.peek() != minX){
            int t = heap.poll();//堆顶元素/2
            t = t / 2;
            minX = Math.min(minX,t);//比较谁更小
            heap.offer(t);//重新建堆
            //除一次 就操作一次
//            System.out.println(heap);
            res++;
        }
        System.out.println(res);
    }
}
