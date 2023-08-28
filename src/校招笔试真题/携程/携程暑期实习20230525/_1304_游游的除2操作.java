package 校招笔试真题.携程.携程暑期实习20230525;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/6/26
 */

//可以使用大根堆去维护所有的竹子高度 如果最高的竹子高度与最低的竹子高度相等 则说明已经达到了题目所要求的条件
    //最后4个过不去是因为建堆很耗时。
public class _1304_游游的除2操作 {
    public static void main(String[] args) {
//        LinkedList s = new LinkedList();
//        s.addLast();
        int minX = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 堆在java里面有现成的数据结构,就是优先队列,但是默认为小根堆
        //直接使用lambda表达式建造大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1,o2) -> (o2 - o1));//大根堆
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            heap.offer(x);//添加到大根堆中
            minX = Math.min(minX,x); //取最小的数字
        }
        long res = 0;
        //如果最大元素和最小元素不一致就要继续/2
        while (heap.peek() != minX){
            int t = heap.poll();//堆顶元素/2
            t = t / 2;
            minX = Math.min(minX,t);//比较谁更小
            heap.offer(t);//重新建堆
            res++;
        }
        System.out.println(res);
    }
}
